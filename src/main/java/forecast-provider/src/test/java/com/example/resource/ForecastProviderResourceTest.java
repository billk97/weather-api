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

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@Transactional
@TestHTTPEndpoint(ForecastProviderResource.class)
class ForecastProviderResourceTest {

//    Test add forecast provider
//          give empty dto name and expect error
//          give proper name and assert that provider created and his name is the given
//    Test getAllProviders
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
    void this_is_ok(){

    }

//    @Test
//    void given_a_json_with_empty_name_should_fail_with_status_400(){
//        CreateForecastProviderDTO dto = new CreateForecastProviderDTO(null, "Description");
//
//        given()
//                .contentType(ContentType.JSON)
//                .body(dto)
//                .when()
//                .post()
//                .then()
//                .statusCode(400);
//    }
}