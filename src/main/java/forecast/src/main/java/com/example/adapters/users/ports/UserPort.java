package com.example.adapters.users.ports;

import com.example.adapters.users.dtos.UserDTO;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import static java.time.temporal.ChronoUnit.*;


@Path("/api")
@RegisterRestClient(configKey = "user-api")
@Retry(maxRetries = 4, delay = 1000,
        delayUnit = MILLIS)
public interface UserPort {

    @GET
    @Path("/users/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Optional<UserDTO> findUserById(@PathParam String id);
}
