package com.example.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "forecast-providers")
public class ForecastProvider {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private long forecastProviderId;

    @Column(name="name", unique=true)
    private String name;

    @Column(name = "description")
    private String description;

    /**
     * Instantiates a new Service.
     */
    public ForecastProvider(){}

    /**
     * Instantiates a new Service.
     *
     * @param name        the name
     * @param description the description
     */
    public ForecastProvider(String name, String description) {
        this.name = name;
        this.description = description;
    }


    /**
     * Get forecast id int.
     *
     * @return the int
     */
    public long getId(){
        return forecastProviderId;
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
     * Get description string.
     *
     * @return the string
     */
    public String getDescription(){
        return description;
    }

    /**
     * Set description.
     *
     * @param description the description
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * Gets service id.
     *
     * @return the service id
     */
    public long getForecastProviderId() {
        return forecastProviderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForecastProvider service = (ForecastProvider) o;
        return name == service.name && description == service.description;

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
