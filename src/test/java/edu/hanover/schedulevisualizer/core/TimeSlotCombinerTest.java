package edu.hanover.schedulevisualizer.core;

import edu.hanover.schedulevisualizer.core.entity.TimeSlot;
import edu.hanover.schedulevisualizer.core.entity.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeSlotCombinerTest extends ContextAwareTest {
    @Test
    void combineWithFirstAssignedSecondUnassignedWorks() {
        TimeSlot FirstAssigned = ef.makeHCTimeSlot(Weekday.MWF(), 1);
        TimeSlot SecondUnassigned = ef.makeUnassignedTimeslot();
        TimeSlotHandler handler = new TimeSlotHandler(ef);
        TimeSlot result = handler.combine(FirstAssigned, SecondUnassigned);
        assertEquals(ef.makeUnassignedTimeslot(), result);
    }

    @Test
    void combinedWithBothUnassigned() {
        TimeSlotHandler handler = new TimeSlotHandler(ef);
        TimeSlot result = handler.combinedWithBothUnassigned();
        assertEquals(ef.makeUnassignedTimeslot(), result);
    }

    @Test
    void combineWithBothAssignedWorksforMW() {
        TimeSlot FirstAssigned = ef.makeHCTimeSlot(Weekday.MW(), 1);
        TimeSlot SecondAssigned = ef.makeHCTimeSlot(Weekday.MWF(), 2);
        TimeSlotHandler handler = new TimeSlotHandler(ef);
        TimeSlot result = handler.combine(FirstAssigned, SecondAssigned);
        assertEquals(ef.makeHCTimeSlot(Weekday.MW(), 2), result);
    }
    @Test
    void combineWithBothAssignedWorksforW() {
        TimeSlot FirstAssigned = ef.makeHCTimeSlot(Weekday.W(), 1);
        TimeSlot SecondAssigned = ef.makeHCTimeSlot(Weekday.MWF(), 2);
        TimeSlotHandler handler = new TimeSlotHandler(ef);
        TimeSlot result = handler.combine(FirstAssigned, SecondAssigned);
        assertEquals(ef.makeHCTimeSlot(Weekday.W(), 2), result);
    }

    @Test
    void combineWithBothAssignedWorksforTR() {
        TimeSlot FirstAssigned = ef.makeHCTimeSlot(Weekday.MWF(), 5);
        TimeSlot SecondAssigned = ef.makeHCTimeSlot(Weekday.TR(), 4);
        TimeSlotHandler handler = new TimeSlotHandler(ef);
        TimeSlot result = handler.combine(FirstAssigned, SecondAssigned);
        assertEquals(ef.makeHCTimeSlot(Weekday.TR(), 4), result);
    }

    @Test
    void combineWithBothAssignedWorksforR() {
        TimeSlot FirstAssigned = ef.makeHCTimeSlot(Weekday.R(), 5);
        TimeSlot SecondAssigned = ef.makeHCTimeSlot(Weekday.TR(), 4);
        TimeSlotHandler handler = new TimeSlotHandler(ef);
        TimeSlot result = handler.combine(FirstAssigned, SecondAssigned);
        assertEquals(ef.makeHCTimeSlot(Weekday.R(), 4), result);
    }

    @Test
    void combineWithFirstUnassignedSecondAssignedForMWF() {
        TimeSlot FirstUnassigned = ef.makeUnassignedTimeslot();
        TimeSlot SecondAssigned = ef.makeHCTimeSlot(Weekday.MWF(), 4);
        TimeSlotHandler handler = new TimeSlotHandler(ef);
        TimeSlot result = handler.combine(FirstUnassigned, SecondAssigned);
        assertEquals(ef.makeHCTimeSlot(Weekday.MWF(), 4), result);
    }

    @Test
    void combineWithFirstUnassignedSecondAssignedForTR() {
        TimeSlot FirstUnassigned = ef.makeUnassignedTimeslot();
        TimeSlot SecondAssigned = ef.makeHCTimeSlot(Weekday.TR(), 3);
        TimeSlotHandler handler = new TimeSlotHandler(ef);
        TimeSlot result = handler.combine(FirstUnassigned, SecondAssigned);
        assertEquals(ef.makeHCTimeSlot(Weekday.TR(), 3), result);
    }

}
