package com.example.app.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)

public class ServiceTests {
    private Service service;

    @BeforeEach
    void init(){
        service = new Service("Meteo", "The best service");
    }

    @Test
    void given_same_object_equals_should_succeed(){
        assertEquals(service, service);
    }

    @Test
    void given_null_object_equals_should_fail(){
        Service newService = null;
        assertFalse(service.equals(newService));
    }

    @Test
    void given_same_service_name_should_succeed(){
        Service newService = new Service("Meteo", "The best service");
        assertTrue(service.equals(newService));
    }

    @Test
    void given_different_service_name_should_fail(){
        Service newService = new Service("Emy", "Other Service");
        assertFalse(service.equals(newService));
    }

    @Test
    void given_same_service_name_different_service_description_should_fail(){
        Service newService = new Service("Meteo", "The wrong service");
        assertFalse(service.equals(newService));
    }

    @Test
    void given_different_service_name_same_service_description_should_fail(){
        Service newService = new Service("Emy", "The best service");
        assertFalse(service.equals(newService));
    }

}
