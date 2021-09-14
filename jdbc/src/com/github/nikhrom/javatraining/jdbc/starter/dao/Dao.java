package com.github.nikhrom.javatraining.jdbc.starter.dao;

import com.github.nikhrom.javatraining.jdbc.starter.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface Dao<K, V>{

    List<V> findAll();

    Optional<V> findById(K id);

    void update(V entity);

    V save(V entity);

    boolean delete(K id);
}
