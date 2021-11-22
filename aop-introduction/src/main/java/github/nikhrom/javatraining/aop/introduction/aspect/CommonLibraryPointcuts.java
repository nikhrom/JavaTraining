package github.nikhrom.javatraining.aop.introduction.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CommonLibraryPointcuts {
    @Pointcut("execution(* github.nikhrom.javatraining.aop.introduction.Library.get*())")
    public void libraryGetMethods(){};

    @Pointcut("execution(* github.nikhrom.javatraining.aop.introduction.Library.return*(..))")
    public void libraryReturnMethods(){};

    @Pointcut("execution(* github.nikhrom.javatraining.aop.introduction.Library.*(..))")
    public void allLibraryMethods(){};
}
