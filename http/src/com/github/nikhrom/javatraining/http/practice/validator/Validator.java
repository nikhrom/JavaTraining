package com.github.nikhrom.javatraining.http.practice.validator;

public interface Validator<T> {

    ValidationResult isValid(T object);
}
