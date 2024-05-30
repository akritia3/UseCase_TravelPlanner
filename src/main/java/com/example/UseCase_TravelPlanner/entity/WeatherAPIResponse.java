package com.example.UseCase_TravelPlanner.entity;

import lombok.Data;

import java.util.List;

@Data
public class WeatherAPIResponse {
    private List<WeatherDataPoint> data;

    @Data
    public static class WeatherDataPoint {
        private String time;
        private Values values;

        @Data
        public static class Values {
            private double temperature;
            private double windSpeed;
            private double precipitationProbability;
        }
    }
}
