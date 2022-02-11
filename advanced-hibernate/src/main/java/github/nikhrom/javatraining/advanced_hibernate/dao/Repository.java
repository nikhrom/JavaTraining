package github.nikhrom.javatraining.advanced_hibernate.dao;

import github.nikhrom.javatraining.advanced_hibernate.entity.BaseEntity;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Repository<K extends Serializable, E extends BaseEntity<K>> {

    E save(E entity);

    void deleteById(K id);

    void update(E entity);

    Optional<E> findById(K id, Map<String, Object> properties);

    default Optional<E> findById(K id){
        return findById(id, Collections.emptyMap());
    }

    List<E> findAll();
}
