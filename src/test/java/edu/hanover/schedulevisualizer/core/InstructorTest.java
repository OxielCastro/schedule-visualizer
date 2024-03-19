package edu.hanover.schedulevisualizer.core;

import edu.hanover.schedulevisualizer.core.entity.Instructor;
import edu.hanover.schedulevisualizer.core.entity.Section;
import edu.hanover.schedulevisualizer.core.entity.Weekday;
import edu.hanover.schedulevisualizer.core.simpleEntity.SimpleInstructor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class InstructorTest extends ContextAwareTest {
    @Test
    public void instructorsHaveFullName() {
        Instructor instructor1 = ef.makeInstructor("Bradley", "Burdick", "burdick@hanover.edu");
        Instructor instructor2 = ef.makeInstructor("Bradley","Burdick", "burdick@hanover.edu");
        assertThat(instructor2.getFullName(), equalTo(instructor1.getFullName()));
    }

    @Test
    public void canAssignInstructorToSection() {
        Instructor instructor1 = ef.makeInstructor("Bradley","Burdick", "burdick@hanover.edu");
        Section CalcI = ef.makeSection(ef.makeCourse("MAT", "121", "Calculus I"), null);
        CalcI.addInstructor(instructor1);
        assertThat(CalcI.getInstructorList(), equalTo(List.of(instructor1)));
    }

    @Test
    public void checkForInstructorTimeSlotOverlapTrue() {
        Instructor instructor = ef.makeInstructor("Barbara", "Wahl", "wahl@hanover.edu");
        Section section1 = ef.makeSection(ef.makeCourse("CS", "220", "Fundamentals of Computer Science"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        Section section2 = ef.makeSection(ef.makeCourse("MAT", "121", "Calculus I"), ef.makeHCTimeSlot(Weekday.MWF(), 1));

        section1.addInstructor(instructor);
        section2.addInstructor(instructor);

        assertTrue(instructor.hasTimeSlotOverlap(section1, section2));
    }

    @Test
    public void checkForInstructorTimeSlotOverlapFalse() {
        Instructor instructor = ef.makeInstructor("Barbara", "Wahl", "wahl@hanover.edu");
        Section section1 = ef.makeSection(ef.makeCourse("CS", "220", "Fundamentals of Computer Science"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        Section section2 = ef.makeSection(ef.makeCourse("MAT", "121", "Calculus I"), ef.makeHCTimeSlot(List.of(Weekday.Tuesday), 7));

        section1.addInstructor(instructor);
        section2.addInstructor(instructor);

        assertFalse(instructor.hasTimeSlotOverlap(section1, section2));
    }

    @Test
    public void canCompareInstructorsLast() {
        Instructor instructor1 = ef.makeInstructor("Barbara", "Wahl", "wahl@hanover.edu");
        Instructor instructor2 = ef.makeInstructor("Donald", "Millar", "millar@hanover.edu");
        assertTrue(instructor1.compareTo(instructor2) > 0);
    }

    @Test
    public void canCompareInstructorsFirst() {
        Instructor instructor1 = ef.makeInstructor("Barbara", "Wahl", "wahl@hanover.edu");
        Instructor instructor2 = ef.makeInstructor("Barbara", "Waal", "wall@hanover.edu");

        assertFalse(instructor1.compareTo(instructor2) < 0);
    }

    @Test
    public void sortInstrcutorsName() {
        Instructor[] instructors = {
                ef.makeInstructor("Millie", "Zurick", "zurick@hanover.edu"),
                ef.makeInstructor("Barbara", "Wahl", "wahl@hanover.edu"),
                ef.makeInstructor("Donald", "Millar", "millar@hanover.edu"),
                ef.makeInstructor("Barbara", "Waal", "wall@hanover.edu"),
                ef.makeInstructor("Haris", "Skiadas", "skiadas@hanover.edu")
        };

        Instructor.sortInstructors(instructors);

        assertEquals("Donald Millar", instructors[0].getFullName());
        assertEquals("Haris Skiadas", instructors[1].getFullName());
        assertEquals("Barbara Waal", instructors[2].getFullName());
        assertEquals("Barbara Wahl", instructors[3].getFullName());
        assertEquals("Millie Zurick", instructors[4].getFullName());
    }
}
