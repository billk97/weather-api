package com.example.app.repository;

import com.example.app.dao.ForecastDAO;
import com.example.app.domain.Forecast;
import com.example.app.domain.Location;
import com.example.app.persistence.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * The type Forecast repository.
 */
public class ForecastRepository implements ForecastDAO {

    private  EntityManager entityManager = JPAUtil.getCurrentEntityManager();

    @Override
    public List<Forecast> findAll() {
        Query query = entityManager.createQuery("Select e from Forecast e");
        return query.getResultList();
    }

    @Override
    public void save(Forecast forecast) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(forecast);
        transaction.commit();
    }

    @Override
    public void delete(Forecast forecast) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // Todo Check if transaction exists
        entityManager.remove(forecast);
        transaction.commit();
    }

    @Override
    public Optional<Forecast> findOneById(int forecastId) {
        return Optional.ofNullable(entityManager.find(Forecast.class, forecastId));
    }

    @Override
    public List<Forecast> findByLocation(Location location){
        Query query  = entityManager.createQuery("Select f from Forecast f join f.location l where l.cityName = :cityName" ,Forecast.class);
        return query.setParameter("cityName", location.getCityName()).getResultList();
    }

    @Override
    public List<Forecast> findCurrentByLocation(Location location, boolean mock){
        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        if(mock){
            localTime = LocalTime.of(6,0);
            localDate = LocalDate.of(2020,11,23);
        }
        Query query  = entityManager.createQuery("Select f from Forecast f join f.location l " +
                "where l.cityName = :cityName and f.localTime = :localTime and f.localDate = :localDate" ,Forecast.class);
        return query.setParameter("cityName", location.getCityName())
                .setParameter("localTime", localTime)
                .setParameter("localDate", localDate)
                .getResultList();
    }

    @Override
    public List<Forecast> findByLocationTime(Location location, LocalTime localTime, boolean mock) {
        LocalDate localDate = LocalDate.now();
        if(mock){
            localTime = LocalTime.of(6,0);
            localDate = LocalDate.of(2020,11,23);
        }
        Query query  = entityManager.createQuery("Select f from Forecast f join f.location l " +
                "where l.cityName = :cityName and f.localTime = :localTime and f.localDate = :localDate" ,Forecast.class);
        return query.setParameter("cityName", location.getCityName())
                .setParameter("localTime", localTime)
                .setParameter("localDate", localDate)
                .getResultList();
    }

    @Override
    public List<Forecast> findByLocationDateAndTime(Location location, LocalDate localDate, LocalTime localTime) {
        Query query  = entityManager.createQuery("Select f from Forecast f join f.location l " +
                "where l.cityName = :cityName and f.localTime = :localTime and f.localDate = :localDate" ,Forecast.class);
        return query.setParameter("cityName", location.getCityName())
                .setParameter("localTime", localTime)
                .setParameter("localDate", localDate)
                .getResultList();
    }
}
