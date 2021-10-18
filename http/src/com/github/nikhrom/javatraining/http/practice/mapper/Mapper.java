package com.github.nikhrom.javatraining.http.practice.mapper;

@FunctionalInterface
public interface Mapper<F, T> {
    T mapFrom(F object);
}
