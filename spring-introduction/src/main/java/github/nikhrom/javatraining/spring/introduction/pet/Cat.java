package github.nikhrom.javatraining.spring.introduction.pet;

public class Cat implements Pet {

    public Cat(){
        System.out.println("Cat is created");
    }

    @Override
    public void say() {
        System.out.println("Cat says");
    }
}
