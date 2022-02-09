package github.nikhrom.javatraining.advanced_hibernate.dao;

import com.querydsl.jpa.impl.JPAQuery;
import github.nikhrom.javatraining.advanced_hibernate.entity.BaseEntity;
import github.nikhrom.javatraining.advanced_hibernate.entity.QPayment;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractRepository<K extends Serializable, E extends BaseEntity<K>>
        implements Repository<K, E> {

    private final SessionFactory sessionFactory;
    private final Class<E> clazz;

    @Override
    public E save(E entity) {
        @Cleanup var session = sessionFactory.openSession();
        session.save(entity);
        return entity;
    }

    @Override
    public void deleteById(K id) {
        @Cleanup var session = sessionFactory.openSession();
        var entity = session.find(clazz, id);
        session.delete(entity);
        session.flush();
    }

    @Override
    public void update(E entity) {
        @Cleanup var session = sessionFactory.openSession();
        session.merge(entity);
    }

    @Override
    public Optional<E> findById(K id) {
        @Cleanup var session = sessionFactory.openSession();
        return Optional.ofNullable(session.find(clazz, id));
    }

    @Override
    public List<E> findAll() {
        @Cleanup var session = sessionFactory.openSession();
        return new JPAQuery<E>(session)
                .from(QPayment.payment)
                .fetch();
    }
}
