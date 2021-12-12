package github.nikhrom.javatraining.spring.mvc.introduction.dto;

import github.nikhrom.javatraining.spring.mvc.introduction.validation.CheckEmail;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDto implements Serializable{
    @Min(18)
    @Max(100)
    int age;
    @NotBlank
    String name;
    @NotBlank
    String lastName;
    @NotBlank
    String department;
    @NotBlank
    String gender;
    @CheckEmail
    String email;
    @NotEmpty
    String[] preferLanguages;
}
