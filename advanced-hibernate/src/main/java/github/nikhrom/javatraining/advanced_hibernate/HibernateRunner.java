package github.nikhrom.javatraining.advanced_hibernate;

import github.nikhrom.javatraining.advanced_hibernate.dao.CompanyRepository;
import github.nikhrom.javatraining.advanced_hibernate.dao.PaymentRepository;
import github.nikhrom.javatraining.advanced_hibernate.dao.UserRepository;
import github.nikhrom.javatraining.advanced_hibernate.dto.UserCreateDto;
import github.nikhrom.javatraining.advanced_hibernate.entity.*;
import github.nikhrom.javatraining.advanced_hibernate.interceptor.TransactionInterceptor;
import github.nikhrom.javatraining.advanced_hibernate.mapper.CompanyReadMapper;
import github.nikhrom.javatraining.advanced_hibernate.mapper.UserCreateMapper;
import github.nikhrom.javatraining.advanced_hibernate.mapper.UserReadMapper;
import github.nikhrom.javatraining.advanced_hibernate.service.UserService;
import github.nikhrom.javatraining.advanced_hibernate.util.HibernateUtil;
import github.nikhrom.javatraining.advanced_hibernate.util.TestDataImporter;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.internal.bytebuddy.ByteBuddy;
import org.assertj.core.internal.bytebuddy.implementation.MethodDelegation;
import org.assertj.core.internal.bytebuddy.matcher.ElementMatcher;
import org.assertj.core.internal.bytebuddy.matcher.ElementMatchers;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

import javax.persistence.EntityManager;
import java.lang.reflect.Proxy;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {

            var session = (Session) Proxy.newProxyInstance(Session.class.getClassLoader(), new Class[]{Session.class},
                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));

            session.beginTransaction();

            var userRepository = new UserRepository(session);
            var companyRepository = new CompanyRepository(session);

            var companyReadMapper = new CompanyReadMapper();
            var userReadMapper = new UserReadMapper(companyReadMapper);
            var userCreateMapper = new UserCreateMapper(companyRepository);

            UserService userService = new UserService(userRepository, userReadMapper, userCreateMapper);

            userService.findById(1L).ifPresent(System.out::println);

            var userCreateDto = UserCreateDto.builder()
                    .companyId(1)
                    .role(Role.USER)
                    .userName("nikhrom")
                    .personalData(PersonalData.builder()
                            .firstname("Ivan")
                            .lastname("Ivanov")
                            .birthday(new Birthday(LocalDate.of(2000, 1, 1)))
                            .build())
                    .info("Some info")
                    .build();


            userService.create(userCreateDto);

            session.getTransaction().commit();
        }
    }

}
