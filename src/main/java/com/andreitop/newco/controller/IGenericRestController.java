package com.andreitop.newco.controller;

import java.util.List;

public interface IGenericRestController<E, K> {
    void create(final E entity);

    void update(final E entity);

    void deleteById(final K id);

    E findById(final K key);

    List<E> findAll();
}
