package github.nikhrom.javatraining.aop.introduction;

import github.nikhrom.javatraining.aop.introduction.config.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test1 {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(Config.class)) {
            var library = context.getBean("libraryBean", Library.class);
            var book = library.getBook();
        }

    }
}
