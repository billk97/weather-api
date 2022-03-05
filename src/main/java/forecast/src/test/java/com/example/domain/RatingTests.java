package com.example.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RatingTests {

    private Rating oldRating, newRating;

    @Test
    void test_equals_works() {
        Rating rating = new Rating();
        Rating rating2 = new Rating();
        assertEquals(rating, rating2);

    }

    @Test
    void given_null_object_equals_should_fail() {
        Rating rating1 = new Rating();
        Rating rating = null;
        assertFalse(rating1.equals(rating));
    }


    @Test
    void settersGettersShouldWork(){
        int expectedRating = 5;
        Rating localRating = new Rating();
        localRating.setScore(5);
        localRating.setUserId(1);

        assertEquals(expectedRating, localRating.getScore());
        assertEquals(1, localRating.getUserId());
    }


}