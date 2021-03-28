package com.luxoft.springdb.lab2.dao.jpa;

import com.luxoft.springdb.lab2.dao.CountryDao;
import com.luxoft.springdb.lab2.model.Country;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.Collections;
import java.util.List;

@Repository
public class CountryJpaDaoImpl implements CountryDao {

    @PersistenceUnit
    protected EntityManagerFactory entityManagerFactory;

    @Override
    public void save(Country country) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(country);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Country> getAllCountries() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            return entityManager.createQuery("from Country e", Country.class).getResultList();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return Collections.emptyList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Country getCountryByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            return entityManager.createQuery("SELECT e FROM Country e WHERE e.name LIKE :name", Country.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        } finally {
            entityManager.close();
        }
    }
}
