package edu.hanover.schedulevisualizer.core;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ContextTest {
    @Test
    public void canRetrieveCreatedTimeslotFromId() {
        Context context = Context.getInstance();
        TimeSlot initialTimeslot = context.makeHCTimeSlot(Weekday.MWF(), 1);
        TimeSlot retrievedTimeSlot = context.getTimeslotWithId(initialTimeslot.getId());
        assertEquals(initialTimeslot, retrievedTimeSlot);
        initialTimeslot = context.makeUnassignedTimeslot();
        retrievedTimeSlot = context.getTimeslotWithId(initialTimeslot.getId());
        assertEquals(initialTimeslot, retrievedTimeSlot);
    }

    @Test
    public void hasMasterInstructorList() {

    }
}