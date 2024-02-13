package edu.hanover.schedulevisualizer.conflict;

import edu.hanover.schedulevisualizer.core.Section;

import java.util.Optional;

/**
 * This class uses the constraint interface and compares two sections and determines weather
 * they are the same and depending on that result produces a conflict. This class contains
 * two methods that return something. One of these methods is the twoConflictingCourses
 * which gives us information about the two sections. The second method that reutrns something
 * is generateConflict this makes a conflict based off the result comparing the two sections
 */

public class TwoCourseConstraint implements Constraint {

    Section section1;
    Section section2;

    public TwoCourseConstraint(final Section section1, final Section section2) {
        this.section1 = section1;
        this.section2 = section2;
    }

    public boolean twoConflictingCourses(final Section section1, final Section section2) {
        return section1.IsSameCourse(section2);
    }

    public Optional<Conflict> generateConflict(final Section section1, final Section section2) {
        if (twoConflictingCourses(section1, section2)) {
            return Optional.of(new TwoCourseConflict(section1, section2));
        }
        return Optional.empty();
    }
}
