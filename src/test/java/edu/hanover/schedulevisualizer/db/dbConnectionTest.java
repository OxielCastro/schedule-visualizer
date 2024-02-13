package edu.hanover.schedulevisualizer.db;

import edu.hanover.schedulevisualizer.core.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;

public class dbConnectionTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("testdb");

    @Test
    void canConfigureDb() {
        EntityManager em = emf.createEntityManager();
        Course c = new Course("cs","229", "Data Wrangling");
        em.persist(c);
    }

}
