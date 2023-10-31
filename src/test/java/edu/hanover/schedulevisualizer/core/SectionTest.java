package edu.hanover.schedulevisualizer.core;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
public class SectionTest {
    @Test
    public void canCreateCourse(){
        assertCreatedCourseHasCorrectParameters("Data Structures", "223", "CS", new HCTimeSlot(Weekday.MWF(), 1));
        assertCreatedCourseHasCorrectParameters("System Fundamentals", "231", "CS", new HCTimeSlot(Weekday.TR(), 7));
        assertCreatedCourseHasCorrectParameters("Discrete Mathematics", "243", "MAT", new HCTimeSlot(Weekday.MWF(), 3));
    }

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
}
