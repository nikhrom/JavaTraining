package github.nikhrom.javatraining.advanced_hibernate.dao;

import github.nikhrom.javatraining.advanced_hibernate.entity.Company;
import github.nikhrom.javatraining.advanced_hibernate.entity.Payment;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;

public class CompanyRepository extends AbstractRepository<Integer, Company> {

    public CompanyRepository(EntityManager entityManager) {
        super(entityManager, Company.class);
    }

}
