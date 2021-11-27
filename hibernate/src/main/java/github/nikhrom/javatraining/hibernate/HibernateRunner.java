package github.nikhrom.javatraining.hibernate;


import github.nikhrom.javatraining.hibernate.entity.Detail;
import github.nikhrom.javatraining.hibernate.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class HibernateRunner {
    public static void main(String[] args) {
        try (SessionFactory configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();
        ) {
            saveEntity(configuration);
        }


    }

    private static void deleteEntity(SessionFactory configuration) {
        try (Session session = configuration.getCurrentSession()) {

            var transaction = session.beginTransaction();

            // first way
            var employee = session.get(Employee.class, 15);
            session.delete(employee);

            // second way
            session.createQuery("delete from Employee " +
                    "where id = 15")
                    .executeUpdate();

            transaction.commit();
        }
    }

    private static void updateEntity(SessionFactory configuration) {
        try (Session session = configuration.getCurrentSession()) {

            var transaction = session.beginTransaction();

            var employee = session.get(Employee.class, 10);
            employee.setSalary(1000);
            session.update(employee);

            transaction.commit();
        }
    }

    private static void getEntities(SessionFactory configuration) {
        try (Session session = configuration.getCurrentSession()) {

            var transaction = session.beginTransaction();

//            session.createQuery("from Employee where id > 10")
//                    .getResultList()
//                    .forEach(System.out::println);

            session.createSQLQuery("select * from employee")
                    .addEntity(Employee.class)
                    .getResultList()
                    .forEach(System.out::println);

            transaction.commit();
        }
    }

    private static void getEntity(SessionFactory configuration) {
        try (Session session = configuration.getCurrentSession()) {

            var transaction = session.beginTransaction();

            var employee = session.get(Employee.class, 15);

            System.out.println(employee);
            transaction.commit();
        }
    }

    private static void saveEntity(SessionFactory configuration) {
        try (Session session = configuration.getCurrentSession()) {

            var employee = Employee.builder()
                    .name("Nikolay")
                    .surname("Nikolaev")
                    .salary(100)
                    .department("Google")
                    .build();

            var detail = Detail.builder()
                    .email("test1@gmail.com")
                    .phoneNumber("+79827372621")
                    .employee(employee)
                    .build();

            employee.setDetail(detail);

            var transaction = session.beginTransaction();
            session.save(detail);
            transaction.commit();
        }
    }
}
