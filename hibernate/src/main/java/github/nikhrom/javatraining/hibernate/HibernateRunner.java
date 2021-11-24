package github.nikhrom.javatraining.hibernate;


import github.nikhrom.javatraining.hibernate.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class HibernateRunner {
    public static void main(String[] args) {
        SessionFactory configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try (Session session = configuration.getCurrentSession()) {

            var transaction = session.beginTransaction();

            var employee = session.get(Employee.class, 10);
            employee.setSalary(1000);
            session.update(employee);

            transaction.commit();
        }
    }

    private static void getEntities() {
        SessionFactory configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

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

    private static void getEntity() {
        SessionFactory configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try (Session session = configuration.getCurrentSession()) {

            var transaction = session.beginTransaction();

            var employee = session.get(Employee.class, 9);

            System.out.println(employee);
            transaction.commit();
        }
    }

    private static void saveEntity() {
        SessionFactory configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try (Session session = configuration.getCurrentSession()) {
            var employee = Employee.builder()
                    .name("Nik")
                    .surname("Hrom")
                    .salary(100)
                    .department("Google")
                    .build();


            var transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }
}
