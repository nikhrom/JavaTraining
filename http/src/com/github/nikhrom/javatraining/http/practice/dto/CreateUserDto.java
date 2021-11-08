package com.github.nikhrom.javatraining.http.practice.dto;

import lombok.Builder;
import lombok.Value;

import javax.servlet.http.Part;
import java.util.Optional;

@Value
@Builder
public class CreateUserDto {
    String name;
    String birthday;
    String email;
    String password;
    String role;
    String gender;
    Part image;

    public Optional<Part> getImage() {
        return Optional.ofNullable(image);
    }
}
