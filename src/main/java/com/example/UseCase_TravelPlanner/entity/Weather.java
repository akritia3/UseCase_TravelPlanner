package com.example.UseCase_TravelPlanner.entity;

import lombok.Data;

@Data
public class Weather {
    private float temperature;
    private float precipitationProbability;
    private float windSpeed;
}
