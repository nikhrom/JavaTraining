package github.nikhrom.javatraining.spring.mvc.introduction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto implements Serializable{
    int age;
    String name;
    String lastName;
    String department;
    String gender;
    String[] preferLanguages;
}
