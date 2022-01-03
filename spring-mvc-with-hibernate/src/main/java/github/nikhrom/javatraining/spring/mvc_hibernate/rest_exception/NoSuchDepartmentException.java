package github.nikhrom.javatraining.spring.mvc_hibernate.rest_exception;

public class NoSuchDepartmentException extends RestException{

    public NoSuchDepartmentException(String info) {
        super(info);
    }
}
