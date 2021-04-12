package com.example.app.resource;

import com.example.app.domain.Location;
import com.example.app.domain.Service;
import com.example.app.domain.User;
import com.example.app.dtos.BooleanResponseDTO;
import com.example.app.dtos.CredentialsDTO;
import com.example.app.dtos.RegisterUserDTO;
import com.example.app.dtos.UserAddServiceDTO;
import com.example.app.repository.ServiceRepository;
import com.example.app.repository.UserRepository;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserResourceTest extends ResourceTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(UserResource.class, DebugExceptionMapper.class);
    }

    @Test
    public void given_a_username_user_should_return_successfully(){
        User user = target("users/Bill").request().get(new GenericType<User>(){});
        assertEquals("Bill", user.getName());
    }

    @Test
    public void given_a_username_and_password_login_should_succeed(){
        CredentialsDTO credentialsDTO = new CredentialsDTO("Bill", "password");
        Response response = target("users/login").request().post(Entity.entity(credentialsDTO, MediaType.APPLICATION_JSON));
        assertEquals(200, response.getStatus());
        assertTrue(response.readEntity(BooleanResponseDTO.class).isResponse());
    }

    @Test
    public void given_a_user_details_registration_should_succeed(){
        RegisterUserDTO  registerUserDTO= new RegisterUserDTO("maki", "password","athens");
        Response response = target("users").request().post(Entity.entity(registerUserDTO, MediaType.APPLICATION_JSON));
        assertEquals(200, response.getStatus());
    }

    @Test
    public void given_a_user_location_update_should_succeed(){
        RegisterUserDTO  registerUserDTO= new RegisterUserDTO("Bill", "password","patra");
        Response response = target("users/location").request().post(Entity.entity(registerUserDTO, MediaType.APPLICATION_JSON));
        assertEquals(204, response.getStatus());
        UserRepository userRepository = new UserRepository();
        Optional<User> user = userRepository.findByUsername("Bill");
        assertEquals("patra", user.get().getLocation().getCityName());
    }

    @Test
    public void given_a_user_service_addition_should_succeed(){
        UserAddServiceDTO userAddServiceDTO= new UserAddServiceDTO("Bill", "password","meteo");
        Response response = target("users/service").request().post(Entity.entity(userAddServiceDTO
                , MediaType.APPLICATION_JSON));
        assertEquals(204, response.getStatus());
        UserRepository userRepository = new UserRepository();
        ServiceRepository serviceRepository = new ServiceRepository();
        Service service = serviceRepository.findByName("meteo").get();
        Optional<User> user = userRepository.findByUsername("Bill");
        assertTrue(user.get().getServices().contains(service));
    }
    @Test
    public void given_a_user_service_should_succeed(){
        Set<Service> services = target("users/services/Bill").request().get(new GenericType<Set<Service>>(){});
        assertEquals(1, services.size());
    }
    @Test
    public void given_a_user_location_should_succeed(){
        String locationName = target("users/location/Bill").request().get(String.class);
        assertEquals("athens", locationName);
    }
}