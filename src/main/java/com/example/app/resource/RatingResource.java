package com.example.app.resource;

import com.example.app.dtos.ForecastRatingDTO;
import com.example.app.dtos.ObjectIdDTO;
import com.example.app.service.RatingService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * The type Rating resource.
 */
@Path("rate")
public class RatingResource {

    private RatingService ratingService = new RatingService();

    /**
     * Add rating to forecast object id dto.
     *
     * @param ratingDTO the rating dto
     * @return the object id dto
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ObjectIdDTO addRatingToForecast(ForecastRatingDTO ratingDTO){
        return  new ObjectIdDTO(ratingService.addRatingToForecast(ratingDTO.getScore(),ratingDTO.getUsername(), ratingDTO.getForecastId()));
    }
}
