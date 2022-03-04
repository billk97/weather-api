package com.example.resource;

import com.example.domain.Forecast;
import com.example.dtos.in.CreateForecastDTO;
import com.example.dtos.out.ObjectIdDTO;
import com.example.repositories.ForecastRepository;
import com.example.usecases.CreateForecast;
import com.example.usecases.GetProviderForecasts;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("api/forecast")
@ApplicationScoped
@Consumes({MediaType.APPLICATION_JSON})
@Produces(MediaType.APPLICATION_JSON)
public class ForecastService {

    @Inject
    ForecastRepository forecastRepo;

    @Inject
    CreateForecast createForecast;

    @Inject
    GetProviderForecasts getProviderForecasts;

    @POST
    public ObjectIdDTO addForecast(CreateForecastDTO dto){
        return createForecast.command(dto);
    }

    @GET
    @Path("provider-forecasts/{providerId}")
    public List<Forecast> getAllProviderForecasts(
            @PathParam("providerId") String providerId
    ){
        return getProviderForecasts.query(providerId);
    }

}
