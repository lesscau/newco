package com.andreitop.newco.repository;

import java.util.List;

public interface IGenericRepository<E, K> {
    void save(final E entity);

    void update(final E entity);

    void deleteById(final K id);

    E findById(final K key);

    List<E> findAll();
}
