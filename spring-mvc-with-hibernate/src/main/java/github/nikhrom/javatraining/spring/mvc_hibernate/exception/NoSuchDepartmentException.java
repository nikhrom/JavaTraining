package github.nikhrom.javatraining.spring.mvc_hibernate.exception;


public class NoSuchDepartmentException extends RestException{

    public NoSuchDepartmentException(String info) {
        super(info);
    }
}
