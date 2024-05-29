package com.example.UseCase_TravelPlanner.repository;

import com.example.UseCase_TravelPlanner.entity.Activity;
import com.example.UseCase_TravelPlanner.entity.AllTravelDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    // TODO: take date and location params to filter queries
    // TODO: date validation, loggers
    @Query(value="select i, a from Itinerary i, Activity a where i.location = a.location")
    public List<AllTravelDetails> getAllTravelPlans();
}
