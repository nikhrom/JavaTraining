package github.nikhrom.javatraining.spring.mvc_hibernate.service;

import com.sun.xml.bind.v2.util.StackRecorder;
import github.nikhrom.javatraining.spring.mvc_hibernate.dao.Dao;
import github.nikhrom.javatraining.spring.mvc_hibernate.dao.DepartmentDao;
import github.nikhrom.javatraining.spring.mvc_hibernate.dto.DepartmentDto;
import github.nikhrom.javatraining.spring.mvc_hibernate.entity.Department;
import github.nikhrom.javatraining.spring.mvc_hibernate.mapper.DepartmentDtoMapper;
import github.nikhrom.javatraining.spring.mvc_hibernate.mapper.DepartmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component

@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentDao departmentDao;
    private final DepartmentDtoMapper departmentDtoMapper;
    private final DepartmentMapper departmentMapper;

    @Transactional
    public List<DepartmentDto> getAllDepartments(){
       return departmentDao.getAll()
               .stream()
               .map(departmentDtoMapper::mapFrom)
               .collect(Collectors.toList());
    };

    @Transactional
    public void addDepartment(DepartmentDto departmentDto){
        departmentDao.save(departmentMapper.mapFrom(departmentDto));
    }
}
