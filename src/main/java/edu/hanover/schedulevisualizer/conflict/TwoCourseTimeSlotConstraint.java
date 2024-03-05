package edu.hanover.schedulevisualizer.conflict;

import edu.hanover.schedulevisualizer.core.entity.Section;

public class TwoCourseTimeSlotConstraint extends PairwiseConstraint{
    Section section1;
    Section section2;

    public TwoCourseTimeSlotConstraint(Section section1, Section section2) {
        this.section1 = section1;
        this.section2 = section2;
    }

    public Conflict getPairwiseConstraint(Section s1, Section s2) {
        if (s1.getTimeslot() == s2.getTimeslot()) {
            return new TwoCourseTimeSlotConflict(s1, s2);
        } return null;
    }
}
