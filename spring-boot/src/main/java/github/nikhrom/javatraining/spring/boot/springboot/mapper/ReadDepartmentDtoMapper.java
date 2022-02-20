package github.nikhrom.javatraining.spring.boot.springboot.mapper;

import github.nikhrom.javatraining.spring.boot.springboot.dto.ReadDepartmentDto;
import github.nikhrom.javatraining.spring.boot.springboot.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class ReadDepartmentDtoMapper implements Mapper<Department, ReadDepartmentDto> {
    @Override
    public ReadDepartmentDto mapFrom(Department object) {
        return ReadDepartmentDto.builder()
                .id(object.getId())
                .name(object.getName())
                .maxSalary(object.getMaxSalary().toString())
                .minSalary(object.getMinSalary().toString())
                .build();
    }
}
