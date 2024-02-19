package edu.hanover.schedulevisualizer.core;

import edu.hanover.schedulevisualizer.conflict.*;
import edu.hanover.schedulevisualizer.core.entity.Section;
import edu.hanover.schedulevisualizer.core.entity.Weekday;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenerateConflictsTest extends ContextAwareTest {
    @Test
    public void GenerateConflictsTestForTwoCourseConflictWithThreeCourses() {
        Section section1 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        Section section2 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        Section section3 = ef.makeSection(ef.makeCourse("CS", "220", "Fundamentals Of CS"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        List<Section> sectionlist = new ArrayList<>();
        sectionlist.add(section1);
        sectionlist.add(section2);
        sectionlist.add(section3);
        Constraint generalconstraint = new TwoCourseConstraint(section1, section2);
        List<Optional<Conflict>> conflictlist = new ArrayList<>();
        conflictlist.add(Optional.of(new TwoCourseConflict(section1, section2)));
        conflictlist.add(Optional.empty());
        conflictlist.add(Optional.empty());
        assertEquals(generalconstraint.generateConflicts(sectionlist), conflictlist);
    }
}

