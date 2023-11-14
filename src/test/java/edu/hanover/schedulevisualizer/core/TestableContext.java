package edu.hanover.schedulevisualizer.core;

public class TestableContext extends Context {

    Schedule getSchedule(){
        return schedule;
    }

    void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
