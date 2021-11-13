package github.nikhrom.javatraining.spring.introduction;

import github.nikhrom.javatraining.spring.introduction.pet.Pet;
import lombok.Data;


@Data
public class Person {
    private Pet pet;
    private String name;
    private int age;

    public void say(){
        System.out.println("Person called yourself pet");
        pet.say();
    }
}