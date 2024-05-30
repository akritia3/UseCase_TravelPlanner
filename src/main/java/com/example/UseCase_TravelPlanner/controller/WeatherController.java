package com.example.UseCase_TravelPlanner.controller;

import com.example.UseCase_TravelPlanner.entity.WeatherData;
import com.example.UseCase_TravelPlanner.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/fetchWeatherData")
    public void fetchWeatherData(@RequestParam String parameter) {
        weatherService.fetchAndStoreWeatherData(parameter);
    }

    @GetMapping("/weatherData")
    public List<WeatherData> getWeatherDataBetween(@RequestParam String startTime, @RequestParam String endTime) {
        ZonedDateTime start = ZonedDateTime.parse(startTime);
        ZonedDateTime end = ZonedDateTime.parse(endTime);
        return weatherService.getWeatherDataBetween(start, end);
    }
}
