package github.nikhrom.javatraining.spring.boot.springboot.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository<K extends Serializable, E> {

    List<E> getAll();

    Optional<E> get(K id);
    E save(E entity);
    void update(E entity);
    void delete(K id);
}
