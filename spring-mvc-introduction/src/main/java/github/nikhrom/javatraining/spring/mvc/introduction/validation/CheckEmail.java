package github.nikhrom.javatraining.spring.mvc.introduction.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckEmailValidator.class)
public @interface CheckEmail {
    String value() default "test.com";
    String message() default "email must ends with test.com";

    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
