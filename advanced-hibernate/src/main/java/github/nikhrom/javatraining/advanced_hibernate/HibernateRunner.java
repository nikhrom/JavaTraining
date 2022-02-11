package github.nikhrom.javatraining.advanced_hibernate;

import github.nikhrom.javatraining.advanced_hibernate.dao.CompanyRepository;
import github.nikhrom.javatraining.advanced_hibernate.dao.UserRepository;
import github.nikhrom.javatraining.advanced_hibernate.dto.UserCreateDto;
import github.nikhrom.javatraining.advanced_hibernate.entity.Birthday;
import github.nikhrom.javatraining.advanced_hibernate.entity.PersonalData;
import github.nikhrom.javatraining.advanced_hibernate.entity.Role;
import github.nikhrom.javatraining.advanced_hibernate.interceptor.TransactionInterceptor;
import github.nikhrom.javatraining.advanced_hibernate.mapper.CompanyReadMapper;
import github.nikhrom.javatraining.advanced_hibernate.mapper.UserCreateMapper;
import github.nikhrom.javatraining.advanced_hibernate.mapper.UserReadMapper;
import github.nikhrom.javatraining.advanced_hibernate.service.UserService;
import github.nikhrom.javatraining.advanced_hibernate.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.time.LocalDate;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {

            var session = (Session) Proxy.newProxyInstance(Session.class.getClassLoader(), new Class[]{Session.class},
                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));

            var userRepository = new UserRepository(session);
            var companyRepository = new CompanyRepository(session);

            var companyReadMapper = new CompanyReadMapper();
            var userReadMapper = new UserReadMapper(companyReadMapper);
            var userCreateMapper = new UserCreateMapper(companyRepository);

            var transactionInterceptor = new TransactionInterceptor(sessionFactory);

            UserService userService = new ByteBuddy()
                    .subclass(UserService.class)
                    .method(ElementMatchers.any())
                    .intercept(MethodDelegation.to(transactionInterceptor))
                    .make()
                    .load(UserService.class.getClassLoader())
                    .getLoaded()
                    .getDeclaredConstructor(UserRepository.class, UserReadMapper.class, UserCreateMapper.class)
                    .newInstance(userRepository, userReadMapper, userCreateMapper);

            userService.findById(1L).ifPresent(System.out::println);

            var userCreateDto = UserCreateDto.builder()
                    .companyId(1)
                    .role(Role.USER)
                    .userName("nikhrom2")
                    .personalData(PersonalData.builder()
                            .firstname("Ivan")
                            .lastname("Ivanov")
                            .birthday(new Birthday(LocalDate.of(2000, 1, 1)))
                            .build())
                    .info("Some info")
                    .build();


            userService.create(userCreateDto);
        }
    }

}
