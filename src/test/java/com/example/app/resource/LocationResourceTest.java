package com.example.app.resource;

import com.example.app.domain.Location;
import com.example.app.dtos.EmptyDTO;
import com.example.app.dtos.ObjectIdDTO;
import com.example.app.repository.LocationRepository;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LocationResourceTest extends  ResourceTest{

    private LocationRepository locationRepository;
    @Override
    protected Application configure() {
        return new ResourceConfig(LocationResource.class, DebugExceptionMapper.class);
    }

    @Test
    public void given_a_new_location_name_addition_should_succeed(){
        Response response = target("locations/kalabaka").request()
                .post(Entity.entity(new EmptyDTO(), MediaType.APPLICATION_JSON));
        assertEquals(200, response.getStatus());
        int locationId = response.readEntity(ObjectIdDTO.class).getObjectId();
        locationRepository =  new LocationRepository();
        assertTrue(locationRepository.findById(locationId).isPresent());
    }

    @Test
    public void given_a_location_name_the_location_should_return_successfully(){
        Location location = target("locations/athens").request().get(new GenericType<Location>(){});
        assertEquals("athens", location.getCityName());
    }

    @Test
    public void all_locations_should_be_returned_successfully(){
        List<Location> locations = target("locations").request().get(new GenericType<List<Location>>(){});
        assertEquals(4, locations.size());
    }

}