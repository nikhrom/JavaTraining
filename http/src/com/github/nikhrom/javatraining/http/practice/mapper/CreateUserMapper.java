package com.github.nikhrom.javatraining.http.practice.mapper;

import com.github.nikhrom.javatraining.http.practice.dto.CreateUserDto;
import com.github.nikhrom.javatraining.http.practice.entity.Gender;
import com.github.nikhrom.javatraining.http.practice.entity.User;
import com.github.nikhrom.javatraining.http.practice.entity.UserRole;
import com.github.nikhrom.javatraining.http.practice.util.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.servlet.http.Part;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserMapper implements Mapper<CreateUserDto, User>{

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();
    private static final String IMAGE_FOLDER = "users/";

    @Override
    public User mapFrom(CreateUserDto object) {
        var build = User.builder()
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .name(object.getName())
                .email(object.getEmail())
                .gender(Gender.valueOf(object.getGender()))
                .role(UserRole.valueOf(object.getRole()))
                .password(object.getPassword())
                .image(object.getImage()
                        .map(Part::getSubmittedFileName)
                        .filter(fileName -> !fileName.isEmpty())
                        .map(fileName -> IMAGE_FOLDER + fileName))
                .build();
        return build;
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
