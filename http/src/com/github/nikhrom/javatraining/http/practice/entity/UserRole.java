package com.github.nikhrom.javatraining.http.practice.entity;

import java.util.Arrays;
import java.util.Optional;

public enum UserRole {
    ADMIN, USER;

    public static Optional<UserRole> find(String userRole){
        return Arrays.stream(values())
                .filter(it -> it.name().equals(userRole))
                .findFirst();
    }
}
