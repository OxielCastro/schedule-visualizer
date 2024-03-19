package edu.hanover.schedulevisualizer.conflict;

import edu.hanover.schedulevisualizer.core.entity.Section;

public class InstructorConstraint extends PairwiseConstraint {
    Section section1;
    Section section2;

    public InstructorConstraint(Section section1, Section section2) {
        this.section1 = section1;
        this.section2 = section2;
    }

    public InstructorConflict getPairwiseConstraint(Section s1, Section s2) {
        if (s1.getInstructorList().isEmpty() || s2.getInstructorList().isEmpty()) return null;
        if (s1.getInstructorList().equals(s2.getInstructorList()) && s1.getTimeslot() == s2.getTimeslot()) {
            return new InstructorConflict(s1, s2);
        } return null;
    }
}
