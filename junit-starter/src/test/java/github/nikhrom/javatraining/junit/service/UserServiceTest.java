package github.nikhrom.javatraining.junit.service;

import github.nikhrom.javatraining.junit.dao.UserDao;
import github.nikhrom.javatraining.junit.dto.UserDto;
import github.nikhrom.javatraining.junit.paramresolver.UserServiceParameterResolver;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_METHOD;

@Tag("fast")
@Tag("user")
@TestInstance(PER_METHOD)
@ExtendWith({
        UserServiceParameterResolver.class
})
class UserServiceTest {


    private UserDao userDao;
    private UserService userService;
    private static final UserDto IVAN = UserDto.builder()
            .email("ivan@mail.ru")
            .password("123")
            .id(1)
            .build();
    private static final UserDto ALEX = UserDto.builder()
            .email("alex@mail.ru")
            .password("example")
            .id(2)
            .build();

    @BeforeEach
    void prepare(){
        System.out.println("BeforeEach: " + this);
        this.userDao = Mockito.mock(UserDao.class);
        this.userService = new UserService(this.userDao);
    }

    @Test
    void shouldDeleteExistedUser(){
        userService.add(IVAN);
        Mockito.doReturn(true).when(userDao).delete(IVAN.getId());
        var deleteResult = userService.delete(IVAN.getId());

        assertThat(deleteResult).isTrue();
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

        @ParameterizedTest
        @MethodSource({
                "github.nikhrom.javatraining.junit.service.UserServiceTest#argumentsForLogin"
        })
        void loginParameterizedTest(String email, String password, Optional<UserDto> expectedUser){
            expectedUser.ifPresent(user -> userService.add(user));

            UserDto userDto = UserDto.builder()
                    .email(email)
                    .password(password)
                    .build();

            Optional<UserDto> maybeUser = userService.login(userDto);
            assertThat(maybeUser).isEqualTo(expectedUser);
        }

    }

    static Stream<Arguments> argumentsForLogin(){
        return Stream.of(
                Arguments.of(IVAN.getEmail(), IVAN.getPassword(), Optional.of(IVAN)),
                Arguments.of("dummy", IVAN.getPassword(), Optional.empty()),
                Arguments.of(IVAN.getEmail(), "dummy", Optional.empty()),
                Arguments.of("dummy", "dummy", Optional.empty())
        );
    }

    @AfterEach
    void deleteDataFromDB(){
        System.out.println("AfterEach: " + this);
    }
}
