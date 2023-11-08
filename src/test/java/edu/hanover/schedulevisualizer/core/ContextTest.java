package edu.hanover.schedulevisualizer.core;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static edu.hanover.schedulevisualizer.core.Context.getInstructorSchedule;
import static org.junit.Assert.*;

public class ContextTest {

    @Before
    public void setUp() throws Exception {

    }

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
    public void canGetInstructorIdGivenInstructor() {
        Section CS220;
        Section MAT121;
        HCTimeSlot MWF1 = new HCTimeSlot(Weekday.MWF(), 1);
        HCTimeSlot MWF2 = new HCTimeSlot(Weekday.MWF(), 2);
        Schedule schedule = new Schedule(List.of(
                CS220 = new Section("CS", "220", "Fundamentals of Computer Science", MWF1),
                MAT121 = new Section("MAT", "121", "Calculus I", MWF2)
        )) ;
        Instructor instructor = new Instructor("Barb", "Wahl", "wahlb@hanover.edu");
        CS220.addInstructor(instructor);
        Instructor instructor2 = new Instructor("Hallett", "Harrison", "halletth@hanover.edu");
        MAT121.addInstructor(instructor2);
        assertEquals(instructor.getId(), getInstructorSchedule("wahlb@hanover.edu", schedule));
        assertEquals(instructor2.getId(), getInstructorSchedule("halletth@hanover.edu", schedule));

    }

    @Test
    public void hasMasterInstructorList() {

    }
}