package com.example.app.service;

import com.example.app.persistence.DatabaseUtils;
import com.example.app.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import javax.persistence.RollbackException;

import static org.junit.jupiter.api.Assertions.*;
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LocationServiceTest {

    private LocationService locationService;
    private LocationRepository locationRepository;
    @BeforeEach
    void init(){
        DatabaseUtils databaseUtils = new DatabaseUtils();
        databaseUtils.createInitialState();
        locationService = new LocationService();
        locationRepository = new LocationRepository();
    }
    @Test
    void given_a_valid_location_name_addition_should_succeed(){
        int locationId = locationService.addNewLocation("kolopetinitsa");
        assertEquals("kolopetinitsa",locationRepository.findById(locationId).get().getCityName());
    }
    @Test
    void given_an_existing_location_name_addition_should_fail(){
        assertThrows(IllegalArgumentException.class, ()->{
            locationService.addNewLocation("athens");
        });
    }

    @Test
    void given_an_existing_location_with_no_connections_name_deletion_should_succeed(){
        locationService.addNewLocation("kolopetinitsa");
        locationService.deleteLocation("kolopetinitsa");
        assertFalse(locationRepository.findOneByName("kolopetinitsa").isPresent());
    }
    @Test
    void given_an_existing_location_name_deletion_should_fail(){
        assertThrows(RollbackException.class, ()->{
            locationService.deleteLocation("athens");
        });
    }



}