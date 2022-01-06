package github.nikhrom.javatraining.spring.mvc_hibernate.mapper;

import github.nikhrom.javatraining.spring.mvc_hibernate.dto.DepartmentDto;
import github.nikhrom.javatraining.spring.mvc_hibernate.entity.Department;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DepartmentDtoMapper implements Mapper<DepartmentDto, Department>{
    @Override
    public Department mapFrom(DepartmentDto object) {
        return Department.builder()
                .id(object.getId())
                .name(object.getName())
                .maxSalary(new BigDecimal(object.getMaxSalary()))
                .minSalary(new BigDecimal(object.getMinSalary()))
                .build();
    }
}
