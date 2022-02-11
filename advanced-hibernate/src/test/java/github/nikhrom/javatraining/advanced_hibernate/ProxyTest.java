package github.nikhrom.javatraining.advanced_hibernate;

import github.nikhrom.javatraining.advanced_hibernate.entity.Company;
import github.nikhrom.javatraining.advanced_hibernate.entity.User;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;


public class ProxyTest {

    @Test
    void dynamicProxy(){
        var company = new Company();
        Proxy.newProxyInstance(company.getClass().getClassLoader(), company.getClass().getInterfaces(),
                (proxy, method, args) -> method.invoke(company, args));
    }
}
