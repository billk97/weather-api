package com.example.app.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RatingTests {
    private Rating rating, newRating;

    @Mock
    User user;
    @Mock
    Forecast forecast;


    @BeforeEach
    void init(){
        rating = new Rating(user, forecast, 5);
        newRating = new Rating(user, forecast, 4);
    }

    @Test
    void given_same_object_equals_should_succeed(){
        assertEquals(rating, rating);
    }

    @Test
    void given_null_object_equals_should_fail(){
        Rating newRating = null;

        assertFalse(rating.equals(newRating));
    }

    @Test
    void given_different_score_object_equals_should_fail(){
        assertFalse(rating.equals(newRating));
    }

    @Test
    void given_same_score_object_equals_should_succeed(){
        newRating.setScore(5);
        assertTrue(rating.equals(newRating));
    }

    @Test
    void settersGettersShouldWork(){
        int expectedRating = 5;
        Rating rating = new Rating();
        rating.setScore(5);
        rating.setUser(user);
        rating.setForecast(forecast);

        assertEquals(expectedRating, rating.getScore());
        assertEquals(user, rating.getUser());
        assertEquals(forecast, rating.getForecast());
    }
}
