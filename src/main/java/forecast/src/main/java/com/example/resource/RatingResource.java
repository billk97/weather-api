package com.example.resource;

import com.example.domain.Rating;
import com.example.dtos.in.CreateForecastRatingDTO;
import com.example.dtos.out.ObjectIdDTO;
import com.example.usecases.CreateForecastRating;
import com.example.usecases.DeleteRatingById;
import com.example.usecases.GetAllRatings;
import com.example.usecases.GetRatingById;
import java.util.List;
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

    @Inject
    private GetAllRatings getAllRatings;

    @Inject
    private GetRatingById getRatingById;

    @Inject
    private DeleteRatingById deleteRatingById;


    @POST
    public ObjectIdDTO addRatingForForecast(@RequestBody CreateForecastRatingDTO dto) {
        return new ObjectIdDTO(createForecastRating.command(dto));
    }

    @GET
    @Path("{ratingId}")
    public Rating getRatingById(@PathParam("ratingId") String ratingId) {
        return getRatingById.query(ratingId);
    }

    @GET
    public List<Rating> getAllRatings() {
        return getAllRatings.query();
    }


    @DELETE
    @Path("{ratingId}")
    public void deleteRatingWithId(
        @PathParam("ratingId") String ratingId) {
        deleteRatingById.command(ratingId);
    }



}
