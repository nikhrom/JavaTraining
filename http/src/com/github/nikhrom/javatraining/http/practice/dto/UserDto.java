package com.github.nikhrom.javatraining.http.practice.dto;

import com.github.nikhrom.javatraining.http.practice.entity.Gender;
import com.github.nikhrom.javatraining.http.practice.entity.UserRole;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Optional;

public class UserDto {
    Integer id;
    String name;
    LocalDate birthday;
    String email;
    String password;
    UserRole role;
    Gender gender;
    String image;
}
