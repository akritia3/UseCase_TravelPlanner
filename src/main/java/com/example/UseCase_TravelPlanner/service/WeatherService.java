package com.example.UseCase_TravelPlanner.service;

import com.example.UseCase_TravelPlanner.entity.WeatherAPIResponse;
import com.example.UseCase_TravelPlanner.entity.WeatherData;
import com.example.UseCase_TravelPlanner.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class WeatherService {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    private final WebClient webClient;

    public WeatherService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.tomorrow.io/v4/weather/forecast?location=new%20delhi&apikey=CSLYr8T5hipOezopvGOGOHCWLVUMi0No")
                .build();
    }

    public void fetchAndStoreWeatherData(String parameter) {
        webClient.get()
                .uri("/forecast?param=" + parameter)
                .retrieve()
                .bodyToMono(WeatherAPIResponse.class)
                .map(this::convertToEntities)
                .subscribe(weatherDataRepository::saveAll);
    }

    private List<WeatherData> convertToEntities(WeatherAPIResponse response) {
        return response.getData().stream()
                .map(data -> {
                    WeatherData weatherData = new WeatherData();
                    weatherData.setTime(ZonedDateTime.parse(data.getTime()));
                    weatherData.setTemperature(data.getValues().getTemperature());
                    weatherData.setWindSpeed(data.getValues().getWindSpeed());
                    weatherData.setPrecipitationProbability(data.getValues().getPrecipitationProbability());
                    return weatherData;
                })
                .toList();
    }

    public List<WeatherData> getWeatherDataBetween(ZonedDateTime startTime, ZonedDateTime endTime) {
        return weatherDataRepository.findByTimeBetween(startTime, endTime);
    }
}
