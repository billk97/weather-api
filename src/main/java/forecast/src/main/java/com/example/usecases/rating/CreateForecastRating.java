package com.example.usecases.rating;

import com.example.domain.Forecast;
import com.example.domain.Rating;
import com.example.dtos.in.CreateForecastRatingDTO;
import com.example.repository.ForecastRatingRepository;
import com.example.repository.ForecastRepository;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@RequestScoped
public class CreateForecastRating {

    @Inject
    private ForecastRatingRepository forecastRatingRepo;
    @Inject
    private ForecastRepository forecastRepo;

    @Transactional
    public long command(CreateForecastRatingDTO dto) {
        Forecast forecast = forecastRepo.findById(dto.forecastId());
        if (forecast == null) {
            throw  new IllegalArgumentException(String.format("Forecast with id: %d not found", dto.forecastId()));
        }
        Rating rating = new Rating(dto, forecast);
        forecastRatingRepo.persist(rating);
        return rating.getId();
    }

}
