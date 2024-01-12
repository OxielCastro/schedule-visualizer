package edu.hanover.schedulevisualizer.core;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class InstructorTest {
    @Test
    public void instructorsHaveFullName() {
        Instructor instructor1 = new Instructor("Bradley","Burdick", "burdick@hanover.edu");
        Instructor instructor2 = new Instructor("Bradley","Burdick", "burdick@hanover.edu");
        assertThat(instructor2.getFullName(), equalTo(instructor1.getFullName()));
    }

    @Test
    public void canAssignInstructorToSection() {
        Instructor instructor1 = new Instructor("Bradley","Burdick", "burdick@hanover.edu");
        Section CalcI = new Section("MAT", "121", "Calculus I", null);
        CalcI.addInstructor(instructor1);
        assertThat(CalcI.getInstructorList(), equalTo(List.of(instructor1)));
    }

    @Test
    public void checkForInstructorTimeSlotOverlapTrue() {
        Instructor instructor = new Instructor("Barbara", "Wahl", "wahl@hanover.edu");
        Section section1 = new Section("CS", "220", "Fundamentals of Computer Science", Context.getInstance().makeHCTimeSlot(Weekday.MWF(), 1));
        Section section2 = new Section("MAT", "121", "Calculus I", Context.getInstance().makeHCTimeSlot(Weekday.MWF(), 1));

        section1.addInstructor(instructor);
        section2.addInstructor(instructor);

        assertTrue(instructor.hasTimeSlotOverlap(section1, section2));
    }

    @Test
    public void checkForInstructorTimeSlotOverlapFalse() {
        Instructor instructor = new Instructor("Barbara", "Wahl", "wahl@hanover.edu");
        Section section1 = new Section("CS", "220", "Fundamentals of Computer Science", Context.getInstance().makeHCTimeSlot(Weekday.MWF(), 1));
        Section section2 = new Section("MAT", "121", "Calculus I", Context.getInstance().makeHCTimeSlot(List.of(Weekday.Tuesday), 7));

        section1.addInstructor(instructor);
        section2.addInstructor(instructor);

        assertFalse(instructor.hasTimeSlotOverlap(section1, section2));
    }

    @Test
    public void canCompareInstructorsLast() {
        Instructor instructor1 = new Instructor("Barbara", "Wahl", "wahl@hanover.edu");
        Instructor instructor2 = new Instructor("Donald", "Millar", "millar@hanover.edu");
        assertTrue(instructor1.compareTo(instructor2) > 0);
    }

    @Test
    public void canCompareInstructorsFirst() {
        Instructor instructor1 = new Instructor("Barbara", "Wahl", "wahl@hanover.edu");
        Instructor instructor2 = new Instructor("Barbara", "Waal", "wall@hanover.edu");

        assertFalse(instructor1.compareTo(instructor2) < 0);
    }

    @Test
    public void sortInstrcutorsName() {
        Instructor[] instructors = {
                new Instructor("Millie", "Zurick", "zurick@hanover.edu"),
                new Instructor("Barbara", "Wahl", "wahl@hanover.edu"),
                new Instructor("Donald", "Millar", "millar@hanover.edu"),
                new Instructor("Barbara", "Waal", "wall@hanover.edu"),
                new Instructor("Haris", "Skiadas", "skiadas@hanover.edu")
        };

        Instructor.sortInstructors(instructors);

        assertEquals("Donald Millar", instructors[0].getFullName());
        assertEquals("Haris Skiadas", instructors[1].getFullName());
        assertEquals("Barbara Waal", instructors[2].getFullName());
        assertEquals("Barbara Wahl", instructors[3].getFullName());
        assertEquals("Millie Zurick", instructors[4].getFullName());
    }
}
