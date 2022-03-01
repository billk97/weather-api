package com.example.usecases.rating;

import static org.junit.jupiter.api.Assertions.*;

import com.example.domain.Forecast;
import com.example.domain.Location;
import com.example.dtos.in.CreateForecastRatingDTO;
import com.example.enums.WeatherCategory;
import com.example.repository.ForecastRatingRepository;
import com.example.repository.ForecastRepository;
import com.example.repository.LocationRepository;
import io.quarkus.test.junit.QuarkusTest;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@QuarkusTest
@Transactional
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CreateForecastRatingTests {

    @Inject
    private ForecastRepository forecastRepo;
    @Inject
    private ForecastRatingRepository forecastRatingRepo;
    @Inject
    private CreateForecastRating createForecastRating;


    @Inject
    private LocationRepository locationRepo;

    @AfterEach
    void tierDown() {
        forecastRatingRepo.deleteAll();
        forecastRepo.deleteAll();
        locationRepo.deleteAll();
    }

    @Test
    void given_a_non_existing_forecast_id_IT_should_throw() {
        assertThrows(IllegalArgumentException.class, () -> {
           createForecastRating.command(new CreateForecastRatingDTO(1,99999,1));
        });
    }

    @Test
    void given_a_forecast_id_IT_should_add_the_rating() {
        Location location = new Location("mexico", 1.0, 1.0);
        locationRepo.persist(location);

        Forecast forecast = new Forecast(Instant.now().minus(3, ChronoUnit.DAYS), 1, 1L, 1, WeatherCategory.Cold, location, 1L);
        forecastRepo.persist(forecast);
        createForecastRating.command(new CreateForecastRatingDTO(1,location.getLocationId(),1));
        assertEquals(1, forecastRatingRepo.findAll().stream().count());
    }
}