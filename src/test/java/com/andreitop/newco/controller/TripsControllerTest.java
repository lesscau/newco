package com.andreitop.newco.controller;

import com.andreitop.newco.common.ApiConstant;
import com.andreitop.newco.dto.TripDto;
import com.andreitop.newco.service.TripService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TripsController.class)
public class TripsControllerTest {

    private static final String TRIP_JSON = "{\"origin\": \"LED\" , \"destination\":\"MOW\", \"price\" : 12256}";
    private static final String UPDATE_TRIP_JSON = "{\"id\": 1 ,\"origin\": \"LED\" , \"destination\":\"MOW\", \"price\" : 12256}";
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final String API_URL = ApiConstant.API_V_1 + "/trips";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripService tripService;

    @Before
    public void setup() {
        TripDto tripDto = new TripDto(1L, "MOW", "LED", 4232);

        List<TripDto> allTrips = Collections.singletonList(tripDto);
        given(tripService.findAll()).willReturn(allTrips);

        given(tripService.findById(tripDto.getId())).willReturn(tripDto);
    }

    @Test
    public void whenPostTrip_thenCreateTrip() throws Exception {
        mockMvc.perform(post(API_URL)
                .contentType(CONTENT_TYPE)
                .content(TRIP_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void givenTrip_whenGetTripById_thenReturnJson() throws Exception {
        mockMvc.perform(get(API_URL + "/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.origin", is("MOW")))
                .andExpect(jsonPath("$.destination", is("LED")))
                .andExpect(jsonPath("$.price", is(4232)))
                .andDo(print());
        verify(tripService, times(1)).findById(1L);
        verifyNoMoreInteractions(tripService);
    }

    @Test
    public void whenPutTrip_thenUpdateTrip() throws Exception {
        mockMvc.perform(put(API_URL)
                .contentType(CONTENT_TYPE)
                .content(UPDATE_TRIP_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void givenTrips_whenGetTrips_thenReturnJsonArray() throws Exception {
        mockMvc.perform(get(API_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].origin", is("MOW")))
                .andExpect(jsonPath("$[0].destination", is("LED")))
                .andExpect(jsonPath("$[0].price", is(4232)))
                .andDo(print());
        verify(tripService, times(1)).findAll();
        verifyNoMoreInteractions(tripService);
    }

    @Test
    public void givenTrip_whenDeleteTripById_thenDeleteTrip() throws Exception {

        mockMvc.perform(delete(API_URL + "/{id}", 1))
                .andExpect(status().isNoContent())
                .andDo(print());

        verify(tripService, times(1)).deleteById(1L);
        verifyNoMoreInteractions(tripService);
    }
}