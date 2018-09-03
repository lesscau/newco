package com.andreitop.newco.controller;

import com.andreitop.newco.common.ApiConstant;
import com.andreitop.newco.dto.TripDto;
import com.andreitop.newco.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstant.API_V_1 + "/trips")
public class TripsController extends GenericRestControllerImpl<TripDto, Long> {

    @Autowired
    public TripsController(TripService service) {
        super(service);
    }

}
