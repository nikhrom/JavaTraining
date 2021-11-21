package github.nikhrom.javatraining.aop.introduction.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

@Component
@Aspect
@Order(2)
public class LoggingAspect{

    @Before("github.nikhrom.javatraining.aop.introduction.aspect.CommonLibraryPointcuts.libraryGetMethods()")
    public void beforeLibraryGetMethodsAdvice(JoinPoint joinPoint){
        System.out.println("beforeLibraryGetMethodsAdvice: вызов get в Library");
        var signature = joinPoint.getSignature();
        System.out.println(signature.getDeclaringTypeName());
        System.out.println(signature.getName());
        System.out.println(Modifier.isPublic(signature.getModifiers()));
        System.out.println(joinPoint);
    }

    @Before("github.nikhrom.javatraining.aop.introduction.aspect.CommonLibraryPointcuts.libraryReturnMethods()")
    public void beforeLibraryReturnMethodsAdvice(){
        System.out.println("beforeLibraryReturnMethodsAdvice: вызов return в Library");
    }

    @Before("github.nikhrom.javatraining.aop.introduction.aspect.CommonLibraryPointcuts.libraryReturnMethods() || " +
            "github.nikhrom.javatraining.aop.introduction.aspect.CommonLibraryPointcuts.libraryGetMethods()")
    public void beforeLibraryReturnAndGetMethodsAdvice(){
        System.out.println("beforeLibraryReturnAndGetMethodsAdvice: вызов get или return в Library");
    }

    @Before("github.nikhrom.javatraining.aop.introduction.aspect.CommonLibraryPointcuts.allLibraryMethods() && " +
            "!github.nikhrom.javatraining.aop.introduction.aspect.CommonLibraryPointcuts.libraryGetMethods()")
    public void beforeAllLibraryMethodsExceptGetAdvice(){
        System.out.println("beforeAllLibraryMethodsExceptGet: вызов какого-то метода в Library, который не get");
    }
}
