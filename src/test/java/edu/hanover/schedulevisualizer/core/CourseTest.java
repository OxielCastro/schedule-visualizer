package edu.hanover.schedulevisualizer.core;


import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CourseTest {
    @Test
    public void equalsMethodWorks() {
        Course course1 = new Course("CS", "321", "Software Development Practicum");
        Course course2 = new Course("CS", "321", "Software Development Practicum");
        assertTrue(course1.equals(course2));
        Course course3 = new Course("CS", "223", "Data Structures");
        assertFalse(course1.equals(course3));
    }
}
