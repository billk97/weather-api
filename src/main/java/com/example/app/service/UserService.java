package com.example.app.service;

import com.example.app.domain.Location;
import com.example.app.domain.Service;
import com.example.app.domain.User;
import com.example.app.repository.LocationRepository;
import com.example.app.repository.ServiceRepository;
import com.example.app.repository.UserRepository;

import java.util.Optional;

/**
 * The type User service.
 */
public class UserService {

    private LocationRepository locationRepository = new LocationRepository();
    private UserRepository userRepository = new UserRepository();
    private ServiceRepository serviceRepository = new ServiceRepository();

    /**
     * Create user int.
     *
     * @param name         the name
     * @param password     the password
     * @param userLocation the user location
     * @return the int
     */
    public int createUser(String name, String password, String userLocation){
        Optional<Location> location = locationRepository.findOneByName(userLocation);
        User user = new User(name, password);
        if(!location.isPresent()){
            Location location1 = new Location();
            location1.setCityName(userLocation);
            locationRepository.save(location1);
            user.setLocation(location1);
        }else {
            user.setLocation(location.get());
        }
        userRepository.save(user);
        return user.getUserId();
    }

    /**
     * Login boolean.
     *
     * @param username the username
     * @param password the password
     * @return the boolean
     */
    public boolean login(String username, String password){
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()){
            return false;
        }
        if (!user.get().getPassword().equals(password)){
            return false;
        }
        return true;
    }

    /**
     * Change user location.
     *
     * @param username the username
     * @param cityName the city name
     */
    public void changeUserLocation(String username, String cityName){
        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent()){
            throw new IllegalArgumentException("no user with username: "+ username);
        }
        Optional<Location> locationOptional = locationRepository.findOneByName(cityName);
        if(!locationOptional.isPresent()){
            throw new IllegalArgumentException("location with name: " +cityName+ " does not exist");
        }
        user.get().setLocation(locationOptional.get());
        userRepository.save(user.get());
    }

    /**
     * Get user user.
     *
     * @param username the username
     * @return the user
     */
    public User getUser(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent()){
            throw new IllegalArgumentException("no user with username: "+ username);
        }
        return  user.get();
    }

    /**
     * Add service.
     *
     * @param username    the username
     * @param password    the password
     * @param serviceName the service name
     */
    public void addService(String username, String password, String serviceName){
        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent()){
            throw new IllegalArgumentException("no user with username: "+ username);
        }
        if(!user.get().getPassword().equals(password)){
            throw new IllegalArgumentException("User not authenticated "+ username);
        }
        Optional<Service> optionalService = serviceRepository.findByName(serviceName);
        if(!optionalService.isPresent()){
            throw new IllegalArgumentException("service doesn't exist: "+ serviceName);
        }
        user.get().addService(optionalService.get());
        userRepository.save(user.get());

    }

}
