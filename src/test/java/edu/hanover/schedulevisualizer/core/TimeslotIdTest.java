package edu.hanover.schedulevisualizer.core;

import edu.hanover.schedulevisualizer.observable.MyObserver;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Observer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

    @Test
    public void notifyObservers() {
        MyObserver<List<Section>> mockObserver = mock(MyObserver.class);
        Context context = Context.getInstance();
        context.addObserver(mockObserver);

        TimeSlot newTimeSlot = context.makeHCTimeSlot(Weekday.MWF(), 3);
        Section section = new Section("ABC", "325", "Web Application Develop", newTimeSlot);
        context.addSections(section);
        TimeSlot updatedTimeSlot = context.makeHCTimeSlot(Weekday.MWF(), 4);
        context.assignTimeslot(section.getCourseId(), updatedTimeSlot.getId());

        verify(mockObserver).update(any());
    }

}


