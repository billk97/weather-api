package com.example.adapters;

import com.example.domain.Forecast;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.MILLIS;

@Path("/api")
@RegisterRestClient(configKey = "forecast-api")
@Retry(maxRetries = 4, delay = 1000,
        delayUnit = MILLIS)
public interface ForecastPort {

    @POST
    @Path("forecasts")
    @Produces(MediaType.APPLICATION_JSON)
    void provideProviderForecasts(@RequestBody Forecast forecast);
}
