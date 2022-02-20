package github.nikhrom.javatraining.advanced_hibernate.dao;

import github.nikhrom.javatraining.advanced_hibernate.entity.Payment;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;

public class PaymentRepository extends AbstractRepository<Long, Payment> {

    public PaymentRepository(EntityManager entityManager) {
        super(entityManager, Payment.class);
    }
}
