package com.example.domain;

import com.example.dtos.in.CreateForecastDTO;
import com.example.enums.WeatherCategory;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "forecasts")
public class Forecast {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "iso_time")
    private Instant isoTime;

    @Column(name = "temperature")
    private int temperature;

    @Column(name = "humidity")
    private float humidity;

    @Column(name = "wind")
    private int wind;

    @Enumerated
    @Column(name = "weather_description")
    private WeatherCategory weatherCategory;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "providerId")
    private ForecastProvider forecastProvider;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "locationId")
    private Location location;

    /**
     * Instantiates a new Forecast.
     */
    public Forecast(){}

    /**
     * Instantiates a new Forecast.
     *
     * @param isoTime       the time the forecast prediction is referring in iso
     * @param temperature     the temperature
     * @param humidity        the humidity
     * @param wind            the wind
     * @param weatherCategory the weather category
     * @param forecastProvider the forecastProvider
     * @param location        the location
     */
    public Forecast(Instant isoTime, int temperature, float humidity, int wind, WeatherCategory weatherCategory, ForecastProvider forecastProvider, Location location) {
        this.isoTime = isoTime;
        this.temperature = temperature;
        this.humidity = humidity;
        this.wind = wind;
        this.weatherCategory = weatherCategory;
        this.forecastProvider = forecastProvider;
        this.location = location;
    }

    public Forecast(CreateForecastDTO dto, Location location, ForecastProvider forecastProvider) {
        this.isoTime = dto.forecastTime();
        this.temperature = dto.temperature();
        this.humidity = dto.humidity();
        this.wind = dto.wind();
        this.weatherCategory = dto.weatherCategory();
        this.location = location;
        this.forecastProvider = forecastProvider;
    }

    /**
     * Get forecast id int.
     *
     * @return the int
     */
    public long getId(){
        return id;
    }

    /**
     * Gets local time.
     *
     * @return the local time
     */
    public Instant getIsoTime() {
        return isoTime;
    }


    /**
     * Sets local time.
     *
     * @param isoTime the time the forecast is referring to
     */
    public void setIsoTime(Instant isoTime) {
        this.isoTime = isoTime;
    }


    /**
     * Gets temperature.
     *
     * @return the temperature
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * Sets temperature.
     *
     * @param temperature the temperature
     */
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    /**
     * Gets humidity.
     *
     * @return the humidity
     */
    public float getHumidity() {
        return humidity;
    }

    /**
     * Sets humidity.
     *
     * @param humidity the humidity
     */
    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    /**
     * Gets wind.
     *
     * @return the wind
     */
    public int getWind() {
        return wind;
    }

    /**
     * Sets wind.
     *
     * @param wind the wind
     */
    public void setWind(int wind) {
        this.wind = wind;
    }

    /**
     * Gets weather category.
     *
     * @return the weather category
     */
    public WeatherCategory getWeatherCategory() {
        return weatherCategory;
    }

    /**
     * Sets weather category.
     *
     * @param weatherCategory the weather category
     */
    public void setWeatherCategory(WeatherCategory weatherCategory) {
        this.weatherCategory = weatherCategory;
    }

    /**
     * Gets service.
     *
     * @return the service
     */
    public ForecastProvider getService() {
        return forecastProvider;
    }

    /**
     * Sets service.
     *
     * @param forecastProvider the service
     */
    public void setService(ForecastProvider forecastProvider) {
        this.forecastProvider = forecastProvider;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "is=" + id +
                ", isoTime=" + isoTime +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", wind=" + wind +
                ", weatherCategory=" + weatherCategory +
                ", forecastProvider =" + forecastProvider +
                ", location=" + location +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Forecast forecast = (Forecast) o;
        return id == forecast.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean isBadWeather(){
        if (temperature < 5){
            return true;
        }
        if(wind >8){
            return true;
        }
        if (weatherCategory.equals(WeatherCategory.Raining)){
            return true;
        }
        return false;
    }
}
