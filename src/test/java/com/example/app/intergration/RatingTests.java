package com.example.app.intergration;

import com.example.app.domain.Forecast;
import com.example.app.domain.Rating;
import com.example.app.domain.User;
import com.example.app.persistence.DatabaseUtils;
import com.example.app.persistence.JPAUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RatingTests {
    @Mock
    Forecast forecast;
    @Mock
    User user;
    @BeforeEach
    void setUp() {
        DatabaseUtils databaseUtils = new DatabaseUtils();
        databaseUtils.createInitialState();
    }

    @Test
    void database_should_not_be_null(){
        EntityManager entityManager = JPAUtil.getCurrentEntityManager();
        Query query = entityManager.createQuery("select r from Rating r ");
        assertNotNull(query.getFirstResult());
        entityManager.close();
    }
    @Test
    void adding_a_rating_to_the_database_should_succeed(){
        EntityManager entityManager = JPAUtil.getCurrentEntityManager();
        Rating rating = new Rating();
        rating.setScore(5);
        rating.setForecast(forecast);
        rating.setUser(user);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(rating);
        entityManager.flush();
        transaction.commit();
        Rating receivedRating = entityManager.find(Rating.class, rating.getRatingId());
        assertEquals(rating.getScore(), receivedRating.getScore());
        entityManager.close();
    }

    @Test
    void adding_and_deleting_rating_to_the_database_should_succeed(){
        EntityManager entityManager = JPAUtil.getCurrentEntityManager();
        Rating rating = new Rating();
        rating.setScore(5);
        rating.setForecast(forecast);
        rating.setUser(user);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(rating);
        entityManager.flush();

        Rating receivedRating = entityManager.find(Rating.class, rating.getRatingId());
        assertEquals(rating.getScore(), receivedRating.getScore());
        entityManager.remove(rating);
        entityManager.flush();
        Rating rating1 = entityManager.find(Rating.class, rating.getRatingId());
        assertNull(rating1);
        transaction.commit();
        entityManager.close();
    }

    @Test
    void finding_a_rating_should_be_successful() {
        EntityManager entityManager = JPAUtil.getCurrentEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("SELECT r from Rating r", Rating.class);
        List<Rating> ratings = query.getResultList();
        assertNotNull(ratings);
        assertEquals(1, ratings.size());
        assertEquals(5, ratings.get(0).getScore());
        transaction.commit();
        entityManager.close();
    }

}
