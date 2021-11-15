package github.nikhrom.javatraining.spring.introduction;

import github.nikhrom.javatraining.spring.introduction.config.ManualCreationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OnlyAnnotationConfigRunner {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ManualCreationConfig.class)) {
            var personBean = context.getBean("personBean", Person.class);
            System.out.println(personBean);
        }
    }

}
