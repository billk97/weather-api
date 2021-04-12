package com.example.app.dao;

import com.example.app.domain.Service;
import com.example.app.persistence.DatabaseUtils;
import com.example.app.repository.ServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;

import static org.junit.jupiter.api.Assertions.*;

class ServiceRepositoryTests {

    @Mock
    private Service service;

    private ServiceRepository serviceRepository;

    @BeforeEach
    void setUp() {
        DatabaseUtils databaseUtils = new DatabaseUtils();
        databaseUtils.createInitialState();
        serviceRepository = new ServiceRepository();
    }

    @Test
    void service_repository_should_return_not_null_list() {
        int expectedInitialListSize = 3;
        assertNotNull(serviceRepository.findAll());
        assertEquals(expectedInitialListSize, serviceRepository.findAll().size());
    }

    @Test
    void given_a_service_deletion_should_succeed() {
        Service service = new Service("non_existant_service", "The best service");
        serviceRepository.save(service);

        assertEquals(Optional.of(service),serviceRepository.findByName(service.getName()));
        serviceRepository.delete(service);

        assertFalse(serviceRepository.findByName(service.getName()).isPresent());
    }

    //todo
    @Test
    void given_a_service_deletion_that_not_exists_should_throw() {
        Service service = new Service("non_existant_service", "The best service");
        
        serviceRepository.delete(service);

        assertFalse(serviceRepository.findByName(service.getName()).isPresent());

    }

    @Test
    void given_a_service_entity_manager_should_save_to_sb_successfully() {
        Service service = new Service("non_existant_service", "The best service");
        serviceRepository.save(service);

        assertEquals(Optional.of(service),serviceRepository.findByName(service.getName()));
    }

    @Test
    void given_a_service_that_exists_entity_manager_should_throw_error() {
        Service service = new Service("meteo", "The best service");

        assertThrows(RollbackException.class,()->{
            serviceRepository.save(service);
        });
    }

}
