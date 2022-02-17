package github.nikhrom.javatraining.spring.boot.springboot.repository;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractRepository<K extends Serializable, E>
        implements Repository<K, E> {

    private final EntityManager entityManager;
    private final Class<E> clazz;


    @Override
    public Optional<E> get(K id) {
        return Optional.ofNullable(entityManager
                .find(clazz, id));
    }

    @Override
    public E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void update(E entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(K id) {
        get(id).ifPresent(entityManager::detach);
        entityManager.flush();
    }
}
