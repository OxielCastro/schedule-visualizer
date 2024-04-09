package edu.hanover.schedulevisualizer.core.simpleEntity;

import edu.hanover.schedulevisualizer.conflict.*;
import edu.hanover.schedulevisualizer.core.ContextAwareTest;
import edu.hanover.schedulevisualizer.core.entity.Instructor;
import edu.hanover.schedulevisualizer.core.entity.Section;
import edu.hanover.schedulevisualizer.core.entity.Weekday;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupConstraintTest extends ContextAwareTest {

    @Test
    void getConflictsTestForGroupConstraintWithOneConflict() {
        Section section1 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        Section section2 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        List<Section> sectionList = new ArrayList<>();
        Collections.addAll(sectionList, section1, section2);

        Constraint twoCourseTimeSlotConstraint = new TwoCourseTimeSlotConstraint(section1, section2);
        List<Constraint> constraintList = new ArrayList<>();
        constraintList.add(twoCourseTimeSlotConstraint);

        Constraint groupConstraint = new GroupConstraint(constraintList);

        Conflict twoCourseTimeSlotConflict = new TwoCourseTimeSlotConflict(section1, section2);
        List<Conflict> correctList = new ArrayList<>();
        correctList.add(twoCourseTimeSlotConflict);

        assertEquals(correctList, groupConstraint.getConflicts(sectionList));
    }

    @Test
    void getConflictsTestForGroupConstraintWithMultipleConflicts() {
        Section section1 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        Section section2 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 2));
        Section section3 = ef.makeSection(ef.makeCourse("CS", "220", "Fundamentals Of CS"), ef.makeHCTimeSlot(Weekday.MWF(), 1));

        Instructor instructor1 = ef.makeInstructor("John", "Smith", "456357");
        section1.addInstructor(instructor1);
        section3.addInstructor(instructor1);

        List<Section> sectionList = new ArrayList<>();
        Collections.addAll(sectionList, section1, section2, section3);

        Constraint twoCourseTimeSlotConstraint = new TwoCourseTimeSlotConstraint(section1, section3);
        Constraint twoSectionConstraint = new TwoSectionConstraint(section1, section2);
        Constraint instructorConstraint = new InstructorConstraint(section1, section3);
        List<Constraint> constraintList = new ArrayList<>();
        Collections.addAll(constraintList, twoCourseTimeSlotConstraint, twoSectionConstraint, instructorConstraint);

        Constraint groupConstraint = new GroupConstraint(constraintList);

        Conflict twoCourseTimeSlotConflict = new TwoCourseTimeSlotConflict(section1, section3);
        Conflict twoSectionConflict = new TwoSectionConflict(section1, section2);
        Conflict instructorConflict = new InstructorConflict(section1, section3);
        List<Conflict> correctList = new ArrayList<>();
        Collections.addAll(correctList, twoCourseTimeSlotConflict, twoSectionConflict, instructorConflict);

        assertEquals(correctList, groupConstraint.getConflicts(sectionList));
    }

    @Test
    void canAddConstraintToList() {
        Section section1 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        Section section2 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        Section section3 = ef.makeSection(ef.makeCourse("CS", "220", "Fundamentals Of CS"), ef.makeHCTimeSlot(Weekday.MWF(), 1));

        Constraint sectionOneAndThreeTimeSlot = new TwoCourseTimeSlotConstraint(section1, section3);
        Constraint twoSectionConstraint = new TwoSectionConstraint(section1, section2);
        Constraint sectionOneAndTwoTimeSlot = new TwoCourseTimeSlotConstraint(section1, section2);
        List<Constraint> constraintList = new ArrayList<>();
        Collections.addAll(constraintList, sectionOneAndThreeTimeSlot, twoSectionConstraint);

        GroupConstraint groupConstraint = new GroupConstraint(constraintList);
        groupConstraint.addConstraint(sectionOneAndTwoTimeSlot);
        assertEquals(List.of(sectionOneAndThreeTimeSlot, twoSectionConstraint, sectionOneAndTwoTimeSlot), groupConstraint.constraintList);
    }

    @Test
    void canRemoveConstraintFromList() {
        Section section1 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        Section section2 = ef.makeSection(ef.makeCourse("CS", "321", "Software Development Practicum"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        Section section3 = ef.makeSection(ef.makeCourse("CS", "220", "Fundamentals Of CS"), ef.makeHCTimeSlot(Weekday.MWF(), 1));

        Constraint sectionOneAndThreeTimeSlot = new TwoCourseTimeSlotConstraint(section1, section3);
        Constraint twoSectionConstraint = new TwoSectionConstraint(section1, section2);
        Constraint sectionOneAndTwoTimeSlot = new TwoCourseTimeSlotConstraint(section1, section2);
        List<Constraint> constraintList = new ArrayList<>();
        Collections.addAll(constraintList, sectionOneAndThreeTimeSlot, twoSectionConstraint, sectionOneAndTwoTimeSlot);

        GroupConstraint groupConstraint = new GroupConstraint(constraintList);
        groupConstraint.removeConstraint(sectionOneAndTwoTimeSlot);
        assertEquals(List.of(sectionOneAndThreeTimeSlot, twoSectionConstraint), groupConstraint.constraintList);
    }
}
