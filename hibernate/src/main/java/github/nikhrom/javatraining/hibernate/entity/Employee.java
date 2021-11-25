package github.nikhrom.javatraining.hibernate.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Optional;


@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Builder.Default
    private Integer id = 0;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "department")
    private String department;

    @Column(name = "salary")
    private Integer salary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detail_id")
    private Detail detail;

    public Optional<String> getDepartment() {
        return Optional.ofNullable(department);
    }

    public Optional<Integer> getSalary() {
        return Optional.ofNullable(salary);
    }
}
