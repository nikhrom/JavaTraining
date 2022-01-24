package github.nikhrom.javatraining.advanced_hibernate;

import github.nikhrom.javatraining.advanced_hibernate.entity.Chat;
import github.nikhrom.javatraining.advanced_hibernate.entity.Company;
import github.nikhrom.javatraining.advanced_hibernate.entity.Profile;
import github.nikhrom.javatraining.advanced_hibernate.entity.User;
import github.nikhrom.javatraining.advanced_hibernate.util.HibernateUtil;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Arrays;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;

class HibernateRunnerTest {

    @Test
    void checkManyToMany(){
        try(var sessionFactory = HibernateUtil.buildSessionFactory();
            var session = sessionFactory.openSession()){

            session.beginTransaction();

            var user = session.get(User.class, 1L);

            var chat = Chat.builder()
                    .name("new_chat")
                    .build();

            session.save(chat);
            user.addChat(chat);

            session.getTransaction().commit();
        }
    }

    @Test
    void checkOneToOne(){
        try(var sessionFactory = HibernateUtil.buildSessionFactory();
            var session = sessionFactory.openSession()){

            session.beginTransaction();

            var user = User.builder()
                    .username("nik2")
                    .build();

            var profile = Profile.builder()
                    .user(user)
                    .street("prospekt 2")
                    .language("Rus")
                    .build();

            session.save(profile);

            session.getTransaction().commit();
        }
    }

    @Test
    void checkOrphanRemoval(){
        try(var sessionFactory = HibernateUtil.buildSessionFactory();
            var session = sessionFactory.openSession()){

            session.beginTransaction();

            var company = session.get(Company.class, 7);
            company.getUsers().removeIf(user -> user.getId() == 16);

            session.getTransaction().commit();
        }
    }

    @Test
    void checkLazyInitialization(){
        Company company;
        try(var sessionFactory = HibernateUtil.buildSessionFactory();
            var session = sessionFactory.openSession()){

            session.beginTransaction();

            company = session.get(Company.class, 7);

            session.getTransaction().commit();
        }

        var users = company.getUsers();
        users.forEach(System.out::println);
    }

    @Test
    void deleteCompany(){
        try(var sessionFactory = HibernateUtil.buildSessionFactory();
            var session = sessionFactory.openSession()){

            session.beginTransaction();

            var company = session.get(Company.class, 6);
            session.delete(company);

            session.getTransaction().commit();
        }
    }

    @Test
    void addUserToNewCompany(){
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
            var session = sessionFactory.openSession()) {

            session.beginTransaction();

            var company = Company.builder()
                    .name("Facebook")
                    .build();

            var user = User.builder()
                    .username("nikhrom@mail.ru")
                    .build();

            company.addUser(user);
            session.save(company);

            session.getTransaction().commit();
        }
    }

    @Test
    void oneToMany(){
        try(var sessionFactory = HibernateUtil.buildSessionFactory();
            var session = sessionFactory.openSession()){

            session.beginTransaction();

            var company = session.get(Company.class, 1);
            System.out.println();

            session.getTransaction().commit();
        }


    }

    @Test
    void checkReflectionApi(){
        var user = User.builder()
                .build();

        String sql = "insert\n" +
                "into\n" +
                "%s\n" +
                "(%s)\n" +
                "values\n" +
                "(%s);\n";

        var tableName = ofNullable(user.getClass().getAnnotation(Table.class))
                .map(tableAnnotation -> tableAnnotation.schema() + "." + tableAnnotation.name())
                .orElse(user.getClass().getName());

        var declaredFields = user.getClass().getDeclaredFields();
        var columnNames = Arrays.stream(declaredFields)
                .map(field -> ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName()))
                .collect(joining(", "));

        var columnValues = Arrays.stream(declaredFields)
                .map(field -> "?")
                .collect(joining(", "));

        sql = String.format(sql, tableName, columnNames, columnValues);

        System.out.println(sql);

        

    }

}