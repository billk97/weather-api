package com.example.app.dao;

import com.example.app.domain.*;
import com.example.app.enums.WeatherCategory;
import com.example.app.persistence.DatabaseUtils;
import com.example.app.repository.ForecastRepository;
import com.example.app.repository.RatingRepository;
import com.example.app.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RatingRepositoryTests {

    private RatingRepository ratingRepository;
    private UserRepository userRepository;
    private ForecastRepository forecastRepository;

    @Mock
    private Service service;

    @Mock
    private Location location;

    @Mock
    private User user;

    @BeforeEach
    void setUp() {
        DatabaseUtils databaseUtils = new DatabaseUtils();
        databaseUtils.createInitialState();
        ratingRepository = new RatingRepository();
        userRepository = new UserRepository();
        forecastRepository =  new ForecastRepository();
    }

    @Test
    void rating_repository_should_return_not_null_list(){
        assertEquals(1, ratingRepository.findAll().size());
    }

    @Test
    void given_a_rating_addition_to_db_should_succeed(){
        Rating rating = new Rating();
        Forecast forecast = new Forecast(LocalTime.of(6,0), LocalDate.of(2020,11,23),30,100f,2, WeatherCategory.Cloudy,service, location);
        rating.setForecast(forecast);
        rating.setScore(1);
        rating.setUser(user);
        ratingRepository.save(rating);

        assertEquals(2, ratingRepository.findAll().size());
        assertTrue(ratingRepository.findAll().contains(rating));
        assertEquals(rating, ratingRepository.findByForecast(forecast).get(0));
        assertEquals(rating.getScore(), ratingRepository.findByForecast(forecast).get(0).getScore());
    }

    @Test
    void given_a_rating_deletion_from_db_should_succeed(){
        Rating rating = new Rating();
        Forecast forecast = new Forecast(LocalTime.of(6,0), LocalDate.of(2020,11,23),30,100f,2, WeatherCategory.Cloudy,service, location);
        rating.setForecast(forecast);
        rating.setScore(1);
        rating.setUser(user);
        ratingRepository.save(rating);

        assertEquals(2, ratingRepository.findAll().size());
        assertTrue(ratingRepository.findAll().contains(rating));
        assertEquals(rating, ratingRepository.findByForecast(forecast).get(0));

        ratingRepository.delete(rating);

        assertEquals(1, ratingRepository.findAll().size());
        assertNotNull(ratingRepository.findAll());
        assertEquals(new ArrayList<Forecast>(),ratingRepository.findByForecast(forecast));
    }

    @Test
    void given_a_user_existing_rating_should_be_found(){
        Rating rating = new Rating();
        Forecast forecast = new Forecast(LocalTime.of(6,0), LocalDate.of(2020,11,23),30,100f,2, WeatherCategory.Cloudy,service, location);
        rating.setForecast(forecast);
        rating.setScore(1);
        User user = new User("bill", "password",location,new HashSet<>());
        rating.setUser(user);
        ratingRepository.save(rating);

        assertEquals(2, ratingRepository.findAll().size());

        ratingRepository.findByUser(user);

        assertEquals(1, ratingRepository.findByUser(user).size());
        assertNotNull(ratingRepository.findByUser(user));
    }

    @Test
    void given_an_existing_rating_rating_should_be_found(){
        Rating rating = new Rating();
        Forecast forecast = new Forecast(LocalTime.of(6,0), LocalDate.of(2020,11,23),30,100f,2, WeatherCategory.Cloudy,service, location);
        rating.setForecast(forecast);
        rating.setScore(1);
        User user = new User("bill", "password",location,new HashSet<>());
        rating.setUser(user);
        ratingRepository.save(rating);

        assertEquals(2, ratingRepository.findAll().size());
        assertNotNull(ratingRepository.findByRating(rating).get());
        assertEquals(rating, ratingRepository.findByRating(rating).get());
    }

    @Test
    void given_an_existing_rating_id_rating_should_be_found(){
        Rating rating = new Rating();
        Forecast forecast = new Forecast(LocalTime.of(6,0), LocalDate.of(2020,11,23),30,100f,2, WeatherCategory.Cloudy,service, location);
        rating.setForecast(forecast);
        rating.setScore(1);
        User user = new User("bill", "password",location,new HashSet<>());
        rating.setUser(user);
        ratingRepository.save(rating);

        assertEquals(2, ratingRepository.findAll().size());
        assertNotNull(ratingRepository.findById(rating.getRatingId()).get());
        assertEquals(rating, ratingRepository.findById(rating.getRatingId()).get());
    }

    @Test
    void given_not_existing_rating_rating_should_throw_exeption(){
        Rating rating = new Rating();
        Forecast forecast = new Forecast(LocalTime.of(6,0), LocalDate.of(2020,11,23),30,100f,2, WeatherCategory.Cloudy,service, location);
        rating.setForecast(forecast);
        rating.setScore(1);
        User user = new User("bill", "password",location,new HashSet<>());
        rating.setUser(user);

        assertEquals(Optional.empty(),ratingRepository.findByRating(rating));
        assertEquals(Optional.empty(),ratingRepository.findById(rating.getRatingId()));
        // assertEquals(new ArrayList<>(),ratingRepository.findByForecast(forecast));
    }

    @Test
    void given_userId_forecastId_rating_should_return(){
        Rating rating = new Rating();
        Forecast forecast = new Forecast(LocalTime.of(6,0), LocalDate.of(2020,11,23),30,100f,2, WeatherCategory.Cloudy,service, location);
        rating.setForecast(forecast);
        rating.setScore(1);
        User user = new User("bill", "password",location,new HashSet<>());
        rating.setUser(user);
        userRepository.save(user);
        forecastRepository.save(forecast);
        ratingRepository.save(rating);
        assertEquals(1,ratingRepository.findByUserIdAndForecastId(user.getUserId(),forecast.getId()).get().getScore());
    }
}
