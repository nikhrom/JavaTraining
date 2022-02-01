package github.nikhrom.javatraining.advanced_hibernate.dao;


import github.nikhrom.javatraining.advanced_hibernate.entity.Payment;
import github.nikhrom.javatraining.advanced_hibernate.entity.PersonalData_;
import github.nikhrom.javatraining.advanced_hibernate.entity.User;
import github.nikhrom.javatraining.advanced_hibernate.entity.User_;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
        return session.createQuery("""
                    select u
                    from Company c
                    join c.users u
                    where c.name = :companyName
                    """, User.class)
                .setParameter("companyName", companyName)
                .list();
    }

    /**
     * Возвращает все выплаты, полученные сотрудниками компании с указанными именем,
     * упорядоченные по имени сотрудника, а затем по размеру выплаты
     */
    public List<Payment> findAllPaymentsByCompanyName(Session session, String companyName){
        return session.createQuery("""
                    select p
                    from Company c
                    join c.users u
                    join u.payments p
                    where c.name = :companyName
                    order by u.personalData.firstname, p.amount
                    """, Payment.class)
                .setParameter("companyName", companyName)
                .list();

    }


    /**
     * Возвращает среднюю зарплату сотрудника с указанными именем и фамилией
     */
    public Double findAveragePaymentAmountByFirstAndLastName(Session session,
                                                             String firstname,
                                                             String lastname){
        return session.createQuery("""
                    select avg (p.amount)
                    from Payment p
                    join p.receiver u
                    where u.personalData.firstname = :firstname and 
                          u.personalData.lastname = :lastname
                    """, Double.class)
                .setParameter("firstname", firstname)
                .setParameter("lastname", lastname)
                .getSingleResult();
    }

    /**
     * Возвращает для каждой компании: название, среднюю зарплату всех её сотрудников.
     * Компании упорядочены по названию.
     */
    public List<Object[]> findCompanyNamesWithAvgUserPaymentsOrderedByCompanyName(
            Session session
    ){
        return session.createQuery("""
                        SELECT c.name, avg(p.amount)
                        FROM Payment p
                        JOIN p.receiver u
                        JOIN u.company c
                        GROUP BY c.name   
                        ORDER BY c.name                
                        """, Object[].class)
                .list();
    }


    /**
     * Возвращает список: сотрудник (объект User), средний размер выплат, но только для тех сотрудников, чей средний размер выплат
     * больше среднего размера выплат всех сотрудников
     * Упорядочить по имени сотрудника
     */
    public List<Object[]> isItPossible(Session session) {
        var query = session.createQuery("""
                SELECT u, avg(p.amount)
                FROM User u
                JOIN u.payments p
                GROUP BY u.username
                HAVING avg(p.amount) > (SELECT avg(p.amount)
                                       FROM Payment p)
                ORDER BY u.username
                """, Object[].class);

        return query.list();
    }
//    ORDER BY u.username
    public static UserDao getInstance(){
        return INSTANCE;
    }
}
