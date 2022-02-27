package com.example.dtos.out;

import com.example.enums.WeatherCategory;
import java.time.Instant;

public record ForecastSummaryDTO(String locationName, WeatherCategory weatherCategory, Instant isoTime) {
}
