package com.example.app.repository;

import com.example.app.dao.LocationDAO;
import com.example.app.domain.Forecast;
import com.example.app.domain.Location;
import com.example.app.persistence.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 * The type Location repository.
 */
public class LocationRepository implements LocationDAO {

    private EntityManager entityManager = JPAUtil.getCurrentEntityManager();

    @Override
    public List<Location> findAll() {
        Query query =  entityManager.createQuery("SELECT l from Location l", Location.class);
        return query.getResultList();
    }

    @Override
    public void save(Location location) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(location);
        transaction.commit();
    }

    @Override
    public void delete(Location location) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(location);
        transaction.commit();
    }

    // TODO test very carefully
    @Override
    public Optional<Location> findOneByName(String cityName) {
        Query query = entityManager.createQuery("SELECT l from Location l WHERE l.cityName = :cityName",Location.class);
        return query.setParameter("cityName", cityName).getResultList().stream().findFirst();
    }

    @Override
    public Optional<Location> findById(int locationId) {
        return Optional.ofNullable(entityManager.find(Location.class, locationId));
    }
}
