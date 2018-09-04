package com.andreitop.newco.controller;

import com.andreitop.newco.dto.IDto;
import com.andreitop.newco.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public abstract class GenericRestControllerImpl<E extends IDto, K> implements IGenericRestController<E, K> {

    private IGenericService<E, K> service;

    @Autowired
    public GenericRestControllerImpl(IGenericService<E, K> service) {
        this.service = service;
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody E entity) {
        service.save(entity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> update(@RequestBody E entity) {
        service.update(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteById(@PathVariable("id") K id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<E> findById(@PathVariable("id") K id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<E>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }
}
