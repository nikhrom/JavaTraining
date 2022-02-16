package github.nikhrom.javatraining.spring.security.repository;

import com.sun.istack.NotNull;
import github.nikhrom.javatraining.spring.security.entity.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository{

    private final SessionFactory sessionFactory;

    public List<User> list() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("select u from User u", User.class)
                .list();
    }

    public Optional<User> findByUsername(String username) {
        var user = sessionFactory.getCurrentSession()
                .createQuery("select u from User u where username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
        return Optional.ofNullable(user);
    }
}
