package com.example.UseCase_TravelPlanner.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;

    private String location;

    private String name;

    private String setting; // Indoors or Outdoors

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
