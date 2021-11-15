package github.nikhrom.javatraining.spring.introduction;

import github.nikhrom.javatraining.spring.introduction.pet.Cat;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConfigWithAnnotationRunner {

    public static void main(String[] args) {
        try (var context = new ClassPathXmlApplicationContext("applicationContextForAnnotations.xml")) {
            var personBeanOne = context.getBean("personBean");
            var personBeanTwo = context.getBean("personBean");
            System.out.println("Один и тот же бин? " + (personBeanOne == personBeanTwo ? "Да": "Нет"));
        }
    }

    private static void createBeanWithDependency() {
        try (var context = new ClassPathXmlApplicationContext("applicationContextForAnnotations.xml")) {
            var person = context.getBean("personBean", Person.class);
            System.out.println(person);
        }
    }

    private static void createBeanWithNoArgsConstructor() {
        try (var context = new ClassPathXmlApplicationContext("applicationContextForAnnotations.xml")) {
            var catBean = context.getBean("catBean", Cat.class);
            catBean.say();
        }
    }
}
