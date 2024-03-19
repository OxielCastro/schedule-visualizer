package edu.hanover.schedulevisualizer.core.entity;

import java.util.List;

public class EntityFactoryLoggingDecorator implements EntityFactory {
    private final EntityFactory ef;

    public EntityFactoryLoggingDecorator(EntityFactory ef) {
        this.ef = ef;
    }

    public Course makeCourse(String prefix, String courseNum, String courseDescription) {
        System.out.format("Making course: %s %s - %s", prefix, courseNum, courseDescription);
        return ef.makeCourse(prefix, courseNum, courseDescription);
    }

    public Section makeSection(Course course, TimeSlot timeslot) {
        System.out.format("Making section: %s %s ", course, timeslot);
        return ef.makeSection(course, timeslot);
    }

    public Instructor makeInstructor(String first, String last, String id) {
        return ef.makeInstructor(first, last, id);
    }

    public Schedule makeSchedule() {
        return ef.makeSchedule();
    }

    public TimeSlot makeHCTimeSlot(List<Weekday> Tuesday, int slotnum) {
        return ef.makeHCTimeSlot(Tuesday, slotnum);
    }

    public TimeSlot makeUnassignedTimeslot() {
        return ef.makeUnassignedTimeslot();
    }

    public TimeSlot getTimeslotWithId(String timeslotId) {
        return ef.getTimeslotWithId(timeslotId);
    }

    public TimeSlot makeTimeSlotFromAbbrId(String dbData) {
        return ef.makeTimeSlotFromAbbrId(dbData);
    }
}
