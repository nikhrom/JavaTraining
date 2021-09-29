package com.github.nikhrom.javatraining.http.practice.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, V>{

    List<V> findAll();
    V save(V entity);
    Optional<V> findById(K id);
    void update(V entity);
    boolean delete(K id);

}
