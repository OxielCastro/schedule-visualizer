package edu.hanover.schedulevisualizer.core;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class InstructorReplacementTest {

    @Test
    public void testReplaceInstructor() {
        // Create the current instructor
        Instructor currentInstructor = new Instructor("Barbara", "Wahl", "wahl@hanover.edu");

        // Create the new instructor
        Instructor newInstructor = new Instructor("Bradley", "Burdick", "burdick@hanover.edu");

        // Create a section assigned to the current instructor
        Section section = new Section("MAT", "121", "Calculus I", Context.getInstance().makeHCTimeSlot(List.of(Weekday.Tuesday), 7));

        // Assign the section to the current instructor
        currentInstructor.assignSection(section);

        // Reassign the section to the new instructor
        currentInstructor.reassignSection(section, newInstructor);

        // Verify that the section is now assigned to the new instructor
        assertTrue(section.getInstructorList().contains(newInstructor));

        // Verify that the section is no longer assigned to the current instructor
        assertFalse(section.getInstructorList().contains(currentInstructor));
    }
}

