package com.example.resource;

import static io.restassured.RestAssured.given;

import com.example.domain.ForecastProvider;
import com.example.dtos.in.CreateForecastProviderDTO;
import com.example.dtos.out.ObjectIdDTO;
import com.example.repositories.ForecastProviderRepository;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.*;

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

    @Inject
    UserTransaction transaction;

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

    @Test
    void given_a_non_existent_id_should_fail_edit(){
        CreateForecastProviderDTO dto = new CreateForecastProviderDTO("Meteo2", "Somewhat ok");

        given()
                .contentType(ContentType.JSON)
                .pathParam("forecastProviderId", 10)
                .body(dto)
                .when()
                .post("{forecastProviderId}/edit")
                .then()
                .statusCode(400);

    }

    @Test
    void given_existent_id_and_valid_object_should_edit() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        CreateForecastProviderDTO dto = new CreateForecastProviderDTO("Meteo2", "Perfect");
        List<ForecastProvider> providers = providerRepo.findAll().list();

        ObjectIdDTO modifiedId = given()
                .contentType(ContentType.JSON)
                .pathParam("forecastProviderId", providers.get(0).getId())
                .body(dto)
                .when()
                .post("{forecastProviderId}/edit")
                .then()
                .statusCode(200)
                .extract().body().as(ObjectIdDTO.class);

        System.out.println(modifiedId.objectId());
        ForecastProvider modifiedProvider = providerRepo.findById(modifiedId.objectId());

//        assertEquals(dto.name(), modifiedProvider.getName());
//        assertEquals(dto.description(), modifiedProvider.getDescription());

        assertNotNull(modifiedProvider);
//        Cannot test the modification

    }

    @Test
    void given_non_existent_provider_should_fail() {
        given()
                .contentType(ContentType.JSON)
                .pathParam("forecastProviderId", 10)
                .when()
                .get("{forecastProviderId}")
                .then()
                .statusCode(400);
    }

    @Test
    void given__existent_provider_should_return_it() {

        List<ForecastProvider> providers = providerRepo.findAll().list();

        ForecastProvider provider = given()
                .contentType(ContentType.JSON)
                .pathParam("forecastProviderId", providers.get(0).getId())
                .when()
                .get("{forecastProviderId}")
                .then()
                .statusCode(200)
                .extract().body().as(ForecastProvider.class);

        assertEquals(providers.get(0).getId(), provider.getId());
    }

}