package com.example.repository;

import com.example.domain.Forecast;
import com.example.domain.Rating;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ForecastRatingRepository implements PanacheRepository<Rating> {

    public void deleteAllByForecast(Forecast forecast) {
        delete("forecast", forecast);
    }
}
