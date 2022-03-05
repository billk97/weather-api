package com.example.adapters.serviceProviders.ports;

import com.example.adapters.serviceProviders.dtos.ServiceProviderDTO;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api")
@RegisterRestClient(configKey = "forecast-provider-api/")
public interface ForecastProviderPort {


    @GET
    @Path("/forecast-provider/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    ServiceProviderDTO findForecastProviderById(Long forecastProvidersId);
}
