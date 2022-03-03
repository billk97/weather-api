package com.example.usecases.forecast;

import static org.junit.jupiter.api.Assertions.*;

import com.example.domain.Forecast;
import com.example.domain.Location;
import com.example.dtos.in.CreateForecastDTO;
import com.example.dtos.out.ObjectIdDTO;
import com.example.enums.WeatherCategory;
import com.example.repository.ForecastRepository;
import com.example.repository.LocationRepository;
import io.quarkus.test.junit.QuarkusTest;
import java.time.Instant;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@QuarkusTest
@Transactional
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CreateForecastTests {

    @Inject
    private ForecastRepository forecastRepo;
    @Inject
    private LocationRepository locationRepo;

    @Inject
    private CreateForecast createForecast;

    @AfterEach
    void tierDown() {
        forecastRepo.deleteAll();
        locationRepo.deleteAll();
    }

    @Test
    void given_a_forecast_with_null_time_IT_should_throw(){
        CreateForecastDTO dto = new CreateForecastDTO(null, 1, 1l, 1 , WeatherCategory.Cold, 1, 1);
        assertThrows(IllegalArgumentException.class, () -> {
            createForecast.command(dto);
        });
    }

    @Test
    void given_a_forecast_with_invalid_location_IT_should_throw(){
        CreateForecastDTO dto = new CreateForecastDTO(Instant.now(), 1, 1l, 1 , WeatherCategory.Cold, 1, 999);
        assertThrows(IllegalArgumentException.class, () -> {
            createForecast.command(dto);
        });
    }

    @Test
    void given_a_valid_forecast_IT_should_be_saved_successfully(){
        Location location = new Location("mexico", 1.0, 1.0);
        locationRepo.persist(location);

        CreateForecastDTO dto = new CreateForecastDTO(Instant.now(), 1, 1l, 1 , WeatherCategory.Cold, 1, location.getLocationId());
        ObjectIdDTO idDTO = createForecast.command(dto);
        Forecast f = forecastRepo.findById(idDTO.objectId());
        assertEquals(location.getName(), f.getLocation().getName());
    }
}