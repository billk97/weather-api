package com.example.app.repository;

import com.example.app.dao.ServiceDAO;
import com.example.app.domain.Service;
import com.example.app.persistence.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class ServiceRepository implements ServiceDAO {

    private EntityManager entityManager = JPAUtil.getCurrentEntityManager();

    @Override
    public List<Service> findAll() {
        Query query = entityManager.createQuery("Select s from Service s");
        return query.getResultList();
    }

    @Override
    public void save(Service service) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(service);
        transaction.commit();
    }

    @Override
    public void delete(Service service) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(service);
        transaction.commit();
    }

    @Override
    public Optional<Service> findById(int serviceId) {
        return Optional.ofNullable(entityManager.find(Service.class, serviceId));
    }

    @Override
    public Optional<Service> findByName(String serviceName) {
        Query query = entityManager.createQuery("SELECT s from Service s where s.name = :serviceName", Service.class);
        return query.setParameter("serviceName", serviceName).getResultList().stream().findFirst();
    }
}
