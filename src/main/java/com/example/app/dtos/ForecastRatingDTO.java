package com.example.app.dtos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The type Forecast rating dto.
 */
@XmlRootElement
public class ForecastRatingDTO {
    private String username;
    private String password;
    private int forecastId;
    private int score;

    /**
     * Instantiates a new Forecast rating dto.
     */
    public ForecastRatingDTO(){}

    /**
     * Instantiates a new Forecast rating dto.
     *
     * @param username   the username
     * @param password   the password
     * @param forecastId the forecast id
     * @param score      the score
     */
    public ForecastRatingDTO(String username, String password, int forecastId, int score) {
        this.username = username;
        this.password = password;
        this.forecastId = forecastId;
        this.score = score;
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
     * Gets forecast id.
     *
     * @return the forecast id
     */
    public int getForecastId() {
        return forecastId;
    }

    /**
     * Sets forecast id.
     *
     * @param forecastId the forecast id
     */
    public void setForecastId(int forecastId) {
        this.forecastId = forecastId;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(int score) {
        this.score = score;
    }
}
