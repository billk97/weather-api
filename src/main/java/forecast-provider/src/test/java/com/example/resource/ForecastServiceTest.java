package com.example.resource;

import com.example.domain.Forecast;
import com.example.domain.ForecastProvider;
import com.example.domain.Location;
import com.example.dtos.in.CreateForecastDTO;
import com.example.dtos.in.CreateLocationDTO;
import com.example.dtos.out.ObjectIdDTO;
import com.example.enums.WeatherCategory;
import com.example.repositories.ForecastProviderRepository;
import com.example.repositories.ForecastRepository;
import com.example.repositories.LocationRepository;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import java.time.Instant;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@Transactional
@TestHTTPEndpoint(ForecastService.class)
class ForecastServiceTest {

    @Inject
    LocationRepository locationRepo;

    @Inject
    ForecastRepository forecastRepo;

    @Inject
    ForecastProviderRepository providerRepo;

    @BeforeEach
    void init() {

        locationRepo.deleteAll();
        providerRepo.deleteAll();
        forecastRepo.deleteAll();

        Location loc = new Location("Larisa", 39.6237566,22.4128815);
        locationRepo.persist(loc);

        ForecastProvider provider = new ForecastProvider("Meteo", "The most inaccurate forecast provider in Greece!");
        providerRepo.persist(provider);

        CreateForecastDTO dto = new CreateForecastDTO(Instant.now().toString(), 1, 1l, 1 , WeatherCategory.Cold, provider.getId(), loc.getLocationId());

        Forecast forecast = new Forecast(dto, loc, provider);
        forecastRepo.persist(forecast);
    }

    @Test
    void on_empty_location_should_fail(){

        CreateForecastDTO dto = new CreateForecastDTO(Instant.now().toString(), 1, 1l, 1 , WeatherCategory.Cold, 1, 10);

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post()
                .then()
                .statusCode(400);
    }

    @Test
    void on_empty_provider_should_fail(){
        CreateForecastDTO dto = new CreateForecastDTO(Instant.now().toString(), 1, 1l, 1 , WeatherCategory.Cold, 100, 1);

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post()
                .then()
                .statusCode(400);
    }

    @Test
    void given_proper_location_and_provider_should_save_successfully(){
        CreateForecastDTO dto = new CreateForecastDTO(Instant.now().toString(), 1, 1l, 1 , WeatherCategory.Cold, 1, 1);

        ObjectIdDTO newForecastId = given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post()
                .then()
                .statusCode(400)
                .extract().body().as(ObjectIdDTO.class);

        Forecast newForecast = forecastRepo.findById(newForecastId.objectId());

        assertNotNull(newForecast);
    }
}