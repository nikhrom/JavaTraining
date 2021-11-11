package github.nikhrom.javatraining.junit.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    Integer id;
    String email;
    String password;
}
