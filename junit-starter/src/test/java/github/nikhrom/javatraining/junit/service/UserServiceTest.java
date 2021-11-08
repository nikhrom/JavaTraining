package github.nikhrom.javatraining.junit.service;

import github.nikhrom.javatraining.junit.dto.UserDto;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_METHOD;

@Tag("fast")
@Tag("user")
@TestInstance(PER_METHOD)
class UserServiceTest {

    private UserService userService;
    private static final UserDto IVAN = UserDto.builder()
            .email("ivan@mail.ru")
            .password("123")
            .build();
    private static final UserDto ALEX = UserDto.builder()
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

        assertThat(all).isEmpty();
    }



    @Test
    void usersSizeIfUserAdded(){
        System.out.println("Test2: " + this);
        userService.add(ALEX);
        userService.add(IVAN);

        assertThat(userService.getAll()).hasSize(2);
    }

    @Nested
    @DisplayName("user login test functionality")
    @Tag("login")
    class LoginTest{
        @Test
        void loginSuccessIfUserExists(){
            UserDto userDto = UserDto.builder()
                    .email(IVAN.getEmail())
                    .password(IVAN.getPassword())
                    .build();
            userService.add(userDto);

            Optional<UserDto> maybeUser = userService.login(userDto);
            assertThat(maybeUser).isPresent();


            maybeUser.ifPresent(user -> assertThat(user).isEqualTo(IVAN));
        }

        @Test
        void loginFailureIfPasswordIsIncorrect(){
            UserDto userDto = UserDto.builder()
                    .email(IVAN.getEmail())
                    .password("wrong")
                    .build();
            userService.add(userDto);

            Optional<UserDto> maybeUser = userService.login(userDto);

            assertThat(maybeUser).isPresent();
        }

        @Test
        void loginFailureIfEmailIsIncorrect(){
            UserDto userDto = UserDto.builder()
                    .email("wrong")
                    .password(IVAN.getPassword())
                    .build();
            userService.add(userDto);

            Optional<UserDto> maybeUser = userService.login(userDto);

            assertThat(maybeUser).isPresent();
        }
    }


    @AfterEach
    void deleteDataFromDB(){
        System.out.println("AfterEach: " + this);
    }
}
