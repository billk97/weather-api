package com.example.usecases;

import com.example.domain.Rating;
import com.example.repository.ForecastRatingRepository;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class GetAllRatings {

    @Inject
    private ForecastRatingRepository forecastRatingRepo;

    public List<Rating> query() {
        return forecastRatingRepo.findAll().stream().toList();
    }
}
