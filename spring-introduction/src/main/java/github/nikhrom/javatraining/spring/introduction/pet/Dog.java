package github.nikhrom.javatraining.spring.introduction.pet;

public class Dog implements Pet {

    public Dog(){
        System.out.println("Dog is created");
    }

    @Override
    public void say() {
        System.out.println("Dog says");
    }
}
