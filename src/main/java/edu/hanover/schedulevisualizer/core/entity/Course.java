package edu.hanover.schedulevisualizer.core.entity;

public interface Course {
    /**
     * This method allows you to extract the prefix of a course.
     * @return this returns the String of the prefix of a particular
     * instance of a course.
     */
    String getPrefix();

    /**
     * This allows you to extract the course number of a particular instance of a course.
     * @return this returns the String of the course number of a particular
     * instance of a course.
     */
    String getCourseNum();

    /**
     * This allows you to extract the course name of a particular instance of a course.
     * @return this returns the String of the course name of a particular
     * instance of a course.
     */
    String getCourseName();

    /**
     * This allows you to extract the full course code of a particular instance of a
     * course using the prefix and courseNum fields.
     * @return This returns the concatenated String of prefix and courseNum to give
     * you the full course code.
     */
    String getCourseCode();
}
