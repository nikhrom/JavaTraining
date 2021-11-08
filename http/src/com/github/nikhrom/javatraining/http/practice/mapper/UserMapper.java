package com.github.nikhrom.javatraining.http.practice.mapper;


import com.github.nikhrom.javatraining.http.practice.dto.UserDto;
import com.github.nikhrom.javatraining.http.practice.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<User, UserDto> {
    private static final UserMapper INSTANCE = new UserMapper();

    @Override
    public UserDto mapFrom(User object) {
        return UserDto.builder()
                .id(object.getId())
                .birthday(object.getBirthday())
                .email(object.getEmail())
                .gender(object.getGender())
                .image(object.getImage().orElse(null))
                .name(object.getName())
                .role(object.getRole())
                .build();
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}
