package com.andreitop.newco.service;

import com.andreitop.newco.dto.IDto;
import com.andreitop.newco.repository.IGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class GenericServiceImpl<E extends IDto, K> implements IGenericService<E, K> {

    private IGenericRepository<E, K> repository;

    @Autowired
    public GenericServiceImpl(IGenericRepository<E, K> repository) {
        this.repository = repository;
    }

    @Override
    public void save(E entity) {
        repository.save(entity);
    }

    @Override
    public void update(E entity) {
        repository.update(entity);
    }

    @Override
    public void deleteById(K id) {
        repository.deleteById(id);
    }

    @Override
    public E findById(K id) {
        return repository.findById(id);
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }
}
