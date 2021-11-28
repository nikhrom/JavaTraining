package github.nikhrom.javatraining.hibernate.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Entity
@Table(name = "department")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Department {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "min_salary", nullable = false)
    private int minSalary;

    @Column(name = "max_salary", nullable = false)
    private int maxSalary;


    @OneToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH},
            mappedBy = "department"
    )
    @ToString.Exclude
    private List<Employee> employees;


    public void addEmployees(Employee... employees) {
        if (Objects.isNull(this.employees)) {
            this.employees = new ArrayList<>();
        }

        Stream.of(employees)
                .forEach(employee -> {
                    this.employees.add(employee);
                    employee.setDepartment(this);
                });
    }
}
