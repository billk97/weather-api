package com.example.usecases;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.domain.Forecast;
import com.example.domain.Location;
import com.example.dtos.in.CreateForecastDTO;
import com.example.dtos.out.ObjectIdDTO;
import com.example.enums.WeatherCategory;
import com.example.repository.ForecastRepository;
import com.example.repository.LocationRepository;
import io.quarkus.test.junit.QuarkusTest;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@QuarkusTest
@Transactional
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CreateForecastTests {

    @Inject
    private ForecastRepository forecastRepository;
    @Inject
    private LocationRepository locationRepository;

    @Inject
    private CreateForecast createForecast;

    private Location location = new Location("Athens", 0.0, 0.0);

    @BeforeEach
    void init() {
        locationRepository.persist(location);
    }

    @AfterEach
    void tearDown() {
        forecastRepository.deleteAll();
        locationRepository.deleteAll();
    }

    @Test
    void given_a_dto_with_null_forecast_time_IT_should_fail() {
        CreateForecastDTO dto =  new CreateForecastDTO(
            null,
            1,
            1,
            1,
            WeatherCategory.Cold,
            1
        );

        assertThrows(IllegalArgumentException.class, () -> {
            createForecast.command(dto);
        });
    }

    @Test
    void given_a_dto_with_for_non_existing_location_IT_should_fail() {
        CreateForecastDTO dto =  new CreateForecastDTO(
            Instant.now(),
            1,
            1,
            1,
            WeatherCategory.Cold,
            9999
        );

        assertThrows(IllegalArgumentException.class, () -> {
            createForecast.command(dto);
        });
    }

    @Test
    void given_a_valid_dto_IT_should_save_successfully() {

        CreateForecastDTO dto =  new CreateForecastDTO(
            Instant.now(),
            1,
            1,
            1,
            WeatherCategory.Cold,
            location.getLocationId()
        );
        ObjectIdDTO idDTO = createForecast.command(dto);
        Forecast forecast = forecastRepository.findById(idDTO.objectId());
        assertNotNull(forecast);
        assertEquals(1, forecast.getTemperature());
        assertEquals(WeatherCategory.Cold, forecast.getWeatherCategory());
        assertEquals("Athens", forecast.getLocation().getName());

    }
}
