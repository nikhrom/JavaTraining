package github.nikhrom.javatraining.spring.mvc_hibernate.service;

import github.nikhrom.javatraining.spring.mvc_hibernate.dao.DepartmentDao;
import github.nikhrom.javatraining.spring.mvc_hibernate.dto.CreateDepartmentDto;
import github.nikhrom.javatraining.spring.mvc_hibernate.dto.DepartmentDto;
import github.nikhrom.javatraining.spring.mvc_hibernate.entity.Department;
import github.nikhrom.javatraining.spring.mvc_hibernate.mapper.CreateDepartmentDtoMapper;
import github.nikhrom.javatraining.spring.mvc_hibernate.mapper.DepartmentMapper;
import github.nikhrom.javatraining.spring.mvc_hibernate.mapper.DepartmentDtoMapper;
import github.nikhrom.javatraining.spring.mvc_hibernate.exception.NoSuchDepartmentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component

@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentDao departmentDao;
    private final DepartmentMapper departmentMapper;
    private final DepartmentDtoMapper departmentDtoMapper;
    private final CreateDepartmentDtoMapper createDepartmentDtoMapper;

    @Transactional
    public List<DepartmentDto> getAllDepartments(){
       return departmentDao.getAll()
               .stream()
               .map(departmentMapper::mapFrom)
               .collect(Collectors.toList());
    };

    @Transactional
    public DepartmentDto addDepartment(CreateDepartmentDto departmentDto){
        var department = createDepartmentDtoMapper.mapFrom(departmentDto);
        departmentDao.save(department);
        return departmentMapper.mapFrom(department);
    }

    @Transactional
    public DepartmentDto getDepartmentById(Integer id){
        Department department = departmentDao.get(id)
                .orElseThrow(() -> new NoSuchDepartmentException("Department with id = " + id +
                        " doesn't exist"));

        return departmentMapper.mapFrom(department);
    }

    @Transactional
    public DepartmentDto updateDepartment(DepartmentDto departmentDto){
        var maybeDepartment = departmentDao.get(departmentDto.getId());
        if(maybeDepartment.isEmpty())
            throw new NoSuchDepartmentException(
                    "Department with id = " + departmentDto.getId() +
                     "doesn't exist"
            );

        var department = departmentDtoMapper.mapFrom(departmentDto);
        departmentDao.update(department);
        return departmentMapper.mapFrom(department);
    }

    @Transactional
    public DepartmentDto deleteDepartmentById(Integer id){
        var department = Department.builder().id(id).build();
        departmentDao.delete(department);
        return departmentMapper.mapFrom(department);
    }
}
