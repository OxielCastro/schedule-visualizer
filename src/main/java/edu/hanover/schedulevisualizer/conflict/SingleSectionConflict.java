package edu.hanover.schedulevisualizer.conflict;

import edu.hanover.schedulevisualizer.core.entity.Course;
import edu.hanover.schedulevisualizer.core.entity.Instructor;
import edu.hanover.schedulevisualizer.core.entity.Section;

public class SingleSectionConflict implements Conflict{
    private Section section;
    private Instructor instructor;
    private Course course;

    public SingleSectionConflict(Section section) {
        this.section = section;
    }

    public boolean addSection(Section newSection) {
        if (newSection.equals(section)) {
            System.out.println("The section is already in Instructor Section list.");
            return false;
        } else {
            section = newSection;
            return true;
        }
    }

    public boolean addInstructor(Instructor newInstructor) {
        if (newInstructor.equals(instructor)){
            System.out.println("The instructor is already added in this Section");
            return false;
        }else {
            instructor = newInstructor;
            return true;
        }
    }

    public boolean addInstructor(Course newCourse) {
        if (newCourse.equals(course)){
            System.out.println("The instructor is already added in this Section");
            return false;
        }else {
            course = newCourse;
            return true;
        }
    }

    public Section getSection() {
        return section;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public Course getCourse() {
        return course;
    }


}
