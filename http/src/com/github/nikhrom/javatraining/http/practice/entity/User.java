package com.github.nikhrom.javatraining.http.practice.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Optional;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class User {
    Integer id;
    String name;
    LocalDate birthday;
    String email;
    String password;
    UserRole role;
    Gender gender;
    Optional<String> image = Optional.empty();
}
