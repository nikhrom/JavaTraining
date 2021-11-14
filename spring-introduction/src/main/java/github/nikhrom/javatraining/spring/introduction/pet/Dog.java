package github.nikhrom.javatraining.spring.introduction.pet;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component("dogBean")
public class Dog implements Pet {

    @Override
    public void say() {
        System.out.println("Dog says");
    }
}
