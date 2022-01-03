package github.nikhrom.javatraining.spring.mvc_hibernate.rest_exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DepartmentExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<DepartmentIncorrectData> handleException(NoSuchDepartmentException exception){
        var departmentIncorrectData = new DepartmentIncorrectData(exception.getMessage());
        return new ResponseEntity<>(departmentIncorrectData, HttpStatus.NOT_FOUND);
    }

}
