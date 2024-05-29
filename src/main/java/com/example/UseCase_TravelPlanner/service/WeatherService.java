package com.example.UseCase_TravelPlanner.service;

import com.example.UseCase_TravelPlanner.entity.Weather;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    @Autowired
    private WeatherAPIService weatherAPIService;

    @Autowired
    

    @Transactional
    public void processWeather(String location) {
        weatherAPIService.getDataFromWeatherAPI(location).map(apiResponse -> {
            Weather weather = new Weather();
            weather.setTemperature(apiResponse.getTemperature());
            weather.setPrecipitationProbability(apiResponse.getPrecipitationProbability());
            weather.setWindSpeed(apiResponse.getWindSpeed());
            return weather;
        }).subscribe(weather ->{

        });
    }
}
