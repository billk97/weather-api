package com.example.app.resource;

import com.example.app.dtos.BooleanResponseDTO;
import com.example.app.dtos.ForecastRatingDTO;
import com.example.app.dtos.ObjectIdDTO;
import com.example.app.repository.ForecastRepository;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

public class RatingResourceTest extends ResourceTest {

    private ForecastRepository forecastRepository;
    @Override
    protected Application configure() {
        return new ResourceConfig(RatingResource.class, DebugExceptionMapper.class);
    }


    @Test
    public void given_valid_credentials_and_ForecastId_rating_should_be_added_successfully(){
        forecastRepository = new ForecastRepository();
        int forecastId = forecastRepository.findAll().get(0).getForecastId();
        ForecastRatingDTO ratingDTO = new ForecastRatingDTO("Bill", "password",
                forecastId,5);
        Response response = target("rate").request().post(Entity.entity(ratingDTO, MediaType.APPLICATION_JSON));
        assertEquals(200, response.getStatus());
        assertNotNull(response.readEntity(ObjectIdDTO.class).getObjectId());
    }

}