package com.example.usecases.location;

import static org.junit.jupiter.api.Assertions.*;

import com.example.domain.Location;
import com.example.repository.LocationRepository;
import io.quarkus.test.junit.QuarkusTest;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.jupiter.api.*;

@QuarkusTest
@Transactional
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GetAllLocationsTests {


    @Inject
    LocationRepository locationRepo;

    @Inject
    GetAllLocations getAllLocations;



    @BeforeEach
    void init() {
        locationRepo.deleteAll();
    }

    @AfterEach
    void tierDown() {
        locationRepo.deleteAll();
    }

    @Test
    void given_no_location_it_should_return_empty_list() {
        assertTrue(getAllLocations.query().isEmpty());

    }

    @Test
    void given_a_location_it_should_return_one_location() {
        Location location = new Location("mexico", 1.0, 1.0);
        locationRepo.persist(location);
        assertFalse(getAllLocations.query().isEmpty());
        assertEquals(1, locationRepo.count());
    }

}