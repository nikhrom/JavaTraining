package github.nikhrom.javatraining.aop.introduction;

import github.nikhrom.javatraining.aop.introduction.config.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test1 {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(Config.class)) {
            var university = context.getBean("universityBean", University.class);
            university.addStudents(
                    new Student("Petr Petrov", 1, 4.5),
                    new Student("Ivan Ivanov", 1, 4.2),
                    new Student("Petr Ivanov", 1, 3.4),
                    new Student("Alexey Alexeyev", 1, 5)
            );
            System.out.println(university.getStudents());
            System.out.println(university.getStudent(5));
        }
    }

    private static void firstUsingAspect() {
        try (var context = new AnnotationConfigApplicationContext(Config.class)) {
            var library = context.getBean("libraryBean", Library.class);
            var book = library.getBook();
            library.returnBook(book);
        }
    }
}
