package github.nikhrom.javatraining.aop.introduction.aspect;


import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class ExceptionHandlingAspect {

    @Before("github.nikhrom.javatraining.aop.introduction.aspect.CommonLibraryPointcuts.libraryGetMethods()")
    public void beforeGetExceptionAdvice(){
        System.out.println("beforeGetExceptionAdvice: throw exception в методе get класса Library");
    }

}
