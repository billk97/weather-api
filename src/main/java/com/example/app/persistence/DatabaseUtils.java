package com.example.app.persistence;

import com.example.app.domain.*;
import com.example.app.enums.WeatherCategory;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class DatabaseUtils {

    public void eraseAllData(){
        EntityManager entityManager = JPAUtil.getCurrentEntityManager();
        // why do i need a transaction?
        EntityTransaction tx = entityManager.getTransaction();
        if(tx.isActive()){
            tx.commit();
        }
        tx.begin();
        Query query = entityManager.createQuery("delete from Rating ");
        query.executeUpdate();
        query = entityManager.createQuery("delete from Forecast");
        query.executeUpdate();
        query = entityManager.createQuery("delete from User");
        query.executeUpdate();
        query = entityManager.createQuery("delete from Location");
        query.executeUpdate();
        query = entityManager.createQuery("delete from Service");
        query.executeUpdate();
        tx.commit();
        entityManager.close();
    }

    public void createInitialState() {
        eraseAllData();

        Service meteo = new Service("meteo", "Greek weather station");
        Service google = new Service("google", "Fastests weather station");
        Service emy = new Service("emy", "National service of Greece");

        Location athens = new Location("athens");
        Location thesaloniki = new Location("thesaloniki");
        Location patra = new Location("patra");
        Location hraklio = new Location("hraklio");

        User bill = new User("Bill", "password", athens, new HashSet<Service>(){{add(google);}});
        User sozon = new User("sozon", "dcjn#$%T450-3", athens, new HashSet<Service>(){{add(emy);}});
        User manos = new User("manos", "secret", athens, new HashSet<Service>(){{add(meteo);}});

        // braking the conventions about the length of a line
        Forecast forecastDay23Time6 = new Forecast(LocalTime.of(6,0), LocalDate.of(2020,11,23),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay23Time7 = new Forecast(LocalTime.of(7,0), LocalDate.of(2020,11,23),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay23Time8 = new Forecast(LocalTime.of(8,0), LocalDate.of(2020,11,23),30,100f,2, WeatherCategory.Cloudy,meteo, athens);

        Forecast forecastDay23Time1 = new Forecast(LocalTime.of(1,0), LocalDate.of(2020,11,23),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay23Time2 = new Forecast(LocalTime.of(2,0), LocalDate.of(2020,11,23),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay23Time3 = new Forecast(LocalTime.of(3,0), LocalDate.of(2020,11,23),30,100f,2, WeatherCategory.Cloudy,meteo, athens);

        Forecast forecastDay23Time18 = new Forecast(LocalTime.of(18,0), LocalDate.of(2020,11,23),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay23Time19 = new Forecast(LocalTime.of(19,0), LocalDate.of(2020,11,23),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay23Time20 = new Forecast(LocalTime.of(20,0), LocalDate.of(2020,11,23),30,100f,2, WeatherCategory.Cloudy,meteo, athens);


        Forecast forecastDay24Time6 = new Forecast(LocalTime.of(6,0), LocalDate.of(2020,11,24),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay24Time7 = new Forecast(LocalTime.of(7,0), LocalDate.of(2020,11,24),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay24Time8 = new Forecast(LocalTime.of(8,0), LocalDate.of(2020,11,24),30,100f,2, WeatherCategory.Cloudy,meteo, athens);

        Forecast forecastDay24Time1 = new Forecast(LocalTime.of(1,0), LocalDate.of(2020,11,24),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay24Time2 = new Forecast(LocalTime.of(2,0), LocalDate.of(2020,11,24),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay24Time3 = new Forecast(LocalTime.of(3,0), LocalDate.of(2020,11,24),30,100f,2, WeatherCategory.Cloudy,meteo, athens);

        Forecast forecastDay24Time18 = new Forecast(LocalTime.of(18,0), LocalDate.of(2020,11,24),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay24Time19 = new Forecast(LocalTime.of(19,0), LocalDate.of(2020,11,24),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay24Time20 = new Forecast(LocalTime.of(20,0), LocalDate.of(2020,11,24),30,100f,2, WeatherCategory.Cloudy,meteo, athens);


        Forecast forecastDay25Time6 = new Forecast(LocalTime.of(6,0), LocalDate.of(2020,11,25),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay25Time7 = new Forecast(LocalTime.of(7,0), LocalDate.of(2020,11,25),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay25Time8 = new Forecast(LocalTime.of(8,0), LocalDate.of(2020,11,25),30,100f,2, WeatherCategory.Cloudy,meteo, athens);

        Forecast forecastDay25Time1 = new Forecast(LocalTime.of(1,0), LocalDate.of(2020,11,25),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay25Time2 = new Forecast(LocalTime.of(2,0), LocalDate.of(2020,11,25),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay25Time3 = new Forecast(LocalTime.of(3,0), LocalDate.of(2020,11,25),30,100f,2, WeatherCategory.Cloudy,meteo, athens);

        Forecast forecastDay25Time18 = new Forecast(LocalTime.of(18,0), LocalDate.of(2020,11,25),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay25Time19 = new Forecast(LocalTime.of(19,0), LocalDate.of(2020,11,25),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay25Time20 = new Forecast(LocalTime.of(20,0), LocalDate.of(2020,11,25),30,100f,2, WeatherCategory.Cloudy,meteo, athens);

        Forecast forecastDay26Time6 = new Forecast(LocalTime.of(6,0), LocalDate.of(2020,11,26),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay26Time7 = new Forecast(LocalTime.of(7,0), LocalDate.of(2020,11,26),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay26Time8 = new Forecast(LocalTime.of(8,0), LocalDate.of(2020,11,26),30,100f,2, WeatherCategory.Cloudy,meteo, athens);

        Forecast forecastDay26Time1 = new Forecast(LocalTime.of(1,0), LocalDate.of(2020,11,26),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay26Time2 = new Forecast(LocalTime.of(2,0), LocalDate.of(2020,11,26),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay26Time3 = new Forecast(LocalTime.of(3,0), LocalDate.of(2020,11,26),30,100f,2, WeatherCategory.Cloudy,meteo, athens);

        Forecast forecastDay26Time18 = new Forecast(LocalTime.of(18,0), LocalDate.of(2020,11,26),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay26Time19 = new Forecast(LocalTime.of(19,0), LocalDate.of(2020,11,26),30,100f,2, WeatherCategory.Cloudy,meteo, athens);
        Forecast forecastDay26Time20 = new Forecast(LocalTime.of(20,0), LocalDate.of(2020,11,26),30,100f,2, WeatherCategory.Cloudy,meteo, athens);

        Rating rating = new Rating(bill,forecastDay23Time1, 5);

        EntityManager em = JPAUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(meteo);
        em.persist(google);
        em.persist(emy);

        em.persist(athens);
        em.persist(thesaloniki);
        em.persist(patra);
        em.persist(hraklio);

        em.persist(bill);
        em.persist(sozon);
        em.persist(manos);

        em.persist(forecastDay23Time6);
        em.persist(forecastDay23Time7);
        em.persist(forecastDay23Time8);

        em.persist(forecastDay23Time1);
        em.persist(forecastDay23Time2);
        em.persist(forecastDay23Time3);

        em.persist(forecastDay23Time18);
        em.persist(forecastDay23Time19);
        em.persist(forecastDay23Time20);

        em.persist(forecastDay24Time6);
        em.persist(forecastDay24Time7);
        em.persist(forecastDay24Time8);

        em.persist(forecastDay24Time1);
        em.persist(forecastDay24Time2);
        em.persist(forecastDay24Time3);

        em.persist(forecastDay24Time18);
        em.persist(forecastDay24Time19);
        em.persist(forecastDay24Time20);

        em.persist(forecastDay25Time6);
        em.persist(forecastDay25Time7);
        em.persist(forecastDay25Time8);

        em.persist(forecastDay25Time1);
        em.persist(forecastDay25Time2);
        em.persist(forecastDay25Time3);

        em.persist(forecastDay25Time18);
        em.persist(forecastDay25Time19);
        em.persist(forecastDay25Time20);

        em.persist(forecastDay26Time6);
        em.persist(forecastDay26Time7);
        em.persist(forecastDay26Time8);

        em.persist(forecastDay26Time1);
        em.persist(forecastDay26Time2);
        em.persist(forecastDay26Time3);

        em.persist(forecastDay26Time18);
        em.persist(forecastDay26Time19);
        em.persist(forecastDay26Time20);


        em.persist(rating);

        tx.commit();
        em.close();
    }
}
