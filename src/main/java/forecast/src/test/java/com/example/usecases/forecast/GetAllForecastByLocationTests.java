package com.example.usecases.forecast;

import static org.junit.jupiter.api.Assertions.*;

import com.example.domain.Forecast;
import com.example.domain.Location;
import com.example.enums.WeatherCategory;
import com.example.exceptions.IllegalArgumentExceptionWithCode;
import com.example.repository.ForecastRepository;
import com.example.repository.LocationRepository;
import io.quarkus.test.junit.QuarkusTest;
import java.time.Instant;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@QuarkusTest
@Transactional
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GetAllForecastByLocationTests {


    @Inject
    private ForecastRepository forecastRepo;

    @Inject
    private LocationRepository locationRepo;

    @Inject
    private GetAllForecastByLocation getAllForecastByLocation;

    @AfterEach
    void tierDown() {
        forecastRepo.deleteAll();
        locationRepo.deleteAll();
    }

    @Test
    void given_a_non_integer_id_It_should_throw() {
        assertThrows(IllegalArgumentExceptionWithCode.class, () -> {
            getAllForecastByLocation.query("test");
        });
    }

    @Test
    void given_a_non_existing_location_id_It_should_throw() {
        assertThrows(IllegalArgumentExceptionWithCode.class, () -> {
            getAllForecastByLocation.query("9999");
        });
    }

    @Test
    void given_a_valid_location_id_It_should_return_all_existing_forecasts() {
        Location location = new Location("mexico", 1.0, 1.0);
        locationRepo.persist(location);
        Location location2 = new Location("USA", 1.0, 1.0);
        locationRepo.persist(location2);
        Forecast forecast = new Forecast(Instant.now(), 1, 1L, 1, WeatherCategory.Cold, location, 1L);
        forecastRepo.persist(forecast);
        Forecast forecast2 = new Forecast(Instant.now(), 1, 1L, 1, WeatherCategory.Cold, location, 2L);
        forecastRepo.persist(forecast2);
        Forecast forecast3 = new Forecast(Instant.now(), 1, 1L, 1, WeatherCategory.Cold, location2, 2L);
        forecastRepo.persist(forecast3);

        List<Forecast> forecasts = getAllForecastByLocation.query(String.valueOf(location.getLocationId()));
        assertEquals(2, forecasts.size());
        assertEquals(location.getName(), forecasts.get(0).getLocation().getName());

    }

}