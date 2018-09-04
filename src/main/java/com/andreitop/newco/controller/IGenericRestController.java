package com.andreitop.newco.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IGenericRestController<E, K> {
    ResponseEntity<Void> create(final E entity);

    ResponseEntity<Void> update(final E entity);

    ResponseEntity<Void> deleteById(final K id);

    ResponseEntity<E> findById(final K key);

    ResponseEntity<List<E>> findAll();
}
