package com.example.app.resource;

import com.example.app.domain.Service;
import com.example.app.dtos.EmptyDTO;
import com.example.app.dtos.NewServiceNameDTO;
import com.example.app.repository.ServiceRepository;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceResourceTest extends ResourceTest {

    private ServiceRepository serviceRepository;

    @Override
    protected Application configure() {
        return new ResourceConfig(ServiceResource.class, DebugExceptionMapper.class);
    }

    @Test
    public void given_a_serviceName_resource_should_be_created(){
        Response response = target("services/test").request().post(Entity.entity(new EmptyDTO(), MediaType.APPLICATION_JSON_TYPE));
        assertEquals(200, response.getStatus());
        serviceRepository = new ServiceRepository();
        assertTrue(serviceRepository.findByName("test").isPresent());

    }

    @Test
    public void given_a_existing_serviceName_change_to_name_should_succeed(){
        Response response =  target("services/google").request().put(Entity.entity(new NewServiceNameDTO("bill"), MediaType.APPLICATION_JSON_TYPE));
        assertEquals(204, response.getStatus());
        serviceRepository = new ServiceRepository();
        assertTrue(serviceRepository.findByName("bill").isPresent());
    }

    @Test
    public void all_services_should_be_returned_correctly(){
        List<Service> services = target("services").request().get(new GenericType<List<Service>>(){});
        assertEquals(3, services.size());
    }

    @Test
    public void given_a_service_name_the_howle_service_should_be_returned_successffuly(){
        Service service = target("services/meteo").request().get(new GenericType<Service>(){});
        assertEquals("Greek weather station", service.getDescription());
    }
}