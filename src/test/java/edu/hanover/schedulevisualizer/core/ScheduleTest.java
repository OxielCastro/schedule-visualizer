package edu.hanover.schedulevisualizer.core;

import edu.hanover.schedulevisualizer.core.entity.*;
import edu.hanover.schedulevisualizer.observable.MyObserver;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ScheduleTest extends ContextAwareTest {

    private Section cs220 = ef.makeSection(ef.makeCourse("CS", "220", "Fundamentals of Computer Science"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
    private Section mat121 = ef.makeSection(ef.makeCourse("MAT", "121", "Calculus I"), ef.makeHCTimeSlot(List.of(Weekday.Tuesday), 7));
    private Section fy101 = ef.makeSection(ef.makeCourse("FY", "101", "First Year"), ef.makeUnassignedTimeslot());

    @Test
    public void canMakeNewEmptySchedule(){
        Schedule schedule = ef.makeSchedule();
        assertTrue(schedule.getSections().isEmpty());
    }

    @Test
    public void canMakeScheduleWithListOfSections(){
        List<Section> sections = new ArrayList<>(List.of(cs220, mat121, fy101));
        Schedule schedule = ef.makeSchedule(sections);
        assertEquals(Set.of(cs220, mat121, fy101), schedule.getSections());
    }

    @Test
    public void scheduleWillNotTakeDuplicateSections(){
        List<Section> sectionsRepeated = new ArrayList<>(List.of(cs220, mat121, mat121, fy101));
        Schedule schedule = ef.makeSchedule(sectionsRepeated);
        assertEquals(Set.of(cs220, mat121, fy101), schedule.getSections());
    }

    @Test
    public void scheduleWillNotAddDuplicateSections(){
        List<Section> sections = new ArrayList<>(List.of(cs220, fy101));
        Schedule schedule = ef.makeSchedule(sections);
        assertEquals(Set.of(cs220, fy101), schedule.getSections());
        schedule.addSection(cs220);
        assertEquals(Set.of(cs220, fy101), schedule.getSections());
    }

    @Test
    public void canAddCourses(){
        Schedule schedule = ef.makeSchedule();
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
        List<Section> sections = new ArrayList<>(List.of(cs220, mat121));
        Schedule schedule = ef.makeSchedule(sections);
        assertTrue(schedule.hasSection(cs220));
        assertTrue(schedule.hasSection(mat121));
        schedule.removeSection(mat121);
        assertTrue(schedule.hasSection(cs220));
        assertFalse(schedule.hasSection(mat121));
        schedule.removeSection(cs220);
        assertFalse(schedule.hasSection(cs220));
        assertFalse(schedule.hasSection(mat121));
    }

    @Test
    public void removeCoursesNotInSections() {
        List<Section> sections = new ArrayList<>(List.of(cs220, mat121));
        Schedule schedule = ef.makeSchedule(sections);
        schedule.removeSection(fy101);
        assertEquals(Set.of(cs220, mat121), schedule.getSections());
        schedule.removeSection(cs220);
        schedule.removeSection(cs220);
        assertEquals(Set.of(mat121), schedule.getSections());
    }

    @Test
    public void contextCanCreateNewEmptySchedule(){
        Schedule originalSchedule = context.getSchedule();
        context.createNewEmptySchedule();
        Schedule newSchedule = context.getSchedule();
        assertFalse(originalSchedule == newSchedule);
    }

    @Test
    public void newCreatedScheduleIsEmpty(){
        context.createNewEmptySchedule();
        assertTrue(context.getSchedule().getSections().isEmpty());
    }

    @Test
    public void observersAreNotified(){
        MyObserver<List<Section>> observer = mock(MyObserver.class);
        context.addObserver(observer);
        context.createNewEmptySchedule();
        verify(observer, times(1)).update(any());
    }
    @Disabled
    @Test
    public void findSectionForWorksCorrectly() {
        TimeSlot MWF1 = ef.makeHCTimeSlot(Weekday.MWF(), 1);
        TimeSlot MWF2 = ef.makeHCTimeSlot(Weekday.MWF(), 2);
        Section CS220 = ef.makeSection(ef.makeCourse("CS", "220", "Fundamentals of Computer Science"), MWF1);
        Section MAT121 = ef.makeSection(ef.makeCourse("MAT", "121", "Calculus I"), MWF2);
        Schedule schedule = ef.makeSchedule(List.of(CS220, MAT121)) ;
        Instructor instructor = ef.makeInstructor("Barb", "Wahl", "wahlb@hanover.edu");
        CS220.addInstructor(instructor);
        MAT121.addInstructor(instructor);
        Instructor instructor2 = ef.makeInstructor("Hallett", "Harrison", "halletth@hanover.edu");
        MAT121.addInstructor(instructor2);
        List<Section> correctList = new ArrayList<>(List.of(CS220, MAT121));
        List<Section> correctList2 = new ArrayList<>(List.of(MAT121));
        assertEquals(correctList.size(), schedule.findSectionFor(instructor).size());
        assertTrue(schedule.findSectionFor(instructor).contains(CS220));
        assertTrue(schedule.findSectionFor(instructor).contains(MAT121));
        assertEquals(correctList2.size(), schedule.findSectionFor(instructor2).size());
        assertTrue(schedule.findSectionFor(instructor2).contains(MAT121));

    }

    @Test
    public void forLoopWorksForIterableSchedule() {
        // test for empty schedule
        Schedule emptySchedule = ef.makeSchedule();
        long numberOfCourses = 0;
        for(Section s : emptySchedule) { numberOfCourses += 1; }
        assertEquals(0, numberOfCourses);
        // test for schedule with 3 sections
        numberOfCourses = 0;
        Schedule schedule = ef.makeSchedule(List.of(cs220, mat121, fy101));
        for(Section s : schedule) { numberOfCourses += 1; }
        assertEquals(3, numberOfCourses);
    }
}
