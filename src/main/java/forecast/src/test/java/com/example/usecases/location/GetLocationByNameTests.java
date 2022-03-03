package com.example.usecases.location;

import static org.junit.jupiter.api.Assertions.*;

import com.example.domain.Location;
import com.example.exceptions.IllegalArgumentExceptionWithCode;
import com.example.repository.LocationRepository;
import io.quarkus.test.junit.QuarkusTest;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@QuarkusTest
@Transactional
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GetLocationByNameTests {

    @Inject
    private LocationRepository locationRepo;

    @Inject
    private GetLocationByName getLocationByName;

    @AfterEach
    void tierDown() {
        locationRepo.deleteAll();
    }

    @Test
    void given_a_non_numeric_location_it_should_throw() {
        assertThrows(IllegalArgumentExceptionWithCode.class, () -> {
           getLocationByName.query("something");
        });
    }

    @Test
    void given_a_non_existing_location_it_should_throw() {
        assertThrows(IllegalArgumentException.class, () -> {
            getLocationByName.query("999");
        });
    }


    @Test
    void given_a_valid_location_id_IT_should_return_the_location() {
        Location location = new Location("mexico", 1.0, 1.0);
        locationRepo.persist(location);
        Location fetchedLocation = getLocationByName.query(String.valueOf(location.getLocationId()));
        assertEquals(location.getName(), fetchedLocation.getName());

    }
}