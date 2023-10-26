package edu.hanover.schedulevisualizer.core;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ScheduleTest {

    @Test
    public void canMakeNewEmptySchedule(){
        Schedule schedule = new Schedule();
        assertTrue(schedule.getSections().isEmpty());
    }

}
