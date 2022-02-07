package github.nikhrom.javatraining.advanced_hibernate;

import github.nikhrom.javatraining.advanced_hibernate.entity.Payment;
import github.nikhrom.javatraining.advanced_hibernate.util.HibernateUtil;
import github.nikhrom.javatraining.advanced_hibernate.util.TestDataImporter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()){
            TestDataImporter.importData(sessionFactory);
            session.beginTransaction();

            var payment = session.get(Payment.class, 1L);

            session.getTransaction().commit();
        }
    }

}
