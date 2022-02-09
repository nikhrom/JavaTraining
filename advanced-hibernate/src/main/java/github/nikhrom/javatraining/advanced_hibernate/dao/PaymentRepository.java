package github.nikhrom.javatraining.advanced_hibernate.dao;

import github.nikhrom.javatraining.advanced_hibernate.entity.Payment;
import org.hibernate.SessionFactory;

public class PaymentRepository extends AbstractRepository<Long, Payment> {

    private static PaymentRepository INSTANCE;

    private PaymentRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Payment.class);
    }

    public static PaymentRepository getInstance(SessionFactory sessionFactory){
        if(INSTANCE == null){
            INSTANCE = new PaymentRepository(sessionFactory);
        }
        return INSTANCE;
    }
}
