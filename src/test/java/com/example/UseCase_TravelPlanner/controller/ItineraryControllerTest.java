package com.example.UseCase_TravelPlanner.controller;

import com.example.UseCase_TravelPlanner.entity.Activity;
import com.example.UseCase_TravelPlanner.entity.Itinerary;
import com.example.UseCase_TravelPlanner.service.ItineraryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItineraryControllerTest {

    @InjectMocks
    private ItineraryController itineraryController;

    @Mock
    private ItineraryService itineraryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllItineraries() {
        List<Itinerary> itineraries = new ArrayList<>();
        itineraries.add(new Itinerary());
        itineraries.add(new Itinerary());

        when(itineraryService.getAllItineraries()).thenReturn(itineraries);

        List<Itinerary> result = itineraryController.getAllItineraries();

        verify(itineraryService, times(1)).getAllItineraries();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void getItineraryById() {
        Itinerary mockActivity = new Itinerary();
        mockActivity.setItineraryNumber(1L);

        when(itineraryService.getItineraryById(1L)).thenReturn(mockActivity);

        Itinerary result = itineraryController.getItineraryById(1L);

        verify(itineraryService, times(1)).getItineraryById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getItineraryNumber());
    }

    @Test
    void addItinerary() {
        Itinerary itinerary = new Itinerary();

        when(itineraryService.addItinerary(itinerary)).thenReturn(itinerary);

        Itinerary result = itineraryController.addItinerary(itinerary);

        verify(itineraryService, times(1)).addItinerary(itinerary);

        assertNotNull(result);
        assertEquals(itinerary, result);
    }

    @Test
    void updateItinerary() {
        Long itineraryNumber = 1L;
        Itinerary itinerary = new Itinerary();
        itinerary.setItineraryNumber(itineraryNumber);
        itinerary.setLocation("Updated Location");

        when(itineraryService.updateItinerary(eq(itineraryNumber), any(Itinerary.class))).thenReturn(itinerary);

        Itinerary requestItinerary = new Itinerary();
        requestItinerary.setLocation("Updated Location");

        Itinerary result = itineraryController.updateItinerary(itineraryNumber, requestItinerary);

        verify(itineraryService, times(1)).updateItinerary(eq(itineraryNumber), any(Itinerary.class));

        assertNotNull(result);
        assertEquals(itineraryNumber, result.getItineraryNumber());
        assertEquals("Updated Location", result.getLocation());
    }

    @Test
    void deleteItinerary() {
        Long itineraryNumber = 1L;
        String expectedMessage = "Itinerary deleted successfully";

        when(itineraryService.deleteItinerary(itineraryNumber)).thenReturn(expectedMessage);

        String result = itineraryController.deleteItinerary(itineraryNumber);

        verify(itineraryService, times(1)).deleteItinerary(itineraryNumber);

        assertNotNull(result);
        assertEquals(expectedMessage, result);
    }
}