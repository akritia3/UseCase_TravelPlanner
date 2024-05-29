package com.example.UseCase_TravelPlanner.service;

import com.example.UseCase_TravelPlanner.entity.Activity;
import com.example.UseCase_TravelPlanner.entity.Itinerary;
import com.example.UseCase_TravelPlanner.exceptions.InvalidRequestException;
import com.example.UseCase_TravelPlanner.exceptions.NotFoundException;
import com.example.UseCase_TravelPlanner.repository.ItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItineraryService {
    @Autowired
    private ItineraryRepository itineraryRepository;

    public List<Itinerary> getAllItineraries() {
        return itineraryRepository.findAll();
    }

    public Itinerary getItineraryById(Long itineraryId) {
        Optional<Itinerary> itinerary = itineraryRepository.findById(itineraryId);
        if (itinerary.isPresent()) {
            return itinerary.orElse(null);
        }
        throw new NotFoundException("itinerary with id " + itineraryId + " not found");
    }

    public Itinerary addItinerary(Itinerary itinerary) {
        return itineraryRepository.save(itinerary);
    }

    public Itinerary updateItinerary(Long itineraryId, Itinerary itinerary) {
        Optional<Itinerary> itineraryOptional = itineraryRepository.findById(itineraryId);
        if (itineraryOptional.isPresent()) {
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
            throw new NotFoundException("itinerary with id " + itineraryId + " not found");
        }

    }

    public String deleteItinerary(Long itineraryId) {
        if (itineraryRepository.findById(itineraryId).isPresent()) {
            itineraryRepository.deleteById(itineraryId);
            return "Itinerary with id " + itineraryId + " deleted successfully";
        }
        else {
            throw new NotFoundException("Itinerary with id " + itineraryId + " not found");
        }
    }
}
