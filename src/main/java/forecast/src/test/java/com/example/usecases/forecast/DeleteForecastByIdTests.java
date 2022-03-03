package com.example.usecases.forecast;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.domain.Forecast;
import com.example.domain.Location;
import com.example.domain.Rating;
import com.example.enums.ErrorCode;
import com.example.enums.WeatherCategory;
import com.example.exceptions.IllegalArgumentExceptionWithCode;
import com.example.repository.ForecastRatingRepository;
import com.example.repository.ForecastRepository;
import com.example.repository.LocationRepository;
import io.quarkus.test.TestTransaction;
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
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Transactional
class DeleteForecastByIdTests {

    @Inject
    ForecastRepository forecastRepo;
    @Inject
    LocationRepository locationRepo;
    @Inject
    ForecastRatingRepository forecastRatingRepo;

    @Inject
    DeleteForecastById deleteForecastById;



    @AfterEach
    void tierDown() {
        forecastRatingRepo.deleteAll();
        forecastRepo.deleteAll();
        locationRepo.deleteAll();
    }

    @Test
    void given_a_non_numeric_id_IT_should_throw() {
        IllegalArgumentExceptionWithCode e = assertThrows(IllegalArgumentExceptionWithCode.class, ()->{
           deleteForecastById.command("something");
        });
        assertEquals(ErrorCode.INVALID_INPUT, e.getErrorCode());
    }

    @Test
    void given_a_non_existing_Forecast_id_IT_should_throw() {
        IllegalArgumentExceptionWithCode e = assertThrows(IllegalArgumentExceptionWithCode.class, ()->{
            deleteForecastById.command("9999");
        });
        assertEquals(ErrorCode.FORECAST_NOT_FOUND, e.getErrorCode());
    }


    @Test
    void given_aForecast_id_IT_should_be_deleted_successfully() {

        Location location = new Location("mexico", 1.0, 1.0);
        locationRepo.persist(location);
        Forecast forecast = new Forecast(Instant.now().minus(3, ChronoUnit.DAYS), 1, 1L, 1, WeatherCategory.Cold, location, 1L);
        forecastRepo.persist(forecast);
        long forecastId = forecast.getId();
        Rating rating = new Rating(1, forecast, 1);
        forecastRatingRepo.persist(rating);
        deleteForecastById.command(String.valueOf(forecast.getId()));
        assertNull(forecastRepo.findById(forecastId));
    }

}