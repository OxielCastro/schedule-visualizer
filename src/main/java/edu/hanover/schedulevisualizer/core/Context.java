package edu.hanover.schedulevisualizer.core;

import edu.hanover.schedulevisualizer.observable.MyObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Context {
    private static Context instance = new Context();//creates one
    private Map<String, TimeSlot> createdTimeslots = new HashMap<>();
    private List<MyObserver<List<Section>>> observers = new ArrayList<>();
    private Schedule schedule;
    private long courseId;
    private String id;

    private Context(){
        this.schedule = new Schedule(List.of(
                new Section("CS", "220", "Fundamentals of Computer Science", makeHCTimeSlot(Weekday.MWF(), 1)),
                new Section("MAT", "121", "Calculus I", makeHCTimeSlot(List.of(Weekday.Tuesday), 7)),
                new Section("FY", "101", "First Year", makeUnassignedTimeslot()),
                new Section("FY2", "102", "First Year2", makeUnassignedTimeslot())
        )) ;
    }

    public TimeSlot makeUnassignedTimeslot() {
        return addIfNeededThenReturn(UnassignedTimeSlot.getInstance());
    }

    public TimeSlot makeHCTimeSlot(List<Weekday> Tuesday, int slotnum) {
        return addIfNeededThenReturn(new HCTimeSlot(Tuesday, slotnum));
    }

    private TimeSlot addIfNeededThenReturn(TimeSlot timeSlot) {
        if (createdTimeslots.containsKey(timeSlot.getId())) {
            return createdTimeslots.get(timeSlot.getId());
        }
        createdTimeslots.put(timeSlot.getId(), timeSlot);
        return timeSlot;
    }

    ;//prevents others from calling

    public static Context getInstance() {
        return instance;
    }

    public void getData() {
        notifyObservers();
    }

    public void addObserver(MyObserver<List<Section>> observer) {
        observers.add(observer);
    }

    void notifyObservers() {
        observers.forEach((obj) -> obj.update(new ArrayList<Section>(schedule.getSections())));
    }

    public Section getCourseWithId(Long courseId) {
        for (Section section : schedule.getSections()) {
            if (section.getCourseId() == courseId) {
                return section;
            }
        }
        throw new RuntimeException("Cannot find course with id: " + courseId);
    }

    public TimeSlot getTimeslotWithId(String timeslotId) {
        // TODO: Add error-checking if id doesn't exist
        if (!createdTimeslots.containsKey(timeslotId))
            throw new RuntimeException("Should not have a timeslot id that is not stored");
        return createdTimeslots.get(timeslotId);
    }

    public void moveCourseToTimeslot(Long courseId, String timeslotId) {
        Section section = getCourseWithId(courseId);
        TimeSlot timeslot = getTimeslotWithId(timeslotId);
        section.setTimeslot(timeslot);
        System.out.println("Dropped: " + section + timeslot);
    }

    public void addSections(Section section) {
        schedule.addSection(section);
    }

    public void assignTimeslot(long courseId, String id) {
        Section section = getCourseWithId(courseId);
        TimeSlot timeslot = getTimeslotWithId(id);
        section.setTimeslot(timeslot);
        notifyObservers();
    }


}
