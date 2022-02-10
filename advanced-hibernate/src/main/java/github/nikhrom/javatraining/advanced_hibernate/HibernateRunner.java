package github.nikhrom.javatraining.advanced_hibernate;

import github.nikhrom.javatraining.advanced_hibernate.dao.PaymentRepository;
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

import javax.persistence.EntityManager;
import java.lang.reflect.Proxy;
import java.util.Date;
import java.util.List;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        try  (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()){

            var session = (Session) Proxy.newProxyInstance(Session.class.getClassLoader(), new Class[]{Session.class},
                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1) );

            session.beginTransaction();

            var paymentRepository = new PaymentRepository(session);

            paymentRepository.findById(1L).ifPresent(System.out::println);

            session.getTransaction().commit();
        }
    }

}
