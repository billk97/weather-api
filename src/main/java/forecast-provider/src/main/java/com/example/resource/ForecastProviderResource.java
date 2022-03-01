package com.example.resource;

import com.example.domain.ForecastProvider;
import com.example.dtos.in.CreateForecastProviderDTO;
import com.example.dtos.out.ObjectIdDTO;
import com.example.repositories.ForecastProviderRepository;
import com.example.usecases.CreateForecastProvider;
import com.example.usecases.UpdateForecastProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("api/forecast-providers")
@ApplicationScoped
@Consumes({MediaType.APPLICATION_JSON})
@Produces(MediaType.APPLICATION_JSON)
public class ForecastProviderResource {

    @Inject
    private ForecastProviderRepository forecastProviderRepo;

    @Inject
    private CreateForecastProvider createForecastProvider;

    @Inject
    private UpdateForecastProvider updateForecastProvider;

    @GET
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
}
