package edu.hanover.schedulevisualizer.core;

import edu.hanover.schedulevisualizer.core.entity.Schedule;

public class TestableContext extends Context {

    Schedule getSchedule(){
        return schedule;
    }

    void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
