package com.example.UseCase_TravelPlanner.controller;

import com.example.UseCase_TravelPlanner.entity.Activity;
import com.example.UseCase_TravelPlanner.entity.AllTravelDetails;
import com.example.UseCase_TravelPlanner.repository.ActivityRepository;
import com.example.UseCase_TravelPlanner.service.ActivityService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ActivityControllerTest {

    @InjectMocks
    private ActivityController activityController;

    @Mock
    private ActivityService activityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllActivities() {
        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity());
        activities.add(new Activity());

        when(activityService.getAllActivities()).thenReturn(activities);

        List<Activity> result = activityController.getAllActivities();

        verify(activityService, times(1)).getAllActivities();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void getActivityById() {
        Activity mockActivity = new Activity();
        mockActivity.setActivityId(1L);

        when(activityService.getActivityById(1L)).thenReturn(mockActivity);

        Activity result = activityController.getActivityById(1L);

        verify(activityService, times(1)).getActivityById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getActivityId());
    }

    @Test
    void addActivity() {
        Activity newActivity = new Activity();

        when(activityService.addActivity(newActivity)).thenReturn(newActivity);

        Activity result = activityController.addActivity(newActivity);

        verify(activityService, times(1)).addActivity(newActivity);

        assertNotNull(result);
        assertEquals(newActivity, result);
    }

    @Test
    void updateActivity() {
        Long activityId = 1L;
        Activity mockActivity = new Activity();
        mockActivity.setActivityId(activityId);
        mockActivity.setName("Updated Activity");

        when(activityService.updateActivity(eq(activityId), any(Activity.class))).thenReturn(mockActivity);

        Activity requestActivity = new Activity();
        requestActivity.setName("Updated Activity");

        Activity result = activityController.updateActivity(activityId, requestActivity);

        verify(activityService, times(1)).updateActivity(eq(activityId), any(Activity.class));

        assertNotNull(result);
        assertEquals(activityId, result.getActivityId());
        assertEquals("Updated Activity", result.getName());
    }

    @Test
    void deleteActivity() {
        Long activityId = 1L;
        String expectedMessage = "Activity deleted successfully";

        when(activityService.deleteActivity(activityId)).thenReturn(expectedMessage);

        String result = activityController.deleteActivity(activityId);

        verify(activityService, times(1)).deleteActivity(activityId);

        assertNotNull(result);
        assertEquals(expectedMessage, result);
    }

    @Test
    void getAllTravelDetails() {
        List<AllTravelDetails> travelDetails = new ArrayList<>();
        travelDetails.add(new AllTravelDetails());
        travelDetails.add(new AllTravelDetails());

        when(activityService.getAllTravelDetails()).thenReturn(travelDetails);

        List<AllTravelDetails> result = activityController.getAllTravelDetails();

        verify(activityService, times(1)).getAllTravelDetails();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void deletePastActivities() {
        activityController.deletePastActivities();
        verify(activityService).deletePastActivities();
    }

    @Test
    void getTravelDetailsBetweenDates() {
    }

    @Test
    void getActivityByTime() {
    }
}