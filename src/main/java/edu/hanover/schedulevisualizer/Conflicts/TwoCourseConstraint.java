package edu.hanover.schedulevisualizer.Conflicts;

import edu.hanover.schedulevisualizer.core.Section;

import java.util.List;

public class TwoCourseConstraint extends Constraint {

    Section section1;
    Section section2;

    public TwoCourseConstraint(Section section1, Section section2) {
        this.section1 = section1;
        this.section2 = section2;
    }

    public Boolean twoConflictingCourses(Section section1, Section section2) {
        return section1.IsSameCourse(section2);
    }
}
