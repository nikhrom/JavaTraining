package github.nikhrom.javatraining.advanced_hibernate;

import github.nikhrom.javatraining.advanced_hibernate.entity.*;
import github.nikhrom.javatraining.advanced_hibernate.util.HibernateUtil;
import github.nikhrom.javatraining.advanced_hibernate.util.TestDataImporter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.GraphSemantic;
import org.hibernate.graph.RootGraph;
import org.hibernate.graph.SubGraph;
import org.hibernate.jpa.QueryHints;
import org.hibernate.query.Query;

import javax.persistence.LockModeType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()){
            session.beginTransaction();


            var payment = session.find(Payment.class, 1L, LockModeType.OPTIMISTIC); // OPTIMISTIC - по умолчанию
            payment.setAmount(2);

            session.getTransaction().commit();
//
        }
    }

}
