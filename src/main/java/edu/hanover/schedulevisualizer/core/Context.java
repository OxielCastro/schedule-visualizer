package edu.hanover.schedulevisualizer.core;

import edu.hanover.schedulevisualizer.core.entity.*;
import edu.hanover.schedulevisualizer.core.simpleEntity.SimpleEntityFactory;
import edu.hanover.schedulevisualizer.core.simpleEntity.SimpleSection;
import edu.hanover.schedulevisualizer.observable.MyObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Context {
    private static Context instance = new Context();//creates one
    public final EntityFactory ef = new SimpleEntityFactory();
    private List<MyObserver<List<Section>>> observers = new ArrayList<>();
    private Map<String, Instructor> instructorMasterList = new HashMap<>();
    protected Schedule schedule;
    private long courseId;
    private String id;

    protected Context() {
        this.schedule = ef.makeSchedule(List.of(
                ef.makeSection(ef.makeCourse("CS", "220", "Fundamentals of Computer Science"), ef.makeHCTimeSlot(Weekday.MWF(), 1)),
                ef.makeSection(ef.makeCourse("MAT", "121", "Calculus I"), ef.makeHCTimeSlot(List.of(Weekday.Tuesday), 7)),
                ef.makeSection(ef.makeCourse("FY", "101", "First Year"), ef.makeUnassignedTimeslot()),
                ef.makeSection(ef.makeCourse("FY2", "102", "First Year2"), ef.makeUnassignedTimeslot())
        ));
    }

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
            if (section.getSectionId() == courseId) {
                return section;
            }
        }
        throw new RuntimeException("Cannot find course with id: " + courseId);
    }

    public void moveCourseToTimeslot(final Long courseId, final String timeslotId) {
        final Section section = getCourseWithId(courseId);
        final TimeSlot timeslot = ef.getTimeslotWithId(timeslotId);
        section.setTimeslot(timeslot);
        System.out.println("Dropped: " + section + timeslot);
    }


    public void addSections(final Section section) {
        schedule.addSection(section);
    }

    public void assignTimeslot(final long courseId, final String id) {
        final Section section = getCourseWithId(courseId);
        final TimeSlot timeslot = ef.getTimeslotWithId(id);
        section.setTimeslot(timeslot);
        notifyObservers();
    }

    public void assignInstructor(final long courseId, final String id, final String instructorId){
        final Section section = getCourseWithId(courseId);
        final TimeSlot timeslot = ef.getTimeslotWithId(id);
        final Instructor instructor = getInstructorWithId(instructorId);
        section.addInstructor(instructor);
        notifyObservers();
    }


    public void createNewEmptySchedule() {
        this.schedule = ef.makeSchedule();
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
}
