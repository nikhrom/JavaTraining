package github.nikhrom.javatraining.spring.introduction.pet;

import lombok.Data;
import lombok.ToString;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Data
@Component("catBean")
public class Cat implements Pet {
    @Override
    public void say() {
        System.out.println("Cat says");
    }
}
