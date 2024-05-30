package com.example.UseCase_TravelPlanner.repository;

import com.example.UseCase_TravelPlanner.entity.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    List<WeatherData> findByTimeBetween(ZonedDateTime startTime, ZonedDateTime endTime);
}
