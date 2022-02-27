package com.example.app.service;

import com.example.app.persistence.DatabaseUtils;
import com.example.app.repository.ForecastRepository;
import com.example.app.repository.RatingRepository;
import com.example.app.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RatingServiceTest {
    private RatingService ratingService;
    private RatingRepository ratingRepository;
    private UserRepository userRepository;
    private ForecastRepository forecastRepository;

    @BeforeEach
    void init(){
        DatabaseUtils databaseUtils = new DatabaseUtils();
        databaseUtils.createInitialState();
        ratingService = new RatingService();
        ratingRepository = new RatingRepository();
        userRepository = new UserRepository();
        forecastRepository = new ForecastRepository();
    }

    @Test
    void given_a_valid_username_forecastId_rating_should_succeed(){
        int forecastId = forecastRepository.findAll().get(0).getId();
        int ratingId = ratingService.addRatingToForecast(5, "Bill", forecastId);
        assertTrue(ratingRepository.findById(ratingId).isPresent()); 
    }

    @Test
    void given_a_invalid_username_forecastId_rating_should_succeed(){
        int forecastId = forecastRepository.findAll().get(0).getId();
        assertThrows(IllegalArgumentException.class, ()->{
            ratingService.addRatingToForecast(5, "Bidddll", forecastId);
        });
    }

    @Test
    void given_a_invalid_forecastId_rating_should_succeed(){
        assertThrows(IllegalArgumentException.class, ()->{
            ratingService.addRatingToForecast(5, "Bill", 0);
        });
    }

}