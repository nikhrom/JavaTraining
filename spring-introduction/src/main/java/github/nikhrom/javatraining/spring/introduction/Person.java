package github.nikhrom.javatraining.spring.introduction;

import github.nikhrom.javatraining.spring.introduction.pet.Pet;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Data
@Component("personBean")
public class Person {

    private Pet pet;
    private String name;
    private int age;

    @Autowired
    public Person(@Qualifier("dogBean") Pet pet){
        this.pet = pet;
    }

    public Person(String name){
    }

    public void say(){
        System.out.println("Person called yourself pet");
        pet.say();
    }
}