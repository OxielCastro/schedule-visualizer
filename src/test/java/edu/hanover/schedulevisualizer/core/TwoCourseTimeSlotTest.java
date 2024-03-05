package edu.hanover.schedulevisualizer.core;

import edu.hanover.schedulevisualizer.conflict.TwoCourseTimeSlotConflict;
import edu.hanover.schedulevisualizer.conflict.TwoCourseTimeSlotConstraint;
import edu.hanover.schedulevisualizer.core.entity.Section;
import edu.hanover.schedulevisualizer.core.entity.Weekday;
import edu.hanover.schedulevisualizer.core.simpleEntity.SimpleSection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TwoCourseTimeSlotTest extends ContextAwareTest {

    @Test
    public void TwoCoursesHaveSameTimeSlot() {
        Section section1 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        SimpleSection section2 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        TwoCourseTimeSlotConstraint twoCourse = new TwoCourseTimeSlotConstraint(section1, section2);
        assertEquals(new TwoCourseTimeSlotConflict(section1, section2), twoCourse.getPairwiseConstraint(section1, section2));
    }

    @Test
    public void TwoCoursesDifferentTimeSlot() {
        Section section1 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        SimpleSection section2 = ef.makeSection(ef.makeCourse("CS", "220", "Fundamentals of Computer Science"), ef.makeHCTimeSlot(Weekday.MWF(), 3));
        TwoCourseTimeSlotConstraint twoCourse = new TwoCourseTimeSlotConstraint(section1, section2);
        assertNull(twoCourse.getPairwiseConstraint(section1, section2));
    }
}
