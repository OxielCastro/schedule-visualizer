package edu.hanover.schedulevisualizer.core;

import edu.hanover.schedulevisualizer.conflict.*;
import edu.hanover.schedulevisualizer.core.entity.Section;
import edu.hanover.schedulevisualizer.core.entity.Weekday;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenerateConflictsTest extends ContextAwareTest {
    @Test
    public void getConflictsTestForTwoCourseConflictWithThreeCourses() {
        Section section1 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        Section section2 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        Section section3 = ef.makeSection(ef.makeCourse("CS", "220", "Fundamentals Of CS"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        List<Section> sectionlist = new ArrayList<>();
        sectionlist.add(section1);
        sectionlist.add(section2);
        sectionlist.add(section3);
        List<Conflict> conflictlist = new ArrayList<>();
        Constraint demo = new TwoSectionConstraint(section1, section2);
        conflictlist.add(new TwoSectionConflict(section1, section2));
        assertEquals(conflictlist, demo.getConflicts(sectionlist));
    }

    @Test
    public void getConflictsTestForTwoCourseTimeSlotWithThreeCourses() {
        Section section1 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        Section section2 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 2));
        Section section3 = ef.makeSection(ef.makeCourse("CS", "220", "Fundamentals Of CS"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        List<Section> sectionlist = new ArrayList<>();
        sectionlist.add(section1);
        sectionlist.add(section2);
        sectionlist.add(section3);
        List<Conflict> conflictlist = new ArrayList<>();
        Constraint demo = new TwoCourseTimeSlotConstraint(section1, section2);
        conflictlist.add(new TwoCourseTimeSlotConflict(section1, section3));
        assertEquals(conflictlist, demo.getConflicts(sectionlist));
    }
}

