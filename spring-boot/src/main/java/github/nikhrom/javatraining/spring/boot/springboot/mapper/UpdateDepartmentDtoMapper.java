package github.nikhrom.javatraining.spring.boot.springboot.mapper;

import github.nikhrom.javatraining.spring.boot.springboot.dto.UpdateDepartmentDto;
import github.nikhrom.javatraining.spring.boot.springboot.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class UpdateDepartmentDtoMapper implements Mapper<UpdateDepartmentDto, Department> {
    @Override
    public Department mapFrom(UpdateDepartmentDto object) {
        return Department.builder()
                .id(object.getId())
                .name(object.getName())
                .maxSalary(Double.valueOf(object.getMaxSalary()))
                .minSalary(Double.valueOf(object.getMinSalary()))
                .build();
    }
}
