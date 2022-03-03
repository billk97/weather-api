package com.example.app.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The type User.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "username", unique = true)
    private String name;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            fetch=FetchType.LAZY)
    @JoinTable(name = "user_services",
    joinColumns = {@JoinColumn(name="user_id")},
    inverseJoinColumns = {@JoinColumn(name="service_id")})
    private Set<Service> services = new HashSet<>();


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
     * @param location the location
     * @param services the services
     */

    public User(String name, String password, Location location, Set<Service> services) {
        this.name = name;
        this.password = password;
        this.location = location;
        this.services = services;
    }

    /**
     * Get user id int.
     *
     * @return the int
     */
    public int getUserId(){
        return userId;
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
     * Gets package com.example.dtos.in;

/**
 * The type Empty dto.
 */
public class EmptyDTO {
    /**
     * Instantiates a new Empty dto.
     */
    public EmptyDTO(){}
}

    public Set<Service> getServices() {
        return services;
    }

    /**
     * Sets services.
     *
     * @param services the services
     */
    public void setServices(Set<Service> services) {
       this.services = services;
    }

    /**
     * Add service.
     *
     * @param service the service
     */
    public void addService(Service service) {
        this.services.add(service);
    }

    /**
     * Remove service.
     *
     * @param service the service
     */
    public void removeService(Service service){
        this.services.remove(service);
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

    public boolean canRegister(){
        if (services.size()>=3){
            return false;
        }
        return true;
    }
}
