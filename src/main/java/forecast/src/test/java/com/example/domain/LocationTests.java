package com.example.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LocationTests {


    private Location location;

    @BeforeEach
    void init(){
        location = new Location("Athens", 0.0, 0.0);
    }

    @Test
    void given_same_object_equals_should_succeed(){
        assertEquals(location, location);
    }

    @Test
    void given_null_object_equals_should_fail(){
        Location newlocation = null;
        assertFalse(location.equals(newlocation));
    }

    @Test
    void given_same_location_name_should_succeed(){
        Location newLocation = new Location("Athens", 0.0, 0.0);
        assertTrue(location.equals(newLocation));
    }

    @Test
    void given_different_location_name_should_fail(){
        Location newLocation = new Location("Thessaloniki", 0.0, 0.0);
        assertFalse(location.equals(newLocation));
    }
}
