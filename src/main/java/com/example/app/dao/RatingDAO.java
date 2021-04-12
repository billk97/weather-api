package com.example.app.dao;

import com.example.app.domain.Forecast;
import com.example.app.domain.Rating;
import com.example.app.domain.Service;
import com.example.app.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * The interface Rating dao.
 */
public interface RatingDAO {

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Rating> findAll();

    /**
     * Save.
     *
     * @param rating the rating
     */
    void save(Rating rating);

    /**
     * Delete.
     *
     * @param rating the rating
     */
    void delete(Rating rating);

    /**
     * Find by forecast list.
     *
     * @param forecast the forecast
     * @return the list
     */
    List<Rating> findByForecast(Forecast forecast);

    /**
     * Find by user list.
     *
     * @param user the user
     * @return the list
     */
    List<Rating> findByUser(User user);

    /**
     * Find by id optional.
     *
     * @param ratingId the rating id
     * @return the optional
     */
    Optional<Rating> findById(int ratingId);

    /**
     * Find by rating optional.
     *
     * @param rating the rating
     * @return the optional
     */
    Optional<Rating> findByRating(Rating rating);

    Optional<Rating> findByUserIdAndForecastId(int userId, int forecastId);

}
