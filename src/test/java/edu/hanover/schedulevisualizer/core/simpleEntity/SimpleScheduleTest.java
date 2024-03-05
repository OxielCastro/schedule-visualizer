package edu.hanover.schedulevisualizer.core.simpleEntity;

import edu.hanover.schedulevisualizer.core.entity.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleScheduleTest {
    private SimpleSection cs220 = new SimpleSection( new SimpleCourse("CS", "220", "Fundamentals of Computer Science"), new HCTimeSlot(Weekday.MWF(), 1));
    private SimpleSection mat121 = new SimpleSection( new SimpleCourse("MAT", "121", "Calculus I"), new HCTimeSlot(Weekday.fromLetters("T"), 7));
    private SimpleSection fy101 = new SimpleSection( new SimpleCourse("FY", "101", "First Year"), new UnassignedTimeSlot());

    @Test
    public void canMakeNewEmptySchedule(){
        SimpleSchedule schedule = new SimpleSchedule();
        assertTrue(schedule.getSections().isEmpty());
    }

    @Test
    public void canMakeScheduleWithListOfSections(){
        List<SimpleSection> sections = List.of(cs220, mat121, fy101);
        SimpleSchedule schedule = new SimpleSchedule(sections);
        assertEquals(Set.of(cs220, mat121, fy101), schedule.getSections());
    }
    @Test
    public void scheduleWillNotTakeDuplicateSections(){
        List<SimpleSection> sectionsRepeated = new ArrayList<>(List.of(cs220, mat121, mat121, fy101));
        SimpleSchedule schedule = new SimpleSchedule(sectionsRepeated);
        assertEquals(Set.of(cs220, mat121, fy101), schedule.getSections());
    }

    @Test
    public void scheduleWillNotAddDuplicateSections(){
        List<SimpleSection> sections = new ArrayList<>(List.of(cs220, fy101));
        SimpleSchedule schedule = new SimpleSchedule(sections);
        assertEquals(Set.of(cs220, fy101), schedule.getSections());
        schedule.addSection(cs220);
        assertEquals(Set.of(cs220, fy101), schedule.getSections());
    }

    @Test
    public void canAddCourses(){
        SimpleSchedule schedule = new SimpleSchedule();
        assertFalse(schedule.hasSection(cs220));
        assertFalse(schedule.hasSection(mat121));
        schedule.addSection(cs220);
        assertTrue(schedule.hasSection(cs220));
        assertFalse(schedule.hasSection(mat121));
        schedule.addSection(mat121);
        assertTrue(schedule.hasSection(cs220));
        assertTrue(schedule.hasSection(mat121));
    }

    @Test
    public void canRemoveCourses() {
        List<SimpleSection> sections = new ArrayList<>(List.of(cs220, mat121));
        SimpleSchedule schedule = new SimpleSchedule(sections);
        assertTrue(schedule.hasSection(cs220));
        assertTrue(schedule.hasSection(mat121));
        schedule.removeSection(mat121);
        assertTrue(schedule.hasSection(cs220));
        assertFalse(schedule.hasSection(mat121));
        schedule.removeSection(cs220);
        assertFalse(schedule.hasSection(cs220));
        assertFalse(schedule.hasSection(mat121));
    }

}
