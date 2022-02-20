package com.example.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.dtos.in.NewForecastDTO;
import com.example.enums.WeatherCategory;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class ForecastTest {


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
            location
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
        NewForecastDTO dto = new NewForecastDTO(currentTime,
            expectedTemperature,
            expectedHumidity,
            expectedWind,
            WeatherCategory.Cold,
            1
        );
        Forecast forecast = new Forecast(dto, location);
        assertEquals(expectedHumidity, forecast.getHumidity());
        assertEquals(expectedTemperature, forecast.getTemperature());
        assertEquals(currentTime, forecast.getIsoTime());
        assertEquals(expectedWind, forecast.getWind());
    }
    @Test
    void given_a_forecast_higth_temp_weather_should_be_bad(){
        Forecast forecast = new Forecast();
        forecast.setTemperature(4);
        assertTrue(forecast.isBadWeather());
    }
    @Test
    void given_a_forecast_weather_hight_wind_should_be_bad(){
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

}