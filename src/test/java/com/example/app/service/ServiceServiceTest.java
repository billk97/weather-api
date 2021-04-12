package com.example.app.service;

import com.example.app.domain.Service;
import com.example.app.persistence.DatabaseUtils;
import com.example.app.repository.ServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import javax.persistence.RollbackException;

import static org.junit.jupiter.api.Assertions.*;
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ServiceServiceTest {

    private ServiceService serviceService;
    private ServiceRepository serviceRepository;

    @BeforeEach
    void init(){
        DatabaseUtils databaseUtils = new DatabaseUtils();
        databaseUtils.createInitialState();
        serviceService = new ServiceService();
        serviceRepository = new ServiceRepository();
    }

    @Test
    void given_a_non_existing_service_new_should_be_added_successfully(){
        int serviceId = serviceService.addNewService("custom");
        assertTrue(serviceRepository.findById(serviceId).isPresent());
    }

    @Test
    void given_a_existing_service_new_addition_should_throw(){
        assertThrows(IllegalArgumentException.class,()->{
            serviceService.addNewService("meteo");
        });
    }

    @Test
    void given_a_existing_service_name_should_change_successfully(){
        serviceService.updateServiceName("meteo", "cia");
        assertTrue(serviceRepository.findByName("cia").isPresent());
    }

    @Test
    void given_a_non_existing_service_name_should_throw(){
        assertThrows(IllegalArgumentException.class, ()->{
            serviceService.updateServiceName("cia", "cia");
        });
    }

    @Test
    void given_a_existing_service_name_should_throw(){
        assertThrows(IllegalArgumentException.class, ()->{
            serviceService.updateServiceName("meteo", "google");
        });
    }

    @Test
    void given_a_existing_service_deletion_should_succeed(){
        Service service = new Service();
        service.setName("custom");
        serviceRepository.save(service);
        assertTrue(serviceRepository.findByName("custom").isPresent());
        serviceService.deleteService("custom");
        assertFalse(serviceRepository.findByName("custom").isPresent());

    }

    @Test
    void given_a_non_existing_service_deletion_should_throw(){
        assertThrows(IllegalArgumentException.class, ()->{
            serviceService.deleteService("custom");
        });
    }

    @Test
    void given_a_existing_service_being_used_deletion_should_throw(){
        assertThrows(RollbackException.class, ()->{
            serviceService.deleteService("google");
        });
    }

    @Test
    void all_services_should_be_obtained_successfully(){
            assertEquals(3, serviceService.getAllServices().size());
    }

    @Test
    void given_an_existing_service_name_service_should_be_returned_successfully(){
        Service service = serviceService.getService("meteo");
        assertEquals("Greek weather station",service.getDescription());

    }

    @Test
    void given_an_existing_service_name_service_should_throw(){
        assertThrows(IllegalArgumentException.class, ()->{
            serviceService.getService("mttt");
        });
    }



}