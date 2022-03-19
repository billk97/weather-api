package com.example.adapters.location.ports;

import com.example.adapters.location.dtos.LocationDTO;

import java.util.Optional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/api")
@RegisterRestClient(configKey = "forecast-api")
public interface LocationPort {

    @GET
    @Path("/forecasts/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Optional<LocationDTO> findLocationById(@PathParam Long id);
}
