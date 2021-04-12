package com.example.app.dao;

import com.example.app.domain.Forecast;
import com.example.app.domain.Location;

import java.util.List;
import java.util.Optional;

/**
 * The interface Location dao.
 */
public interface LocationDAO {

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Location> findAll();

    /**
     * Save.
     *
     * @param location the location
     */
    void save(Location location);

    /**
     * Delete.
     *
     * @param location the location
     */
    void delete(Location location);

    /**
     * Find one by name optional.
     *
     * @param locationName the location name
     * @return the optional
     */
    Optional<Location> findOneByName(String locationName);

    /**
     * Find by id optional.
     *
     * @param locationId the location id
     * @return the optional
     */
    Optional<Location> findById(int locationId);
}
