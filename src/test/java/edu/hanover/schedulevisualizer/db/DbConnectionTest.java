package edu.hanover.schedulevisualizer.db;

import edu.hanover.schedulevisualizer.core.*;
import jakarta.persistence.*;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class DbConnectionTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("testdb");

    @Disabled
    @Test
    void canConfigureDb() {
        EntityManager em = emf.createEntityManager();

        Course c = new Course("CS", "229", "Data Wrangling");
        TimeSlot timeslot = new HCTimeSlot(Weekday.MWF(), 2);
        Section s = new Section(c, timeslot);
        em.getTransaction().begin();
        em.persist(c);
        em.persist(s);
        em.getTransaction().commit();
        // making new manager to make sure things went to the database
        em = emf.createEntityManager();
        Course course = em.find(Course.class, c.courseId);
        assertThat(course, notNullValue());
        TypedQuery<Section> q = em.createQuery("SELECT s FROM Section s WHERE s.course.prefix = :prefix", Section.class);
        q.setParameter("prefix", "CS");
        List<Section> resultList = q.getResultList();
        System.out.println(resultList);
        System.out.println(resultList.get(0).getTimeslot());
    }
}
