package com.example.UseCase_TravelPlanner.controller;

import com.example.UseCase_TravelPlanner.entity.Activity;
import com.example.UseCase_TravelPlanner.entity.AllTravelDetails;
import com.example.UseCase_TravelPlanner.entity.Itinerary;
import com.example.UseCase_TravelPlanner.exceptions.InvalidRequestException;
import com.example.UseCase_TravelPlanner.service.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

@RestController
public class ActivityController {

    private static final Logger log = LoggerFactory.getLogger(ActivityController.class);
    @Autowired
    ActivityService activityService;

    @GetMapping("/activity")
    public List<Activity> getAllActivities() {
        return activityService.getAllActivities();
    }

    @GetMapping("/activity/{id}")
    public Activity getActivityById(@PathVariable Long id) {
        return activityService.getActivityById(id);
    }

    @PostMapping("/activity")
    public Activity addActivity(@RequestBody Activity activity) {
        return activityService.addActivity(activity);
    }

    @PutMapping("/activity/{id}")
    public Activity updateActivity(@PathVariable Long id, @RequestBody Activity activity) {
        return activityService.updateActivity(id, activity);
    }

    @DeleteMapping("/activity/{id}")
    public String deleteActivity(@PathVariable Long id) {
        return activityService.deleteActivity(id);
    }

    @GetMapping("/travel")
    public List<AllTravelDetails> getAllTravelDetails() {
        return activityService.getAllTravelDetails();
    }

    @DeleteMapping("/delete-past")
    public void deletePastActivities() {
        activityService.deletePastActivities();
    }

    @GetMapping("/travel/{startDate}/{endDate}")
    public List<AllTravelDetails> getTravelDetailsBetweenDates(@PathVariable String startDate, @PathVariable String endDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date start;
        Date end;
        try {
            start = formatter.parse(startDate);
            end = formatter.parse(endDate);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format. Please use 'yyyy-MM-dd'.", e);
        }
        return activityService.getTravelDetailsBetweenDates(start, end);
    }

    @GetMapping("/activity/{startTime}/{endTime}")
    public List<Activity> getActivityByTime(@PathVariable String startTime, @PathVariable String endTime) {
        try {
            log.info("reached getActivityByTime without error");
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            LocalTime start = LocalTime.parse(startTime, formatter);
            LocalTime end = LocalTime.parse(endTime, formatter);
            return activityService.getActivityByTime(start, end);
        } catch (DateTimeParseException e) {
            log.error(e.getMessage());
            throw new InvalidRequestException("Invalid date format. Please use the ISO date format: yyyy-MM-dd'T'HH:mm:ss");
        }
    }
}
