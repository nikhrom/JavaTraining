package github.nikhrom.javatraining.spring.mvc_hibernate.dao;

import github.nikhrom.javatraining.spring.mvc_hibernate.entity.Department;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentDao implements Dao<Integer, Department>{
    private final SessionFactory sessionFactory;

    @Autowired
    private DepartmentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Department> get(Integer id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public List<Department> getAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.createQuery("from Department", Department.class)
                .getResultList();
    }

    @Override
    public void save(Department value) {

    }

    @Override
    public void update(Department value) {

    }

    @Override
    public void delete(Department value) {

    }
}
