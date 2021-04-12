package com.example.app.intergration;

import com.example.app.domain.Forecast;
import com.example.app.domain.Location;
import com.example.app.domain.Service;
import com.example.app.persistence.DatabaseUtils;
import com.example.app.persistence.JPAUtil;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ForecastTests {
    @BeforeEach
    void setUp() {
        DatabaseUtils databaseUtils = new DatabaseUtils();
        databaseUtils.createInitialState();
    }

    @Test
    void database_should_not_be_null(){
        EntityManager entityManager = JPAUtil.getCurrentEntityManager();
        Query query = entityManager.createQuery("select f from Forecast f ");
        assertNotNull(query.getFirstResult());
        entityManager.close();
    }

    @Test
    void addForecast_should_succeed(){
        EntityManager entityManager = JPAUtil.getCurrentEntityManager();

        Forecast forecast = new Forecast();
        forecast.setWind(1);
        forecast.setHumidity(100f);
        forecast.setTemperature(3);
        forecast.setLocalTime(LocalTime.of(12,00));

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("select service from Service service");

        Service service = (Service) query.getResultList().get(0);
        forecast.setService(service);
        Location location = new Location("kastoria");
        entityManager.persist(location);
        forecast.setLocation(location);
        entityManager.persist(forecast);
        transaction.commit();

        query = entityManager.createQuery("select forecast from Forecast forecast where temperature = 3");
        System.out.println();
        assertEquals(forecast, query.getSingleResult());
        entityManager.close();
    }

    @Test
    void delete_forecast_should_be_successful(){
        EntityManager entityManager = JPAUtil.getCurrentEntityManager();
        Query query = entityManager.createQuery("select f from Forecast f", Forecast.class);
        List<Forecast> forecastList = query.getResultList();
        int numberOfForecastBeforeDeletion = forecastList.size();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(forecastList.get(1));
        transaction.commit();
        entityManager.clear();

        query = entityManager.createQuery("select f from Forecast f", Forecast.class);
        assertFalse(query.getResultList().contains(forecastList.get(1)));
        assertTrue(numberOfForecastBeforeDeletion > query.getResultList().size());

    }


    @AfterEach
    void tearDown() {
        DatabaseUtils databaseUtils = new DatabaseUtils();
        databaseUtils.eraseAllData();
    }
}