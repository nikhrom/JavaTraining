package github.nikhrom.javatraining.advanced_hibernate.dao;

import com.querydsl.jpa.impl.JPAQuery;
import github.nikhrom.javatraining.advanced_hibernate.entity.BaseEntity;
import github.nikhrom.javatraining.advanced_hibernate.entity.QPayment;
import lombok.Cleanup;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
public abstract class AbstractRepository<K extends Serializable, E extends BaseEntity<K>>
        implements Repository<K, E> {

    private final EntityManager entityManager;
    private final Class<E> clazz;

    @Override
    public E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void deleteById(K id) {
        var entity = entityManager.find(clazz, id);
        entityManager.remove(entity);
        entityManager.flush();
    }

    @Override
    public void update(E entity) {
        entityManager.merge(entity);
    }

    @Override
    public Optional<E> findById(K id, Map<String, Object> properties) {
        return Optional.ofNullable(entityManager.find(clazz, id, properties));
    }

    @Override
    public List<E> findAll() {
        return new JPAQuery<E>(entityManager)
                .from(QPayment.payment)
                .fetch();
    }
}
