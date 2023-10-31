package edu.hanover.schedulevisualizer.core;

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
}