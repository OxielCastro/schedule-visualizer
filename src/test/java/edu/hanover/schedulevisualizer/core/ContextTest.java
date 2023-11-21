package edu.hanover.schedulevisualizer.core;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;

public class    ContextTest {

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
    public void hasMasterInstructorList() {
        Context context = Context.getInstance();


    }

    // Changing the instructor to a section
    // Methods on the context - basically taking the idea of the instructor and section.
    // It should put the ID
    // Add and remove instructor in to a given instructor

    @Test
    public void isInstructorInMasterList() {
        Context context = Context.getInstance();

        Instructor instructor1 = new Instructor("Barbara", "Wahl", "CABW");
        Instructor instructor2 = new Instructor("Haris", "Skiadas", "CSHS");
        Instructor instructor3 = new Instructor("Donald", "Millar", "CSDM");
        context.addInstructorToMasterList(instructor1);
        context.addInstructorToMasterList(instructor2);
        context.addInstructorToMasterList(instructor3);

        assertFalse(context.isInstructorInMasterList("CSBW"));
        assertTrue(context.isInstructorInMasterList("CSHS"));
        assertTrue(context.isInstructorInMasterList("CSDM"));
    }

    @Test
    public void removeInstructorInMasterList() {
        Context context = Context.getInstance();

        Instructor instructor1 = new Instructor("Barbara", "Wahl", "CABW");
        Instructor instructor2 = new Instructor("Haris", "Skiadas", "CSHS");
        Instructor instructor3 = new Instructor("Donald", "Millar", "CSDM");
        context.addInstructorToMasterList(instructor1);
        context.addInstructorToMasterList(instructor2);
        context.addInstructorToMasterList(instructor3);

        context.removeInstructorToMasterList(instructor1);

        assertFalse(context.isInstructorInMasterList("CSBW"));
        assertTrue(context.isInstructorInMasterList("CSHS"));
        assertTrue(context.isInstructorInMasterList("CSDM"));
    }


    @Test
    public void testSearchInstructorById() {
        Context context = Context.getInstance();

        // Create an instructor and add it to the MasterList
        Instructor instructor = new Instructor("Barbara", "Wahl", "CSBW");
        context.addInstructorToMasterList(instructor);

        // Create sections associated with the instructor
        Section section1 = new Section("CS", "220", "Fundamentals of Computer Science", context.makeHCTimeSlot(Weekday.MWF(), 1));
        Section section2 = new Section("MAT", "121", "Calculus I", context.makeHCTimeSlot(List.of(Weekday.Tuesday), 7));
        section1.addInstructor(instructor);
        section2.addInstructor(instructor);
        context.addSections(section1);
        context.addSections(section2);

        Instructor searchedInstructor = context.getInstructorWithId("CSBW");
        assertEquals(instructor, searchedInstructor);
    }

    @Test
    public void getInstructorScheduleWorks() {
        // Setup
        HCTimeSlot MWF1 = new HCTimeSlot(Weekday.MWF(), 1);
        HCTimeSlot MWF2 = new HCTimeSlot(Weekday.MWF(), 2);
        Section CS220 = new Section("CS", "220", "Fundamentals of Computer Science", MWF1);
        Section MAT121 = new Section("MAT", "121", "Calculus I", MWF2);
        Schedule schedule = new Schedule(List.of(CS220, MAT121)) ;
        Instructor instructor = new Instructor("Barb", "Wahl", "wahlb@hanover.edu");
        CS220.addInstructor(instructor);
        MAT121.addInstructor(instructor);
        Instructor instructor2 = new Instructor("Hallett", "Harrison", "halletth@hanover.edu");
        MAT121.addInstructor(instructor2);
        Context context = new TestableContext();
//        context.setSchedule(schedule);
        List<String> correctList = List.of("CS220 MWF 8:00am - 9:10am", "MAT121 MWF 9:20am - 10:30am");
//        getInstructorSchedule("wahlb@hanover.edu");
    }
}