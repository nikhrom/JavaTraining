package github.nikhrom.javatraining.advanced_hibernate;

import github.nikhrom.javatraining.advanced_hibernate.converter.BirthdayConverter;
import github.nikhrom.javatraining.advanced_hibernate.entity.Birthday;
import github.nikhrom.javatraining.advanced_hibernate.entity.Role;
import github.nikhrom.javatraining.advanced_hibernate.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunner {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.addAttributeConverter(BirthdayConverter.class);
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
            var session = sessionFactory.openSession()) {

            var user = User.builder()
                    .username("nikhrom")
                    .firstname("Ivan")
                    .lastname("Ivanov")
                    .birthDate(new Birthday(LocalDate.of(2000, 1, 1)))
                    .role(Role.ADMIN)
                    .build();

            var transaction = session.beginTransaction();

            session.save(user);

            transaction.commit();


        }
    }

}
