package github.nikhrom.javatraining.spring.mvc_hibernate.mapper;

import github.nikhrom.javatraining.spring.mvc_hibernate.dto.CreateDepartmentDto;
import github.nikhrom.javatraining.spring.mvc_hibernate.dto.DepartmentDto;
import github.nikhrom.javatraining.spring.mvc_hibernate.entity.Department;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CreateDepartmentDtoMapper implements Mapper<CreateDepartmentDto, Department>{
    @Override
    public Department mapFrom(CreateDepartmentDto object) {
        return Department.builder()
                .name(object.getName())
                .maxSalary(new BigDecimal(object.getMaxSalary()))
                .minSalary(new BigDecimal(object.getMinSalary()))
                .build();
    }
}
