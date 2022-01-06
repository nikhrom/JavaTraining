package github.nikhrom.javatraining.spring.mvc_hibernate.exception;

public class RestException extends RuntimeException{

    public RestException(String info){
        super(info);
    }
}
