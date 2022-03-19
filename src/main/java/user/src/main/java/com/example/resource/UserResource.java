package com.example.resource;

import com.example.adapters.location.dtos.LocationDTO;
import com.example.adapters.location.ports.LocationPort;
import com.example.adapters.serviceProviders.dtos.ServiceProviderDTO;
import com.example.adapters.serviceProviders.ports.ForecastProviderPort;
import com.example.domain.ForecastProvider;
import com.example.domain.User;
import com.example.dtos.in.RegisterUserDTO;
import com.example.dtos.out.ObjectIdDTO;
import com.example.dtos.out.UserDTO;
import com.example.repository.UserRepository;
import com.example.usecases.RegisterUserResource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

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
    @RestClient
    ForecastProviderPort forecastProviderPort;

    @Inject
    @RestClient
    LocationPort locationPort;

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
    public UserDTO getUserById(@PathParam("id") String id) {
        User user = userRepository.findById(Long.valueOf(id));
        if(user == null) {
            throw new IllegalArgumentException(String.format("User with id: %s does not exist", id));
        }
        Set<Long> forecastProviders = new HashSet<>();
        for(ForecastProvider fp : user.getForecastProviders()) {
            forecastProviders.add(fp.getProviderId());
        }

        UserDTO dto = new UserDTO(user.getId(), user.getName(), user.getLocationId(), forecastProviders);
        return dto;
    }

    @GET
    public List<User> getUserById() {
        return userRepository.findAll().stream().toList();
    }


    /**
     * Get location
     * @param id the user's id
     * @returns
     */
    @GET
    @Path("location/{id}")
    public Optional<LocationDTO> getLocation(@PathParam("id") Long id) {
        User user = userRepository.findById(id);
        if(user == null) {
            throw new IllegalArgumentException(String.format("User with id: %s does not exist", id));
        }
        // currently i just return the dummy location from the LocationDTO {@see LocationAdapter}
       Optional<LocationDTO> locationDTO = locationPort.findLocationById(user.getLocationId());
        return locationDTO;
    }

    /**
     * Get forecast providers
     * @param id user's id
     * @returns
     */

    @GET
    @Path("forecast-providers/{id}")
    public List<ServiceProviderDTO> getForecastProviders(@PathParam("id") Long id) {
        User user = userRepository.findById(id);
        if(user == null){
            throw new IllegalArgumentException(String.format("User with name: %s does not exist", id));
        }
        List<ServiceProviderDTO> forecastProviders = new ArrayList<>();
        for(ForecastProvider fp : user.getForecastProviders()) {
            forecastProviders.add(forecastProviderPort.findForecastProviderById(String.valueOf(fp.getId())));
        }
        return forecastProviders;
    }

}
