package com.example.app.dao;

import com.example.app.domain.Location;
import com.example.app.domain.Service;
import com.example.app.persistence.DatabaseUtils;
import com.example.app.repository.LocationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LocationRepositoryTest {

    @Mock
    private Service service;

    @Mock
    private LocationRepository locationRepository;

    @BeforeEach
    void setUp() {
        DatabaseUtils databaseUtils = new DatabaseUtils();
        databaseUtils.createInitialState();
        locationRepository = new LocationRepository();
    }

    @Test
    void location_Repository_should_return_not_null_list() {
        int expectedInitialListSize = 4;
        assertNotNull(locationRepository.findAll());
        assertEquals(expectedInitialListSize, locationRepository.findAll().size());
    }

    @Test
    void given_a_location_id_deletion_should_succeed() {
        Location location = new Location("Chania");
        locationRepository.save(location);

        assertEquals(Optional.of(location),locationRepository.findById(location.getLocationId()));
        locationRepository.delete(location);

        assertEquals(Optional.empty(), locationRepository.findById(location.getLocationId()));
    }

    @Test
    void given_a_location_id_entity_manager_should_save_to_sb_successfully() {
        Location location = new Location("Chania");
        locationRepository.save(location);

        assertEquals(Optional.of(location),locationRepository.findById(location.getLocationId()));
        locationRepository.save(location);

        assertEquals(Optional.of(location), locationRepository.findById(location.getLocationId()));
    }

    @Test
    void given_a_location_name_deletion_should_succeed() {
        Location location = new Location("Chania");
        locationRepository.save(location);

        assertEquals(Optional.of(location),locationRepository.findOneByName(location.getCityName()));
        locationRepository.delete(location);

    }

    @Test
    void given_a_location_name_entity_manager_should_save_to_sb_successfully() {
        Location location = new Location("Chania");
        locationRepository.save(location);

        assertEquals(Optional.of(location),locationRepository.findOneByName(location.getCityName()));
        locationRepository.save(location);

        assertEquals(Optional.of(location), locationRepository.findOneByName(location.getCityName()));
    }
 
    @AfterEach
    void tearDown() {
        DatabaseUtils databaseUtils = new DatabaseUtils();
        databaseUtils.eraseAllData();
    }
}