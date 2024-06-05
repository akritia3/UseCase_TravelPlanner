package com.example.UseCase_TravelPlanner.repository;

import com.example.UseCase_TravelPlanner.entity.Activity;
import com.example.UseCase_TravelPlanner.entity.AllTravelDetails;
import com.example.UseCase_TravelPlanner.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query(value="select new com.example.UseCase_TravelPlanner.entity.AllTravelDetails(i, a) from Itinerary i, Activity a where i.location = a.location")
    public List<AllTravelDetails> getAllTravelPlans();

    @Query(value="select a.location, count(a.location) from Activity a group by a.location order by count(a) desc")
    public List<Object[]> countActivitiesByLocation();


    @Query(value="select new com.example.UseCase_TravelPlanner.entity.AllTravelDetails(i, a) from Itinerary i, Activity a where (i.startDate > :startDate) and (i.endDate < :endDate) and (i.location = a.location)")
    public List<AllTravelDetails> getTravelDetailsBetweenDates(@Param("startDate") Date startDate,
                                                               @Param("endDate") Date endDate);

    @Query(value="select a from Activity a where a.startTime > :startTime or a.endTime < :endTime")
    public List<Activity> getActivityByTime(@Param("startTime") LocalTime startTime,
                                            @Param("endTime") LocalTime endTime);

    void deleteByEndTimeBefore(LocalTime now);
}
