package edu.hanover.schedulevisualizer.core;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TimeslotIdTest {
    @Test
    public void reassignedTimeSlot() {
        Context context = Context.getInstance();
        TimeSlot initialTimeslot = context.makeHCTimeSlot(Weekday.MWF(), 1);
        TimeSlot newTimeslot = context.makeHCTimeSlot(Weekday.MWF(), 2);
        Section section = new Section("CS", "220", "Fundamentals of Computer Science", initialTimeslot);

        context.addSections(section);
        context.assignTimeslot(section.getCourseId(), newTimeslot.getId());

        assertThat(section.getTimeslot(), equalTo(newTimeslot));
    }
}
