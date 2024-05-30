package com.example.UseCase_TravelPlanner.repository;

import com.example.UseCase_TravelPlanner.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {

    @Query(value="select i from Itinerary i where i.location = :location")
    public List<Itinerary> getItineraryByLocation(@Param("location") String location);
}
