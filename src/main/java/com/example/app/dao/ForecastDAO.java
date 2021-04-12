package com.example.app.dao;

import com.example.app.domain.Forecast;
import com.example.app.domain.Location;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * The interface Forecast dao.
 */
public interface ForecastDAO {

    /**
     * finds all forecast in database @return the list
     *
     * @return the list
     */
    List<Forecast> findAll();


    /**
     * Save.
     *
     * @param forecast the forecast
     */
    void save(Forecast forecast);

    /**
     * Delete.
     *
     * @param forecast the forecast
     */
    void delete(Forecast forecast);

    /**
     * Find one by id optional.
     *
     * @param forecastId the forecast id
     * @return the optional
     */
    Optional<Forecast> findOneById(int forecastId);

    /**
     * Find by location list.
     *
     * @param location the location
     * @return the list
     */
    List<Forecast> findByLocation(Location location);

    /**
     * Find current by location list.
     *
     * @param location the location
     * @return the list
     */
    List<Forecast> findCurrentByLocation(Location location, boolean mock);

    /**
     * Find by location time list.
     *
     * @param location  the location
     * @param localTime the local time
     * @return the list
     */
    List<Forecast> findByLocationTime(Location location, LocalTime localTime, boolean mock);

    /**
     * Find by location date and time list.
     *
     * @param location  the location
     * @param localDate the local date
     * @param localTime the local time
     * @return the list
     */
    List<Forecast> findByLocationDateAndTime(Location location, LocalDate localDate, LocalTime localTime);


}
