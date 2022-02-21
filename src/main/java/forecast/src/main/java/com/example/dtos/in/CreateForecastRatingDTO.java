package com.example.dtos.in;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The type Forecast rating dto.
 */
@XmlRootElement
public  record CreateForecastRatingDTO(long userId, long forecastId, int score) {
}
