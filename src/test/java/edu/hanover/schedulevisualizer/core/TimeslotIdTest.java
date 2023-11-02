package edu.hanover.schedulevisualizer.core;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;

public class TimeslotIdTest {
    @Test
    public void reassignedTimeSlot() {
        // Create Course, Create Timeslot (2), call assign, check course has new time slot
        Context context = Context.getInstance();
        TimeSlot initialTimeslot = context.makeHCTimeSlot(Weekday.MWF(), 1);
        TimeSlot newTimeSlot = context.makeHCTimeSlot(Weekday.MWF(), 2);
        Section section = new Section("ABC", "220", "Fundamentals of Computer Science", initialTimeslot);
        context.addSections(section);
        context.assignTimeslot(section.getCourseId(), newTimeSlot.getId());
        Section updatedSection = context.getCourseWithId(section.getCourseId());
        assertThat(updatedSection.getTimeslot(), equalTo(newTimeSlot));
    }

    @Test
    public void assignInvalidTimeslot() {
        // If timeslot invalid, get an error
        Context context = Context.getInstance();
        assertThrows(RuntimeException.class, () -> {
            context.assignTimeslot(1234, "invalidTimeslotId");
        });
    }

    @Test
    public void assignTimeslotToInvalidCourse() {
        // if-course invalid, get an error
        Context context = Context.getInstance();
        TimeSlot newTimeSlot = context.makeHCTimeSlot(Weekday.MWF(), 2);
        assertThrows(RuntimeException.class, () -> {
            context.assignTimeslot(1234, newTimeSlot.getId());
        });
    }
}
