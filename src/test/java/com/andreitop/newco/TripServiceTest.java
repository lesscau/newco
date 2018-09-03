package com.andreitop.newco;

import com.andreitop.newco.dto.TripDto;
import com.andreitop.newco.repository.TripRepository;
import com.andreitop.newco.service.TripService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TripServiceTest {

    @Autowired
    private TripService tripService;

    @MockBean
    private TripRepository tripRepository;

    @Test
    public void testGetAllTrips() {
        List<TripDto> tripDtoList = new ArrayList<>();
        tripDtoList.add(new TripDto(1L, "MOW", "LED", 7777));
        tripDtoList.add(new TripDto(2L, "LED", "RIX", 1000));
        tripDtoList.add(new TripDto(3L, "LNC", "PRS", 3333));
        given(tripRepository.findAll()).willReturn(tripDtoList);

        List<TripDto> tripServiceAll = tripService.findAll();
        assertEquals(3, tripServiceAll.size());
    }

    @Test
    public void testGetTripById() {
        TripDto tripDto = new TripDto(1L, "MOW", "LED", 7777);
        given(tripRepository.findById(1L)).willReturn(tripDto);

        TripDto resultTrip = tripService.findById(1L);
        assertEquals(Optional.of(1L), Optional.ofNullable(resultTrip.getId()));
        assertEquals("MOW", resultTrip.getOrigin());
        assertEquals("LED", resultTrip.getDestination());
        assertEquals(Optional.of(7777), Optional.ofNullable(resultTrip.getPrice()));
    }

    @Test
    public void testSaveTrip() {
        TripDto tripDto = new TripDto(1L, "MOW", "LED", 7777);
        tripService.save(tripDto);
        verify(tripRepository, times(1)).save(tripDto);
    }

    @Test
    public void testUpdateTrip() {
        TripDto tripDto = new TripDto(1L, "MOW", "LED", 7777);
        tripService.update(tripDto);
        verify(tripRepository, times(1)).update(tripDto);
    }

    @Test
    public void testDeleteTrip() {
        tripService.deleteById(1L);
        verify(tripRepository, times(1)).deleteById(1L);
    }
}
