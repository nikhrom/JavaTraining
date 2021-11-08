package com.github.nikhrom.javatraining.http.practice.dto;

import com.github.nikhrom.javatraining.http.practice.entity.Gender;
import com.github.nikhrom.javatraining.http.practice.entity.UserRole;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.Optional;

@Value
@Builder
public class UserDto {
    Integer id;
    String name;
    LocalDate birthday;
    String email;
    String password;
    UserRole role;
    Gender gender;
    String image;

    public Optional<String> getImage(){
        return Optional.ofNullable(image);
    }
}
