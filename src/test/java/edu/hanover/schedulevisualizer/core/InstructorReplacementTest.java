package edu.hanover.schedulevisualizer.core;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InstructorReplacementTest extends ContextAwareTest {

    @Test
    public void testChangeInstructor() {
        // Create the current instructor
        Instructor currentInstructor = ef.makeInstructor("Barbara", "Smith", "barbara@hanover.edu");

        // Create the new instructor
        Instructor newInstructor = ef.makeInstructor("Bradley", "Burdick", "burdick@hanover.edu");

        // Create a section assigned to the current instructor
        Section section = ef.makeSection(ef.makeCourse("MAT", "121", "Calculus I"), ef.makeHCTimeSlot(List.of(Weekday.Tuesday), 7));

        // Assign the section to the current instructor
        section.addInstructor(currentInstructor);

        // Reassign the section to the new instructor using the changeInstructor method
        section.changeInstructor(currentInstructor, newInstructor);

        // Verify that the section is now assigned to the new instructor
        assertTrue(section.getInstructorList().contains(newInstructor));

        // Verify that the section is no longer assigned to the current instructor
        assertFalse(section.getInstructorList().contains(currentInstructor));
    }
}

