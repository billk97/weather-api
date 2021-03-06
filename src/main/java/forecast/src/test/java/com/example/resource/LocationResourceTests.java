package com.example.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.domain.Location;
import com.example.dtos.in.CreateLocationDTO;
import com.example.repository.ForecastRepository;
import com.example.repository.LocationRepository;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.jupiter.api.*;

@QuarkusTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Transactional
@TestHTTPEndpoint(LocationResource.class)
class LocationResourceTests {

    @Inject
    LocationRepository locationRepo;
    @Inject
    ForecastRepository forecastRepository;

    private Location location;

    @BeforeEach
    void init() {
        forecastRepository.deleteAll();
        locationRepo.deleteAll();
        location = new Location("athens", 0,0);
        locationRepo.persist(location);
    }

    @AfterEach
    void tearDown() {
        forecastRepository.deleteAll();
        locationRepo.deleteAll();
    }

    @Test
    void given_a_valid_json_location_object_IT_should_be_saved_successfully() {
        CreateLocationDTO dto = new CreateLocationDTO("thesaloniki", 0,0);
        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
            .post()
            .then()
            .statusCode(200);
    }

    @Test
    void given_a_json_with_null_location_IT_should_fail_with_status_400() {
        CreateLocationDTO dto = new CreateLocationDTO(null, 0,0);
        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
            .post()
            .then()
            .statusCode(400);
    }

    @Test
    void given_a_location_id_IT_should_return_the_location() {
        assertEquals(1, locationRepo.count());
        given()
            .pathParam("locationId", location.getLocationId())
            .when()
            .get("{locationId}")
            .then()
            .statusCode(200)
            .body("name", equalTo(location.getName()));
    }

    @Test
    void given_a_non_existing_location_id_IT_should_throw() {

        given()
            .contentType(ContentType.JSON)
            .pathParam("locationId", 99999)
            .when()
            .get("{locationId}")
            .then()
            .statusCode(400);
    }

    @Test
    void IT_should_return_all_locations() {
        given()
            .contentType(ContentType.JSON)
            .when()
            .get()
            .then()
            .statusCode(200)
            .body(notNullValue())
        ;
    }

}