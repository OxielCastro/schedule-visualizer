package edu.hanover.schedulevisualizer.core;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InstructorTest {
    @Test
    public void instructorsHaveFullName() {
        Instructor instructor1 = new Instructor("Bradley","Burdick", "burdick@hanover.edu");
        Instructor instructor2 = new Instructor("Bradley","Burdick", "burdick@hanover.edu");
        assertThat(instructor2.getFullName(), equalTo(instructor1.getFullName()));
    }

    @Test
    public void checkForInstructorTimeSlotOverlapTrue() {
        // Create an instructor with two sections at the same time
        Instructor instructor = new Instructor("Barbara", "Wahl", "wahl@hanover.edu");
        Section section1 = new Section("CS", "220", "Fundamentals of Computer Science", Context.getInstance().makeHCTimeSlot(Weekday.MWF(), 1));
        Section section2 = new Section("MAT", "121", "Calculus I", Context.getInstance().makeHCTimeSlot(Weekday.MWF(), 1));

        instructor.assignSection(section1);
        instructor.assignSection(section2);

        // Check for time slot overlap (instructor has overlapping classes)
        assertTrue(instructor.hasTimeSlotOverlap());
    }

    @Test
    public void checkForInstructorTimeSlotOverlapFalse() {
        // Create an instructor with two sections at a different time
        Instructor instructor = new Instructor("Barbara", "Wahl", "wahl@hanover.edu");
        Section section1 = new Section("CS", "220", "Fundamentals of Computer Science", Context.getInstance().makeHCTimeSlot(Weekday.MWF(), 1));
        Section section2 = new Section("MAT", "121", "Calculus I", Context.getInstance().makeHCTimeSlot(List.of(Weekday.Tuesday), 7));

        instructor.assignSection(section1);
        instructor.assignSection(section2);

        // Check for time slot overlap (instructor has no overlapping classes)
        assertFalse(instructor.hasTimeSlotOverlap());
    }
}
