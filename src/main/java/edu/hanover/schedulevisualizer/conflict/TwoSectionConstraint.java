package edu.hanover.schedulevisualizer.conflict;

import edu.hanover.schedulevisualizer.core.entity.Section;

/**
 * This class takes in two sections and checks if their
 * courses are the same. If their courses are flagged as the same
 * it will create a new TwoSectionConflict. For example if we have two courses
 * 220A and 220B since those courses are the same they can not be at the same timeslot.
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
