package com.example.adapters.users.ports;

import com.example.adapters.users.dtos.UserDTO;
import java.util.Optional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;


@Path("/api")
@RegisterRestClient(configKey = "user-api")
public interface UserPort {

    @GET
    @Path("/users/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Optional<UserDTO> findUserById(@PathParam String id);
}
