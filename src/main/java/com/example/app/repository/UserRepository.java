package com.example.app.repository;

import com.example.app.dao.UserDAO;
import com.example.app.domain.Forecast;
import com.example.app.domain.Location;
import com.example.app.domain.Service;
import com.example.app.domain.User;
import com.example.app.persistence.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 * The type User repository.
 */
public class UserRepository implements UserDAO {

    private EntityManager entityManager = JPAUtil.getCurrentEntityManager();

    @Override
    public List<User> findAll() {
        Query query = entityManager.createQuery("Select u from User u", User.class);
        return query.getResultList();
    }

    @Override
    public void save(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
    }

    @Override
    public void delete(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(user);
        transaction.commit();

    }

    // Todo prosoxi
    @Override
    public void deleteByName(String username) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("SELECT u from User  u where u.name = :username", User.class);
        User user = (User) query.setParameter("username", username).getSingleResult();
        entityManager.remove(user);
        transaction.commit();
    }

    @Override
    public void deleteById(int userId) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        User user = entityManager.find(User.class, userId);
        entityManager.remove(user);
        transaction.commit();
    }

    @Override
    public Optional<User> findById(int userId) {
        return Optional.ofNullable(entityManager.find(User.class, userId));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Query query = entityManager.createQuery("SELECT u from User u where u.name = :username", User.class);
        return query.setParameter("username", username).getResultList().stream().findFirst();
    }

    @Override
    public List<User> findByLocation(Location location) {
        Query query = entityManager.createQuery("SELECT u from User u where u.location = :location", User.class);
        return query.setParameter("location", location).getResultList();
    }

    @Override
    public List<User> findByLocationName(String cityName) {
        Query query = entityManager.createQuery("SELECT u from User u where u.location.cityName = :locationName", User.class);
        return query.setParameter("locationName", cityName).getResultList();
    }

    @Override
    public List<User> findByService(Service service) {
        Query query = entityManager.createQuery("SELECT u from User u where u.services = :service", User.class);
        return query.setParameter("service", service).getResultList();
    }

    // Todo investigate how to handle this
//    @Override
//    public List<User> findBysServiceName(String serviceName) {
//        Query query = entityManager.createQuery("SELECT u from User u where u.services.name = :serviceName", User.class);
//        return query.setParameter("serviceName", serviceName).getResultList();
//    }
}
