package com.example.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.dtos.in.CreateForecastDTO;
import com.example.enums.WeatherCategory;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class ForecastTests {


    @Test
    void setters_and_setters_should_work(){
        int expectedTemperature = 20;
        float expectedHumidity = 30f;
        int expectedWind = 1;
        Location location = new Location("athens", 0.0, 0.0);

        Forecast forecast = new Forecast();
        forecast.setIsoTime(Instant.now());
        forecast.setTemperature(20);
        forecast.setHumidity(30f);
        forecast.setWind(1);
        forecast.setWeatherCategory(WeatherCategory.Cold);
        forecast.setLocation(location);

        assertEquals(expectedTemperature,forecast.getTemperature());
        assertEquals(expectedHumidity,forecast.getHumidity());
        assertEquals(expectedWind, forecast.getWind());
        assertEquals(WeatherCategory.Cold, forecast.getWeatherCategory());
        assertEquals(location,forecast.getLocation());
    }

    @Test
    void all_args_constructor_should_work() {
        int expectedTemperature = 20;
        float expectedHumidity = 30f;
        Instant currentTime = Instant.now();
        int expectedWind = 1;
        Location location = new Location("athens", 0.0, 0.0);
        Forecast forecast = new Forecast(
            currentTime,
            expectedTemperature,
            expectedHumidity,
            expectedWind,
            WeatherCategory.Cold,
            location,
            1
        );
        assertEquals(expectedHumidity, forecast.getHumidity());
        assertEquals(expectedTemperature, forecast.getTemperature());
        assertEquals(currentTime, forecast.getIsoTime());
        assertEquals(expectedWind, forecast.getWind());
    }

    @Test
    void dto_constructor_should_work() {
        int expectedTemperature = 20;
        float expectedHumidity = 30f;
        Instant currentTime = Instant.now();
        int expectedWind = 1;
        Location location = new Location("athens", 0.0, 0.0);
        CreateForecastDTO dto = new CreateForecastDTO(currentTime,
            expectedTemperature,
            expectedHumidity,
            expectedWind,
            WeatherCategory.Cold,
            1,1
        );
        Forecast forecast = new Forecast(dto, location);
        assertEquals(expectedHumidity, forecast.getHumidity());
        assertEquals(expectedTemperature, forecast.getTemperature());
        assertEquals(currentTime, forecast.getIsoTime());
        assertEquals(expectedWind, forecast.getWind());
    }
    @Test
    void given_a_forecast_height_temp_weather_should_be_bad(){
        Forecast forecast = new Forecast();
        forecast.setTemperature(4);
        assertTrue(forecast.isBadWeather());
    }
    @Test
    void given_a_forecast_weather_height_wind_should_be_bad(){
        Forecast forecast = new Forecast();
        forecast.setTemperature(4);
        assertTrue(forecast.isBadWeather());
        forecast.setTemperature(6);
        forecast.setWind(9);
        assertTrue(forecast.isBadWeather());
    }
    @Test
    void given_a_forecast_weather_category_Rainy_should_be_bad(){
        Forecast forecast = new Forecast();
        forecast.setTemperature(4);
        assertTrue(forecast.isBadWeather());
        forecast.setTemperature(6);
        forecast.setWind(9);
        assertTrue(forecast.isBadWeather());
        forecast.setWind(7);
        forecast.setWeatherCategory(WeatherCategory.Raining);
        assertTrue(forecast.isBadWeather());
        forecast.setWeatherCategory(WeatherCategory.Sunny);
        assertFalse(forecast.isBadWeather());
    }
    @Test
    void given_a_forecast_weather_should_be_good(){
        Forecast forecast = new Forecast();
        forecast.setTemperature(4);
        assertTrue(forecast.isBadWeather());
        forecast.setTemperature(6);
        forecast.setWind(9);
        assertTrue(forecast.isBadWeather());
        forecast.setWind(7);
        forecast.setWeatherCategory(WeatherCategory.Raining);
        assertTrue(forecast.isBadWeather());
        forecast.setWeatherCategory(WeatherCategory.Sunny);
        assertFalse(forecast.isBadWeather());
    }

    @Test
    void test_equals_works() {
        Forecast forecast = new Forecast();
        Forecast forecast2 = new Forecast();
        assertEquals(forecast, forecast2);

    }

    @Test
    void given_null_object_equals_should_fail(){
        Forecast forecast1 = new Forecast();
        Forecast forecast = null;
        assertFalse(forecast1.equals(forecast));
    }



}