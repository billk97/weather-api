package com.example.app.resource;

import com.example.app.domain.Service;
import com.example.app.domain.User;
import com.example.app.dtos.*;
import com.example.app.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

/**
 * The type User resource.
 */
@Path("users")
public class UserResource {
    private UserService userService = new UserService();

    /**
     * Get user user resource.
     *
     * @param username the username
     * @return the user resource
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{username}")
    public User getUser(@PathParam("username") String username){
        return userService.getUser(username);
    }

    /**
     * Login string.
     *
     * @param credentialsDTO the credentials dto
     * @return the string
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("login")
    public BooleanResponseDTO login(CredentialsDTO credentialsDTO){
        return new BooleanResponseDTO(userService.login(credentialsDTO.getUsername(),credentialsDTO.getPassword()));
    }

    /**
     * Register.
     *
     * @param registerUserDTO the register user dto
     * @return the object id dto
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ObjectIdDTO register(RegisterUserDTO registerUserDTO){
        return new ObjectIdDTO(userService.createUser(registerUserDTO.getUsername(),
                registerUserDTO.getPassword(), registerUserDTO.getLocation()));
    }

    /**
     * Add location.
     *
     * @param registerUserDTO the register user dto
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("location")
    public void addLocation(RegisterUserDTO registerUserDTO){
        userService.changeUserLocation(registerUserDTO.getUsername(), registerUserDTO.getLocation());
    }

    /**
     * Add service.
     *
     * @param userAddServiceDTO the user add service dto
     */
    @POST
    @Path("service")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void addService(UserAddServiceDTO userAddServiceDTO){
        userService.addService(userAddServiceDTO.getUsername(),
                userAddServiceDTO.getPassword(),
                userAddServiceDTO.getServiceName());
    }

    /**
     * Get locations set.
     *
     * @param username the username
     * @return the set
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("location/{username}")
    public String getLocation(@PathParam("username") String username){
        return userService.getUser(username).getLocation().getCityName();
    }

    /**
     * Get services set.
     *
     * @param username the username
     * @return the set
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("services/{username}")
    public Set<Service> getServices(@PathParam("username") String username){
        return userService.getUser(username).getServices();
    }

}
