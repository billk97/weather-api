package com.example.app.dtos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The type User add service dto.
 */
@XmlRootElement
public class UserAddServiceDTO {
    private String username;
    private String password;
    private String serviceName;

    /**
     * Instantiates a new User add service dto.
     */
    public UserAddServiceDTO() {}

    /**
     * Instantiates a new User add service dto.
     *
     * @param username    the username
     * @param password    the password
     * @param serviceName the service name
     */
    public UserAddServiceDTO(String username, String password, String serviceName) {
        this.username = username;
        this.password = password;
        this.serviceName = serviceName;
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
}
