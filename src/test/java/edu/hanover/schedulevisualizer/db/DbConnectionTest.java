package edu.hanover.schedulevisualizer.db;

import edu.hanover.schedulevisualizer.core.entity.*;
import edu.hanover.schedulevisualizer.core.simpleEntity.SimpleCourse;
import edu.hanover.schedulevisualizer.core.simpleEntity.SimpleEntityFactory;
import edu.hanover.schedulevisualizer.core.simpleEntity.SimpleSchedule;
import edu.hanover.schedulevisualizer.core.simpleEntity.SimpleSection;
import jakarta.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class DbConnectionTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("testdb");
    private EntityFactory ef;

    @BeforeEach
    void setUp() {
        ef = new SimpleEntityFactory();
    }

    @Test
    void canConfigureDb() {
        EntityManager em = emf.createEntityManager();
        SimpleCourse c =  (SimpleCourse) ef.makeCourse("CS", "229", "Data Wrangling");
        TimeSlot timeslot = ef.makeHCTimeSlot(Weekday.MWF(), 2);
        SimpleSection s = (SimpleSection) ef.makeSection(c, timeslot);
        SimpleSchedule sch = (SimpleSchedule) ef.makeSchedule();
        em.getTransaction().begin();
        em.persist(c);
        em.persist(sch);
        em.merge(s);
        em.getTransaction().commit();
        // making new manager to make sure things went to the database
        em = emf.createEntityManager();
        Course course = em.find(SimpleCourse.class, c.courseId);
        assertThat(course, notNullValue());
        TypedQuery<Section> q = em.createQuery("SELECT s FROM Section s WHERE s.course.prefix = :prefix", Section.class);
        q.setParameter("prefix", "CS");
        List<Section> resultList = q.getResultList();
        System.out.println(resultList);
        System.out.println(resultList.get(0).getTimeslot());
    }
}
