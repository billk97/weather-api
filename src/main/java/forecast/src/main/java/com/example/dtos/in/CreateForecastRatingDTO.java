package com.example.dtos.in;

/**
 * The type Forecast rating dto.
 */
public  record CreateForecastRatingDTO(long userId, long forecastId, int score) {
}
