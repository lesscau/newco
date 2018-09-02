package com.andreitop.newco.controller;

import com.andreitop.newco.dto.IDto;
import com.andreitop.newco.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public abstract class GenericRestControllerImpl<E extends IDto, K> implements IGenericRestController<E, K> {

    private IGenericService<E, K> service;

    @Autowired
    public GenericRestControllerImpl(IGenericService<E, K> service) {
        this.service = service;
    }

    public GenericRestControllerImpl() {
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody E entity) {
        service.save(entity);
    }

    @Override
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody E entity) {
        service.update(entity);
    }

    @Override
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") K id) {
        service.deleteById(id);
    }

    @Override
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public E findById(@PathVariable("id") K id) {
        return service.findById(id);
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<E> findAll() {
        return service.findAll();
    }
}
