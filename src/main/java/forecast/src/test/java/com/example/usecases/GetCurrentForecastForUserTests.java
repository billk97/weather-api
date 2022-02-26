package com.example.usecases;

import static org.junit.jupiter.api.Assertions.*;

import com.example.enums.ErrorCode;
import com.example.exceptions.IllegalArgumentExceptionWithCode;
import com.example.repository.ForecastRepository;
import com.example.repository.LocationRepository;
import io.quarkus.test.junit.QuarkusTest;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@QuarkusTest
@Transactional
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GetCurrentForecastForUserTests {

    @Inject
    private ForecastRepository forecastRepository;

    @Inject
    private GetCurrentForecastForUser getCurrentForecastForUser;

    @Test
    void given_a_non_numeric_user_id_IT_should_throw() {
        IllegalArgumentExceptionWithCode e = assertThrows(IllegalArgumentExceptionWithCode.class, ()-> {
            getCurrentForecastForUser.query("hello");
        });
        assertEquals(ErrorCode.INVALID_INPUT, e.getErrorCode());
    }

    @Test
    void given_a_non_existing_user_id_IT_should_throw() {
        IllegalArgumentExceptionWithCode e = assertThrows(IllegalArgumentExceptionWithCode.class, ()-> {
            getCurrentForecastForUser.query(null);
        });
        assertEquals(ErrorCode.INVALID_INPUT, e.getErrorCode());
    }

    @Test
    void given_a_user_with_no_shaved_locations_IT_should_throw() {

    }

    @Test
    void given_a_user_with_invalid_location_ids_IT_should_throw() {

    }

    @Test
    void given_a_user_id_but_no_forecast_for_current_time_IT_should_throw() {

    }

    @Test
    void given_a_user_id_IT_should_return_the_most_recent() {
    }


    @Test
    void given_a_user_with_one_location_and_one_service_IT_should_return_one_forecast(){

    }

    @Test
    void given_a_user_with_one_location_and_multiple_service_IT_should_return_multiple_forecast(){

    }



}