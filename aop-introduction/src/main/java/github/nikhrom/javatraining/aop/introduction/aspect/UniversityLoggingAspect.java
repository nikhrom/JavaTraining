package github.nikhrom.javatraining.aop.introduction.aspect;

import github.nikhrom.javatraining.aop.introduction.Student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class UniversityLoggingAspect {

    @Pointcut("execution(* github.nikhrom.javatraining.aop.introduction.University.getStudents())")
    private void getStudentsFromUniversity() {
    }

    @Pointcut("execution(* github.nikhrom.javatraining.aop.introduction.University.getStudent(..))")
    private void getStudentFromUniversity() {
    }


    @Before("getStudentsFromUniversity()")
    public void beforeGetStudentsAdvice() {
        System.out.println("beforeGetStudentsAdvice: логируем получение списка студентов перед" +
                " вызовом метода getStudents класса University");
    }

    @AfterReturning(pointcut = "getStudentsFromUniversity()",
            returning = "students")
    public void afterGetStudentsAdvice(List<Student> students) {
        students.forEach(student -> student.setFullName("Mr. " + student.getFullName()));

        System.out.println("afterGetStudentsAdvice: логируем получение списка студентов после" +
                " нормального выполнения метода getStudents класса University");
    }


    @AfterThrowing(pointcut = "getStudentFromUniversity()",
            throwing = "exception")
    public void afterThrowingInGetStudentAdvice(Exception exception) {
        var message = exception.getMessage();
        System.out.println(message);

        System.out.println("afterThrowingInGetStudentAdvice: логируем получение студента по индексу после" +
                " выброса исключения в методе getStudent класса University");
    }
}
