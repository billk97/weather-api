package com.example.app.intergration;

import com.example.app.domain.Rating;
import com.example.app.domain.Service;
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
public class ServiceTests {

    @BeforeEach
    void setUp() {
        DatabaseUtils databaseUtils = new DatabaseUtils();
        databaseUtils.createInitialState();
    }

    @Test
    void database_should_not_be_null(){
        EntityManager entityManager = JPAUtil.getCurrentEntityManager();
        Query query = entityManager.createQuery("select s from Service s ");
        assertNotNull(query.getFirstResult());
        entityManager.close();
    }
    @Test
    void adding_a_service_to_the_database_should_succeed(){
        EntityManager entityManager = JPAUtil.getCurrentEntityManager();

        Service service = new Service();
        service.setName("name");
        service.setDescription("DESK");
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(service);
        entityManager.flush();
        transaction.commit();
        Service recieved = entityManager.find(Service.class, service.getServiceId());
        assertEquals(service.getName(), recieved.getName());
        assertEquals(service.getServiceId(), recieved.getServiceId());
        assertEquals(service.getDescription(), recieved.getDescription());
        entityManager.close();
    }

    @Test
    void adding_and_deleting_a_service_to_the_database_should_succeed(){
        EntityManager entityManager = JPAUtil.getCurrentEntityManager();

        Service service = new Service();
        service.setName("name");
        service.setDescription("DESK");
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(service);
        entityManager.flush();

        Service recieved = entityManager.find(Service.class, service.getServiceId());
        assertEquals(service.getServiceId(), recieved.getServiceId());
        assertEquals(service.getDescription(), recieved.getDescription());
        entityManager.remove(service);
        entityManager.flush();
        Service service1 = entityManager.find(Service.class, service.getServiceId());
        assertNull(service1);
        transaction.commit();
        entityManager.close();
    }

    @Test
    void finding_a_service_should_succeed(){
        EntityManager entityManager = JPAUtil.getCurrentEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("SELECT s from Service s", Service.class);
        List<Service> services = query.getResultList();
        assertNotNull(services);
        assertEquals(3, services.size());
        assertEquals("meteo", services.get(0).getName());
        transaction.commit();
        entityManager.close();
    }

}
