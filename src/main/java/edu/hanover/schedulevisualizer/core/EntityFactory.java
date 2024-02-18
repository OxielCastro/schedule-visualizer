package edu.hanover.schedulevisualizer.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityFactory {
    public Map<String, TimeSlot> createdTimeslots = new HashMap<>();

    public Course makeCourse(String prefix, String courseNum, String courseDescription) {
        // TODO Need to persist
        return new Course(prefix, courseNum, courseDescription);
    }

    public Section makeSection(Course course, TimeSlot timeslot) {
        return new Section(course, timeslot);
    }

    public Instructor makeInstructor(String first, String last, String id) {
        return new Instructor(first, last, id);
    }

    public Schedule makeSchedule() { return new Schedule(); }
    public Schedule makeSchedule(Collection<Section> sections) { return new Schedule(sections); }

    public TimeSlot makeHCTimeSlot(final List<Weekday> Tuesday, final int slotnum) {
        return addIfNeededThenReturn(new HCTimeSlot(Tuesday, slotnum));
    }

    public TimeSlot makeUnassignedTimeslot() {
        return addIfNeededThenReturn(UnassignedTimeSlot.getInstance());
    }

    private TimeSlot addIfNeededThenReturn(final TimeSlot timeSlot) {
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
