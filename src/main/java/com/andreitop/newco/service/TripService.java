package com.andreitop.newco.service;

import com.andreitop.newco.dto.TripDto;
import com.andreitop.newco.repository.IGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TripService extends GenericServiceImpl<TripDto, Long> {

    @Autowired
    public TripService(@Qualifier("tripRepository") IGenericRepository<TripDto, Long> repository) {
        super(repository);

    }

    public TripService() {
    }
}
