package com.andreitop.newco.repository;

import com.andreitop.newco.dto.IDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public abstract class GenericRepositoryImpl<E extends IDto, K> implements IGenericRepository<E, K> {

    private final List<E> eList = new ArrayList<>();

    @Override
    public void save(E entity) {
        entity.setId((long) (eList.size() + 1));
        eList.add(entity);
    }

    @Override
    public void update(E entity) {
        eList.stream()
                .filter(t -> t.getId().equals(entity.getId()))
                .findFirst()
                .ifPresent(t -> eList.set(eList.indexOf(t), entity));
    }

    @Override
    public void deleteById(K id) {
        eList.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .ifPresent(eList::remove);
    }

    @Override
    public E findById(K id) {
        return eList.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<E> findAll() {
        return eList;
    }
}
