package com.example.resource;

import com.example.adapters.location.LocationAdapter;
import com.example.adapters.location.dtos.LocationDTO;
import com.example.adapters.location.ports.LocationPort;
import com.example.domain.User;
import com.example.dtos.in.RegisterUserDTO;
import com.example.dtos.out.ObjectIdDTO;
import com.example.repository.UserRepository;
import com.example.usecases.RegisterUserResource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("api/users")
@ApplicationScoped
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class UserResource {

    @Inject
    UserRepository userRepository;

    @Inject
    RegisterUserResource registerUser;

    @Inject
    LocationPort locationPort; // TODO remove it when add the REST CLIENT

    /**
     * Create a new user
     * @param dto object to create a new user
     * @return the object id dto
     */
    @POST
    public ObjectIdDTO registerUser(RegisterUserDTO dto) {return new ObjectIdDTO(registerUser.command(dto));}


    /**
     * Get user by name
     * @param id the user's id
     * @returns the user object
     */
    @GET
    @Path("{id}")
    public User getUserById(@PathParam("id") String id) {
        User user = userRepository.findById(Long.valueOf(id));
        if(user == null) {
            throw new IllegalArgumentException(String.format("User with id: %s does not exist", id));
        }
        return user;
    }


    /**
     * Get location
     * @param name the user's name
     * @returns
     */
    @GET
    @Path("location/{name}")
    public Optional<LocationDTO> getLocation(@PathParam("name") String name) {
        User user = userRepository.findUserByName(name);
        if(user == null) {
            throw new IllegalArgumentException(String.format("User with name: %s does not exist", name));
        }
        // TODO make the REST CLIENT request to the forecast service to get location
        // currently i just return the dummy location from the LocationDTO {@see LocationAdapter}
       Optional<LocationDTO> locationDTO = locationPort.findLocationById(user.getLocationId());
        return locationDTO;
    }

}
