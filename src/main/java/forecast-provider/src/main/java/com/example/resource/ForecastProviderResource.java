package com.example.resource;

import com.example.domain.ForecastProvider;
import com.example.dtos.in.CreateForecastProviderDTO;
import com.example.dtos.out.ObjectIdDTO;
import com.example.repositories.ForecastProviderRepository;
import com.example.usecases.CreateForecastProvider;
import com.example.usecases.GetSingleProvider;
import com.example.usecases.UpdateForecastProvider;
import com.example.usecases.UpdateForecasts;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

@Path("api/forecast-providers")
@ApplicationScoped
@Consumes({MediaType.APPLICATION_JSON})
@Produces(MediaType.APPLICATION_JSON)
public class ForecastProviderResource {

    @Inject
    ForecastProviderRepository forecastProviderRepo;

    @Inject
    CreateForecastProvider createForecastProvider;

    @Inject
    UpdateForecastProvider updateForecastProvider;

    @Inject
    GetSingleProvider getSingleProvider;

    @Inject
    UpdateForecasts updateForecasts;

    @GET
    @Counted(name = "performChecks", description = "a description")
    @Timed(name = "checksTimer", unit = MetricUnits.MILLISECONDS)
    public List<ForecastProvider> getAllProviders() {
        return forecastProviderRepo.findAll().stream().toList();
    }

    @POST
    public ObjectIdDTO addForecastProvider(CreateForecastProviderDTO dto) {
        return createForecastProvider.command(dto);
    }

    @POST
    @Path("{forecastProviderId}/edit")
    public ObjectIdDTO editForecastProvider(
            @PathParam("forecastProviderId") String forecastProviderId,
            CreateForecastProviderDTO dto) {
        return updateForecastProvider.command(forecastProviderId, dto);
    }

    @GET
    @Path("{forecastProviderId}")
    public ForecastProvider getForecastProvider(
            @PathParam("forecastProviderId") String providerId
    ){
        return getSingleProvider.command(providerId);
    }

    @GET
    @Path("update-forecasts-service")
    public void updateForecastService(){
        updateForecasts.command();
    }
}
