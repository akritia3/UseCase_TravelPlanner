package com.example.UseCase_TravelPlanner.repository;

import com.example.UseCase_TravelPlanner.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
}
