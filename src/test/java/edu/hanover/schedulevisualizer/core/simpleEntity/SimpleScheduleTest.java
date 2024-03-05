package edu.hanover.schedulevisualizer.core.simpleEntity;

import edu.hanover.schedulevisualizer.core.entity.HCTimeSlot;
import edu.hanover.schedulevisualizer.core.entity.UnassignedTimeSlot;
import edu.hanover.schedulevisualizer.core.entity.Weekday;
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
        SimpleSchedule schedule = new SimpleSchedule(12,"Fall Schedule", sections);
        assertEquals(Set.of(cs220, mat121, fy101), schedule.getSections());
    }

}
