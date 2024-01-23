package edu.hanover.schedulevisualizer.Conflicts;

import edu.hanover.schedulevisualizer.core.Section;

import java.util.Optional;

public class TwoCourseConstraint implements Constraint {

    Section section1;
    Section section2;

    public TwoCourseConstraint(Section section1, Section section2) {
        this.section1 = section1;
        this.section2 = section2;
    }

    public boolean twoConflictingCourses(Section section1, Section section2) {
        return section1.IsSameCourse(section2);
    }

    public Optional<Conflict> generateConflict(Section section1, Section section2) {
        if (twoConflictingCourses(section1, section2)) {
            return Optional.of(new TwoCourseConflict(section1, section2));
        }
        return Optional.empty();
    }
}
