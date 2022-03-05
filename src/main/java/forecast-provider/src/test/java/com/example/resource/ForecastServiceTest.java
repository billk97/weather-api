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
import java.util.List;

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

    private Location loc;

    private ForecastProvider provider;

    private Forecast forecast;

    @BeforeEach
    void init() {
        forecastRepo.deleteAll();
        locationRepo.deleteAll();
        providerRepo.deleteAll();


        loc = new Location("Larisa", 39.6237566,22.4128815);
        locationRepo.persistAndFlush(loc);

        provider = new ForecastProvider("Meteo", "The most inaccurate forecast provider in Greece!");
        providerRepo.persistAndFlush(provider);

        CreateForecastDTO dto = new CreateForecastDTO(Instant.now().toString(), 1, 1l, 1 , WeatherCategory.Cold, provider.getId(), loc.getLocationId());

        forecast = new Forecast(dto, loc, provider);
        forecastRepo.persistAndFlush(forecast);


    }

    @Test
    void on_empty_location_should_fail(){

        CreateForecastDTO dto = new CreateForecastDTO(Instant.now().toString(), 1, 1l, 1 , WeatherCategory.Cold, provider.getId(), 9999);

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
        CreateForecastDTO dto = new CreateForecastDTO(Instant.now().toString(), 1, 1l, 1 , WeatherCategory.Cold, 9999, loc.getLocationId());

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
        CreateForecastDTO dto = new CreateForecastDTO(Instant.now().toString(), 1, 1l, 1 , WeatherCategory.Cold, provider.getId(), loc.getLocationId());

        ObjectIdDTO newForecastId = given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().body().as(ObjectIdDTO.class);

        Forecast newForecast = forecastRepo.findById(newForecastId.objectId());

        assertNotNull(newForecast);
    }

    @Test
    void on_non_numeric_provider_id_should_fail(){
        given()
                .contentType(ContentType.JSON)
                .pathParam("providerId", "hello")
                .when()
                .get("provider-forecasts/{providerId}")
                .then()
                .statusCode(400);
    }

    @Test
    void on_numeric_provider_on_null_provider_should_fail(){
        given()
                .contentType(ContentType.JSON)
                .pathParam("providerId", "9999")
                .when()
                .get("provider-forecasts/{providerId}")
                .then()
                .statusCode(400);
    }

    @Test
    void on_numeric_and_present_provider_should_fetch_one_forecast(){

        given()
                .contentType(ContentType.JSON)
                .pathParam("providerId", String.valueOf(provider.getId()))
                .when()
                .get("provider-forecasts/{providerId}")
                .then()
                .statusCode(200);


    }
}