package com.example.UseCase_TravelPlanner.repository;

import com.example.UseCase_TravelPlanner.entity.Activity;
import com.example.UseCase_TravelPlanner.entity.AllTravelDetails;
import com.example.UseCase_TravelPlanner.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    // TODO: take date and location params to filter queries
    // TODO: date validation, loggers
    @Query(value="select i, a from Itinerary i, Activity a where i.location = a.location")
    public List<AllTravelDetails> getAllTravelPlans();


    @Query(value="select i,a from Itinerary i, Activity a where i.startDate between :startDate and :endDate")
    public List<AllTravelDetails> getTravelDetailsBetweenDates(@Param("startDate") LocalDateTime startDate,
                                                               @Param("endDate") LocalDateTime endDate);

    @Query(value="select a from Activity a where a.startTime > :startTime or a.endTime < :endTime")
    public List<Activity> getActivityByTime(@Param("startTime") LocalDateTime startTime,
                                            @Param("endTime") LocalDateTime endTime);

    void deleteByEndTimeBefore(LocalDateTime now);
}
