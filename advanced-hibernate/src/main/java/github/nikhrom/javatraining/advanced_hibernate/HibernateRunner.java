package github.nikhrom.javatraining.advanced_hibernate;

import github.nikhrom.javatraining.advanced_hibernate.entity.Role;
import github.nikhrom.javatraining.advanced_hibernate.entity.User;
import github.nikhrom.javatraining.advanced_hibernate.util.HibernateUtil;
import github.nikhrom.javatraining.advanced_hibernate.util.TestDataImporter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.GraphSemantic;
import org.hibernate.jpa.QueryHints;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()){
            session.beginTransaction();


            // GraphSemantic.LOAD.getJpaHintName() == QueryHints.HINT_LOADGRAPH
            Map<String, Object> properties = Map.of(
                    QueryHints.HINT_LOADGRAPH, session.getEntityGraph("withCompanyAndChats")
            );

            var user = session.find(User.class, 1L, properties);

            System.out.println(user.getCompany().getName());
            System.out.println(user.getUserChats().size());


            var users = session.createQuery("select u from User u", User.class)
                    .setHint(QueryHints.HINT_LOADGRAPH, session.getEntityGraph("withCompanyAndChats"))
                    .list();

            users.forEach(user1 -> System.out.println(user1.getCompany().getName()));
            users.forEach(user1 -> System.out.println(user1.getUserChats().size()));


            session.getTransaction().commit();
        }
    }

}
