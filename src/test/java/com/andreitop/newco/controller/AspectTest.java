package com.andreitop.newco.controller;

import com.andreitop.newco.dto.TripDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AspectTest {

    @Autowired
    private TripsController tripsController;

    private PrintStream sysOut;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        sysOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void revertStreams() {
        System.setOut(sysOut);
    }

    @Test
    public void whenDeletedMethodCalled_thenLogging() {
        tripsController.create(new TripDto());
        tripsController.delete(1L);
        assert (outContent.toString().matches("[\\s\\S]*TripsController.delete[\\s\\S]*was called right now[\\s\\S]*"));
        assert (outContent.toString().matches("[\\s\\S]*TripService.delete[\\s\\S]*was called right now[\\s\\S]*"));
        }

    @Test
    public void whenSentPostRequest_thenLogging() throws Exception {
        TripDto tripDto = new TripDto();
        tripDto.setId(1L);
        tripDto.setOrigin("MOW");
        tripDto.setDestination("LED");
        tripDto.setPrice(4232);
        tripsController.create(tripDto);
        assert (outContent.toString().matches("[\\s\\S]*TripService.save[\\s\\S]*was called right now[\\s\\S]*"));
        assert (outContent.toString().matches("[\\s\\S]*Post request[\\s\\S]*Controller[\\s\\S]*is about to be sent[\\s\\S]*"));
        assert (outContent.toString().matches("[\\s\\S]*Content of body:[\\s\\S]*MOW[\\s\\S]*LED[\\s\\S]*4232[\\s\\S]*"));
    }
}
