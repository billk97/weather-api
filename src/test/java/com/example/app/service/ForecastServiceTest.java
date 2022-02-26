package com.example.app.service;

import com.example.app.domain.Forecast;
import com.example.app.domain.User;
import com.example.app.enums.WeatherCategory;
import com.example.app.persistence.DatabaseUtils;
import com.example.app.repository.ForecastRepository;
import com.example.app.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ForecastServiceTest {

    private ForecastService forecastService;
    private ForecastRepository forecastRepository;
    private UserRepository userRepository;

    @BeforeEach
    void init(){
        DatabaseUtils databaseUtils = new DatabaseUtils();
        databaseUtils.createInitialState();
        forecastService = new ForecastService();
        forecastRepository = new ForecastRepository();
        userRepository = new UserRepository();
    }

    @Test
    void given_a_forecast_addition_should_succeed(){
        int forecastId = forecastService.addNewForecast(LocalTime.of(7,0), LocalDate.of(2020,9,23),30,100f,2, WeatherCategory.Cloudy,"meteo", "athens");
        assertEquals(forecastId, forecastRepository.findOneById(forecastId).get().getId());
    }

    @Test
    void given_invalid_forecast_and_location_addition_should_fail(){
        assertThrows(IllegalArgumentException.class, ()->{
            forecastService.addNewForecast(LocalTime.of(7,0), LocalDate.of(2020,9,23),30,100f,2, WeatherCategory.Cloudy,"meteo", "sdfnkjlsdnd");
        });

        assertThrows(IllegalArgumentException.class, ()->{
            forecastService.addNewForecast(LocalTime.of(7,0), LocalDate.of(2020,9,23),30,100f,2, WeatherCategory.Cloudy,"meteosss", "athens");
        });

    }

    @Test
    void given_a_user_and_a_location_forecast_should_be_retrieved_successfully(){
        Forecast forecast = forecastService.getCurrentForecast("Bill", "athens",true);
        assertEquals(30,forecast.getTemperature());
    }
    @Test
    void given_a_user_and_a_location_forecast_should_be_retrieved_throw(){

        assertThrows(IllegalArgumentException.class, ()->{
            forecastService.getCurrentForecast("Bill", "athens",false);
        });
    }
    @Test
    void given_a_non_existing_user_forecast_should_throw(){
        assertThrows(IllegalArgumentException.class, ()->{
            forecastService.getCurrentForecast("bill", "athens",true);
        });
    }
    @Test
    void given_a_non_existing_location_forecast_should_throw(){
        assertThrows(IllegalArgumentException.class, ()->{
            forecastService.getCurrentForecast("Bill", "jdfjfjfathens", true);
        });
    }

    @Test
    void given_a_user_with_no_location_forecast_should_throw(){
        User newUser = new User("test", "test");
        newUser.setLocation(null);
        userRepository.save(newUser);
        assertThrows(IllegalArgumentException.class, ()->{
            forecastService.getCurrentForecast("test", null, true);
        });
    }

    @Test
    void given_null_location_forecast_should_throw(){
        assertThrows(IllegalArgumentException.class, ()->{
            forecastService.getCurrentForecast("Bill", null, true);
        });
    }

    @Test
    void given_an_user_location_forecast_for_the_next_three_hours_should_succeed(){
        List<Forecast> forecasts = forecastService.getNextThreeHoursForecast("Bill", "athens",true);
        assertEquals(30, forecasts.get(0).getTemperature());
    }

    @Test
    void given_a_false_user_forecast_for_the_next_three_hours_should_throw(){
        assertThrows(IllegalArgumentException.class, ()-> {
            forecastService.getNextThreeHoursForecast("ffffill", "athens",true);
        });
    }

    @Test
    void given_a_false_location_forecast_for_the_next_three_hours_should_throw(){
        assertThrows(IllegalArgumentException.class, ()-> {
            forecastService.getNextThreeHoursForecast("Bill", "athensdknnvd",true);
        });
    }

    @Test
    void given_a_false_user_forecast_for_the_next_three_days_should_throw(){
        assertThrows(IllegalArgumentException.class, ()-> {
            forecastService.getNextThreeDays("ffffill", "athens",true);
        });
    }

    @Test
    void given_a_false_location_forecast_for_the_next_three_days_should_throw(){
        assertThrows(IllegalArgumentException.class, ()-> {
            forecastService.getNextThreeDays("Bill", "athensdknnvd",true);
        });
    }

    @Test
    void given_a_false_location_forecast_for_the_next_three_days_summary_should_succeed(){
            List<Forecast> forecasts = forecastService.getNextThreeDays("Bill", "athens",true);
            assertEquals("athens",forecasts.get(0).getLocation().getCityName());
    }

    @Test
    void given_a_false_user_forecast_for_the_next_three_days_summary_should_throw(){
        assertThrows(IllegalArgumentException.class, ()-> {
            forecastService.getSingleNextThreeDays("ffffill", "athens",true);
        });
    }

    @Test
    void given_a_false_location_forecast_for_the_next_three_summary_days_should_throw(){
        assertThrows(IllegalArgumentException.class, ()-> {
            forecastService.getSingleNextThreeDays("Bill", "athensdknnvd",true);
        });
    }

    @Test
    void given_a_false_location_forecast_for_the_next_three_days_should_succeed(){
        List<Forecast> forecasts = forecastService.getSingleNextThreeDays("Bill", "athens",true);
        assertEquals("athens",forecasts.get(0).getLocation().getCityName());
    }

    @Test
    void given_a_locationName_and_user_warning_should_be_returned_successfully(){
        String response = forecastService.warnUsers( "Bill", true);
        assertEquals("There is no bad weather warning for today in location: athens",response);
    }
    @Test
    void given_a_false_locationName_and_user_warning_should_be_returned_successfully(){
        assertThrows(IllegalArgumentException.class,()->{
            User user = new User("djdj","ksks");
            userRepository.save(user);
            forecastService.warnUsers( "djdj", true);
        });

    }
    @Test
    void given_a_locationName_and_false_user_warning_should_be_returned_successfully(){
        assertThrows(IllegalArgumentException.class,()->{
            forecastService.warnUsers( "Bsssijjjjjll", true);
        });

    }
    @Test
    void given_a_locationName_and_false_user_warning_should_be_returned_successfully_no_mock(){
        assertThrows(IllegalArgumentException.class,()->{
            forecastService.warnUsers( "Bsssill",false);
        });

    }





}