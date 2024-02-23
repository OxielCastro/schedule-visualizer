package edu.hanover.schedulevisualizer.core;

import edu.hanover.schedulevisualizer.core.entity.*;
import edu.hanover.schedulevisualizer.core.simpleEntity.SimpleEntityFactory;
import edu.hanover.schedulevisualizer.core.simpleEntity.SimpleScheduleWriter;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScheduleWriterTest {
    @Disabled
    @Test
    void writeScheduleWorks() {
        SimpleEntityFactory ef = new SimpleEntityFactory();
        TimeSlot MWF1 = ef.makeHCTimeSlot(Weekday.MWF(), 1);
        TimeSlot MWF2 = ef.makeHCTimeSlot(Weekday.MWF(), 2);
        Section CS220 = ef.makeSection(ef.makeCourse("CS", "220", "Fundamentals of Computer Science"), MWF1);
        Section MAT121 = ef.makeSection(ef.makeCourse("MAT", "121", "Calculus I"), MWF2);
        Schedule schedule = ef.makeSchedule(List.of(CS220, MAT121)) ;
        Instructor instructor = ef.makeInstructor("Barb", "Wahl", "wahlb@hanover.edu");
        CS220.addInstructor(instructor);
        MAT121.addInstructor(instructor);
        ScheduleWriter sw = new SimpleScheduleWriter();
        String correctString = sw.writeSchedule(schedule, instructor);
        assertEquals("CS,220,Fundamentals of Computer Science, MWF1", correctString);
    }
}
