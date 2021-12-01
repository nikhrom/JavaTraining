package github.nikhrom.javatraining.hibernate.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "local_phone")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocalPhone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "type")
    private String type;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "employee_phone",
            joinColumns = @JoinColumn(name = "local_phone_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    @ToString.Exclude
    private List<Employee> employees;

    public void addEmployee(Employee... employees){
        if(Objects.isNull(employees)) return;
        if(Objects.isNull(this.employees)){
            this.employees = Arrays.asList(employees);
            return;
        }

        this.employees.addAll(Arrays.asList(employees));
    };
}
