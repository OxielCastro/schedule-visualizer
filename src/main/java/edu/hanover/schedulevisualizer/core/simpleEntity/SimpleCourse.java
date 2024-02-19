package edu.hanover.schedulevisualizer.core.simpleEntity;

import edu.hanover.schedulevisualizer.core.entity.Course;

import java.util.Objects;

/**
 * This is a class representing a particular course that is built out of the
 * prefix, courseNum, and courseName parameters.
 * @author mattb
 */
public class SimpleCourse implements Course {
    public int courseId;

    String prefix;
    String courseNum;
    String courseName;

    protected SimpleCourse() {}
    /**
     * This is the constructor for a particular instance of a course.
     * @param prefix this represents the prefix of a course of a particular department.
     * @param courseNum this represents the course number or the course level.
     * @param courseName this is the full name of the course.
     */
    public SimpleCourse(final String prefix, final String courseNum, final String courseName) {
        this.prefix = prefix;
        this.courseNum = courseNum;
        this.courseName = courseName;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return prefix + " " + courseNum;
    }

    /**
     * This is the built-in equals method that compares two Course objects.
     * @param o this is an instance of a Course.
     * @return This returns true or false depending on if the two Course objects are equal.
     */
    @Override
    @SuppressWarnings("PMD.ShortVariable")
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SimpleCourse course = (SimpleCourse) o;
        return Objects.equals(prefix, course.prefix) &&
                Objects.equals(courseNum, course.courseNum) &&
                Objects.equals(courseName, course.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prefix, courseNum, courseName);
    }
}