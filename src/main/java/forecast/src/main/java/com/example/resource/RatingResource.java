package com.example.resource;

import com.example.domain.Rating;
import com.example.dtos.in.CreateForecastRatingDTO;
import com.example.dtos.out.ObjectIdDTO;
import com.example.usecases.CreateForecastRating;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("api/ratings")
@ApplicationScoped
@Consumes({MediaType.APPLICATION_JSON})
@Produces(MediaType.APPLICATION_JSON)
public class RatingResource {

    @Inject
    private CreateForecastRating createForecastRating;


    @POST
    public ObjectIdDTO addRatingForForecast(@RequestBody CreateForecastRatingDTO dto) {
        return new ObjectIdDTO(createForecastRating.command(dto));
    }

    @Deprecated
    @GET
    @Path("{ratingId}")
    public Rating getRatingById(@PathParam("ratingId") String forecastId) {
        // TODO implement this
        return null;
    }


    @Deprecated
    @GET
    public Rating getAllRatings() {
        // TODO implement this
        return null;
    }

    @Deprecated
    @DELETE
    @Path("{ratingId}")
    public void deleteRatingWithId(
        @PathParam("ratingId") String locationId) {
        // TODO implement this
    }



}
