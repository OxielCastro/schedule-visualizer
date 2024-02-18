package edu.hanover.schedulevisualizer.core;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ContextTest extends ContextAwareTest {

    @Test
    public void canRetrieveCreatedTimeslotFromId() {
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

    // Changing the instructor to a section
    // Methods on the context - basically taking the idea of the instructor and section.
    // It should put the ID
    // Add and remove instructor in to a given instructor

    @Test
    public void isInstructorInMasterList() {
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
        // Create an instructor and add it to the MasterList
        Instructor instructor = new Instructor("Barbara", "Wahl", "CSBW");
        context.addInstructorToMasterList(instructor);

        // Create sections associated with the instructor
        Section section1 = new Section(new Course("CS", "220", "Fundamentals of Computer Science"), context.makeHCTimeSlot(Weekday.MWF(), 1));
        Section section2 = new Section(new Course("MAT", "121", "Calculus I"), context.makeHCTimeSlot(List.of(Weekday.Tuesday), 7));
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
        Section CS220 = new Section(new Course("CS", "220", "Fundamentals of Computer Science"), new HCTimeSlot(Weekday.MWF(), 1));
        Section MAT121 = new Section(new Course("MAT", "121", "Calculus I"), new HCTimeSlot(Weekday.MWF(), 2));
        Schedule schedule = new Schedule(List.of(CS220, MAT121)) ;
        Instructor instructor = new Instructor("Barb", "Wahl", "wahlb@hanover.edu");
        CS220.addInstructor(instructor);
        MAT121.addInstructor(instructor);
        Instructor instructor2 = new Instructor("Hallett", "Harrison", "halletth@hanover.edu");
        MAT121.addInstructor(instructor2);
        context.setSchedule(schedule);
        context.addInstructorToMasterList(instructor);
        context.addInstructorToMasterList(instructor2);
        int correctListSize = 2;
        assertEquals(correctListSize, context.getInstructorSchedule("wahlb@hanover.edu").size());
        assertTrue(context.getInstructorSchedule("wahlb@hanover.edu").contains("CS220 MWF 8:00am - 9:10am"));
        assertTrue(context.getInstructorSchedule("wahlb@hanover.edu").contains("MAT121 MWF 9:20am - 10:30am"));
    }

    @Test
    public void getInstructorScheduleThrowsErrorCorrectly() {
        assertThrows(IllegalArgumentException.class, () -> {
            Schedule schedule = new Schedule();
            context.setSchedule(schedule);
            context.getInstructorSchedule("wahlb@hanover.edu");
        });
    }
}