package com.example.app.dao;


import com.example.app.domain.Location;
import com.example.app.domain.Service;
import com.example.app.domain.User;
import com.example.app.persistence.DatabaseUtils;
import com.example.app.repository.LocationRepository;
import com.example.app.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.persistence.RollbackException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UserRepositoryTests {

    private UserRepository userRepository;

    @Mock
    private Location location;

    @Mock
    private Set<Service> serviceSet;

    @BeforeEach
    void setUp() {
        DatabaseUtils databaseUtils = new DatabaseUtils();
        databaseUtils.createInitialState();
        userRepository = new UserRepository();
    }

    @Test
    void given_a_user_save_should_succeed(){
        User user = new User();
        user.setName("a randome name");
        user.setPassword("passss");
        userRepository.save(user);
        user.setLocation(location);
        user.setServices(serviceSet);
        assertEquals(user.getUserId(), userRepository.findById(user.getUserId()).get().getUserId());
    }
    @Test
    void given_a_user_deletion_should_succeed(){
        User user = new User();
        user.setLocation(location);
        user.setName("bill");
        user.setServices(serviceSet);
        user.setPassword("password");

        userRepository.save(user);
        userRepository.delete(user);
        assertEquals(Optional.empty(), userRepository.findById(user.getUserId()));
    }
    @Test
    void given_an_existing_user_save_should_throw(){
        User user = new User();
        user.setLocation(location);
        user.setName("bill");
        user.setServices(serviceSet);
        user.setPassword("password");

        User user1 = new User();
        user1.setLocation(location);
        user1.setName("bill");
        user1.setServices(serviceSet);
        user1.setPassword("password");
        userRepository.save(user);
        System.out.println(userRepository.findById(user.getUserId()).get().getName());
        assertThrows(RollbackException.class, () -> {
            userRepository.save(user1);
        });
    }
    @Test
    void given_a_username_deletion_should_succeed(){
        User user = new User();
        user.setLocation(location);
        user.setName("bill");
        user.setServices(serviceSet);
        user.setPassword("password");
        userRepository.save(user);
        assertEquals(user.getName(), userRepository.findByUsername(user.getName()).get().getName());
        userRepository.deleteByName(user.getName());
        assertThrows(NoSuchElementException.class, () -> {
            userRepository.findById(user.getUserId()).get().getName();
        });
    }
    @Test
    void given_a_location_findByLocation_should_succeed(){
        LocationRepository locationRepository = new LocationRepository();
        assertNotNull(userRepository.findByLocation(locationRepository.findAll().get(0)));
    }

    @Test
    void given_a_location_findByLocationName_should_succeed(){
        assertNotNull(userRepository.findByLocationName("athens"));
    }
    @Test
    void given_a_location_findByServiceName_should_succeed(){
        assertNotNull(userRepository.findByServiceName("meteo"));
    }
    @Test
    void database_should_have_3_users_when_started(){
        int expectedNumberOfUsers = 3;
        assertEquals(expectedNumberOfUsers, userRepository.findAll().size());
    }

    @Test
    void given_a_username_deletion_byId_should_succeed(){
        User user = new User();
        user.setLocation(location);
        user.setName("bill");
        user.setServices(serviceSet);
        user.setPassword("password");
        userRepository.save(user);

        assertEquals(user.getName(), userRepository.findByUsername(user.getName()).get().getName());
        userRepository.deleteById(user.getUserId());
        assertThrows(NoSuchElementException.class, () -> {
            userRepository.findById(user.getUserId()).get().getName();
        });
    }


}
