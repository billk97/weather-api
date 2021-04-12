package com.example.app.service;

import com.example.app.domain.Service;
import com.example.app.persistence.DatabaseUtils;
import com.example.app.repository.ServiceRepository;
import com.example.app.repository.UserRepository;
import org.junit.jupiter.api.*;

import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserServiceTest {
    private  UserService userService;
    private UserRepository userRepository;
    private ServiceRepository serviceRepository;

    @BeforeEach
    void init(){
        DatabaseUtils databaseUtils = new DatabaseUtils();
        databaseUtils.createInitialState();
        userService = new UserService();
        userRepository = new UserRepository();
        serviceRepository = new ServiceRepository();
    }

    @Test
    void given_an_existing_location_user_creation_should_be_successful() {
        int id = userService.createUser("someone", "dddd", "athens");
        assertEquals(id, userRepository.findById(id).get().getUserId());
    }

    @Test
    void given_an_non_existing_location_user_creation_should_be_successful() {
        int id = userService.createUser("someone", "dddd", "kalabaka");
        assertEquals(id, userRepository.findById(id).get().getUserId());
    }
    @Test
    void given_an_existing_user_user_creation_should_be_fail() {
        assertThrows(RollbackException.class, () -> {
            userService.createUser("Bill", "dddd", "athens");
        });
    }
    @Test
    void given_a_username_and_password_login_should_be_true(){
        assertTrue(userService.login("Bill", "password"));

    }
    @Test
    void given_a_false_username_login_should_be_fail(){
        assertFalse(userService.login("fail", "password"));
    }

    @Test
    void given_an_incorrect_password_login_should_be_fail(){
        assertFalse(userService.login("Bill", "fail"));
    }

    @Test
    void given_a_correct_location_user_profile_should_change(){
        userService.changeUserLocation("Bill", "patra");
        assertEquals("patra", userRepository.findByUsername("Bill").get().getLocation().getCityName());
    }
    @Test
    void given_a_non_existing_user_profile_should_change(){
        assertThrows(IllegalArgumentException.class, ()->{
            userService.changeUserLocation("bill", "patra");
        });
    }
    @Test
    void given_a_non_existing_location_profile_should_change(){
        assertThrows(IllegalArgumentException.class, ()->{
            userService.changeUserLocation("Bill", "sscdc");
        });
    }
    @Test
    void given_a_user_service_should_be_add_successfully(){
        userService.addService("Bill", "password","emy");
        Optional<Service> service = serviceRepository.findByName("emy");
        assertTrue(userRepository.findByUsername("Bill").get().getServices().contains(service.get()));
    }
    @Test
    void given_a_false_user_service_should_throw(){
        assertThrows(IllegalArgumentException.class, ()->{
            userService.addService("dddddill", "password","emy");
        });
    }
    @Test
    void given_a_false_service_service_should_throw(){
        assertThrows(IllegalArgumentException.class, ()->{
            userService.addService("Bill", "password","emysdsd");
        });
    }
    @Test
    void given_a_false_password_service_should_throw(){
        assertThrows(IllegalArgumentException.class, ()->{
            userService.addService("Bill", "passwodddrd","emysdsd");
        });
    }

    @Test
    void given_a_username_user_should_be_returned_successfully(){
        assertEquals("password",userService.getUser("Bill").getPassword());
    }
    @Test
    void given_a_false_username_user_should_throw(){
        assertThrows(IllegalArgumentException.class, ()->{
            userService.getUser("Bffill");
        });
    }


}