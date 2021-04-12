package com.example.app.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * The type Location.
 */
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "location_id")
    private int locationId;

    @Column(name = "city_name", unique = true)
    private String cityName;

    /**
     * Instantiates a new Location.
     */
    public Location() {}

    /**
     * Instantiates a new Location.
     *
     * @param cityName the city name
     */
    public Location(String cityName) {
        this.cityName = cityName;
    }

    /**
     * Gets location id.
     *
     * @return the location id
     */
    public int getLocationId() {
        return locationId;
    }

    /**
     * Gets city name.
     *
     * @return the city name
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Sets city name.
     *
     * @param cityName the city name
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", cityName='" + cityName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(cityName, location.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName);
    }
}
