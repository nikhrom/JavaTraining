package github.nikhrom.javatraining.spring.mvc_hibernate.service;

import github.nikhrom.javatraining.spring.mvc_hibernate.dao.DepartmentDao;
import github.nikhrom.javatraining.spring.mvc_hibernate.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentService {

    private final DepartmentDao departmentDao;

    @Autowired
    private DepartmentService(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public List<Department> getAllDepartments(){
       return departmentDao.getAll();
    };

}
