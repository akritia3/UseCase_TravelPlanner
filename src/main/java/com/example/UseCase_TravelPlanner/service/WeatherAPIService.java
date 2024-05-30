package com.example.UseCase_TravelPlanner.service;

import com.example.UseCase_TravelPlanner.entity.WeatherData;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherAPIService {
    private final WebClient webClient;

    public WeatherAPIService() {
        this.webClient = WebClient.builder().baseUrl("https://api.tomorrow.io/v4/weather/realtime?location=toronto&apikey=CSLYr8T5hipOezopvGOGOHCWLVUMi0No").build();
    }

    public Mono<WeatherData> getDataFromWeatherAPI(String parameter) {
        return this.webClient.get()
            .uri("/data?param=" + parameter)
            .retrieve()
            .bodyToMono(WeatherData.class);
    }
}
