package com.example.app.service;

import com.example.app.domain.Forecast;
import com.example.app.domain.Service;
import com.example.app.domain.User;
import com.example.app.domain.Rating;
import com.example.app.repository.ForecastRepository;
import com.example.app.repository.ServiceRepository;
import com.example.app.repository.UserRepository;
import com.example.app.repository.RatingRepository;

import java.util.Optional;

/**
 * The type Rating service.
 */
public class RatingService {
    private UserRepository userRepository = new UserRepository();;
    private ForecastRepository forecastRepository =  new ForecastRepository();
    private RatingRepository ratingRepository = new RatingRepository();

    /**
     * Add rating to forecast int.
     *
     * @param score      the score
     * @param username   the username
     * @param forecastId the forecast id
     * @return the int
     */
    public int addRatingToForecast(int score, String username, int forecastId){
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(!optionalUser.isPresent()){
            throw new IllegalArgumentException("user doesn't exist: " + username);
        }
        Optional<Forecast> optionalForecast = forecastRepository.findOneById(forecastId);
        if (!optionalForecast.isPresent()){
            throw new IllegalArgumentException("forecast with id: "+ forecastId + " doesn't exist");
        }
        Rating rating = new Rating();
        rating.setScore(score);
        rating.setForecast(optionalForecast.get());
        rating.setUser(optionalUser.get());
        ratingRepository.save(rating);
        return rating.getRatingId();
    }

}
