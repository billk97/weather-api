package com.example.app.intergration;

import com.example.app.domain.Location;
import com.example.app.persistence.DatabaseUtils;
import com.example.app.persistence.JPAUtil;
import com.sun.tools.xjc.model.nav.EagerNClass;
import org.junit.jupiter.api.*;

import javax.persistence.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LocationTests {

    @BeforeEach
    void setUp() {
        DatabaseUtils databaseUtils = new DatabaseUtils();
        databaseUtils.createInitialState();
    }
    @Test
    void database_should_not_be_null(){
        EntityManager entityManager = JPAUtil.getCurrentEntityManager();
        Query query = entityManager.createQuery("select l from Location  l where l.cityName =:name", Location.class);
        assertNotNull(query.setParameter("name", "athens").getFirstResult());
        entityManager.close();
    }

    @Test
    void addition_to_db_should_be_successful(){
        EntityManager entityManager = JPAUtil.getCurrentEntityManager();
        Location location = new Location("paris");

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(location);
        transaction.commit();

        Query query = entityManager.createQuery("select l FROM Location l where l.cityName = :name", Location.class);
        List list = query.setParameter("name", "paris").getResultList();
        assertFalse(list.isEmpty());
        assertTrue(list.contains(location));
    }

    @Test
    void adding_already_existing_location_should_throw_unique_constrain(){
        EntityManager entityManager = JPAUtil.getCurrentEntityManager();
        Location location = new Location("athens");

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        assertThrows(RollbackException.class, () ->{
            entityManager.persist(location);
            transaction.commit();
        });
    }

    @Test
    void deletion_to_db_should_throw_exception_foreign_key(){
        EntityManager entityManager = JPAUtil.getCurrentEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("select l FROM Location l where l.cityName = :name", Location.class);
        Location locationToDelete =  (Location) query.setParameter("name", "athens").getSingleResult();
        entityManager.remove(locationToDelete);
        assertThrows(RollbackException.class, () -> {
            transaction.commit();
        });
        entityManager.clear();
    }

    @Test
    @Disabled
    void deletion_of_location_with_no_foreign_to_db_should_succeed(){
        EntityManager entityManager = JPAUtil.getCurrentEntityManager();
        Location location = new Location("paris");

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(location);
        transaction.commit();
        entityManager.clear();

        Query query = entityManager.createQuery("select l FROM Location l where l.cityName = :name", Location.class);
        Location locationToDelete =  (Location) query.setParameter("name", "paris").getSingleResult();

        transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(location);
        transaction.commit();
        entityManager.clear();
        query = entityManager.createQuery("select l FROM Location l where l.cityName = :name", Location.class);
        List <Location> locationList = query.setParameter("name", "paris").getResultList();

        assertFalse(locationList.contains(location));
    }



}
