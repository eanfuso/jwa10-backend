package edu.it.components;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ConectorJPA {
    private EntityManager em;

    public ConectorJPA() {
        var entityManagerFactory =
                Persistence.createEntityManagerFactory("mariadb");

        em = entityManagerFactory.createEntityManager();
    }
    public EntityManager getEntityManager() {
        return em;
    }
}
