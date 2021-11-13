package github.nikhrom.javatraining.spring.introduction;

import github.nikhrom.javatraining.spring.introduction.pet.Pet;

public class Person {
    private Pet pet;

    public Person(Pet pet) {
        this.pet = pet;
    }

    public void say(){
        System.out.println("Person called yourself pet");
        pet.say();
    }

}
