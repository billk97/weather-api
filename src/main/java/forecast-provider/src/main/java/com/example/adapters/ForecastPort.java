package com.example.adapters;

import com.example.domain.Forecast;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/api")
@RegisterRestClient(configKey = "forecast-api")
public interface ForecastPort {

    @POST
    @Path("forecasts")
    @Produces(MediaType.APPLICATION_JSON)
    void provideProviderForecasts(@RequestBody Forecast forecast);
}
