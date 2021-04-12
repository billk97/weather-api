package com.example.app.dao;

import com.example.app.domain.Location;
import com.example.app.domain.Service;
import com.example.app.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The interface User dao.
 */
public interface UserDAO {

    /**
     * Find all list.
     *
     * @return the list
     */
    List<User> findAll();

    /**
     * Save.
     *
     * @param user the user
     */
    void save(User user);

    /**
     * Delete.
     *
     * @param user the user
     */
    void delete(User user);

    /**
     * Delete by name.
     *
     * @param username the username
     */
    void deleteByName(String username);

    /**
     * Delete by id.
     *
     * @param userId the user id
     */
    void deleteById(int userId);

    /**
     * Find by id optional.
     *
     * @param userId the user id
     * @return the optional
     */
    Optional<User> findById(int userId);

    /**
     * Find by username optional.
     *
     * @param username the username
     * @return the optional
     */
    Optional<User> findByUsername(String username);

    /**
     * Find by location list.
     *
     * @param location the location
     * @return the list
     */
    List<User> findByLocation(Location location);

    /**
     * Find by location name list.
     *
     * @param cityName the city name
     * @return the list
     */
    List<User> findByLocationName(String cityName);

    /**
     * Find by service list.
     *
     * @param service the service
     * @return the list
     */
    List<User> findByService(Service service);

    /**
     * Find by service name list.
     *
     * @param serviceName the service name
     * @return the list
     */
    default List<User> findByServiceName(String serviceName) {
        List<User> emptyList = new ArrayList<>();
        return emptyList;
    }

}
