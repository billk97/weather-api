package com.example.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.example.dtos.in.RegisterUserDTO;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTests {

    private User user, newUser;


    Set<ForecastProvider> serviceIds;

    long locationId;

    @BeforeEach
    void init(){
        user = new User("bob", "mypass", locationId, serviceIds );
        newUser = new User("alice", "mypass1", locationId, serviceIds );
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
        newUser = new User("bob", "mypass",locationId, serviceIds );
        assertEquals(user, newUser);
    }

    @Test
    void given_same_password_equals_should_fail(){
        newUser = new User("alice", "mypass", locationId, serviceIds );
        assertNotEquals(user, newUser);
    }

    @Test
    void settersGettersShouldWork(){
        String exceptedName = "mark";
        String expectedPassword = "mypass2";
        User user = new User();
        user.setName("mark");
        user.setPassword("mypass2");
        user.setLocationId(locationId);
        user.setForecastProviderIds(serviceIds);

        assertEquals(exceptedName, user.getName());
        assertEquals(expectedPassword, user.getPassword());
        assertEquals(locationId, user.getLocationId());
        assertEquals(serviceIds, user.getForecastProviders());
    }

    @Test
    void given_a_dto_IT_should_create_a_user_successfully() {
        RegisterUserDTO dto = new RegisterUserDTO("pass", "bill", 1);
        User user = new User(dto);
        assertEquals(dto.name(), user.getName());
        User use2r = new User("name", "name");
        assertEquals(dto.name(), user.getName());

    }

}