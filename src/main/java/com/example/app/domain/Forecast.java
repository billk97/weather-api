package com.example.app.domain;

import com.example.app.enums.WeatherCategory;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * The type Forecast.
 */
@Entity
@Table(name = "forecast")
public class Forecast {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "forecast_id")
    private int forecastId;

    @Column(name = "local_time")
    private LocalTime localTime;

    @Column(name = "local_date")
    private LocalDate localDate;

    @Column(name = "temperature")
    private int temperature;

    @Column(name = "humidity")
    private float humidity;

    @Column(name = "wind")
    private int wind;

    @Enumerated
    @Column(name = "weather_description")
    private WeatherCategory weatherCategory;

    @ManyToOne(fetch=FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "serviceId")
    private Service service;

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
     * @param localTime       the local time
     * @param localDate       the local date
     * @param temperature     the temperature
     * @param humidity        the humidity
     * @param wind            the wind
     * @param weatherCategory the weather category
     * @param service         the service
     * @param location        the location
     */
    public Forecast(LocalTime localTime, LocalDate localDate, int temperature, float humidity, int wind, WeatherCategory weatherCategory, Service service, Location location) {
        this.localTime = localTime;
        this.localDate = localDate;
        this.temperature = temperature;
        this.humidity = humidity;
        this.wind = wind;
        this.weatherCategory = weatherCategory;
        this.service = service;
        this.location = location;
    }

    /**
     * Get forecast id int.
     *
     * @return the int
     */
    public int getForecastId(){
        return forecastId;
    }

    /**
     * Gets local time.
     *
     * @return the local time
     */
    public LocalTime getLocalTime() {
        return localTime;
    }

    /**
     * Sets local time.
     *
     * @param localTime the local time
     */
    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    /**
     * Gets local date.
     *
     * @return the local date
     */
    public LocalDate getLocalDate() {
        return localDate;
    }

    /**
     * Sets local date.
     *
     * @param localDate the local date
     */
    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
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
    public Service getService() {
        return service;
    }

    /**
     * Sets service.
     *
     * @param service the service
     */
    public void setService(Service service) {
        this.service = service;
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
                "forecastId=" + forecastId +
                ", localTime=" + localTime +
                ", localDate=" + localDate +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", wind=" + wind +
                ", weatherCategory=" + weatherCategory +
                ", service=" + service +
                ", location=" + location +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forecast forecast = (Forecast) o;
        return temperature == forecast.temperature && Float.compare(forecast.humidity, humidity) == 0 &&
                wind == forecast.wind &&
                Objects.equals(localTime, forecast.localTime) &&
                Objects.equals(localDate, forecast.localDate) &&
                weatherCategory == forecast.weatherCategory &&
                Objects.equals(service, forecast.service) &&
                Objects.equals(location, forecast.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(forecastId, localTime, localDate, temperature, humidity, wind, weatherCategory, service, location);
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

