package com.andreitop.newco.service;

import java.util.List;

public interface IGenericService<E, K> {
    void save(final E entity);

    void update(final E entity);

    void deleteById(final K id);

    E findById(final K key);

    List<E> findAll();
}
