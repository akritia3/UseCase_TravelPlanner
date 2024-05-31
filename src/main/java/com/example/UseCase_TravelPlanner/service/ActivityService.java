package com.example.UseCase_TravelPlanner.service;

import com.example.UseCase_TravelPlanner.entity.Activity;
import com.example.UseCase_TravelPlanner.entity.AllTravelDetails;
import com.example.UseCase_TravelPlanner.exceptions.InvalidRequestException;
import com.example.UseCase_TravelPlanner.exceptions.NotFoundException;
import com.example.UseCase_TravelPlanner.repository.ActivityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    private static final Logger log = LoggerFactory.getLogger(ActivityService.class);
    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public Activity getActivityById(Long activityId) {
        Optional<Activity> activity = activityRepository.findById(activityId);
        if (activity.isPresent()) {
            log.info("Getting Activity by ID: " + activityId);
            return activity.get();
        } else {
            log.error("activity with id " + activityId + " not found");
            throw new NotFoundException("activity with id " + activityId + " not found");
        }
    }

    public Activity addActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public Activity updateActivity(Long activityId, Activity activity) {
        Optional<Activity> activityOptional = activityRepository.findById(activityId);
        if (activityOptional.isPresent()) {
            log.info("Updating Activity by ID: " + activityId);
            Activity activityToUpdate = activityOptional.get();

            // location update
            if(activity.getLocation() != null && !activity.getLocation().isEmpty()) {
                activityToUpdate.setLocation(activity.getLocation());
            }

            // name update
            if(activity.getName() != null && !activity.getName().isEmpty()) {
                activityToUpdate.setName(activity.getName());
            }

            // setting update
            if (activity.getSetting() != null && !activity.getSetting().isEmpty()) {
                if (activity.getSetting().equalsIgnoreCase("outdoor") || activity.getSetting().equalsIgnoreCase("indoor")) {
                    activityToUpdate.setSetting(activity.getSetting());
                }
                else {
                    log.error("Setting " + activity.getSetting() + " is not supported, can only be indoor or outdoor");
                    throw new InvalidRequestException("Setting can only be outdoor or indoor");
                }
            }

            // dates update
            if (activity.getStartTime() != null) {
                activityToUpdate.setStartTime(activity.getStartTime());
            }
            if (activity.getEndTime() != null) {
                activityToUpdate.setStartTime(activity.getEndTime());
            }

            // save all changes
            return activityRepository.save(activityToUpdate);
        }

        // if activityId not found
        else {
            log.error("activity with id " + activityId + " not found");
            throw new NotFoundException("activity with id " + activityId + " not found");
        }

    }

    public String deleteActivity(Long activityId) {
        if (activityRepository.findById(activityId).isPresent()) {
            log.info("Deleting Activity by ID: " + activityId);
            activityRepository.deleteById(activityId);
            return "Activity with id " + activityId + " deleted successfully";
        }
        else {
            log.error("activity with id " + activityId + " not found");
            throw new NotFoundException("activity with id " + activityId + " not found");
        }
    }

    public List<AllTravelDetails> getAllTravelDetails() {
        return activityRepository.getAllTravelPlans();
    }

    public void deletePastActivities() {
        log.info("Deleting past activities");
        activityRepository.deleteByEndTimeBefore(LocalDateTime.now());
    }

    public List<AllTravelDetails> getTravelDetailsBetweenDates(LocalDateTime startDate,
                                                               LocalDateTime endDate) {
        return activityRepository.getTravelDetailsBetweenDates(startDate, endDate);
    }

    public List<Activity> getActivityByTime(LocalDateTime startTime,
                                            LocalDateTime endTime){
        return activityRepository.getActivityByTime(startTime, endTime);
    }
}
