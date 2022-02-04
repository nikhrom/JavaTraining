package github.nikhrom.javatraining.advanced_hibernate;

import github.nikhrom.javatraining.advanced_hibernate.entity.Role;
import github.nikhrom.javatraining.advanced_hibernate.entity.User;
import github.nikhrom.javatraining.advanced_hibernate.util.HibernateUtil;
import github.nikhrom.javatraining.advanced_hibernate.util.TestDataImporter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.enableFetchProfile("withCompanyAndPayments");
            var user = session.get(User.class, 1L);

            System.out.println(user.getCompany().getName());
            System.out.println(user.getPayments().size());

            session.getTransaction().commit();
        }
    }

}
