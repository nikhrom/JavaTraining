package com.github.nikhrom.javatraining.http.practice.validator;

import lombok.Value;

@Value(staticConstructor = "of")
public class Error {
    String code;
    String message;
}
