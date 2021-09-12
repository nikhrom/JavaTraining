package com.github.nikhrom.javatraining.jdbc.starter.exception;

public class DaoException extends RuntimeException{

    public DaoException(Throwable exception) {
        super(exception);
    }
}
