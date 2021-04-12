package com.example.app.service;

import com.example.app.domain.Location;
import com.example.app.repository.ForecastRepository;
import com.example.app.repository.LocationRepository;
import com.example.app.repository.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * The type Location service.
 */
public class LocationService {
    private UserRepository userRepository = new UserRepository();;
    private ForecastRepository forecastRepository =  new ForecastRepository();
    private LocationRepository locationRepository =  new LocationRepository();

    /**
     * Add new location int.
     *
     * @param locationName the location name
     * @return the int
     */
    public int addNewLocation(String locationName){
        Optional<Location> optionalLocation = locationRepository.findOneByName(locationName);
        if(optionalLocation.isPresent()){
            throw new IllegalArgumentException("location already exists: " + locationName);
        }
        Location location = new Location();
        location.setCityName(locationName);
        locationRepository.save(location);
        return location.getLocationId();
    }

    /**
     * Delete location.
     *
     * @param locationName the location name
     */
    public void deleteLocation(String locationName){
        Optional<Location> optionalLocation = locationRepository.findOneByName(locationName);
        if(!optionalLocation.isPresent()){
            throw new IllegalArgumentException("location doesn't exist:" + locationName);
        }
        locationRepository.delete(optionalLocation.get());
    }

    /**
     * Find all locations list.
     *
     * @return the list
     */
    public List<Location> findAllLocations(){
        List<Location> locations = locationRepository.findAll();
        return locations;
    }

    /**
     * Find location location.
     *
     * @param locationName the location name
     * @return the location
     */
    public Location findLocation(String locationName){
        Optional<Location> optionalLocation = locationRepository.findOneByName(locationName);
        if(!optionalLocation.isPresent()){
            throw new IllegalArgumentException("location doesn't exist:" + locationName);
        }
        return optionalLocation.get();
    }

}

