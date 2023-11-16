package edu.hanover.schedulevisualizer.core;

import edu.hanover.schedulevisualizer.Conflicts.InstructorFilledTimeSlot;
import edu.hanover.schedulevisualizer.Conflicts.TwoCourseConstraint;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InstructorFilledTimeSlotTest {
    @Test
    public void TimeSlotIsFilled(){
        Section section1 = new Section("CS", "321", "Software Development Practicum", new HCTimeSlot(Weekday.MWF(), 1));
        Section section2 = new Section("CS", "321", "Software Development Practicum", new HCTimeSlot(Weekday.MWF(), 1));
        Instructor instructor1 = new Instructor("John", "Smith", "456357");
        section1.addInstructor(instructor1);
        section2.addInstructor(instructor1);
        InstructorFilledTimeSlot FilledTimeSlot = new InstructorFilledTimeSlot();
        assertTrue(FilledTimeSlot.IsSameInstructor(section1, section2));

    }

    @Test
    public void TimeSlotIsNotFilled() {
        Section section1 = new Section("CS", "321", "Software Development Practicum", new HCTimeSlot(Weekday.MWF(), 1));
        Section section2 = new Section("CS", "321", "Software Development Practicum", new HCTimeSlot(Weekday.MWF(), 1));
        Instructor instructor1 = new Instructor("John", "Smith", "456357");
        Instructor instructor2 = new Instructor("Colin", "Sharp", "456857");
        section1.addInstructor(instructor1);
        section2.addInstructor(instructor2);
        InstructorFilledTimeSlot IsntFilledTimeSlot = new InstructorFilledTimeSlot();
        assertFalse(IsntFilledTimeSlot.IsSameInstructor(section1, section2));
    }

    @Test
    public void neitherCourseHasInstructor() {
        Section section1 = new Section("CS", "321", "Software Development Practicum", new HCTimeSlot(Weekday.MWF(), 1));
        Section section2 = new Section("CS", "321", "Software Development Practicum", new HCTimeSlot(Weekday.MWF(), 1));
        InstructorFilledTimeSlot IsntFilledTimeSlot = new InstructorFilledTimeSlot();
        assertFalse(IsntFilledTimeSlot.IsSameInstructor(section1, section2));
    }
}
