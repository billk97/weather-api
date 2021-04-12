package com.example.app.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * The type Jpa util.
 */
public class JPAUtil {

    private static EntityManagerFactory emf;
    private static final ThreadLocal<EntityManager> currentEntityManager = new ThreadLocal<EntityManager>();


    /**
     * Gets entity manager factory.
     *
     * @return the entity manager factory
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("app");
        }
        return emf;
    }


    /**
     * Gets current entity manager.
     *
     * @return the current entity manager
     */
    public static EntityManager getCurrentEntityManager() {
        EntityManager em = currentEntityManager.get();
        if (em  == null || !em.isOpen()) {
            em = getEntityManagerFactory().createEntityManager();
            currentEntityManager.set(em);
        }
        return em;
    }

    /**
     * Create entity manager entity manager.
     *
     * @return the entity manager
     */
    public static EntityManager createEntityManager() {

        return getEntityManagerFactory().createEntityManager();
    }

    /**
     * Transactional.
     *
     * @param runnable the runnable
     */
    public static void transactional(Runnable runnable){

        EntityManager em = getCurrentEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        runnable.run();

        tx.rollback();
        em.close();

    }

}