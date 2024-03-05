package edu.hanover.schedulevisualizer.conflict;

import edu.hanover.schedulevisualizer.core.entity.Section;

/**
 * This class uses the constraint interface and compares two sections and determines weather
 * they are the same and depending on that result produces a conflict. This class contains
 * two methods that return something. One of these methods is the twoConflictingCourses
 * which gives us information about the two sections. The second method that returns something
 * is generateConflict this makes a conflict based off the result comparing the two sections
 */

public class TwoSectionConstraint extends PairwiseConstraint {

    Section section1;
    Section section2;

    public TwoSectionConstraint(final Section section1, final Section section2) {
        this.section1 = section1;
        this.section2 = section2;
    }

    public TwoSectionConflict getPairwiseConstraint(Section s1, Section s2) {
        if (s1.IsSameCourse(s2)) {
            return new TwoSectionConflict(s1, s2);
        } return null;
    }
}
