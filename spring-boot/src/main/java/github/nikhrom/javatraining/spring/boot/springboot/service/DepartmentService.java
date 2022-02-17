package github.nikhrom.javatraining.spring.boot.springboot.service;

import github.nikhrom.javatraining.spring.boot.springboot.dto.CreateDepartmentDto;
import github.nikhrom.javatraining.spring.boot.springboot.dto.ReadDepartmentDto;
import github.nikhrom.javatraining.spring.boot.springboot.dto.UpdateDepartmentDto;
import github.nikhrom.javatraining.spring.boot.springboot.entity.Department;
import github.nikhrom.javatraining.spring.boot.springboot.mapper.CreateDepartmentDtoMapper;
import github.nikhrom.javatraining.spring.boot.springboot.mapper.ReadDepartmentDtoMapper;
import github.nikhrom.javatraining.spring.boot.springboot.mapper.UpdateDepartmentDtoMapper;
import github.nikhrom.javatraining.spring.boot.springboot.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final CreateDepartmentDtoMapper createDepartmentDtoMapper;
    private final ReadDepartmentDtoMapper readDepartmentDtoMapper;
    private final UpdateDepartmentDtoMapper updateDepartmentDtoMapper;

    public List<ReadDepartmentDto> getAllDepartments() {
        return departmentRepository.getAll()
                .stream()
                .map(readDepartmentDtoMapper::mapFrom)
                .collect(toList());
    }

    public ReadDepartmentDto addDepartment(CreateDepartmentDto departmentDto) {
        var department = createDepartmentDtoMapper.mapFrom(departmentDto);
        var savedDepartment = departmentRepository.save(department);
        return readDepartmentDtoMapper.mapFrom(savedDepartment);
    }

    public ReadDepartmentDto getDepartmentById(Integer id) {
        Department department = departmentRepository.get(id)
                .orElseThrow(() -> new RuntimeException("Department with id = " + id +
                        " doesn't exist"));

        return readDepartmentDtoMapper.mapFrom(department);
    }

    public ReadDepartmentDto updateDepartment(UpdateDepartmentDto updateDepartmentDto) {
        var maybeDepartment = departmentRepository.get(updateDepartmentDto.getId());
        maybeDepartment.orElseThrow(() -> new RuntimeException(
                "Department with id = " + updateDepartmentDto.getId() +
                        "doesn't exist"
        ));

        var department = updateDepartmentDtoMapper.mapFrom(updateDepartmentDto);
        departmentRepository.update(department);
        return readDepartmentDtoMapper.mapFrom(department);
    }

    public int deleteDepartmentById(Integer id) {
        var department = departmentRepository.get(id).
                orElseThrow(() -> new RuntimeException(
                        "Department with id = " + id +
                                "doesn't exist"
                ));

        departmentRepository.delete(department.getId());
        return department.getId();
    }


}
