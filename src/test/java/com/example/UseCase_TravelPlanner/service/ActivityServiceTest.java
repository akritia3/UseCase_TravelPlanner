package com.example.UseCase_TravelPlanner.service;

import com.example.UseCase_TravelPlanner.entity.Activity;
import com.example.UseCase_TravelPlanner.entity.AllTravelDetails;
import com.example.UseCase_TravelPlanner.repository.ActivityRepository;
import com.example.UseCase_TravelPlanner.service.ActivityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.UseCase_TravelPlanner.exceptions.NotFoundException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ActivityServiceTest {

    @InjectMocks
    private ActivityService activityService;

    @Mock
    private ActivityRepository activityRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllActivities() {
        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity());
        activities.add(new Activity());

        when(activityRepository.findAll()).thenReturn(activities);

        List<Activity> result = activityService.getAllActivities();

        verify(activityRepository, times(1)).findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void getActivityById_Exists() {
        Activity mockActivity = new Activity();
        mockActivity.setActivityId(1L);

        when(activityRepository.findById(1L)).thenReturn(Optional.of(mockActivity));

        Activity result = activityService.getActivityById(1L);

        verify(activityRepository, times(1)).findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getActivityId());
    }

    @Test
    void getActivityById_NotExists() {
        when(activityRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> activityService.getActivityById(1L));
    }

    @Test
    void addActivity() {
        Activity newActivity = new Activity();

        when(activityRepository.save(newActivity)).thenReturn(newActivity);

        Activity result = activityService.addActivity(newActivity);

        verify(activityRepository, times(1)).save(newActivity);

        assertNotNull(result);
        assertEquals(newActivity, result);
    }

    @Test
    void getAllTravelDetails() {
        List<AllTravelDetails> travelDetails = new ArrayList<>();
        travelDetails.add(new AllTravelDetails());
        travelDetails.add(new AllTravelDetails());

        when(activityRepository.getAllTravelPlans()).thenReturn(travelDetails);

        List<AllTravelDetails> result = activityService.getAllTravelDetails();

        verify(activityRepository, times(1)).getAllTravelPlans();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void deletePastActivities() {
        activityService.deletePastActivities();
        verify(activityRepository).deleteByEndTimeBefore(LocalTime.now());
    }
}