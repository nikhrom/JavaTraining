package github.nikhrom.javatraining.aop.introduction;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("universityBean")
public class University {

    @Getter
    private final List<Student> students = new ArrayList<>();

    public void addStudents(Student... students){
        this.students.addAll(List.of(students));
    };

    // Method for check @AfterThrowing advice work
    public Student getStudent(int index){
        return students.get(index);
    }
}
