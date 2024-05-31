package com.example.UseCase_TravelPlanner.service;

import com.example.UseCase_TravelPlanner.entity.Itinerary;
import com.example.UseCase_TravelPlanner.repository.ItineraryRepository;
import com.example.UseCase_TravelPlanner.service.ItineraryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.UseCase_TravelPlanner.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItineraryServiceTest {
    @InjectMocks
    private ItineraryService itineraryService;

    @Mock
    private ItineraryRepository itineraryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllItineraries() {
        List<Itinerary> itineraries = new ArrayList<>();
        itineraries.add(new Itinerary());
        itineraries.add(new Itinerary());

        when(itineraryRepository.findAll()).thenReturn(itineraries);

        List<Itinerary> result = itineraryService.getAllItineraries();

        verify(itineraryRepository, times(1)).findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetItineraryById_Exists() {
        Itinerary mockItinerary = new Itinerary();
        mockItinerary.setItineraryNumber(1L);

        when(itineraryRepository.findById(1L)).thenReturn(Optional.of(mockItinerary));

        Itinerary result = itineraryService.getItineraryById(1L);

        verify(itineraryRepository, times(1)).findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getItineraryNumber());
    }

    @Test
    void testGetItineraryById_NotExists() {
        when(itineraryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> itineraryService.getItineraryById(1L));
    }

    @Test
    void testAddItinerary() {
        Itinerary newItinerary = new Itinerary();

        when(itineraryRepository.save(newItinerary)).thenReturn(newItinerary);

        Itinerary result = itineraryService.addItinerary(newItinerary);

        verify(itineraryRepository, times(1)).save(newItinerary);

        assertNotNull(result);
        assertEquals(newItinerary, result);
    }

    @Test
    void testUpdateItinerary_Exists() {
        Itinerary mockItinerary = new Itinerary();
        mockItinerary.setItineraryNumber(1L);

        when(itineraryRepository.findById(1L)).thenReturn(Optional.of(mockItinerary));

        Itinerary updatedItinerary = new Itinerary();
        updatedItinerary.setLocation("Updated Location");
        Itinerary result = itineraryService.updateItinerary(1L, updatedItinerary);

        verify(itineraryRepository, times(1)).findById(1L);

        verify(itineraryRepository, times(1)).save(mockItinerary);

        assertEquals("Updated Location", mockItinerary.getLocation());

        assertNotNull(result);
        assertEquals(mockItinerary, result);
    }

    @Test
    void testUpdateItinerary_NotExists() {
        when(itineraryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> itineraryService.updateItinerary(1L, new Itinerary()));
    }

    @Test
    void testDeleteItinerary_Exists() {
        when(itineraryRepository.findById(1L)).thenReturn(Optional.of(new Itinerary()));

        String result = itineraryService.deleteItinerary(1L);

        verify(itineraryRepository, times(1)).findById(1L);

        verify(itineraryRepository, times(1)).deleteById(1L);

        assertEquals("Itinerary with id 1 deleted successfully", result);
    }

    @Test
    void testDeleteItinerary_NotExists() {
        when(itineraryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> itineraryService.deleteItinerary(1L));
    }
}