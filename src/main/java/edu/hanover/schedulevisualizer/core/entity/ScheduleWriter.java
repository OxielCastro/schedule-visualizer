package edu.hanover.schedulevisualizer.core.entity;

public interface ScheduleWriter {
    String writeSchedule(Schedule schedule, Instructor instr);
}
