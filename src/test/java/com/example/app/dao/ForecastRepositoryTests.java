package com.example.app.dao;

import com.example.app.domain.Forecast;
import com.example.app.domain.Location;
import com.example.app.domain.Service;
import com.example.app.enums.WeatherCategory;
import com.example.app.persistence.DatabaseUtils;
import com.example.app.repository.ForecastRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ForecastRepositoryTests {

    @Mock
    private Service service;

    @Mock
    private Location location;

    private ForecastRepository forecastRepository;

    @BeforeEach
    void setUp() {
        DatabaseUtils databaseUtils = new DatabaseUtils();
        databaseUtils.createInitialState();
        forecastRepository = new ForecastRepository();
    }

    @Test
    void forecast_repository_should_return_not_null_list() {
        int expectedInitialListSize = 36;
        assertNotNull(forecastRepository.findAll());
        assertEquals(expectedInitialListSize, forecastRepository.findAll().size());
    }

    @Test
    void given_a_forecast_entity_manager_should_save_to_sb_successfully() {
        Forecast forecast = new Forecast(LocalTime.of(6,0), LocalDate.of(2020,11,23),30,100f,2, WeatherCategory.Cloudy,service, location);
        forecastRepository.save(forecast);

        assertEquals(Optional.of(forecast),forecastRepository.findOneById(forecast.getId()));
    }

    @Test
    void given_a_forecast_deletion_should_succeed() {
        Forecast forecast = new Forecast(LocalTime.of(6,0), LocalDate.of(2020,11,23),30,100f,2, WeatherCategory.Cloudy,service, location);
        forecastRepository.save(forecast);

        assertEquals(Optional.of(forecast),forecastRepository.findOneById(forecast.getId()));
        forecastRepository.delete(forecast);

        assertEquals(Optional.empty(), forecastRepository.findOneById(forecast.getId()));

    }



    @Test
    void given_a_location_entity_manager_should_find_all_forecast_successfully() {
        int expectedNumberOfLocation = 36;
        String expectedLocation = "athens";
        Location location = new Location(expectedLocation);

        assertEquals(expectedNumberOfLocation,forecastRepository.findByLocation(location).size());
        for(Forecast forecast :forecastRepository.findByLocation(location)){
            assertEquals(expectedLocation, forecast.getLocation().getCityName());
        }
    }
    @Test
    void given_a_location_entity_manager_should_find_current_forecast() {
        String expectedLocation = "athens";
        Location location = new Location(expectedLocation);
        assertEquals(expectedLocation,forecastRepository.findCurrentByLocation(location, true).get(0).getLocation().getCityName());
    }
    @Test
    void given_a_location_andTime_should_find_current_forecast() {
        String expectedLocation = "athens";
        Location location = new Location(expectedLocation);
        assertEquals(expectedLocation,forecastRepository.findByLocationTime(location,LocalTime.of(6,0), true).get(0).getLocation().getCityName());
    }
    @Test
    void given_a_location_time_and_date_should_find_current_forecast() {
        String expectedLocation = "athens";
        Location location = new Location(expectedLocation);
        assertEquals(expectedLocation,forecastRepository.findByLocationDateAndTime(location,LocalDate.of(2020,11,23), LocalTime.of(6,0)).get(0).getLocation().getCityName());
    }


    @AfterEach
    void tearDown() {
        DatabaseUtils databaseUtils = new DatabaseUtils();
        databaseUtils.eraseAllData();
    }
}