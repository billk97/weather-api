package com.example.app.repository;

import com.example.app.dao.RatingDAO;
import com.example.app.domain.Forecast;
import com.example.app.domain.Rating;
import com.example.app.domain.Service;
import com.example.app.domain.User;
import com.example.app.persistence.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 * The type Rating repository.
 */
public class RatingRepository implements RatingDAO {

    private EntityManager entityManager = JPAUtil.getCurrentEntityManager();

    @Override
    public List<Rating> findAll() {
        Query query = entityManager.createQuery("Select r from Rating r", Rating.class);
        return query.getResultList();
    }

    @Override
    public void save(Rating rating) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(rating);
        transaction.commit();

    }

    @Override
    public void delete(Rating rating) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(rating);
        transaction.commit();

    }

    // TODO
    @Override
    public List<Rating> findByForecast(Forecast forecast) {
        Query query = entityManager.createQuery("SELECT r FROM Rating r where r.forecast = :forecast", Rating.class);
        return query.setParameter("forecast", forecast).getResultList();
    }

    // TODO
    @Override
    public List<Rating> findByUser(User user) {
        Query query = entityManager.createQuery("SELECT r FROM Rating r where r.user = :user", Rating.class);
        return query.setParameter("user", user).getResultList();
    }

    @Override
    public Optional<Rating> findById(int ratingId) {
        return Optional.ofNullable(entityManager.find(Rating.class, ratingId));
    }

    @Override
    public Optional<Rating> findByRating(Rating rating) {
        return Optional.ofNullable(entityManager.find(Rating.class, rating.getRatingId()));
    }

    @Override
    public Optional<Rating> findByUserIdAndForecastId(int userId, int forecastId) {
        Query query = entityManager.createQuery("SELECT r FROM Rating r where r.user.id = :userId AND r.forecast.id = :forecastId ", Rating.class);
        return  Optional.of((Rating)query.setParameter("userId", userId).setParameter("forecastId", forecastId).getSingleResult());
    }

}
