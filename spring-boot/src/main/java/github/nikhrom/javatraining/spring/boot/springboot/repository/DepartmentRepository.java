package github.nikhrom.javatraining.spring.boot.springboot.repository;

import com.querydsl.jpa.impl.JPAQuery;
import github.nikhrom.javatraining.spring.boot.springboot.entity.Department;
import github.nikhrom.javatraining.spring.boot.springboot.entity.QDepartment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class DepartmentRepository extends AbstractRepository<Integer, Department> {

    private final EntityManager entityManager;

    public DepartmentRepository(EntityManager entityManager) {
        super(entityManager, Department.class);
        this.entityManager = entityManager;
    }

    @Override
    public List<Department> getAll() {
        return new JPAQuery<Department>(entityManager)
                .from(QDepartment.department)
                .fetch();
    }

}