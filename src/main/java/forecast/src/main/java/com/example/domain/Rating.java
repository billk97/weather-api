package com.example.domain;

import com.example.dtos.in.CreateForecastRatingDTO;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The type Rating.
 */
@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private long userId;

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
     * @param userId   the users id
     * @param forecast the forecast
     * @param score    the score
     */
    public Rating(long userId, Forecast forecast, int score) {
        this.userId = userId;
        this.forecast = forecast;
        this.score = score;
    }


    public Rating(CreateForecastRatingDTO dto, Forecast forecast) {
        this.userId = dto.userId();
        this.forecast = forecast;
        this.score = dto.score();
    }

    /**
     * Gets rating id.
     *
     * @return the rating id
     */
    public long getId() {
        return id;
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
    public long getUserId(){
        return userId;
    }

    /**
     * Set user.
     *
     * @param userId the user
     */
    public void setUserId(long userId){
        this.userId = userId;
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
