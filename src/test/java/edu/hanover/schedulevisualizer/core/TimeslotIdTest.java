package edu.hanover.schedulevisualizer.core;

import edu.hanover.schedulevisualizer.core.entity.Section;
import edu.hanover.schedulevisualizer.core.entity.TimeSlot;
import edu.hanover.schedulevisualizer.core.entity.Weekday;
import edu.hanover.schedulevisualizer.observable.MyObserver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TimeslotIdTest extends ContextAwareTest {
    @Test
    public void reassignedTimeSlot() {
        // Create Course, Create Timeslot (2), call assign, check course has new time slot
        TimeSlot initialTimeslot = ef.makeHCTimeSlot(Weekday.MWF(), 1);  //originalTimeSlot
        TimeSlot newTimeSlot = ef.makeHCTimeSlot(Weekday.MWF(), 2);
        Section section = ef.makeSection(ef.makeCourse("ABC", "220", "Fundamentals of Computer Science"), initialTimeslot); // Extract method
        context.addSections(section);
        context.assignTimeslot(section.getSectionId(), newTimeSlot.getId());
        Section updatedSection = context.getCourseWithId(section.getSectionId());
        assertThat(updatedSection.getTimeslot(), equalTo(newTimeSlot));
    }





    @Test
    public void assignInvalidTimeslot() {
        // If timeslot invalid, get an error
        assertThrows(RuntimeException.class, () -> {
            context.assignTimeslot(1234, "invalidTimeslotId");
        });
    }

    @Test
    public void assignTimeslotToInvalidCourse() {
        // if-course invalid, get an error
        TimeSlot newTimeSlot = ef.makeHCTimeSlot(Weekday.MWF(), 2);
        assertThrows(RuntimeException.class, () -> {
            context.assignTimeslot(1234, newTimeSlot.getId());
        });
    }


    @Test
    public void notifyObservers() {
        MyObserver<List<Section>> mockObserver = mock(MyObserver.class);
        context.addObserver(mockObserver);

        TimeSlot newTimeSlot = ef.makeHCTimeSlot(Weekday.MWF(), 3);
        Section section = ef.makeSection(ef.makeCourse("ABC", "325", "Web Application Develop"), newTimeSlot);
        context.addSections(section);
        TimeSlot updatedTimeSlot = ef.makeHCTimeSlot(Weekday.MWF(), 4);
        context.assignTimeslot(section.getSectionId(), updatedTimeSlot.getId());

        verify(mockObserver).update(any());
    }

}


