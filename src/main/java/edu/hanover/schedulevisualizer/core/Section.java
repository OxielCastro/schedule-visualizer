package edu.hanover.schedulevisualizer.core;

import java.util.List;

public class Section {
    private static long nextAvailableCourseId = 0;
    private final long courseId;
    private final Course course;
    private TimeSlot timeslot;


    public Section(String prefix, String courseNum, String courseName, TimeSlot timeslot) {
        this.courseId = nextAvailableCourseId;
        nextAvailableCourseId += 1;
        this.course = new Course(prefix, courseNum, courseName);
        this.timeslot = timeslot;
    }


    public String getPrefix() {
        return course.getPrefix();
    }

    public String getCourseNum() {
        return course.getCourseNum();
    }

    public String getCourseName() {
        return course.getCourseName();
    }

    public TimeSlot getTimeslot() { return timeslot; }

    public String getCourseCode() {
        return course.getCourseCode();
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
