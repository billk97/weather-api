package com.example.resource;


import com.example.domain.Forecast;
import com.example.dtos.in.CreateForecastDTO;
import com.example.dtos.out.ForecastSummaryDTO;
import com.example.dtos.out.ObjectIdDTO;
import com.example.repository.ForecastRepository;
import com.example.usecases.forecast.CreateForecast;
import com.example.usecases.forecast.GetAllForecastByLocation;
import com.example.usecases.forecast.GetCurrentForecastForUser;
import com.example.usecases.forecast.GetUsersForecastForTheNextDays;
import com.example.usecases.forecast.GetUsersForecastForTheNextDaysSummary;
import com.example.usecases.forecast.GetUsersForecastForTheNextHours;
import com.example.usecases.forecast.GetUsersForecastForTheNextHoursSummary;
import com.example.usecases.location.DeleteForecastById;
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
    @Inject
    private GetAllForecastByLocation getAllForecastByLocation;
    @Inject
    private DeleteForecastById deleteForecastById;
    @Inject
    private GetUsersForecastForTheNextDays getUsersForecastForTheNextDays;
    @Inject
    private GetUsersForecastForTheNextDaysSummary getUsersForecastForTheNextDaysSummary;
    @Inject
    private GetUsersForecastForTheNextHours getUsersForecastForTheNextHours;
    @Inject
    private GetUsersForecastForTheNextHoursSummary getUsersForecastForTheNextHoursSummary;


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
        getUsersForecastForTheNextHours.query(userId, numberOfHours);
        return null;
    }

    @GET
    @Path("days/{numberOfDays}")
    public List<Forecast> getUsersForecastsForTheNextDays(
        @PathParam("numberOfDays") String numberOfDays,
        @QueryParam("userId") String userId) {
        return getUsersForecastForTheNextDays.query(userId, numberOfDays);

    }


    @Deprecated
    @GET
    @Path("days/{numberOfDays}/summary")
    public List<ForecastSummaryDTO> getUsersForecastsSummaryForTheNextDays(
        @PathParam("numberOfDays") String numberOfDays,
        @QueryParam("userId") String userId) {
        return getUsersForecastForTheNextDaysSummary.query(userId, numberOfDays);
    }

    @Deprecated
    @GET
    @Path("days/{numberOfDays}/summary")
    public List<ForecastSummaryDTO> getUsersForecastsSummaryForTheNextHours(
        @PathParam("numberOfDays") String numberOfDays,
        @QueryParam("userId") String userId) {
        return getUsersForecastForTheNextHoursSummary.query(userId, numberOfDays);
    }


    @Deprecated
    @GET
    @Path("warn")
    public Forecast getUserWarnings(
        @QueryParam("userId") String userId) {
        // TODO implement this
        return null;
    }

    @GET
    @Path("locations/{locationId}")
    public List<Forecast> getAllForecastForLocations(
        @PathParam("locationId") String locationId){
        return getAllForecastByLocation.query(locationId);
    }

    @DELETE
    @Path("{forecastId}")
    public void deleteForecastWithId(
        @PathParam("forecastId") String forecastId) {
        deleteForecastById.command(forecastId);
    }

}
