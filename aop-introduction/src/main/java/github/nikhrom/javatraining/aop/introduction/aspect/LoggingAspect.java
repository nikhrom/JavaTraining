package github.nikhrom.javatraining.aop.introduction.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect{

    @Pointcut("execution(* github.nikhrom.javatraining.aop.introduction.Library.get*())")
    private void libraryGetMethods(){};

    @Pointcut("execution(* github.nikhrom.javatraining.aop.introduction.Library.return*(..))")
    private void libraryReturnMethods(){};

    @Pointcut("execution(* github.nikhrom.javatraining.aop.introduction.Library.*(..))")
    private void allLibraryMethods(){};

    @Before("libraryGetMethods()")
    public void beforeLibraryGetMethodsAdvice(){
        System.out.println("beforeLibraryGetMethodsAdvice: вызов get в Library");
    }

    @Before("libraryReturnMethods()")
    public void beforeLibraryReturnMethodsAdvice(){
        System.out.println("beforeLibraryReturnMethodsAdvice: вызов return в Library");
    }

    @Before("libraryReturnMethods() || libraryGetMethods()")
    public void beforeLibraryReturnAndGetMethodsAdvice(){
        System.out.println("beforeLibraryReturnAndGetMethodsAdvice: вызов get или return в Library");
    }

    @Before("allLibraryMethods() && !libraryGetMethods()")
    public void beforeAllLibraryMethodsExceptGetAdvice(){
        System.out.println("beforeAllLibraryMethodsExceptGet: вызов какого-то метода в Library, который не get");
    }
}
