package com.example.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

import com.example.domain.Forecast;
import com.example.domain.Location;
import com.example.domain.Rating;
import com.example.dtos.in.CreateForecastRatingDTO;
import com.example.enums.WeatherCategory;
import com.example.repository.ForecastRatingRepository;
import com.example.repository.ForecastRepository;
import com.example.repository.LocationRepository;
import com.example.usecases.rating.CreateForecastRating;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import java.time.Instant;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@QuarkusTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Transactional
@TestHTTPEndpoint(RatingResource.class)
class RatingResourceTests {

    @Inject
    LocationRepository locationRepo;
    @Inject
    ForecastRepository forecastRepo;
    @Inject
    ForecastRatingRepository forecastRatingRepo;


    private Location location;

    private Forecast forecast;
    private Rating rating;

    @BeforeEach
    void init() {
        forecastRatingRepo.deleteAll();
        forecastRepo.deleteAll();
        locationRepo.deleteAll();
        location = new Location("athens", 0,0);
        locationRepo.persist(location);
        forecast = createForecast(location);
        rating = new Rating(1, forecast, 1);
        forecastRatingRepo.persist(rating);
    }

    @AfterEach
    void clear() {
        forecastRatingRepo.deleteAll();
        forecastRepo.deleteAll();
        locationRepo.deleteAll();
    }

    private Forecast createForecast(Location location) {
        Forecast forecast = new Forecast(Instant.now(), 1, 1L, 1, WeatherCategory.Cold, location, 1L);
        forecastRepo.persist(forecast);
        return forecast;
    }

    @Test
    void given_a_new_rating_IT_should_be_saved_successfully() {
        CreateForecastRatingDTO dto = new CreateForecastRatingDTO(1, forecast.getId(),1);
        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
            .post()
            .then()
            .statusCode(200);
    }

    @Test
    void given_an_id_it_should_return_a_rating() {
        given()
            .pathParam("ratingId", rating.getId())
            .when()
            .get("{ratingId}")
            .then()
            .statusCode(200).body("id", is((int)rating.getId()));
    }
    @Test
    void given_a_forecast_id_IT_should_delete_it_successfully() {
        given()
            .pathParam("ratingId", rating.getId())
            .when()
            .delete("{ratingId}")
            .then()
            .statusCode(204);
    }

}