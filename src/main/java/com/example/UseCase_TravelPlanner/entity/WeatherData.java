package com.example.UseCase_TravelPlanner.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Entity
public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entryNumber;
    private ZonedDateTime time;
    private double temperature;
    private double precipitationProbability;
    private double windSpeed;
}
