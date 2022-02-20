package com.example.domain;

import com.example.dtos.in.NewLocationDTO;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The type Location.
 */
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "location_id")
    private long locationId;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;


    /**
     * Instantiates a new Location.
     */
    public Location() {}

    /**
     * Instantiates a new Location.
     *
     * @param name the city name
     */
    public Location(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location(NewLocationDTO dto) {
        this.name = dto.locationName();
        this.latitude = dto.latitude();
        this.longitude = dto.longitude();
    }


    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String cityName) {
        this.name = cityName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Location{" +
            "locationId=" + locationId +
            ", cityName='" + name + '\'' +
            ", latitude=" + latitude +
            ", longitude=" + longitude +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
