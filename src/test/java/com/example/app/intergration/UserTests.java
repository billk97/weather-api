package com.example.app.intergration;

import com.example.app.domain.Service;
import com.example.app.domain.User;
import com.example.app.persistence.DatabaseUtils;
import com.example.app.persistence.JPAUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UserTests {
    @BeforeEach
    void setUp() {
        DatabaseUtils databaseUtils = new DatabaseUtils();
        databaseUtils.createInitialState();
    }

    @Test
    void database_should_not_be_null(){
        EntityManager entityManager = JPAUtil.getCurrentEntityManager();
        Query query = entityManager.createQuery("select u from User u ");
        assertNotNull(query.getFirstResult());
        entityManager.close();
    }
    @Test
    void adding_a_service_to_the_database_should_succeed(){
        EntityManager entityManager = JPAUtil.getCurrentEntityManager();

        User user = new User();
        user.setName("nanana");
        user.setPassword("nanana");
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(user);
        entityManager.flush();
        transaction.commit();
        User recieved = entityManager.find(User.class, user.getUserId());
        assertEquals(user.getName(), recieved.getName());
        assertEquals(user.getUserId(), recieved.getUserId());
        assertEquals(user.getUserId(), recieved.getUserId());
        entityManager.close();
    }

    @Test
    void adding_and_deleting_a_service_to_the_database_should_succeed(){
        EntityManager entityManager = JPAUtil.getCurrentEntityManager();

        User user = new User();
        user.setName("nanana");
        user.setPassword("nanana");
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(user);
        entityManager.flush();

        User recieved = entityManager.find(User.class, user.getUserId());
        assertEquals(user.getUserId(), recieved.getUserId());
        assertEquals(user.getName(), recieved.getName());
        entityManager.remove(user);
        entityManager.flush();
        User service1 = entityManager.find(User.class, user.getUserId());
        assertNull(service1);
        transaction.commit();
        entityManager.close();
    }

    @Test
    void finding_a_service_should_succeed(){
        EntityManager entityManager = JPAUtil.getCurrentEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("SELECT u from User u", User.class);
        List<User> users = query.getResultList();
        assertNotNull(users);
        assertEquals(3, users.size());
        assertEquals("Bill", users.get(0).getName());
        transaction.commit();
        entityManager.close();
    }
}
