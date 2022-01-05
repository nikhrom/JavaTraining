package github.nikhrom.javatraining.spring.mvc_hibernate.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, V>{
    Optional<V> get(K id);
    List<V> getAll();
    void save(V value);
    void update(V value);
    void delete(V value);
}
