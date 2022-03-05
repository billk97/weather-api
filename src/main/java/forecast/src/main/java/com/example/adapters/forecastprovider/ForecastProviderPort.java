package com.example.adapters.forecastprovider;

import com.example.adapters.users.dtos.UserDTO;
import java.util.Optional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/api")
@RegisterRestClient(configKey = "forecast-provider-api/")
public interface ForecastProviderPort {

    @GET
    @Path("/forecast-provider/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Optional<Object> findForecastProviderById(@PathParam("id") String id);
}
