package github.nikhrom.javatraining.spring.mvc_hibernate.rest_exception;

public class RestException extends RuntimeException{

    public RestException(String info){
        super(info);
    }
}
