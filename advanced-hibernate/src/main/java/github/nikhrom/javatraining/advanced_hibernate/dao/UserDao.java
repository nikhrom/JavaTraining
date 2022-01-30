package github.nikhrom.javatraining.advanced_hibernate.dao;


import github.nikhrom.javatraining.advanced_hibernate.entity.Payment;
import github.nikhrom.javatraining.advanced_hibernate.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {

    private static final UserDao INSTANCE = new UserDao();


    public List<User> findAll(Session session){
        return session.createQuery("SELECT u FROM User u", User.class)
                .list();
    }

    public List<User> findAllByFirstName(Session session, String firstName){
        return session.createQuery("""
                SELECT u 
                FROM User u 
                WHERE u.personalData.firstname = :firstname
                """, User.class)
                .setParameter("firstname", firstName)
                .list();
    }

    public List<User> findLimitedUsersOrderedByBirthday(Session session, int limit){
        return session.createQuery("""
                SELECT u 
                FROM User u
                ORDER BY u.personalData.birthday ASC
                """, User.class)
                .setMaxResults(limit)
                .list();
    }

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
                .uniqueResult();
    }


    public List<Object[]> findCompanyNamesWithAvgUserPaymentsOrderedByCompanyName(
            Session session,
            String companyName
    ){
        return session.createQuery("""
                        select c.name, avg(p.amount)
                        from Payment p
                        join p.receiver u
                        join u.company c
                        where c.name = :companyName 
                        group by c.name   
                        order by c.name                
                        """, Object[].class)
                .setParameter("companyName", companyName)
                .list();
    }

    public static UserDao getInstance(){
        return INSTANCE;
    }
}
