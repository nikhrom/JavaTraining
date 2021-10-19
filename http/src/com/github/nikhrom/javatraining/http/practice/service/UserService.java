package com.github.nikhrom.javatraining.http.practice.service;

import com.github.nikhrom.javatraining.http.practice.dao.UserDao;
import com.github.nikhrom.javatraining.http.practice.dto.CreateUserDto;
import com.github.nikhrom.javatraining.http.practice.exception.ValidationException;
import com.github.nikhrom.javatraining.http.practice.mapper.CreateUserMapper;
import com.github.nikhrom.javatraining.http.practice.validator.CreateUserValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import javax.servlet.http.Part;
import java.io.InputStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();

    public Integer saveUser(CreateUserDto userDto) {
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = createUserMapper.mapFrom(userDto);
        imageService.upload(userEntity.getImage(), userDto.getImage().map(this::getInputStreamFromPart));
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
