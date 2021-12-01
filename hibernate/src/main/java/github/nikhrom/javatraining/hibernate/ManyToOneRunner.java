package github.nikhrom.javatraining.hibernate;

import github.nikhrom.javatraining.hibernate.entity.Department;
import github.nikhrom.javatraining.hibernate.entity.Detail;
import github.nikhrom.javatraining.hibernate.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManyToOneRunner {

    public static void main(String[] args) {
        try (SessionFactory configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();
        ) {
            getEntities(configuration);
        }
    }

    private static void deleteEntity(SessionFactory configuration) {
        try (Session session = configuration.getCurrentSession()) {

            var transaction = session.beginTransaction();


            session.createQuery("delete Employee where id = 5")
                    .executeUpdate();

            transaction.commit();
        }
    }

    private static void getEntities(SessionFactory configuration) {
        try (Session session = configuration.getCurrentSession()) {

            var transaction = session.beginTransaction();

            var department = session.get(Department.class, 1);

            System.out.println(department);

            transaction.commit();
        }
    }

    private static void saveEntity(SessionFactory configuration) {
        try (Session session = configuration.getCurrentSession()) {

            var department = Department.builder()
                    .name("Google")
                    .maxSalary(1_000_000)
                    .minSalary(10_000)
                    .build();

            var petr = Employee.builder()
                    .name("Petr")
                    .surname("Petrov")
                    .department(department)
                    .build();

            var ivan = Employee.builder()
                    .name("Ivan")
                    .surname("Ivanov")
                    .department(department)
                    .build();

            department.addEmployees(ivan, petr);

            var transaction = session.beginTransaction();
            session.persist(department);
            transaction.commit();
        }
    }

}
