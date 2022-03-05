package com.example.usecases.rating;

import static org.junit.jupiter.api.Assertions.*;

import com.example.domain.Forecast;
import com.example.domain.Location;
import com.example.dtos.in.CreateForecastRatingDTO;
import com.example.enums.WeatherCategory;
import com.example.repository.ForecastRatingRepository;
import com.example.repository.ForecastRepository;
import com.example.repository.LocationRepository;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.jupiter.api.*;

@QuarkusTest
@Transactional
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CreateForecastRatingTests {

    @Inject
    ForecastRepository forecastRepo;
    @Inject
    ForecastRatingRepository forecastRatingRepo;
    @Inject
    CreateForecastRating createForecastRating;

    @Inject
    LocationRepository locationRepo;

    @BeforeEach
    void init() {
        forecastRatingRepo.deleteAll();
        forecastRepo.deleteAll();
        locationRepo.deleteAll();
    }


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
    @Transactional
    void given_a_forecast_id_IT_should_add_the_rating() {
        Location location = new Location("mexico", 1.0, 1.0);
        locationRepo.persist(location);

        Forecast forecast = new Forecast(Instant.now().minus(3, ChronoUnit.DAYS), 1, 1L, 1, WeatherCategory.Cold, location, 1L);
        forecastRepo.persistAndFlush(forecast);
        System.out.println(forecast.getId());
        createForecastRating.command(new CreateForecastRatingDTO(1,forecast.getId(),1));
        assertEquals(1, forecastRatingRepo.findAll().stream().count());
    }
}