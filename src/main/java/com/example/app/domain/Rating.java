package com.example.app.domain;

import javax.persistence.*;

/**
 * The type Rating.
 */
@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int ratingId;

    @ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "forecast_id")
    private Forecast forecast;

    @Column(name = "score")
    private int score;

    /**
     * Instantiates a new Rating.
     */
    public Rating(){}

    /**
     * Instantiates a new Rating.
     *
     * @param user     the user
     * @param forecast the forecast
     * @param score    the score
     */
    public Rating(User user, Forecast forecast, int score) {
        this.user = user;
        this.forecast = forecast;
        this.score = score;
    }

    /**
     * Gets rating id.
     *
     * @return the rating id
     */
    public int getRatingId() {
        return ratingId;
    }

    /**
     * Instantiates a new Rating.
     *
     * @param score the score
     */
    public Rating(int score) {
        this.score = score;
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

    /**
     * Get user user.
     *
     * @return the user
     */
    public User getUser(){
        return user;
    }

    /**
     * Set user.
     *
     * @param user the user
     */
    public void setUser(User user){
        this.user = user;
    }

    /**
     * Get forecast forecast.
     *
     * @return the forecast
     */
    public Forecast getForecast(){
        return forecast;
    }

    /**
     * Set forecast.
     *
     * @param forecast the forecast
     */
    public void setForecast(Forecast forecast){
        this.forecast = forecast;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return score == rating.score;
    }
}
