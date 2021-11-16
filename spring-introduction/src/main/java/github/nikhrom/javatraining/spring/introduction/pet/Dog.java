package github.nikhrom.javatraining.spring.introduction.pet;

import lombok.Data;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Data
@Component("dogBean")
public class Dog implements Pet {

    @Override
    public void say() {
        System.out.println("Dog says");
    }

    @PostConstruct
    public void init() {
        System.out.println("dogBean init method ");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("dogBean destroy method ");
    }
}
