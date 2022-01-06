package github.nikhrom.javatraining.spring.mvc.introduction.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckEmailValidator implements ConstraintValidator<CheckEmail, String> {

   private String endOfEmail;

   public void initialize(CheckEmail constraint) {
      endOfEmail = constraint.value();
   }

   public boolean isValid(String value, ConstraintValidatorContext context) {
      return value.endsWith(endOfEmail);
   }
}
