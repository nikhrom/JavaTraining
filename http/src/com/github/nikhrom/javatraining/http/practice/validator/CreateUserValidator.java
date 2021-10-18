package com.github.nikhrom.javatraining.http.practice.validator;

import com.github.nikhrom.javatraining.http.practice.dao.UserDao;
import com.github.nikhrom.javatraining.http.practice.dto.CreateUserDto;
import com.github.nikhrom.javatraining.http.practice.entity.Gender;
import com.github.nikhrom.javatraining.http.practice.entity.UserRole;

public class CreateUserValidator implements Validator<CreateUserDto> {

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();
    private final UserDao userDao = UserDao.getInstance();

    @Override
    public ValidationResult isValid(CreateUserDto object) {
        ValidationResult validationResult = new ValidationResult();

        if(Gender.find(object.getGender()).isEmpty()){
            validationResult.add(Error.of("invalid.gender", "Invalid gender"));
        }

        if(UserRole.find(object.getRole()).isEmpty()){
            validationResult.add(Error.of("invalid.userRole", "Invalid user role"));
        }

        if(userDao.hasEmail(object.getEmail())){
            validationResult.add(Error.of("invalid.email", "Email already exists"));
        }

        return validationResult;
    }


    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
