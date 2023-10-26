package edu.hanover.schedulevisualizer.core;

import java.util.List;

public class Section {
    private static long nextAvailableCourseId = 0;
    private final long courseId;
    private TimeSlot timeslot;
    private final String prefix;
    private final String courseNum;
    private final String courseName;


    public Section(String prefix, String courseNum, String courseName, TimeSlot timeslot) {
        this.courseId = nextAvailableCourseId;
        nextAvailableCourseId += 1;
        this.prefix = prefix;
        this.courseNum = courseNum;
        this.courseName = courseName;
        this.timeslot = timeslot;
    }


    public String getPrefix() {
        return prefix;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public TimeSlot getTimeslot() { return timeslot; }

    public String getCourseCode() {
        return prefix + " " + courseNum;
    }

    public List<Weekday> getWeekdays() { return timeslot.getWeekdayList(); }

    public long getCourseId() {
        return courseId;
    }

    public void setTimeslot(TimeSlot timeslot) {
        // TODO: Set observable
        this.timeslot = timeslot;
    }
}
