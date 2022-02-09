package github.nikhrom.javatraining.advanced_hibernate.dao;

import github.nikhrom.javatraining.advanced_hibernate.entity.Company;
import github.nikhrom.javatraining.advanced_hibernate.entity.Payment;
import org.hibernate.SessionFactory;

public class CompanyRepository extends AbstractRepository<Integer, Company> {

    private static CompanyRepository INSTANCE;

    private CompanyRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Company.class);
    }

    public static CompanyRepository getInstance(SessionFactory sessionFactory){
        if(INSTANCE == null){
            INSTANCE = new CompanyRepository(sessionFactory);
        }
        return INSTANCE;
    }
}
