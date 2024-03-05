package edu.hanover.schedulevisualizer.core;

import edu.hanover.schedulevisualizer.conflict.TwoCourseConflict;
import edu.hanover.schedulevisualizer.conflict.TwoCourseConstraint;
import edu.hanover.schedulevisualizer.core.entity.Section;
import edu.hanover.schedulevisualizer.core.entity.Weekday;
import edu.hanover.schedulevisualizer.core.simpleEntity.SimpleSection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TwoSectionConflictTest extends ContextAwareTest {

    @Test
    public void TwoSectionsAreTheSame() {
        Section section1 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        SimpleSection section2 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        TwoCourseConstraint twocourse = new TwoCourseConstraint(section1, section2);
        assertEquals(new TwoCourseConflict(section1, section2), twocourse.getPairwiseConstraint(section1, section2));
    }

    @Test
    public void TwoSectionsAreDifferent() {
        Section section1 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        SimpleSection section2 = ef.makeSection(ef.makeCourse("CS", "220", "Fundamentals Of CS"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        TwoCourseConstraint twocourse = new TwoCourseConstraint(section1, section2);
        assertNull(twocourse.getPairwiseConstraint(section1, section2));
    }
}
