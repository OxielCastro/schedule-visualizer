package edu.hanover.schedulevisualizer.core.simpleEntity;

import edu.hanover.schedulevisualizer.core.entity.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleEntityFactory implements EntityFactory {
    public Map<String, TimeSlot> createdTimeslots = new HashMap<>();

    public Course makeCourse(String prefix, String courseNum, String courseDescription) {
        // TODO Need to persist
        return new SimpleCourse(prefix, courseNum, courseDescription);
    }

    public SimpleSection makeSection(Course course, TimeSlot timeslot) {
        return new SimpleSection((SimpleCourse) course, timeslot);
    }

    public SimpleInstructor makeInstructor(String first, String last, String id) {
        return new SimpleInstructor(first, last, id);
    }

    public Schedule makeSchedule() { return new SimpleSchedule(); }

    public TimeSlot makeHCTimeSlot(final List<Weekday> Tuesday, final int slotnum) {
        return addIfNeededThenReturn(new HCTimeSlot(Tuesday, slotnum));
    }

    public TimeSlot makeUnassignedTimeslot() {
        return addIfNeededThenReturn(UnassignedTimeSlot.getInstance());
    }

    private TimeSlot addIfNeededThenReturn(TimeSlot timeSlot) {
        if (createdTimeslots.containsKey(timeSlot.getId())) {
            return createdTimeslots.get(timeSlot.getId()); //putIfAbsent method will fix exit point issue
        }
        createdTimeslots.putIfAbsent(timeSlot.getId(), timeSlot);
        return timeSlot;
    }

    public TimeSlot getTimeslotWithId(final String timeslotId) {
        // TODO: Add error-checking if id doesn't exist
        if (!createdTimeslots.containsKey(timeslotId))
            throw new RuntimeException("Should not have a timeslot id that is not stored");
        return createdTimeslots.get(timeslotId);
    }

    public TimeSlot makeTimeSlotFromAbbrId(String dbData) {
        if (dbData.equals("-")) return makeUnassignedTimeslot();
        String[] matches = dbData.split("-");
        int slotnum = Integer.parseInt(matches[1]);
        List<Weekday> weekdayList = Weekday.fromLetters(matches[0]);
        return makeHCTimeSlot(weekdayList, slotnum);
    }
}
