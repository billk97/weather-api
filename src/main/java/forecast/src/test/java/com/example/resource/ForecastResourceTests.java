package com.example.resource;

import com.example.domain.Forecast;
import com.example.domain.Location;
import com.example.dtos.in.CreateForecastDTO;
import com.example.enums.WeatherCategory;
import com.example.repository.ForecastRepository;
import com.example.repository.LocationRepository;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.xml.crypto.Data;

import java.sql.Date;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Transactional
@TestHTTPEndpoint(ForecastResource.class)
class ForecastResourceTests {

    @Inject
    LocationRepository locationRepo;
    @Inject
    ForecastRepository forecastRepo;

    private Location location;

    @BeforeEach
    void init() {
        forecastRepo.deleteAll();
        locationRepo.deleteAll();
        location = new Location("athens", 0,0);
        locationRepo.persist(location);
    }

    @AfterEach
    void tearDown() {
        forecastRepo.deleteAll();
        locationRepo.deleteAll();
    }

    @Test
    void given_a_non_numeric_forecast_id_IT_should_throw_400() {

        given()
                .pathParam("forecastId",  "AAAA")
                .when()
                .get("/{forecastId}")
                .then()
                .statusCode(400);
    }

    @Test
    void given_a_forecast_IT_should_return_it() {
        Location location = new Location("mexico", 1.0, 1.0);
        locationRepo.persist(location);
        Forecast forecast = new Forecast(Instant.now(), 1, 1L, 1, WeatherCategory.Cold, location, 1L);
        forecastRepo.persist(forecast);
        given().when().get().then().statusCode(200);
    }

    @Test
    void given_a_null_forecast_IT_should_add_IT_successfully() {
        Location location = new Location("mexico", 1.0, 1.0);
        locationRepo.persist(location);
        DateTimeFormatter.ISO_INSTANT.format(Instant.now());
        CreateForecastDTO dto = new CreateForecastDTO(
                null, 1, 1,1, WeatherCategory.Cold, 1, 1 );
        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post()
                .then()
                .statusCode(400);
    }

    @Test
    void given_a_user_IT_should_return_one_successfully() {
        Location location = new Location("mexico", 1.0, 1.0);
        locationRepo.persist(location);
        given()
                .queryParam("userId",  1)
                .when()
                .get("current")
                .then()
                .statusCode(200);
    }

    @Test
    void given_a_user__and_a_number_of_days_IT_should_return_one_successfully() {
        Location location = new Location("mexico", 1.0, 1.0);
        locationRepo.persist(location);
        given()
                .pathParam("numberOfDays", "2")
                .queryParam("userId",  "1")
                .when()
                .get("days/{numberOfDays}")
                .then()
                .statusCode(200);
    }

    @Test
    void given_a_user__and_a_number_of_hours_IT_should_return_none() {
        Location location = new Location("mexico", 1.0, 1.0);
        locationRepo.persist(location);
        given()
                .pathParam("numberOfHours", "2")
                .queryParam("userId",  "1")
                .when()
                .get("hours/{numberOfHours}")
                .then()
                .statusCode(204);
    }

    @Test
    void given_a_user__and_a_number_of_hours_summary_IT_should_return_none() {
        Location location = new Location("mexico", 1.0, 1.0);
        locationRepo.persist(location);
        given()
                .pathParam("numberOfHours", "1")
                .queryParam("userId",  "1")
                .when()
                .get("hours/{numberOfHours}/summary")
                .then()
                .statusCode(200);
    }

    @Test
    void given_a_user__and_a_number_of_days_summary_IT_should_return_one_successfully() {
        Location location = new Location("mexico", 1.0, 1.0);
        locationRepo.persist(location);
        given()
                .pathParam("numberOfDays", "2")
                .queryParam("userId",  "1")
                .when()
                .get("days/{numberOfDays}/summary")
                .then()
                .statusCode(200);
    }

}