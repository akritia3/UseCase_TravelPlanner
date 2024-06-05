package com.example.UseCase_TravelPlanner.service;

import com.example.UseCase_TravelPlanner.entity.Activity;
import com.example.UseCase_TravelPlanner.entity.Itinerary;
import com.example.UseCase_TravelPlanner.exceptions.InvalidRequestException;
import com.example.UseCase_TravelPlanner.exceptions.NotFoundException;
import com.example.UseCase_TravelPlanner.repository.ItineraryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItineraryService {
    private static final Logger log = LoggerFactory.getLogger(ItineraryService.class);
    @Autowired
    private ItineraryRepository itineraryRepository;

    public List<Itinerary> getAllItineraries() {
        return itineraryRepository.findAll();
    }

    public Itinerary getItineraryById(Long itineraryId) {
        Optional<Itinerary> itinerary = itineraryRepository.findById(itineraryId);
        if (itinerary.isPresent()) {
            log.info("Getting itinerary by itinerary id {}", itineraryId);
            return itinerary.orElse(null);
        } else {
            log.error(HttpStatus.NOT_FOUND + "Itinerary to fetch not found with id: " + itineraryId);
            throw new NotFoundException("itinerary with id " + itineraryId + " not found");
        }
    }

    public Itinerary addItinerary(Itinerary itinerary) {
        return itineraryRepository.save(itinerary);
    }

    public Itinerary updateItinerary(Long itineraryId, Itinerary itinerary) {
        Optional<Itinerary> itineraryOptional = itineraryRepository.findById(itineraryId);
        if (itineraryOptional.isPresent()) {
            log.info("Updating itinerary with itinerary id {}", itineraryId);
            Itinerary itineraryToUpdate = itineraryOptional.get();

            // location update
            if(itinerary.getLocation() != null && !itinerary.getLocation().isEmpty()) {
                itineraryToUpdate.setLocation(itinerary.getLocation());
            }

            // dates update
            if (itinerary.getStartDate() != null) {
                itineraryToUpdate.setStartDate(itinerary.getStartDate());
            }
            if (itinerary.getEndDate() != null) {
                itineraryToUpdate.setStartDate(itinerary.getEndDate());
            }

            // accommodation update
            if(itinerary.getAccommodation() != null && !itinerary.getAccommodation().isEmpty()) {
                itinerary.setAccommodation(itinerary.getAccommodation());
            }

            // travelMode update
            if(itinerary.getTravelMode() != null && !itinerary.getTravelMode().isEmpty()) {
                itinerary.setTravelMode(itinerary.getTravelMode());
            }

            // save all changes
            return itineraryRepository.save(itineraryToUpdate);
        }

        // if activityId not found
        else {
            log.error(HttpStatus.NOT_FOUND + "Itinerary to update not found with id: " + itineraryId);
            throw new NotFoundException("itinerary with id " + itineraryId + " not found");
        }

    }

    public String deleteItinerary(Long itineraryId) {
        if (itineraryRepository.findById(itineraryId).isPresent()) {
            log.info("Deleting itinerary with id {}", itineraryId);
            itineraryRepository.deleteById(itineraryId);
            return "Itinerary with id " + itineraryId + " deleted successfully";
        }
        else {
            log.error(HttpStatus.NOT_FOUND + "Itinerary to delete not found with id: " + itineraryId);
            throw new NotFoundException("Itinerary with id " + itineraryId + " not found");
        }
    }

    public List<Itinerary> getItineraryByLocation(String location) {
        return itineraryRepository.getItineraryByLocation(location);
    }
}
