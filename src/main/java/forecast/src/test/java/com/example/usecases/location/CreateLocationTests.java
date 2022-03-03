package com.example.usecases.location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.dtos.in.CreateLocationDTO;
import com.example.repository.LocationRepository;
import com.example.usecases.location.CreateLocation;
import io.quarkus.test.junit.QuarkusTest;
import javax.inject.Inject;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@QuarkusTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CreateLocationTests {

    @Inject
    LocationRepository locationRepo;

    @Inject
    CreateLocation createLocation;

    @Test
    void given_a_location_dto_with_null_name_IT_should_throw() {
        CreateLocationDTO dto = new CreateLocationDTO(null, 0, 0);
        assertThrows(IllegalArgumentException.class, () -> {
           createLocation.command(dto);
        });
    }

    @Test
    void given_a_location_dto_IT_should_succeed() {
        String name = "athens";
        CreateLocationDTO dto = new CreateLocationDTO(name, 0, 0);
        long locationId =  createLocation.command(dto);
        assertEquals("athens", locationRepo.findById(locationId).getName());
    }

}