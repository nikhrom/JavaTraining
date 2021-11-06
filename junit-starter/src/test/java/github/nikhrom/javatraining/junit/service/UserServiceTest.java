package github.nikhrom.javatraining.junit.service;

import github.nikhrom.javatraining.junit.dto.UserDto;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
class UserServiceTest {

    private UserService userService;

    @BeforeAll
    void init(){
        System.out.println("BeforeAll: " + this);
    }

    @BeforeEach
    void prepare(){
        System.out.println("BeforeEach: " + this);
        userService = new UserService();
    }

    @Test
    void usersEmptyIfNoUserAdded(){
        System.out.println("Test1: " + this);
        List<UserDto> all = userService.getAll();
        assertTrue(all.isEmpty(), () -> "list should be empty");
    }


    @Test
    void usersSizeIfUserAdded(){
        System.out.println("Test2: " + this);
        userService.add(new UserDto());
        userService.add(new UserDto());

        assertEquals(2, userService.getAll().size());
    }

    @AfterEach
    void deleteDataFromDB(){
        System.out.println("AfterEach: " + this);
    }

    @AfterAll
    void closeConnectionPool(){
        System.out.println("AfterAll: " + this);
    }
}
