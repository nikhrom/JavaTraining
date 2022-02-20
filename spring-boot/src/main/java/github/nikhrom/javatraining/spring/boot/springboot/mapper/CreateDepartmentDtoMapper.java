package github.nikhrom.javatraining.spring.boot.springboot.mapper;

import github.nikhrom.javatraining.spring.boot.springboot.dto.CreateDepartmentDto;
import github.nikhrom.javatraining.spring.boot.springboot.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class CreateDepartmentDtoMapper implements Mapper<CreateDepartmentDto, Department>{

    @Override
    public Department mapFrom(CreateDepartmentDto object) {
        return Department.builder()
                .name(object.getName())
                .maxSalary(Double.valueOf(object.getMaxSalary()))
                .minSalary(Double.valueOf(object.getMinSalary()))
                .build();
    }
}
