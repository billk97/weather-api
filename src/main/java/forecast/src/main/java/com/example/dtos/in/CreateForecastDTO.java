package com.example.dtos.in;

import com.example.enums.WeatherCategory;
import java.time.Instant;

public record CreateForecastDTO(
    Instant forecastTime,
    int temperature,
    float humidity,
    int wind,
    WeatherCategory weatherCategory,
    long locationId) {
}
