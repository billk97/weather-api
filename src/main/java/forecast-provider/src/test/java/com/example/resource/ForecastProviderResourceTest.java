package com.example.resource;

import static io.restassured.RestAssured.given;

import com.example.domain.ForecastProvider;
import com.example.dtos.in.CreateForecastProviderDTO;
import com.example.repositories.ForecastProviderRepository;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@Transactional
@TestHTTPEndpoint(ForecastProviderResource.class)
class ForecastProviderResourceTest {

//
//    Test edit provider
//    Test getForecastProvider

    @Inject
    ForecastProviderRepository providerRepo;


    @BeforeEach
    void init() {
//        providerRepo.deleteAll();
        ForecastProvider provider = new ForecastProvider("Meteo", "The most inaccurate forecast provider in Greece!");
        providerRepo.persist(provider);
    }

    @Test
    void given_a_json_with_empty_name_should_fail_with_status_400(){
        CreateForecastProviderDTO dto = new CreateForecastProviderDTO(null, "Description");

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post()
                .then()
                .statusCode(400);
    }

    @Test
    void given_a_json_with_name_should_store() {
        CreateForecastProviderDTO dto = new CreateForecastProviderDTO("Google", "Somewhat ok");

                given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post()
                .then()
                .statusCode(200);
    }
    @Test
    void assert_endpoint_fetches_all_forecast_providers() {
        long providerCount = providerRepo.findAll().count();

        ForecastProvider[] providers = given()
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().body().as(ForecastProvider[].class);

        assertEquals(providerCount, providers.length);
    }

    @Test
    void given_a_json_with_empty_name_should_fail_edit(){
        CreateForecastProviderDTO dto = new CreateForecastProviderDTO(null, "Somewhat ok");
        List<ForecastProvider> providers = providerRepo.findAll().list();

        given()
                .contentType(ContentType.JSON)
                .pathParam("forecastProviderId", providers.get(0).getId())
                .body(dto)
                .when()
                .post("{forecastProviderId}/edit")
                .then()
                .statusCode(400);
    }



}