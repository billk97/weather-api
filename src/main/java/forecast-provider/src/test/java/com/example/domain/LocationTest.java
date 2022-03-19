package com.example.domain;

import com.example.dtos.in.CreateLocationDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    void getters_setters_should_work(){
        String name = "Random";
        Double latitude = 32.23232;
        Double longitude = 47.000;

        CreateLocationDTO dto = new CreateLocationDTO(name, latitude, longitude);

        Location loc = new Location(dto);

        assertEquals(name, loc.getName());
        assertEquals(latitude, loc.getLatitude());
        assertEquals(longitude, loc.getLongitude());

        String name2 = "Random2";
        Double latitude2 = 32.232232332;
        Double longitude2 = 123.1231;

        loc.setName(name2);
        assertEquals(name2, loc.getName());

        loc.setLatitude(latitude2);
        assertEquals(latitude2, loc.getLatitude());

        loc.setLongitude(longitude2);
        assertEquals(longitude2, loc.getLongitude());

    }
}