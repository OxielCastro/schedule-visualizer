package edu.hanover.schedulevisualizer.core;

import edu.hanover.schedulevisualizer.conflict.InstructorConflict;
import edu.hanover.schedulevisualizer.conflict.InstructorConstraint;
import edu.hanover.schedulevisualizer.core.entity.Instructor;
import edu.hanover.schedulevisualizer.core.entity.Section;
import edu.hanover.schedulevisualizer.core.entity.Weekday;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class InstructorConstraintTest extends ContextAwareTest {
    @Test
    public void neitherCourseHasInstructor() {
        Section section1 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        Section section2 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        InstructorConstraint instructorConstraint = new InstructorConstraint(section1, section2);
        assertNull(instructorConstraint.getPairwiseConstraint(section1, section2));
    }

    @Test
    public void twoSectionsHaveSameInstructors() {
        Section section1 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        Section section2 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        Instructor instructor1 = ef.makeInstructor("John", "Smith", "456357");
        section1.addInstructor(instructor1);
        section2.addInstructor(instructor1);
        InstructorConstraint instructorconstraint = new InstructorConstraint(section1, section2);
        assertEquals(new InstructorConflict(section1, section2), instructorconstraint.getPairwiseConstraint(section1, section2));
    }

    @Test
    public void twoSectionsHaveDifferentInstructors() {
        Section section1 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        Section section2 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        Instructor instructor1 = ef.makeInstructor("John", "Smith", "456357");
        Instructor instructor2 = ef.makeInstructor("Colin", "Sharp", "456857");
        section1.addInstructor(instructor1);
        section2.addInstructor(instructor2);
        InstructorConstraint instructorconstraint = new InstructorConstraint(section1, section2);
        assertNull(instructorconstraint.getPairwiseConstraint(section1, section2));
    }
}
