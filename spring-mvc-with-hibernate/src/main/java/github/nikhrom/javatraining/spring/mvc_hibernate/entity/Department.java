package github.nikhrom.javatraining.spring.mvc_hibernate.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "department")
@Data
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "min_salary", nullable = false)
    BigDecimal minSalary;

    @Column(name = "max_salary")
    BigDecimal maxSalary;
}
