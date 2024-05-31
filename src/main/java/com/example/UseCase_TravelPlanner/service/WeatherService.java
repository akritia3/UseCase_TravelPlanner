package com.example.UseCase_TravelPlanner.service;

import com.example.UseCase_TravelPlanner.entity.WeatherAPIResponse;
import com.example.UseCase_TravelPlanner.entity.WeatherData;
import com.example.UseCase_TravelPlanner.repository.WeatherDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class WeatherService {

    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);
    @Autowired
    private WeatherDataRepository weatherDataRepository;

    private final WebClient webClient;

    public WeatherService(WebClient.Builder webClientBuilder) {
        log.info("WebClientBuilder: defining baseUrl");
        this.webClient = webClientBuilder.baseUrl("https://api.tomorrow.io/v4/weather/forecast?location=new%20delhi&apikey=CSLYr8T5hipOezopvGOGOHCWLVUMi0No")
                .build();
    }

    public void fetchAndStoreWeatherData(String location) {
        log.info("fetchAndStoreWeatherData");
        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("location", "42.3478,-71.0466")
                        .queryParam("apiKey", "pLBLr2bgrJj2Qr1PQKzTxtwqFsFaGa7V").build())
                .retrieve()
                .bodyToMono(WeatherAPIResponse.class)
                .map(this::convertToEntities)
                .subscribe(weatherDataRepository::saveAll);
    }

    private List<WeatherData> convertToEntities(WeatherAPIResponse response) {
        log.info("convertToEntities");
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
        log.info("getWeatherDataBetween");
        return weatherDataRepository.findByTimeBetween(startTime, endTime);
    }
}
