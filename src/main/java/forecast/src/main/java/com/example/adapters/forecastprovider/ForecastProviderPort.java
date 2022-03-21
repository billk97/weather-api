package com.example.adapters.forecastprovider;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import static java.time.temporal.ChronoUnit.MILLIS;

@Path("/api")
@RegisterRestClient(configKey = "forecast-provider-api")
@Retry(maxRetries = 4, delay = 1000,
        delayUnit = MILLIS)
public interface ForecastProviderPort {

    @GET
    @Path("/forecast-provider/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Optional<Object> findForecastProviderById(@PathParam("id") String id);
}
