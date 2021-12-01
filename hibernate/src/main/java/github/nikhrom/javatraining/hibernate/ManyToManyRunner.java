package github.nikhrom.javatraining.hibernate;

import github.nikhrom.javatraining.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManyToManyRunner {

    public static void main(String[] args) {
        try (SessionFactory configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Detail.class)
                .addAnnotatedClass(LocalPhone.class)
                .buildSessionFactory();
        ) {
            getEntities(configuration);
        }
    }

    private static void deleteEntity(SessionFactory configuration) {
        try (Session session = configuration.getCurrentSession()) {

            var transaction = session.beginTransaction();

            var phone = session.get(LocalPhone.class, 5);

            session.delete(phone);

            transaction.commit();
        }
    }

    private static void getEntities(SessionFactory configuration) {
        try (Session session = configuration.getCurrentSession()) {

            var transaction = session.beginTransaction();

            var phone = session.get(LocalPhone.class, 13);

            phone.getEmployees()
                    .forEach(System.out::println);

            transaction.commit();
        }
    }

    private static void saveEntity(SessionFactory configuration) {
        try (Session session = configuration.getCurrentSession()) {

            LocalPhone firstPhone = LocalPhone.builder()
                    .number("54-02")
                    .type(LocalPhoneType.LOCAL.name())
                    .build();

            LocalPhone secondPhone = LocalPhone.builder()
                    .number("53-01")
                    .type(LocalPhoneType.LOCAL.name())
                    .build();

            Employee nikita = Employee.builder()
                    .name("Nikita")
                    .surname("Nikitov")
                    .build();

            Employee petya = Employee.builder()
                    .name("Petr")
                    .surname("Petrov")
                    .build();


            nikita.addLocalPhones(firstPhone, secondPhone);
            petya.addLocalPhones(firstPhone);


            var transaction = session.beginTransaction();

            session.persist(nikita);
            session.persist(petya);

            transaction.commit();
        }
    }

}
