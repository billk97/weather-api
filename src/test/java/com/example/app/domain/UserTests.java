package com.example.app.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UserTests {
    private User user, newUser;

    @Mock
    Set<Service> services;
    @Mock
    Location location;

    @BeforeEach
    void init(){
        user = new User("bob", "mypass", location, services );
        newUser = new User("alice", "mypass1", location, services );
    }

    @Test
    void given_same_object_equals_should_succeed(){
        assertEquals(user, user);
    }

    @Test
    void given_null_object_equals_should_fail(){
        User newUser = null;

        assertFalse(user.equals(newUser));
    }

    @Test
    void given_different_username_object_equals_should_fail(){
        assertFalse(user.equals(newUser));
    }

    @Test
    void given_different_password_equals_should_fail(){
        assertFalse(user.equals(newUser));
    }


    @Test
    void given_same_username_object_equals_should_succeed(){
        newUser = new User("bob", "mypass", location, services);
        assertEquals(user, newUser);
    }

    @Test
    void given_same_password_equals_should_fail(){
        newUser = new User("alice", "mypass", location, services);
        assertNotEquals(user, newUser);
    }

    @Test
    void settersGettersShouldWork(){
        String exceptedName = "mark";
        String expectedPassword = "mypass2";
        User user = new User();
        user.setName("mark");
        user.setPassword("mypass2");
        user.setLocation(location);
        user.setServices(services);

        assertEquals(exceptedName, user.getName());
        assertEquals(expectedPassword, user.getPassword());
        assertEquals(location, user.getLocation());
        assertEquals(services, user.getServices());
    }
    @Test
    void given_a_service_users_can_register_should_be_true(){
        User user = new User();
        user.setName("dkdk");
        user.setPassword("sjss");
        Set<Service> services = new HashSet<>();
        services.add(new Service());
        user.setServices(services);
        assertTrue(user.canRegister());
    }
    @Test
    void given_a_service_users_can_register_should_be_fales(){
        User user = new User();
        user.setName("dkdk");
        user.setPassword("sjss");
        Set<Service> services = new HashSet<>();
        services.add(new Service("kdkd","dddd"));
        services.add(new Service("ddsd","ddd"));
        services.add(new Service("sdnsn","dncdn"));
        services.add(new Service("jfnsn", "sdnjsdd"));
        user.setServices(services);
        assertFalse(user.canRegister());
    }
}