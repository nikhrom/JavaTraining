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

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()){
             try(Session session = sessionFactory.openSession()){
                session.beginTransaction();

                var company = session.get(Company.class, 1);
                company.setName(company.getName() + '0');

                session.getTransaction().commit();
            }

            try(Session session = sessionFactory.openSession()){
                session.beginTransaction();

                var auditReader = AuditReaderFactory.get(session);
                var oldCompany = auditReader.find(Company.class, 1, new Date(1644338298805L));
                session.replicate(oldCompany, ReplicationMode.OVERWRITE);


                session.getTransaction().commit();
            }
        }
    }

}
