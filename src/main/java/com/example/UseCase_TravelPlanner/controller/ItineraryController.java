package com.example.UseCase_TravelPlanner.controller;

import com.example.UseCase_TravelPlanner.entity.Itinerary;
import com.example.UseCase_TravelPlanner.service.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class ItineraryController {
    @Autowired
    ItineraryService itineraryService;

    @GetMapping("/itinerary")
    public List<Itinerary> getAllItineraries() {
        return itineraryService.getAllItineraries();
    }

    @GetMapping("/itinerary/{id}")
    public Itinerary getItineraryById(@PathVariable Long id) {
        return itineraryService.getItineraryById(id);
    }

    @PostMapping("/itinerary")
    public Itinerary addItinerary(@RequestBody Itinerary itinerary) {
        return itineraryService.addItinerary(itinerary);
    }

    @PutMapping("/itinerary/{id}")
    public Itinerary updateItinerary(@PathVariable Long id, @RequestBody Itinerary itinerary) {
        return itineraryService.updateItinerary(id, itinerary);
    }

    @DeleteMapping("/itinerary/{id}")
    public String deleteItinerary(@PathVariable Long id) {
        return itineraryService.deleteItinerary(id);
    }

    @GetMapping("/itinerary/{location}")
    public List<Itinerary> getItineraryByLocation(@Param("location") String location) {
        return itineraryService.getItineraryByLocation(location);
    }
}
