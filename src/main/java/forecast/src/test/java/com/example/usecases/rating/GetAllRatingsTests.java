package com.example.usecases.rating;

import static org.junit.jupiter.api.Assertions.*;

import com.example.domain.Forecast;
import com.example.domain.Location;
import com.example.domain.Rating;
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
class GetAllRatingsTests {

    @Inject
    private ForecastRatingRepository forecastRatingRepo;
    @Inject
    private LocationRepository locationRepo;
    @Inject
    private ForecastRepository forecastRepo;
    @Inject
    private GetAllRatings getAllRatings;


    @AfterEach
    void tierDown() {
        forecastRatingRepo.deleteAll();
        forecastRepo.deleteAll();
        locationRepo.deleteAll();
    }

    @Test
    void given_no_location_it_should_return_empty_list() {
        assertTrue(getAllRatings.query().isEmpty());

    }

    @Test
    void given_a_location_it_should_return_one_location() {
        Location location = new Location("mexico", 1.0, 1.0);
        locationRepo.persist(location);
        Forecast forecast = new Forecast(Instant.now().minus(3, ChronoUnit.DAYS), 1, 1L, 1, WeatherCategory.Cold, location, 1L);
        forecastRepo.persist(forecast);
        Rating rating = new Rating(1, forecast, 5);
        forecastRatingRepo.persist(rating);
        assertFalse(getAllRatings.query().isEmpty());
        assertEquals(1, forecastRatingRepo.count());
    }

}