package edu.hanover.schedulevisualizer.core;

import org.junit.Before;
import org.junit.Test;

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
}