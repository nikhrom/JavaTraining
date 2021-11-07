package github.nikhrom.javatraining.junit.service;

import github.nikhrom.javatraining.junit.dto.UserDto;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_METHOD;

@TestInstance(PER_METHOD)
class UserServiceTest {

    private UserService userService;
    private static UserDto IVAN = UserDto.builder()
            .email("ivan@mail.ru")
            .password("123")
            .build();
    private static UserDto ALEX = UserDto.builder()
            .email("alex@mail.ru")
            .password("example")
            .build();

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
    void loginSuccessIfUserExists(){
        UserDto userDto = UserDto.builder()
                .email(IVAN.getEmail())
                .password(IVAN.getPassword())
                .build();
        userService.add(userDto);

        Optional<UserDto> maybeUser = userService.login(userDto);
        assertTrue(maybeUser.isPresent());

        maybeUser.ifPresent(user -> assertEquals(IVAN, user));
    }

    @Test
    void loginFailureIfPasswordIsIncorrect(){
        UserDto userDto = UserDto.builder()
                .email(IVAN.getEmail())
                .password("wrong")
                .build();
        userService.add(userDto);

        Optional<UserDto> maybeUser = userService.login(userDto);
        assertTrue(maybeUser.isPresent());
    }

    @Test
    void loginFailureIfEmailIsIncorrect(){
        UserDto userDto = UserDto.builder()
                .email("wrong")
                .password(IVAN.getPassword())
                .build();
        userService.add(userDto);

        Optional<UserDto> maybeUser = userService.login(userDto);
        assertTrue(maybeUser.isPresent());
    }

    @Test
    void usersSizeIfUserAdded(){
        System.out.println("Test2: " + this);
        userService.add(ALEX);
        userService.add(IVAN);

        assertEquals(2, userService.getAll().size());
    }

    @AfterEach
    void deleteDataFromDB(){
        System.out.println("AfterEach: " + this);
    }
}
