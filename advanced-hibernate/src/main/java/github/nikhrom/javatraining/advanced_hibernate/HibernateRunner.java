package github.nikhrom.javatraining.advanced_hibernate;

import github.nikhrom.javatraining.advanced_hibernate.entity.Audit;
import github.nikhrom.javatraining.advanced_hibernate.entity.Company;
import github.nikhrom.javatraining.advanced_hibernate.entity.Payment;
import github.nikhrom.javatraining.advanced_hibernate.entity.User;
import github.nikhrom.javatraining.advanced_hibernate.util.HibernateUtil;
import github.nikhrom.javatraining.advanced_hibernate.util.TestDataImporter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

import java.util.Date;
import java.util.List;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()){
            User user = null;
             try(Session session = sessionFactory.openSession()){
                session.beginTransaction();

                user = session.get(User.class, 1L);
                var user1 = session.get(User.class, 1L);
                System.out.println(user1.getUserChats().size());

                 var userId = session.createQuery("select p from Payment p where p.receiver.id = :userId", Payment.class)
                         .setParameter("userId", 1L)
                         .setCacheable(true)
                         //.setCacheRegion("queries")
                         .getResultList();


                 session.getTransaction().commit();
            }
            try(Session session = sessionFactory.openSession()){
                session.beginTransaction();

                var user2 = session.get(User.class, 1L);
                System.out.println(user2.getUserChats().size());


                var userId = session.createQuery("select p from Payment p where p.receiver.id = :userId", Payment.class)
                        .setParameter("userId", 1L)
                        .setCacheable(true)
                        .getResultList();

                session.getTransaction().commit();
            }
        }
    }

}
