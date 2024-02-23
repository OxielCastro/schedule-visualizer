package edu.hanover.schedulevisualizer.core.simpleEntity;

import edu.hanover.schedulevisualizer.core.entity.Instructor;
import edu.hanover.schedulevisualizer.core.entity.Schedule;
import edu.hanover.schedulevisualizer.core.entity.ScheduleWriter;

public class SimpleScheduleWriter implements ScheduleWriter {
    public String writeSchedule(Schedule s, Instructor instr) {
        return s.findSectionFor(instr).toString();
    }
}
