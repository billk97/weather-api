package com.example.app.dtos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The type Forecast user name dto.
 */
@XmlRootElement
public class ForecastUserNameDto {
    private String username;
    private boolean mock;

    /**
     * Instantiates a new Forecast user name dto.
     */
    public ForecastUserNameDto(){ }

    /**
     * Instantiates a new Forecast user name dto.
     *
     * @param username the username
     * @param mock     the mock
     */
    public ForecastUserNameDto(String username, boolean mock) {
        this.username = username;
        this.mock = mock;
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
     * Is mock boolean.
     *
     * @return the boolean
     */
    public boolean isMock() {
        return mock;
    }

    /**
     * Sets mock.
     *
     * @param mock the mock
     */
    public void setMock(boolean mock) {
        this.mock = mock;
    }
}
