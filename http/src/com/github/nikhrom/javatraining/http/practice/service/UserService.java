package com.github.nikhrom.javatraining.http.practice.service;

import com.github.nikhrom.javatraining.http.practice.dao.UserDao;
import com.github.nikhrom.javatraining.http.practice.dto.CreateUserDto;
import com.github.nikhrom.javatraining.http.practice.dto.UserDto;
import com.github.nikhrom.javatraining.http.practice.dto.UserFilter;
import com.github.nikhrom.javatraining.http.practice.entity.User;
import com.github.nikhrom.javatraining.http.practice.exception.ValidationException;
import com.github.nikhrom.javatraining.http.practice.mapper.CreateUserMapper;
import com.github.nikhrom.javatraining.http.practice.mapper.UserMapper;
import com.github.nikhrom.javatraining.http.practice.validator.CreateUserValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import javax.servlet.http.Part;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();

    public Optional<UserDto> login(String email, String password){
        return userDao.findByEmailAndPassword(email, password)
                .stream()
                .findFirst()
                .map(userMapper::mapFrom);
    }

    public Integer saveUser(CreateUserDto userDto) {
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = createUserMapper.mapFrom(userDto);
        imageService.upload(userEntity.getImage().orElse(null), userDto.getImage().map(this::getInputStreamFromPart).orElse(null));
        var save = userDao.save(userEntity);
        return save.getId();
    }

    @SneakyThrows
    private InputStream getInputStreamFromPart(Part part){
        return part.getInputStream();
    }


    public static UserService getInstance() {
        return INSTANCE;
    }
}
