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
    private Map<String, Instructor> instructorMasterList = new HashMap<>();
    protected Schedule schedule;
    private long courseId;
    private String id;

    protected Context() {
        this.schedule = new Schedule(List.of(
                new Section(new Course("CS", "220", "Fundamentals of Computer Science"), makeHCTimeSlot(Weekday.MWF(), 1)),
                new Section(new Course("MAT", "121", "Calculus I"), makeHCTimeSlot(List.of(Weekday.Tuesday), 7)),
                new Section(new Course("FY", "101", "First Year"), makeUnassignedTimeslot()),
                new Section(new Course("FY2", "102", "First Year2"), makeUnassignedTimeslot())
        ));
    }

    public TimeSlot makeUnassignedTimeslot() {
        return addIfNeededThenReturn(UnassignedTimeSlot.getInstance());
    }

    public TimeSlot makeHCTimeSlot(final List<Weekday> Tuesday, final int slotnum) {
        return addIfNeededThenReturn(new HCTimeSlot(Tuesday, slotnum));
    }

    private TimeSlot addIfNeededThenReturn(final TimeSlot timeSlot) {
        if (createdTimeslots.containsKey(timeSlot.getId())) {
            return createdTimeslots.get(timeSlot.getId()); //putIfAbsent method will fix exit point issue
        }
        createdTimeslots.putIfAbsent(timeSlot.getId(), timeSlot);
        return timeSlot;
    }

    ;//prevents others from calling

    /**
     * Get a reference to the singleton instance for Context.
     * @return the singleton instance of {@link Context}
     */
    public static Context getInstance() {
        return instance;
    }

    public void getData() {
        notifyObservers();
    }

    public void addObserver(final MyObserver<List<Section>> observer) {
        observers.add(observer);
    }

    void notifyObservers() {
        observers.forEach((obj) -> obj.update(new ArrayList<Section>(schedule.getSections())));
    }


    public Section getCourseWithId(final Long courseId) {
        for (final Section section : schedule.getSections()) {
            if (section.getCourseId() == courseId) {
                return section;
            }
        }
        throw new RuntimeException("Cannot find course with id: " + courseId);
    }


    public TimeSlot getTimeslotWithId(final String timeslotId) {
        // TODO: Add error-checking if id doesn't exist
        if (!createdTimeslots.containsKey(timeslotId))
            throw new RuntimeException("Should not have a timeslot id that is not stored");
        return createdTimeslots.get(timeslotId);
    }

    public void moveCourseToTimeslot(final Long courseId, final String timeslotId) {
        final Section section = getCourseWithId(courseId);
        final TimeSlot timeslot = getTimeslotWithId(timeslotId);
        section.setTimeslot(timeslot);
        System.out.println("Dropped: " + section + timeslot);
    }


    public void addSections(final Section section) {
        schedule.addSection(section);
    }

    public void assignTimeslot(final long courseId, final String id) {
        final Section section = getCourseWithId(courseId);
        final TimeSlot timeslot = getTimeslotWithId(id);
        section.setTimeslot(timeslot);
        notifyObservers();
    }

    public void assignInstructor(final long courseId, final String id, final String instructorId){
        final Section section = getCourseWithId(courseId);
        final TimeSlot timeslot = getTimeslotWithId(id);
        final Instructor instructor = getInstructorWithId(instructorId);
        section.setInstructor(instructor);
        notifyObservers();
    }


    public void createNewEmptySchedule() {
        this.schedule = new Schedule();
        notifyObservers();
    }

    public void addInstructorToMasterList(final Instructor instructor) {
        instructorMasterList.put(instructor.getId(), instructor);
    }

    public void removeInstructorToMasterList(final Instructor instructor) {
        instructorMasterList.remove(instructor.getId(), instructor);
    }

    public boolean isInstructorInMasterList(final String instructorId) {
        return instructorMasterList.containsKey(instructorId);
    }

    public Instructor getInstructorWithId(final String instructorId) {
        if (isInstructorInMasterList(instructorId)) {
            return instructorMasterList.get(instructorId);
        } else {
            throw new IllegalArgumentException("Instructor with ID " + instructorId + " not found.");
        }
    }

    public List<String> getInstructorSchedule(final String id) {
        final Instructor instructor = getInstructorWithId(id);
        final List<Section> instructorSections = schedule.findSectionFor(instructor);
        final List<String> acc = new ArrayList<>();
        for (final Section section : instructorSections) {
            acc.add(section.makeString());
        }
        return acc;
    }

    ;

}
