package github.nikhrom.javatraining.spring.introduction;

import github.nikhrom.javatraining.spring.introduction.pet.Pet;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextRunner {

    public static void main(String[] args) {
        try (var context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            var myPerson = context.getBean("myPerson", Person.class);
            System.out.println(myPerson);
        }
    }

    private static void inverseOfControl() {
        try (var context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            Pet myPet = context.getBean("myPet", Pet.class);
            myPet.say();
        }
    }


}
