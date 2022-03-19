package com.example.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

import com.example.domain.User;
import com.example.dtos.in.RegisterUserDTO;
import com.example.repository.UserRepository;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import java.util.HashSet;
import java.util.Set;
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
@TestHTTPEndpoint(UserResource.class)
class UserResourceTests {

    @Inject
    UserRepository userRepo;

    private User user;


    @BeforeEach
    void init() {
        userRepo.deleteAll();
        user = new User("bill2", "password2", 1, new HashSet<>());
        userRepo.persist(user);
    }

    @AfterEach
    void clear() {
        userRepo.deleteAll();
    }

    @Test
    void given_a_new_user_IT_should_be_saved_successfully() {
        RegisterUserDTO dto = new RegisterUserDTO("password", "username", 1);
        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
            .post()
            .then()
            .statusCode(200).assertThat().body("objectID", greaterThan(1));

    }

    @Test
    void given_a_user_id_IT_should_return_one_successfully() {
        given()
            .pathParam("userId", user.getId())
            .when()
            .get("{userId}")
            .then()
            .statusCode(200).assertThat().body("name", is(user.getName()));
    }

    @Test
    void given_a__non_existing_user_id_IT_should_return_one_successfully() {
        given()
            .pathParam("userId", 9999)
            .when()
            .get("{userId}")
            .then()
            .statusCode(400);
    }

    @Test
    void IT_should_return_all_users() {
        given()
            .when()
            .get()
            .then()
            .statusCode(200).assertThat().body("size()", is(1));
    }

    @Test
    void given_a_user_id_IT_should_return_users_location() {
        given()
            .pathParam("userId", user.getId())
            .when()
            .get("location/{userId}")
            .then()
            .statusCode(200);
    }

    @Test
    void given_a_non_existing_user_id_IT_should_return_users_location() {
        given()
            .pathParam("userId", 99)
            .when()
            .get("location/{userId}")
            .then()
            .statusCode(400);
    }


    @Test
    void given_a_non_existing_user_id_IT_should_return_users_forecast_providers() {
        given()
            .pathParam("userId", 999)
            .when()
            .get("forecast-providers/{userId}")
            .then()
            .statusCode(400);
    }

    @Test
    void given_a_user_id_IT_should_return_users_forecast_providers() {
        given()
            .pathParam("userId", user.getId())
            .when()
            .get("forecast-providers/{userId}")
            .then()
            .statusCode(200).assertThat().body("size()", is(0));
    }

}