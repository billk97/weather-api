package com.example.usecases.forecast;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import com.example.adapters.users.dtos.UserDTO;
import com.example.adapters.users.ports.UserPort;
import com.example.domain.Forecast;
import com.example.domain.Location;
import com.example.enums.ErrorCode;
import com.example.enums.WeatherCategory;
import com.example.exceptions.IllegalArgumentExceptionWithCode;
import com.example.repository.ForecastRepository;
import com.example.repository.LocationRepository;
import com.example.usecases.forecast.GetCurrentForecastForUser;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
@Transactional
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GetCurrentForecastForUserTests {

    @Inject
    ForecastRepository forecastRepo;

    @Inject
    LocationRepository locationRepo;

    @Inject
    GetCurrentForecastForUser getCurrentForecastForUser;

    @InjectMock
    @RestClient
    UserPort userPort;

    @AfterEach
    void tierDown() {
        forecastRepo.deleteAll();
        locationRepo.deleteAll();
    }

    @Test
    void given_a_non_numeric_user_id_IT_should_throw() {
        IllegalArgumentExceptionWithCode e = assertThrows(IllegalArgumentExceptionWithCode.class, ()-> {
            getCurrentForecastForUser.query("hello");
        });
        assertEquals(ErrorCode.INVALID_INPUT, e.getErrorCode());
    }

    @Test
    void given_a_non_existing_user_id_IT_should_throw() {
        IllegalArgumentExceptionWithCode e = assertThrows(IllegalArgumentExceptionWithCode.class, ()-> {
            getCurrentForecastForUser.query(null);
        });
        assertEquals(ErrorCode.INVALID_INPUT, e.getErrorCode());
    }

    @Test
    void given_a_user_with_no_shaved_locations_IT_should_throw() {
        UserDTO dto = new UserDTO(1, "bill", 1, Set.of());
        Mockito
            .when(userPort.findUserById(any()))
            .thenReturn(Optional.of(dto));
        IllegalArgumentExceptionWithCode e = assertThrows(IllegalArgumentExceptionWithCode.class, ()-> {
            getCurrentForecastForUser.query("1");
        });
        assertEquals(ErrorCode.MISSING_USER_FORECAST_PROVIDERS, e.getErrorCode());
    }

    @Test
    void given_a_user_with_invalid_location_ids_IT_should_throw() {
        UserDTO dto = new UserDTO(1, "bill", 0, Set.of());
        Mockito
            .when(userPort.findUserById(any()))
            .thenReturn(Optional.of(dto));
        IllegalArgumentExceptionWithCode e = assertThrows(IllegalArgumentExceptionWithCode.class, ()-> {
            getCurrentForecastForUser.query("1");
        });
        assertEquals(ErrorCode.INVALID_INPUT, e.getErrorCode());
    }

    @Test
    void given_a_user_id_but_no_forecast_for_current_time_IT_should_throw() {
        UserDTO dto = new UserDTO(1, "bill", 1, Set.of(1L, 2L));
        Mockito
            .when(userPort.findUserById(any()))
            .thenReturn(Optional.of(dto));

        List<Forecast> forecasts = getCurrentForecastForUser.query("1");
        assertEquals(0, forecasts.size());

    }

    @Test
    void given_a_user_id_IT_should_return_the_most_recent() {
        Location location = new Location("mexico", 1.0, 1.0);
        locationRepo.persist(location);
        UserDTO dto = new UserDTO(1, "bill", location.getLocationId(), Set.of(1L, 2L));
        Mockito
            .when(userPort.findUserById(any()))
            .thenReturn(Optional.of(dto));


        Forecast forecast = new Forecast(Instant.now(), 1, 1L, 1, WeatherCategory.Cold, location, 1L);
        forecastRepo.persist(forecast);
        List<Forecast> forecasts = getCurrentForecastForUser.query("1");
        assertEquals(1, forecasts.size());
        assertEquals(forecast.getHumidity(), forecasts.get(0).getHumidity());
    }


    @Test
    void given_a_user_with_one_location_and_one_service_IT_should_return_one_forecast(){

        Location location = new Location("mexico", 1.0, 1.0);
        locationRepo.persist(location);
        Location location2 = new Location("USA", 1.0, 1.0);
        locationRepo.persist(location2);

        UserDTO dto = new UserDTO(1, "bill", location.getLocationId(), Set.of(1L));
        Mockito
            .when(userPort.findUserById(any()))
            .thenReturn(Optional.of(dto));
        Forecast forecast = new Forecast(Instant.now(), 1, 1L, 1, WeatherCategory.Cold, location, 1L);
        forecastRepo.persist(forecast);
        Forecast forecast2 = new Forecast(Instant.now(), 1, 1L, 1, WeatherCategory.Cold, location2, 2L);
        forecastRepo.persist(forecast2);

        List<Forecast> forecasts = getCurrentForecastForUser.query("1");
        assertEquals(1, forecasts.size());
        assertEquals(forecast.getForecastProviderId(), forecasts.get(0).getForecastProviderId());

    }

    @Test
    void given_a_user_with_one_location_and_multiple_service_IT_should_return_multiple_forecast(){
        Location location = new Location("mexico", 1.0, 1.0);
        locationRepo.persist(location);
        Location location2 = new Location("USA", 1.0, 1.0);
        locationRepo.persist(location2);

        UserDTO dto = new UserDTO(1, "bill", location.getLocationId(), Set.of(1L, 2L));
        Mockito
            .when(userPort.findUserById(any()))
            .thenReturn(Optional.of(dto));
        Forecast forecast = new Forecast(Instant.now(), 1, 1L, 1, WeatherCategory.Cold, location, 1L);
        forecastRepo.persist(forecast);
        Forecast forecast2 = new Forecast(Instant.now(), 1, 1L, 1, WeatherCategory.Cold, location, 2L);
        forecastRepo.persist(forecast2);

        List<Forecast> forecasts = getCurrentForecastForUser.query("1");
        assertEquals(2, forecasts.size());
        assertEquals(forecast.getForecastProviderId(), forecasts.get(0).getForecastProviderId());

    }



}