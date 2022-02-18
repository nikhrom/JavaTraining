package github.nikhrom.javatraining.spring.boot.springboot.repository;

import github.nikhrom.javatraining.spring.boot.springboot.entity.Department;
import github.nikhrom.javatraining.spring.boot.springboot.entity.QDepartment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class DepartmentRepository extends AbstractRepository<Integer, Department> {

    public DepartmentRepository(EntityManager entityManager) {
        super(entityManager, QDepartment.department, Department.class);
    }
}