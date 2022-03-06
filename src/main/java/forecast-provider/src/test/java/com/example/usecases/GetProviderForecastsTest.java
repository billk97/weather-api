package com.example.usecases;

import com.example.domain.Forecast;
import com.example.domain.ForecastProvider;
import com.example.domain.Location;
import com.example.dtos.in.CreateForecastDTO;
import com.example.enums.WeatherCategory;
import com.example.repositories.ForecastProviderRepository;
import com.example.repositories.ForecastRepository;
import com.example.repositories.LocationRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import javax.transaction.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@Transactional
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GetProviderForecastsTest {

    @Inject
    ForecastRepository forecastRepo;

    @Inject
    ForecastProviderRepository providerRepo;

    @Inject
    LocationRepository locationRepo;

    @Inject
    GetProviderForecasts getProviderForecasts;

    private Location loc;

    private ForecastProvider provider;

    private Forecast forecastFirst;

    private Forecast forecastSecond;

    @BeforeEach
    void init(){
        forecastRepo.deleteAll();
        locationRepo.deleteAll();
        providerRepo.deleteAll();


        loc = new Location("Larisa", 39.6237566,22.4128815);
        locationRepo.persistAndFlush(loc);

        provider = new ForecastProvider("Meteo", "The most inaccurate forecast provider in Greece!");
        providerRepo.persistAndFlush(provider);

        CreateForecastDTO dto = new CreateForecastDTO(Instant.now().toString(), 1, 1l, 1 , WeatherCategory.Cold, provider.getId(), loc.getLocationId());

        forecastFirst = new Forecast(dto, loc, provider);
        forecastRepo.persistAndFlush(forecastFirst);

        dto = new CreateForecastDTO(Instant.now().plus(1, ChronoUnit.DAYS).toString(), 1, 1l, 5 , WeatherCategory.Windy, provider.getId(), loc.getLocationId());

        forecastSecond = new Forecast(dto, loc, provider);
        forecastRepo.persistAndFlush(forecastSecond);


    }

    @AfterEach
    void tearDown(){
        forecastRepo.deleteAll();
        locationRepo.deleteAll();
        providerRepo.deleteAll();
    }

    @Test
    void given_numeric_and_existing_provider_should_fetch_two_forecasts() {

        List<Forecast> forecasts = getProviderForecasts.query(String.valueOf(provider.getId()));

        assertEquals(2, forecasts.size());

        assertEquals(forecasts.get(0), forecastFirst);
    }
}