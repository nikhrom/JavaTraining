package github.nikhrom.javatraining.advanced_hibernate.dao;


import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.PredicateOperation;
import com.querydsl.jpa.impl.JPAQuery;
import github.nikhrom.javatraining.advanced_hibernate.dto.CompanyDto;
import github.nikhrom.javatraining.advanced_hibernate.entity.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static github.nikhrom.javatraining.advanced_hibernate.entity.QCompany.*;
import static github.nikhrom.javatraining.advanced_hibernate.entity.QUser.user;
import static github.nikhrom.javatraining.advanced_hibernate.entity.QPayment.payment;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {

    private static final UserDao INSTANCE = new UserDao();


    /**
     * Возвращает всех сотрудников
     */
    public List<User> findAll(Session session){
//        return session.createQuery("SELECT u FROM User u", User.class)
//                .list();
        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .fetch();
    }

    /**
     * Возвращает всех сотрудников с указанным firstName
     */
    public List<User> findAllByFirstName(Session session, String firstName){
//        return session.createQuery("""
//                SELECT u
//                FROM User u
//                WHERE u.personalData.firstname = :firstname
//                """, User.class)
//                .setParameter("firstname", firstName)
//                .list();
        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .where(user.personalData.firstname.eq(firstName))
                .fetch();
    }

    /**
     * Возвращает первые {limit} сотрудников, упорядоченных по дате рождения (в порядке возрастания)
     */
    public List<User> findLimitedUsersOrderedByBirthday(Session session, int limit){
//        return session.createQuery("""
//                SELECT u
//                FROM User u
//                ORDER BY u.personalData.birthday ASC
//                """, User.class)
//                .setMaxResults(limit)
//                .list();
        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .orderBy(user.personalData.birthday.date.asc())
                .limit(limit)
                .fetch();
    }

    /**
     * Возвращает всех сотрудников компании с указанным названием
     */
    public List<User> findByCompanyName(Session session, String companyName){
//        return session.createQuery("""
//                    select u
//                    from Company c
//                    join c.users u
//                    where c.name = :companyName
//                    """, User.class)
//                .setParameter("companyName", companyName)
//                .list();

        return new JPAQuery<User>(session)
                .select(user)
                .from(company)
                .join(company.users)
                .where(company.name.eq(companyName))
                .fetch();
    }

    /**
     * Возвращает все выплаты, полученные сотрудниками компании с указанными именем,
     * упорядоченные по имени сотрудника, а затем по размеру выплаты
     */
    public List<Payment> findAllPaymentsByCompanyName(Session session, String companyName){
//        return session.createQuery("""
//                    select p
//                    from Company c
//                    join c.users u
//                    join u.payments p
//                    where c.name = :companyName
//                    order by u.personalData.firstname, p.amount
//                    """, Payment.class)
//                .setParameter("companyName", companyName)
//                .list();
        return new JPAQuery<User>(session)
                .select(payment)
                .from(company)
                .join(company.users, user)
                .join(user.payments, payment)
                .where(company.name.eq(companyName))
                .orderBy(user.personalData.firstname.asc(), payment.amount.asc())
                .fetch();
    }


    /**
     * Возвращает среднюю зарплату сотрудника с указанными именем и фамилией
     */
    public Double findAveragePaymentAmountByFirstAndLastName(Session session,
                                                             String firstname,
                                                             String lastname){
//        return session.createQuery("""
//                    select avg (p.amount)
//                    from Payment p
//                    join p.receiver u
//                    where u.personalData.firstname = :firstname and
//                          u.personalData.lastname = :lastname
//                    """, Double.class)
//                .setParameter("firstname", firstname)
//                .setParameter("lastname", lastname)
//                .getSingleResult();

        return new JPAQuery<Double>(session)
                .select(payment.amount.avg())
                .from(payment)
                .join(payment.receiver, user)
                .where(user.personalData.firstname.eq(firstname),
                        user.personalData.lastname.eq(lastname))
                .fetchOne();
    }

    /**
     * Возвращает для каждой компании: название, среднюю зарплату всех её сотрудников.
     * Компании упорядочены по названию.
     */
    public List<Tuple> findCompanyNamesWithAvgUserPaymentsOrderedByCompanyName(
            Session session
    ){
//        return session.createQuery("""
//                        SELECT c.name, avg(p.amount)
//                        FROM Payment p
//                        JOIN p.receiver u
//                        JOIN u.company c
//                        GROUP BY c.name
//                        ORDER BY c.name
//                        """, Object[].class)
//                .list();

        return new JPAQuery<>(session)
                .select(company.name, payment.amount.avg())
                .from(payment)
                .join(payment.receiver, user)
                .join(user.company, company)
                .groupBy(company.name)
                .orderBy(company.name.asc())
                .fetch();
    }


    /**
     * Возвращает список: сотрудник (объект User), средний размер выплат, но только для тех сотрудников, чей средний размер выплат
     * больше среднего размера выплат всех сотрудников
     * Упорядочить по имени сотрудника
     */
    public List<Tuple> isItPossible(Session session) {
//        var query = session.createQuery("""
//                SELECT u, avg(p.amount)
//                FROM User u
//                JOIN u.payments p
//                GROUP BY u.username
//                HAVING avg(p.amount) > (SELECT avg(p.amount)
//                                       FROM Payment p)
//                ORDER BY u.username
//                """, Object[].class);
//
//        return query.list();


        return new JPAQuery<>(session)
                .select(user, payment.amount.avg())
                .from(user)
                .join(user.payments, payment)
                .groupBy(user.username)
                .having(payment.amount.avg().gt(
                    new JPAQuery<Double>()
                            .select(payment.amount.avg())
                            .from(payment)
                ))
                .orderBy(user.username.asc())
                .fetch();
    }
//    ORDER BY u.username
    public static UserDao getInstance(){
        return INSTANCE;
    }
}
