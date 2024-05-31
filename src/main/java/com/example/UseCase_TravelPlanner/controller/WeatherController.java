package com.example.UseCase_TravelPlanner.controller;

import com.example.UseCase_TravelPlanner.entity.WeatherData;
import com.example.UseCase_TravelPlanner.exceptions.InvalidRequestException;
import com.example.UseCase_TravelPlanner.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
public class WeatherController {
    private static final Logger log = LoggerFactory.getLogger(WeatherController.class);
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/fetchWeatherData")
    public void fetchWeatherData(@RequestParam(required = false) String location) {
        log.info("Fetching weather data for location: " + location);
        weatherService.fetchAndStoreWeatherData(location);
    }

    @GetMapping("/weatherData")
    public List<WeatherData> getWeatherDataBetween(@RequestParam(required = false) String startTime, @RequestParam(required = false) String endTime) {
        try {
            log.info("reached getWeatherDataBetween without error");
            ZonedDateTime start = ZonedDateTime.parse(startTime);
            ZonedDateTime end = ZonedDateTime.parse(endTime);
            return weatherService.getWeatherDataBetween(start, end);
        } catch (DateTimeParseException e) {
            log.error(e.getMessage());
            throw new InvalidRequestException("Invalid date or time format. Please use the ISO date format: yyyy-MM-dd'T'HH:mm:ss");
        }
    }
}
