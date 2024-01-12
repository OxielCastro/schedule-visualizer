package edu.hanover.schedulevisualizer.core;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SectionTest {
    @Test
    public void canCreateCourse(){
        assertCreatedCourseHasCorrectParameters("Data Structures", "223", "CS", new HCTimeSlot(Weekday.MWF(), 1));
        assertCreatedCourseHasCorrectParameters("System Fundamentals", "231", "CS", new HCTimeSlot(Weekday.TR(), 7));
        assertCreatedCourseHasCorrectParameters("Discrete Mathematics", "243", "MAT", new HCTimeSlot(Weekday.MWF(), 3));
    }
    @Test
    private static void assertCreatedCourseHasCorrectParameters(String courseName, String courseNum, String prefix, HCTimeSlot timeslot1) {
        Section section1 = new Section(prefix, courseNum, courseName, timeslot1);
        assertThat(section1.getPrefix(), equalTo(prefix));
        assertThat(section1.getCourseNum(), equalTo(courseNum));
        assertThat(section1.getCourseName(), equalTo(courseName));
        assertThat(section1.getTimeslot(), equalTo(timeslot1));
    }
    @Test
    public void canCombinePrefixAndCourseNumber(){
        Section section1 = new Section("CS", "223", "Data Structures", new HCTimeSlot(Weekday.MWF(), 1));
        assertThat(section1.getCourseCode(), equalTo("CS 223"));
    }

    @Test
    public void canConstructorWorkWithNullTimeSlot() {
        UnassignedTimeSlot nulltimeslot = new UnassignedTimeSlot();
        Section section1 = new Section("CS", "223", "Data Structures", nulltimeslot);
        assertThat(section1.getTimeslot(), equalTo(nulltimeslot));
    }

    @Test
    void canCreateSectionFromCourseObject() {
        Course course1 = new Course("CS", "321", "Software Development Practicum");
        UnassignedTimeSlot nulltimeslot = new UnassignedTimeSlot();
        Section section1 = new Section(course1, nulltimeslot);
        assertThat(section1.getPrefix(), equalTo("CS"));
        assertThat(section1.getCourseNum(), equalTo("321"));
        assertThat(section1.getCourseName(), equalTo("Software Development Practicum"));
    }

    @Test
    public void canAddInstructorList() {
        Instructor barb = new Instructor("Barbara","Wahl", "wahl@hanover.edu");
        Section section1 = new Section("CS", "223", "Data Structures", null);
        section1.addInstructor(barb);
        assertThat(section1.getInstructorList(), equalTo(List.of(barb)));
    }

    // This test only add one instructor per section
    @Test
    public void addInstructorToSection() {
        Section section = new Section("CS", "220", "Fundamentals of Computer Science", null);
        Instructor instructor = new Instructor("Barbara", "Wahl", "Wahl@hanover.edu");
        section.addInstructor(instructor);
        assertThat(section.getInstructorList().size(), equalTo(1));
    }

    // This test is adding more than 1 instructor per section
    @Test
    public void multipleInstructorsInOneSection() {
        Section section = new Section("MAT", "121", "Calculus I", null);
        Instructor instructor1 = new Instructor("Bradley", "Burdick", "burdick@hanover.edu");
        Instructor instructor2 = new Instructor("Carl", "Jagels", "jagels@hanover.edu");
        section.addInstructor(instructor1);
        section.addInstructor(instructor2);

        assertThat(section.getInstructorList().size(), equalTo(2));
    }

    @Test
    void TwoSectionsHaveSameCourse() {
        Course course1 = new Course("CS", "321", "Software Development Practicum");
        Section section1 = new Section(course1, null);
        Section section2 = new Section(course1, null);
        assertTrue(section1.IsSameCourse(section2));
    }

    @Test
    void makeStringWorks() {
        Course course1 = new Course("CS", "321", "Software Development Practicum");
        HCTimeSlot timeSlot = new HCTimeSlot(Weekday.MWF(), 1);
        Section section1 = new Section(course1, timeSlot);
        String string = section1.makeString();
        String correctString = "CS321 MWF 8:00am - 9:10am";
        assertEquals(correctString, string);
    }
}

