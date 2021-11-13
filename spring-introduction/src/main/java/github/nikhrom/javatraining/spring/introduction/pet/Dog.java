package github.nikhrom.javatraining.spring.introduction.pet;

import lombok.Data;

@Data
public class Dog implements Pet {

    @Override
    public void say() {
        System.out.println("Dog says");
    }
}
