package github.nikhrom.javatraining.spring.introduction;

import github.nikhrom.javatraining.spring.introduction.pet.Cat;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConfigWithAnnotationRunner {

    public static void main(String[] args) {
        try (var context = new ClassPathXmlApplicationContext("applicationContextForAnnotations.xml")) {
            var catBean = context.getBean("catBean", Cat.class);
            catBean.say();
        }
    }
}
