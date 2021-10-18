package com.github.nikhrom.javatraining.http.practice.service;

import com.github.nikhrom.javatraining.http.practice.dao.UserDao;
import com.github.nikhrom.javatraining.http.practice.dto.CreateUserDto;
import com.github.nikhrom.javatraining.http.practice.entity.User;
import com.github.nikhrom.javatraining.http.practice.entity.UserRole;
import com.github.nikhrom.javatraining.http.practice.exception.ValidationException;
import com.github.nikhrom.javatraining.http.practice.mapper.CreateUserMapper;
import com.github.nikhrom.javatraining.http.practice.validator.CreateUserValidator;
import com.github.nikhrom.javatraining.http.practice.validator.ValidationResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();

    public Integer saveUser(CreateUserDto userDto) {
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = createUserMapper.mapFrom(userDto);
        var save = userDao.save(userEntity);
        return save.getId();
    }



    public static UserService getInstance() {
        return INSTANCE;
    }
}
