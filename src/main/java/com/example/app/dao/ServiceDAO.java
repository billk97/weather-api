package com.example.app.dao;

import com.example.app.domain.Service;

import java.util.List;
import java.util.Optional;

/**
 * The interface Service dao.
 */
public interface ServiceDAO {

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Service> findAll();

    /**
     * Save.
     *
     * @param service the service
     */
    void save(Service service);

    /**
     * Delete.
     *
     * @param service the service
     */
    void delete(Service service);

    /**
     * Find by id optional.
     *
     * @param serviceId the service id
     * @return the optional
     */
    Optional<Service> findById(int serviceId);

    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the optional
     */
    Optional<Service> findByName(String name);

}
