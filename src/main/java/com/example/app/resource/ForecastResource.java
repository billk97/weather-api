package com.example.app.resource;

import com.example.app.domain.Forecast;
import com.example.app.dtos.ForecastDTO;
import com.example.app.dtos.ForecastUserNameDto;
import com.example.app.dtos.ObjectIdDTO;
import com.example.app.dtos.WarningMessageDTO;
import com.example.app.enums.WeatherCategory;
import com.example.app.service.ForecastService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * The type Forecast resource.
 */
@Path("forecasts")
public class ForecastResource {
    private ForecastService forecastService = new ForecastService();

    /**
     * Gets current forecast fore user.
     *
     * @param locationName the location name
     * @param username     the username
     * @param mock         the mock
     * @return the current forecast fore user
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{locationName}")
    public Forecast getCurrentForecastForeUser(@PathParam("locationName") String locationName,
                                              @QueryParam("username") String username,
                                               @QueryParam("mock") boolean mock){
        return forecastService.getCurrentForecast(username,locationName, mock);
    }

    /**
     * Add forecast object id dto.
     *
     * @param forecastDTO the forecast dto
     * @return the object id dto
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public ObjectIdDTO addForecast(ForecastDTO forecastDTO){

        int temp = forecastService.addNewForecast(LocalTime.parse(forecastDTO.getLocalTime()),
                LocalDate.parse(forecastDTO.getLocalDate()), forecastDTO.getTemperature(),
                forecastDTO.getHumidity(), forecastDTO.getWind(),
                WeatherCategory.valueOf(forecastDTO.getCategory()),
                forecastDTO.getServiceName(), forecastDTO.getLocationName());
        System.out.println(temp);
        return new ObjectIdDTO(temp);
    }

    /**
     * Health string.
     *
     * @return the string
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String health(){
        return "ok";
    }

    /**
     * Gets next three hours forecast.
     *
     * @param locationName the location name
     * @param username     the username
     * @param mock         the mock
     * @return the next three hours forecast
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("hours/{locationName}")
    public List<Forecast> getNextThreeHoursForecast(@PathParam("locationName") String locationName,
                                                   @QueryParam("username") String username,
                                                   @QueryParam("mock") boolean mock){
        return forecastService.getNextThreeHoursForecast(username,locationName, mock);
    }

    /**
     * Gets next three days.
     *
     * @param locationName the location name
     * @param username     the username
     * @param mock         the mock
     * @return the next three days
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("days/{locationName}")
    public List<Forecast> getNextThreeDays(@PathParam("locationName") String locationName,
                                                    @QueryParam("username") String username,
                                                    @QueryParam("mock") boolean mock){
        return forecastService.getNextThreeDays(username,locationName, mock);
    }
    /**
     * Gets next three days.
     *
     * @param locationName the location name
     * @param username     the username
     * @param mock         the mock
     * @return the next three days
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("days/summary/{locationName}")
    public List<Forecast> getNextThreeDaysSummary(@PathParam("locationName") String locationName,
                                           @QueryParam("username") String username,
                                           @QueryParam("mock") boolean mock){
        return forecastService.getSingleNextThreeDays(username,locationName, mock);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("warn/{username}")
    public WarningMessageDTO getWarningsForToday(@PathParam("username")String username, @QueryParam("mock")boolean mock){
        return new WarningMessageDTO(forecastService.warnUsers(username, mock));
    }

}
