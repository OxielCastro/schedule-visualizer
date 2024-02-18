package edu.hanover.schedulevisualizer.core;

import java.util.Objects;

/**
 * This is a class representing a particular course that is built out of the
 * prefix, courseNum, and courseName parameters.
 * @author mattb
 */
public class Course {
    public int courseId;

    String prefix;
    String courseNum;
    String courseName;

    protected Course() {}
    /**
     * This is the constructor for a particular instance of a course.
     * @param prefix this represents the prefix of a course of a particular department.
     * @param courseNum this represents the course number or the course level.
     * @param courseName this is the full name of the course.
     */
    Course(final String prefix, final String courseNum, final String courseName) {
        this.prefix = prefix;
        this.courseNum = courseNum;
        this.courseName = courseName;
    }

    /**
     * This method allows you to extract the prefix of a course.
     * @return this returns the String of the prefix of a particular
     * instance of a course.
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * This allows you to extract the course number of a particular instance of a course.
     * @return this returns the String of the course number of a particular
     * instance of a course.
     */
    public String getCourseNum() {
        return courseNum;
    }

    /**
     * This allows you to extract the course name of a particular instance of a course.
     * @return this returns the String of the course name of a particular
     * instance of a course.
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * This allows you to extract the full course code of a particular instance of a
     * course using the prefix and courseNum fields.
     * @return This returns the concatenated String of prefix and courseNum to give
     * you the full course code.
     */
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
        final Course course = (Course) o;
        return Objects.equals(prefix, course.prefix) &&
                Objects.equals(courseNum, course.courseNum) &&
                Objects.equals(courseName, course.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prefix, courseNum, courseName);
    }
}