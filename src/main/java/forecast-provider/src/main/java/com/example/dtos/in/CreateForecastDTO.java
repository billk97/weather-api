package com.example.dtos.in;

import com.example.domain.ForecastProvider;
import com.example.domain.Location;
import com.example.enums.WeatherCategory;
import java.time.Instant;

public record CreateForecastDTO(
        String forecastTime,
        int temperature,
        float humidity,
        int wind,
        WeatherCategory weatherCategory,
        long providerId,
        long locationId) {
}
