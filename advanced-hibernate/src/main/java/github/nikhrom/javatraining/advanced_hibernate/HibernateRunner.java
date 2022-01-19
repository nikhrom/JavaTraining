package github.nikhrom.javatraining.advanced_hibernate;

import github.nikhrom.javatraining.advanced_hibernate.entity.Company;
import github.nikhrom.javatraining.advanced_hibernate.entity.PersonalData;
import github.nikhrom.javatraining.advanced_hibernate.entity.User;
import github.nikhrom.javatraining.advanced_hibernate.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        var google = Company.builder()
                .name("Google")
                .build();
        var user = User.builder()
                .username("nikhrom2")
                .personalData(PersonalData.builder()
                        .firstname("petr")
                        .lastname("petrov").build())
                .company(google)
                .build();

        log.info("User entity is transient, object: {}", user);

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {

            var transaction = session.beginTransaction();

            log.trace("Transaction is began: {}", transaction);
            var user1 = session.get(User.class, 1L);

//            session.save(google);
//            session.save(user);
            log.trace("User is in persistent state: user {}, session {}", user, session);

            transaction.commit();
            log.trace("Transaction is commited: transaction {}", transaction);

        }catch (Exception exception){
            log.error("Exception occurred: {}", exception);
            throw exception;
        }
    }

}
