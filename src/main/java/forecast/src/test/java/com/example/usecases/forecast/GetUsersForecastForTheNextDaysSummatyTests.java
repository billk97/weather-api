package com.example.usecases.forecast;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import com.example.adapters.users.dtos.UserDTO;
import com.example.adapters.users.ports.UserPort;
import com.example.domain.Forecast;
import com.example.domain.Location;
import com.example.dtos.out.ForecastSummaryDTO;
import com.example.enums.ErrorCode;
import com.example.enums.WeatherCategory;
import com.example.exceptions.IllegalArgumentExceptionWithCode;
import com.example.repository.ForecastRepository;
import com.example.repository.LocationRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
class GetUsersForecastForTheNextDaysSummatyTests {

    @Inject
    private ForecastRepository forecastRepo;

    @Inject
    private LocationRepository locationRepo;

    @Inject
    private GetUsersForecastForTheNextDaysSummary getUsersForecastForTheNextDaysSummary;

    @InjectMock
    @RestClient
    private UserPort userPort;

    @AfterEach
    void tierDown() {
        forecastRepo.deleteAll();
        locationRepo.deleteAll();
    }

    @Test
    void given_non_numeric_userId_or_number_of_days_IT_should_throw() {
        assertThrows(IllegalArgumentExceptionWithCode.class, () -> {
           getUsersForecastForTheNextDaysSummary.query("aa", "1");
        });
        assertThrows(IllegalArgumentExceptionWithCode.class, () -> {
            getUsersForecastForTheNextDaysSummary.query("1", "aa");
        });

        assertThrows(IllegalArgumentExceptionWithCode.class, () -> {
            getUsersForecastForTheNextDaysSummary.query("aa", "aa");
        });

    }

    @Test
    void given_non_existing_user_IT_should_throw() {
        Mockito
            .when(userPort.findUserById(any()))
            .thenReturn(Optional.empty());
        IllegalArgumentExceptionWithCode e = assertThrows(IllegalArgumentExceptionWithCode.class, () -> {
            getUsersForecastForTheNextDaysSummary.query("1", "1");
        });
        assertEquals(ErrorCode.USER_NOT_FOUND, e.getErrorCode());
    }

    @Test
    void given_no_forecast_for_the_time_range_IT_should_return_empty_list() {
        Location location = new Location("mexico", 1.0, 1.0);
        locationRepo.persist(location);

        UserDTO dto = new UserDTO(1, "bill", location.getLocationId(), Set.of(1L));
        Mockito
            .when(userPort.findUserById(any()))
            .thenReturn(Optional.of(dto));
        Forecast forecast = new Forecast(Instant.now().minus(3, ChronoUnit.DAYS), 1, 1L, 1, WeatherCategory.Cold, location, 1L);
        forecastRepo.persist(forecast);
        Forecast forecast2 = new Forecast(Instant.now().plus(5, ChronoUnit.DAYS), 1, 1L, 1, WeatherCategory.Cold, location, 2L);
        forecastRepo.persist(forecast2);
        Forecast forecast3 = new Forecast(Instant.now().minus(10, ChronoUnit.DAYS), 1, 1L, 1, WeatherCategory.Cold, location, 1L);
        forecastRepo.persist(forecast3);

        List<ForecastSummaryDTO> forecastList = getUsersForecastForTheNextDaysSummary.query("1", "3");
        assertEquals(0, forecastList.size());

    }


    @Test
    void given_forecasts_for_the_time_range_IT_should_return_the_forecast() {
        Location location = new Location("mexico", 1.0, 1.0);
        locationRepo.persist(location);
        UserDTO dto = new UserDTO(1, "bill", location.getLocationId(), Set.of(1L));
        Mockito
            .when(userPort.findUserById(any()))
            .thenReturn(Optional.of(dto));
        Forecast forecast = new Forecast(Instant.now(), 1, 1L, 1, WeatherCategory.Cold, location, 1L);
        forecastRepo.persist(forecast);
        Forecast forecast2 = new Forecast(Instant.now().plus(2, ChronoUnit.DAYS), 1, 1L, 1, WeatherCategory.Cold, location, 2L);
        forecastRepo.persist(forecast2);
        Forecast forecast3 = new Forecast(Instant.now().minus(2, ChronoUnit.DAYS), 1, 1L, 1, WeatherCategory.Cold, location, 1L);
        forecastRepo.persist(forecast3);

        List<ForecastSummaryDTO> forecastList = getUsersForecastForTheNextDaysSummary.query("1", "3");
        assertEquals(1, forecastList.size());


    }

}