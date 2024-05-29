package com.example.UseCase_TravelPlanner.controller;

import com.example.UseCase_TravelPlanner.entity.Itinerary;
import com.example.UseCase_TravelPlanner.service.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
