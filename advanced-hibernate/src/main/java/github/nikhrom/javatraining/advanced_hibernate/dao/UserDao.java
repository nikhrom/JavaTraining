package github.nikhrom.javatraining.advanced_hibernate.dao;


import github.nikhrom.javatraining.advanced_hibernate.dto.CompanyDto;
import github.nikhrom.javatraining.advanced_hibernate.entity.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {

    private static final UserDao INSTANCE = new UserDao();


    /**
     * Возвращает всех сотрудников
     */
    public List<User> findAll(Session session){
//        return session.createQuery("SELECT u FROM User u", User.class)
//                .list();
        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(User.class);
        var user = criteria.from(User.class);
        criteria.select(user);
        return session.createQuery(criteria)
                .list();
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

        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(User.class);
        var user = criteria.from(User.class);
        criteria.select(user).where(
                cb.equal(user.get(User_.personalData).get(PersonalData_.firstname), firstName)
        );

        return session.createQuery(criteria)
                .list();
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

        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(User.class);
        var user = criteria.from(User.class);

        criteria.select(user).orderBy(
                cb.asc(user.get(User_.personalData).get(PersonalData_.birthday))
        );

        return session.createQuery(criteria)
                .setMaxResults(limit)
                .list();
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

        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(User.class);
        var company = criteria.from(Company.class);
        var users = company.join(Company_.users);

        criteria.select(users).where(
                cb.equal(company.get(Company_.NAME), companyName)
        );

        return session.createQuery(criteria)
                .list();
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
        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(Payment.class);
        var company = criteria.from(Company.class);
        var users = company.join(Company_.users);
        var payments = users.join(User_.payments);

        criteria.select(payments).where(cb.equal(company.get(Company_.name), companyName))
            .orderBy(
                    cb.asc(users.get(User_.personalData).get(PersonalData_.firstname)),
                    cb.asc(payments.get(Payment_.amount))
            );

        return session.createQuery(criteria)
                .list();
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

        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(Double.class);
        var payment = criteria.from(Payment.class);
        var user = payment.join(Payment_.receiver);

        criteria.select(cb.avg(payment.get(Payment_.amount)))
                .where(
                        cb.equal(user.get(User_.personalData).get(PersonalData_.firstname), firstname),
                        cb.equal(user.get(User_.personalData).get(PersonalData_.lastname), lastname)
                );

        return session.createQuery(criteria)
                .getSingleResult();
    }

    /**
     * Возвращает для каждой компании: название, среднюю зарплату всех её сотрудников.
     * Компании упорядочены по названию.
     */
    public List<CompanyDto> findCompanyNamesWithAvgUserPaymentsOrderedByCompanyName(
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

        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(CompanyDto.class);
        var payment = criteria.from(Payment.class);
        var user = payment.join(Payment_.receiver);
        var company = user.join(User_.company);

        criteria.select(
            cb.construct(
                CompanyDto.class,
                company.get(Company_.name),
                cb.avg(payment.get(Payment_.amount))
            )
        )
            .groupBy(company.get(Company_.name))
            .orderBy(cb.asc(company.get(Company_.name)));

        return session.createQuery(criteria)
                .list();
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

        var cb = session.getCriteriaBuilder();
        var criteria = cb.createTupleQuery();
        var user = criteria.from(User.class);
        var payment = user.join(User_.payments);

        var criteriaForAvgPayment = criteria.subquery(Double.class);
        var avgPayment = criteriaForAvgPayment.from(Payment.class);
        criteriaForAvgPayment.select(cb.avg(avgPayment.get(Payment_.amount)));

        criteria.select(
                cb.tuple(user, cb.avg(payment.get(Payment_.amount)))
        )
                .groupBy(user.get(User_.username))
                .having(cb.greaterThan(
                        cb.avg(payment.get(Payment_.amount)),
                        criteriaForAvgPayment
                ))
                .orderBy(cb.asc(user.get(User_.username)));

        return session.createQuery(criteria)
                .list();

    }
//    ORDER BY u.username
    public static UserDao getInstance(){
        return INSTANCE;
    }
}
