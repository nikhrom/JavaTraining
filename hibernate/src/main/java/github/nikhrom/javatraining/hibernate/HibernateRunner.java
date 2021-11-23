package github.nikhrom.javatraining.hibernate;


import github.nikhrom.javatraining.hibernate.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;

public class HibernateRunner
{
    public static void main(String[] args )
    {
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
