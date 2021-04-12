package com.example.app.domain;

import com.example.app.enums.WeatherCategory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ForecastTests {
    private Forecast forecast, newForecast;
    private LocalTime TIME_STAMP = LocalTime.of(10,0);
    private LocalDate DATE_STAMP = LocalDate.of(2020,11,23);

    @Mock
    Service service;
    @Mock
    Location location;

    @BeforeEach
    void init(){
        forecast = new Forecast(TIME_STAMP, DATE_STAMP,
                30,10f,1, WeatherCategory.Cloudy,service,location);
        newForecast =  new Forecast(LocalTime.of(10,0),
                LocalDate.of(2020,11,23),
                30,10f,1, WeatherCategory.Cloudy,service,location);
    }

    @Test
    void given_same_object_equals_should_succeed(){
        assertEquals(forecast, forecast);
    }
    @Test
    void given_null_object_equals_should_fail(){
        Forecast newForecast = null;

        assertFalse(forecast.equals(newForecast));
    }
    @Test
    void given_different_temperature_object_equals_should_fail(){
        newForecast.setTemperature(100);

        assertFalse(forecast.equals(newForecast));
    }
    @Test
    void given_different_humidity_equals_should_fail(){
        newForecast.setHumidity(200);

        assertFalse(forecast.equals(newForecast));
    }
    @Test
    void given_different_wind_equals_should_fail(){
        newForecast.setWind(100);

        assertFalse(forecast.equals(newForecast));

    }
    @Test
    void given_different_category_equals_should_fail(){
        newForecast.setWeatherCategory(WeatherCategory.Cold);

        assertFalse(forecast.equals(newForecast));
    }

    @Test
    void given_different_time_equals_should_fail(){
        newForecast.setLocalTime(LocalTime.now());

        assertFalse(forecast.equals(newForecast));
    }
    @Test
    void given_different_date_equals_should_fail(){
        newForecast.setLocalDate(LocalDate.now());

        assertFalse(forecast.equals(newForecast));
    }
    @Test
    void given_Mock(){
        assertTrue(forecast.equals(newForecast));
    }
    @Test
    void settersGettersShouldWork(){
        int expectedTemperature = 20;
        float expectedHumidity = 30f;
        int expectedWind = 1;
        Forecast forecast = new Forecast();
        forecast.setLocalTime(TIME_STAMP);
        forecast.setLocalDate(DATE_STAMP);
        forecast.setTemperature(20);
        forecast.setHumidity(30f);
        forecast.setWind(1);
        forecast.setWeatherCategory(WeatherCategory.Cold);
        forecast.setLocation(location);
        forecast.setService(service);

        assertEquals(TIME_STAMP, forecast.getLocalTime());
        assertEquals(DATE_STAMP, forecast.getLocalDate());
        assertEquals(expectedTemperature,forecast.getTemperature());
        assertEquals(expectedHumidity,forecast.getHumidity());
        assertEquals(expectedWind, forecast.getWind());
        assertEquals(WeatherCategory.Cold, forecast.getWeatherCategory());
        assertEquals(location,forecast.getLocation());
        assertEquals(service, forecast.getService());
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
