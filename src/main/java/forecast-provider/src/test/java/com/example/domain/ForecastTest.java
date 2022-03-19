package com.example.domain;

import com.example.enums.WeatherCategory;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class ForecastTest {

    private ForecastProvider provider;
    private Location loc;

    @BeforeEach
    void init() {
        loc = new Location("Larisa", 39.6237566,22.4128815);
        provider = new ForecastProvider("Meteo", "The most inaccurate forecast provider in Greece!");

    }

    @Test
    void getters_setters_should_work(){
        Instant now = Instant.now();
        int temperature = 15;
        float hum = 2l;
        int wind = 5;
        WeatherCategory cat = WeatherCategory.Raining;

        Forecast forecast = new Forecast(now, temperature, hum, wind, cat, provider, loc);

        assertNotNull(forecast);

        assertEquals(now, forecast.getIsoTime());
        assertEquals(temperature, forecast.getTemperature());
        assertEquals(hum, forecast.getHumidity());
        assertEquals(wind, forecast.getWind());
        assertEquals(cat, forecast.getWeatherCategory());
        assertEquals(provider, forecast.getService());
        assertEquals(loc, forecast.getLocation());

        Instant tomorrow = Instant.now().plus(1, ChronoUnit.DAYS);
        int temperatureTom = 15;
        float humTom = 2l;
        int windTom = 5;
        WeatherCategory catTom = WeatherCategory.Raining;

        forecast.setIsoTime(tomorrow);
        assertEquals(tomorrow, forecast.getIsoTime());

        forecast.setTemperature(temperatureTom);
        assertEquals(temperatureTom, forecast.getTemperature());

        forecast.setHumidity(humTom);
        assertEquals(humTom, forecast.getHumidity());

        forecast.setWind(windTom);
        assertEquals(windTom, forecast.getWind());

        forecast.setWeatherCategory(catTom);
        assertEquals(catTom, forecast.getWeatherCategory());

        Location loc2 = new Location("Larisa2", 39.6237566,22.4128815);
        ForecastProvider provider2 = new ForecastProvider("Meteo2", "The most inaccurate forecast provider in Greece!");

        forecast.setService(provider2);
        assertEquals(provider2, forecast.getService());

        forecast.setLocation(loc2);
        assertEquals(loc2, forecast.getLocation());

        assertTrue(forecast.isBadWeather());
    }
}