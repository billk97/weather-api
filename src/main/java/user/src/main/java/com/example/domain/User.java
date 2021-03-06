package com.example.domain;

import com.example.dtos.in.RegisterUserDTO;

import javax.persistence.*;
import java.util.*;
import java.util.HashSet;

/**
 * The User Entity
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username", unique = true)
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "location_id")
    private long locationId;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch=FetchType.LAZY)
    @JoinTable(name = "user_forecast_provider",
        joinColumns = {@JoinColumn(name="user_id")},
        inverseJoinColumns = {@JoinColumn(name="forecast_provider_id")})
    private Set<ForecastProvider> forecastProviderIds = new HashSet<>();

    /**
     * Instantiates a new User.
     */
    public User(){}

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * Instantiates a new User.
     *
     * @param name     the name
     * @param password the password
     * @param locationId the location id
     * @param forecastProviderIds the service ids
     */

    public User(String name, String password, long locationId, Set<ForecastProvider> forecastProviderIds) {
        this.name = name;
        this.password = password;
        this.locationId = locationId;
        this.forecastProviderIds = forecastProviderIds;
    }

    public User(RegisterUserDTO dto) {
        this.name = dto.name();
        this.password = dto.password();
        this.locationId = dto.locationId();
    }

    /**
     * Get user id int.
     *
     * @return the int
     */
    public long getId(){
        return id;
    }

    /**
     * Get name string.
     *
     * @return the string
     */
    public String getName(){
        return name;
    }

    /**
     * Set name.
     *
     * @param name the name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Get password string.
     *
     * @return the string
     */
    public String getPassword(){
        return password;
    }

    /**
     * Set password.
     *
     * @param password the password
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Get location id long
     * @return long
     */
    public long getLocationId() { return locationId;}

    /**
     * set locationId
     * @param locationId the location id
     */
    public void setLocationId(long locationId) {this.locationId = locationId;}

    public Set<ForecastProvider> getForecastProviders() {
        return forecastProviderIds;
    }

    public void setForecastProviderIds(
        Set<ForecastProvider> forecastProviderIds) {
        this.forecastProviderIds = forecastProviderIds;
    }

    /**
     * HashSet forecastProviderIds
     * @param
     * @return Set<Long>
     */


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name == user.name;
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}
