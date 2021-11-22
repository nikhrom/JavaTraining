package github.nikhrom.javatraining.aop.introduction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
public class Student {
    private String fullName;
    private int course;
    private double avgGrade;
}
