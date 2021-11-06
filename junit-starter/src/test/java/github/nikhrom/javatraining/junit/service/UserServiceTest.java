package github.nikhrom.javatraining.junit.service;

import github.nikhrom.javatraining.junit.dto.UserDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Test
    void usersEmptyIfNoUserAdded(){
        UserService userService = new UserService();
        List<UserDto> all = userService.getAll();
        assertFalse(all.isEmpty(), () -> "list should be empty");
    }

}
