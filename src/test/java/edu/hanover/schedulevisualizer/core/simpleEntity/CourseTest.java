package edu.hanover.schedulevisualizer.core.simpleEntity;


import edu.hanover.schedulevisualizer.core.entity.Course;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseTest {
    @Test
    public void equalsMethodWorks() {
        SimpleCourse course1 = new SimpleCourse("CS", "321", "Software Development Practicum");
        SimpleCourse course2 = new SimpleCourse("CS", "321", "Software Development Practicum");
        assertTrue(course1.equals(course2));
        SimpleCourse course3 = new SimpleCourse("CS", "223", "Data Structures");
        assertFalse(course1.equals(course3));
    }
}
