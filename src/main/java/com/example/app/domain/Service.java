package com.example.app.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * The type Service.
 */
@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="service_id")
    private int serviceId;

    @Column(name="name", unique=true)
    private String name;

    @Column(name = "description")
    private String description;

    /**
     * Instantiates a new Service.
     */
    public Service(){}

    /**
     * Instantiates a new Service.
     *
     * @param name        the name
     * @param description the description
     */
    public Service(String name, String description) {
        this.name = name;
        this.description = description;
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
    public int getServiceId() {
        return serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return name == service.name && description == service.description;

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
