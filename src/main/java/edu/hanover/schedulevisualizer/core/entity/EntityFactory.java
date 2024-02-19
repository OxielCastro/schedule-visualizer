package edu.hanover.schedulevisualizer.core.entity;

import edu.hanover.schedulevisualizer.core.simpleEntity.SimpleInstructor;
import edu.hanover.schedulevisualizer.core.simpleEntity.SimpleSection;

import java.util.Collection;
import java.util.List;

public interface EntityFactory {
    Course makeCourse(String prefix, String courseNum, String courseDescription);

    SimpleSection makeSection(Course course, TimeSlot timeslot);

    SimpleInstructor makeInstructor(String first, String last, String id);

    Schedule makeSchedule();

    /**
     * Create a new Schedule class from a collection of Sections
     * as a parameter.
     *
     * @param sections The sections to add
     */
    default Schedule makeSchedule(Collection<Section> sections) {
        Schedule schedule = makeSchedule();
        sections.forEach(schedule::addSection);
        return schedule;
    }

    TimeSlot makeHCTimeSlot(List<Weekday> Tuesday, int slotnum);

    TimeSlot makeUnassignedTimeslot();

    TimeSlot getTimeslotWithId(String timeslotId);

    TimeSlot makeTimeSlotFromAbbrId(String dbData);
}
