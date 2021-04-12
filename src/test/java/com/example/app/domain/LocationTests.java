package com.example.app.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LocationTests {
    private Location location;

    @BeforeEach
    void init(){
        location = new Location("Athens");
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
        Location newLocation = new Location("Athens");
        assertTrue(location.equals(newLocation));
    }

    @Test
    void given_different_location_name_should_fail(){
        Location newLocation = new Location("Thessaloniki");
        assertFalse(location.equals(newLocation));
    }
}
