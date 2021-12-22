package github.nikhrom.javatraining.spring.mvc_hibernate.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DaoLoggingAspect {


    @Around("within(github.nikhrom.javatraining.spring.mvc_hibernate.dao..*)")
    public Object allMethodsInAllDaos(ProceedingJoinPoint pjp) throws Throwable {

        var methodName = pjp.getSignature().toShortString();

        System.out.println(methodName + ": begin");
        var result = pjp.proceed();
        if(result != null)
            System.out.println(result.toString());
        System.out.println(methodName + ": end");

        return result;
    }

}
