package com.example.resource;

import static io.restassured.RestAssured.given;

import com.example.dtos.in.CreateLocationDTO;
import com.example.repository.LocationRepository;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import javax.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@QuarkusTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestTransaction
@TestHTTPEndpoint(LocationResource.class)
class LocationResourceTests {

    @Inject
    LocationRepository locationRepo;

    @AfterEach
    void tearDown() {
        locationRepo.deleteAll();
    }

    @Test
    void given_a_valid_json_location_object_IT_should_be_saved_successfully() {
        CreateLocationDTO dto = new CreateLocationDTO("athens", 0,0);
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

}