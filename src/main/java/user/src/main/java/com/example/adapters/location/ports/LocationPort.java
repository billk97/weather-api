package com.example.adapters.location.ports;

import com.example.adapters.location.dtos.LocationDTO;

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
@RegisterRestClient(configKey = "forecast-api")
@Retry(maxRetries = 4, delay = 1000,
        delayUnit = MILLIS)
public interface LocationPort {

    @GET
    @Path("/forecasts/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Optional<LocationDTO> findLocationById(@PathParam Long id);
}
