package com.example.resource;


import com.example.domain.Forecast;
import com.example.dtos.in.CreateForecastDTO;
import com.example.dtos.out.ObjectIdDTO;
import com.example.repository.ForecastRepository;
import com.example.usecases.CreateForecast;
import com.example.usecases.GetCurrentForecastForUser;
import java.util.List;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.commons.lang3.StringUtils;

/**
 * The type Forecast resource.
 */
@Path("api/forecasts")
@ApplicationScoped
@Consumes({MediaType.APPLICATION_JSON})
@Produces(MediaType.APPLICATION_JSON)
public class ForecastResource {

    @Inject
    private ForecastRepository forecastRepo;
    @Inject
    private CreateForecast createForecast;
    @Inject
    private GetCurrentForecastForUser getCurrentForecastForUser;

    /**
     * Gets current forecast fore user.
     *
     * @param forecastId   the id of a specific forcast
     * @return the current forecast fore user
     */
    @GET
    @Path("{forecastId}")
    public Forecast getForecastById(@PathParam("forecastId") String forecastId) {
        if(!StringUtils.isNumeric(forecastId)) {
            throw new IllegalArgumentException("forecastId must be a valid number");
        }
        return forecastRepo.findById(Long.valueOf(forecastId));
    }

    @GET
    public List<Forecast> getAllForecasts() {
        return forecastRepo.findAll().stream().toList();
    }

    /**
     * Add forecast object id dto.
     *
     * @param dto the forecast dto
     * @return the object id dto
     */
    @POST
    public ObjectIdDTO addForecast(CreateForecastDTO dto){
        return createForecast.command(dto);
    }


    @GET
    @Path("current")
    public List<Forecast> getCurrentForecastForUser(
            @QueryParam("userId") String userId) {
        return getCurrentForecastForUser.query(userId);
    }

    @Deprecated
    @GET
    @Path("hours/{numberOfHours}")
    public Forecast getUsersForecastsForTheNextHours(
        @PathParam("numberOfHours") String numberOfHours,
        @QueryParam("userId") String userId) {

        // TODO implement this
        return null;
    }

    @Deprecated
    @GET
    @Path("days/{numberOfDays}")
    public Forecast getUsersForecastsForTheNextDays(
        @PathParam("numberOfDays") String numberOfDays,
        @QueryParam("userId") String userId) {
        // TODO implement this
        return null;
    }


    @Deprecated
    @GET
    @Path("days/{numberOfDays}/summary")
    public Forecast getUsersForecastsSummaryForTheNextDays(
        @PathParam("numberOfDays") String numberOfDays,
        @QueryParam("userId") String userId) {
        // TODO implement this
        return null;
    }


    @Deprecated
    @GET
    @Path("warn")
    public Forecast getUsersForecastSummaryForTheNextDays(
        @QueryParam("userId") String userId) {
        // TODO implement this
        return null;
    }

    @Deprecated
    @GET
    @Path("locations/{locationId}")
    public Forecast getAllForecastForLocations(
        @PathParam("locationId") String locationId,
        @QueryParam("userId") String userId) {
        // TODO implement this
        return null;
    }
    @Deprecated
    @DELETE
    @Path("{locationId}")
    public void deleteForecastWithId(
        @PathParam("locationId") String locationId) {
        // TODO implement this
    }

}
