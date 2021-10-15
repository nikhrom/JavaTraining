package com.github.nikhrom.javatraining.http.practice.service;

import com.github.nikhrom.javatraining.http.practice.dao.UserDao;
import com.github.nikhrom.javatraining.http.practice.dto.CreateUserDto;
import com.github.nikhrom.javatraining.http.practice.entity.User;
import com.github.nikhrom.javatraining.http.practice.entity.UserRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService {
    static final UserService INSTANCE = new UserService();
    static final UserDao USER_DAO = UserDao.getInstance();

    public boolean saveUser(CreateUserDto userDto) {
        try {
            USER_DAO.save(buildUser(userDto));
            return true;
        }catch (Exception exception){
            exception.printStackTrace();
            return false;
        }
    }

    private User buildUser(CreateUserDto userDto) {
        return User.builder()
                .birthday(LocalDate.parse(userDto.getBirthday()))
                .name(userDto.getName())
                .email(userDto.getName())
                .gender(userDto.getGender())
                .role(UserRole.valueOf(userDto.getRole()))
                .password(userDto.getPassword())
                .build();
    }

    ;

    public static UserService getInstance() {
        return INSTANCE;
    }
}
