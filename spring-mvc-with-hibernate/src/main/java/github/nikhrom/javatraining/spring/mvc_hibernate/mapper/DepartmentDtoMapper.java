package github.nikhrom.javatraining.spring.mvc_hibernate.mapper;

import github.nikhrom.javatraining.spring.mvc_hibernate.dto.DepartmentDto;
import github.nikhrom.javatraining.spring.mvc_hibernate.entity.Department;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class DepartmentDtoMapper implements Mapper<Department, DepartmentDto>{
    @Override
    public DepartmentDto mapFrom(Department object) {
        return DepartmentDto.builder()
                .name(object.getName())
                .maxSalary(object.getMaxSalary().toString())
                .minSalary(object.getMinSalary().toString())
                .build();
    }
}
