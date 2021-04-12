package com.example.app.dtos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The type Forecast dto.
 */
@XmlRootElement
public class ForecastDTO {
    private String localTime;
    private String localDate;
    private int temperature;
    private float humidity;
    private int wind;
    private String category;
    private String serviceName;
    private String locationName;

    /**
     * Instantiates a new Forecast dto.
     *
     * @param localTime    the local time
     * @param localDate    the local date
     * @param temperature  the temperature
     * @param humidity     the humidity
     * @param wind         the wind
     * @param category     the category
     * @param serviceName  the service name
     * @param locationName the location name
     */
    public ForecastDTO(String localTime, String localDate, int temperature, float humidity, int wind, String category, String serviceName, String locationName) {
        this.localTime = localTime;
        this.localDate = localDate;
        this.temperature = temperature;
        this.humidity = humidity;
        this.wind = wind;
        this.category = category;
        this.serviceName = serviceName;
        this.locationName = locationName;
    }

    /**
     * Instantiates a new Forecast dto.
     */
    public ForecastDTO(){}

    /**
     * Gets local time.
     *
     * @return the local time
     */
    public String getLocalTime() {
        return localTime;
    }

    /**
     * Sets local time.
     *
     * @param localTime the local time
     */
    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }

    /**
     * Gets local date.
     *
     * @return the local date
     */
    public String getLocalDate() {
        return localDate;
    }

    /**
     * Sets local date.
     *
     * @param localDate the local date
     */
    public void setLocalDate(String localDate) {
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
     * Gets category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets service name.
     *
     * @return the service name
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Sets service name.
     *
     * @param serviceName the service name
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * Gets location name.
     *
     * @return the location name
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * Sets location name.
     *
     * @param locationName the location name
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
