package com.example.resource;

import static io.restassured.RestAssured.given;

import com.example.domain.Location;
import com.example.dtos.in.CreateLocationDTO;
import com.example.repository.LocationRepository;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Transactional
@TestHTTPEndpoint(LocationResource.class)
class LocationResourceTests {

    @Inject
    LocationRepository locationRepo;

    private Location location;

    @BeforeEach
    void init() {
        location = new Location("athens", 0,0);
        locationRepo.persist(location);
    }

    @AfterEach
    void tearDown() {
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
    void given_a_non_existing_location_id_IT_should_return_the_location() {
    }

    @Test
    void iT_should_return_all_locations() {
    }

}