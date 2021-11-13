package github.nikhrom.javatraining.spring.introduction.pet;

import lombok.Data;
import lombok.ToString;

@Data
public class Cat implements Pet {
    @Override
    public void say() {
        System.out.println("Cat says");
    }
}
