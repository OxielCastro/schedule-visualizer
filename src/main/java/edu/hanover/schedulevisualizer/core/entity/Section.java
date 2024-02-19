package edu.hanover.schedulevisualizer.core.entity;

import java.util.List;

public interface Section {
    String getPrefix();

    String getCourseNum();

    String getCourseName();

    TimeSlot getTimeslot();

    String getCourseCode();

    List<Weekday> getWeekdays();

    long getSectionId();

    void setTimeslot(TimeSlot timeslot);

    List<? extends Instructor> getInstructorList();

    void addInstructor(Instructor instructor);

    void changeInstructor(Instructor oldInstructor, Instructor newInstructor);

    void removeInstructor(Instructor instructor);

    Boolean IsSameCourse(Section section1);

    boolean hasInstructor(Instructor instr);

    String makeString();
}
