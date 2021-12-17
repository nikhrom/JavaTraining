package github.nikhrom.javatraining.spring.mvc_hibernate.service;

import github.nikhrom.javatraining.spring.mvc_hibernate.dao.Dao;
import github.nikhrom.javatraining.spring.mvc_hibernate.dao.DepartmentDao;
import github.nikhrom.javatraining.spring.mvc_hibernate.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DepartmentService {

    @Autowired
    private Dao<Integer, Department> departmentDao;

    public List<Department> getAllDepartments(){
       return departmentDao.getAll();
    };

}
