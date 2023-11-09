package edu.hanover.schedulevisualizer.core;

import edu.hanover.schedulevisualizer.Conflicts.TwoCourseConstraint;
import edu.hanover.schedulevisualizer.core.Course;
import edu.hanover.schedulevisualizer.core.HCTimeSlot;
import edu.hanover.schedulevisualizer.core.Section;
import edu.hanover.schedulevisualizer.core.Weekday;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class TwoSectionConflictTest {

    @Test
    public void TwoSectionsAreTheSame() {
        Section section1 = new Section("CS", "321", "Software Development Practicum", new HCTimeSlot(Weekday.MWF(), 1));
        Section section2 = new Section("CS", "321", "Software Development Practicum", new HCTimeSlot(Weekday.MWF(), 1));
        TwoCourseConstraint twocourse = new TwoCourseConstraint(section1, section2);
        assertTrue(twocourse.twoConflictingCourses(section1, section2));
    }

    @Test
    public void TwoSectionsAreDifferent() {
        Section section1 = new Section("CS", "321", "Software Development Practicum", new HCTimeSlot(Weekday.MWF(), 1));
        Section section2 = new Section("CS", "220", "Fundamentals Of CS", new HCTimeSlot(Weekday.MWF(), 1));
        TwoCourseConstraint twocourse = new TwoCourseConstraint(section1, section2);
        assertFalse(twocourse.twoConflictingCourses(section1, section2));
    }
}
