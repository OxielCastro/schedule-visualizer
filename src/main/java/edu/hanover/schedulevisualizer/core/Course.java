package edu.hanover.schedulevisualizer.core;

import java.util.Objects;

public class Course {
    final String prefix;
    final String courseNum;
    final String courseName;

    public Course(String prefix, String courseNum, String courseName) {
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

    @Override
    @SuppressWarnings("PMD.ShortVariable")
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(prefix, course.prefix) &&
                Objects.equals(courseNum, course.courseNum) &&
                Objects.equals(courseName, course.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prefix, courseNum, courseName);
    }
}