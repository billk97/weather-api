package com.example.app.dtos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The type Register user dto.
 */
@XmlRootElement
public class RegisterUserDTO {
    private String username;
    private String password;
    private String location;

    /**
     * Instantiates a new Register user dto.
     */
    public RegisterUserDTO(){}

    /**
     * Instantiates a new Register user dto.
     *
     * @param username the username
     * @param password the password
     * @param location the location
     */
    public RegisterUserDTO(String username, String password, String location) {
        this.username = username;
        this.password = password;
        this.location = location;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
    }
}
