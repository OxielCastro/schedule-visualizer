package edu.hanover.schedulevisualizer.Conflicts;

import edu.hanover.schedulevisualizer.core.Instructor;
import edu.hanover.schedulevisualizer.core.Section;

import java.util.Optional;

public class InstructorConstraint implements Constraint {
    Instructor instructor;

    public boolean twoConflictingCourses(Section section1, Section section2) {
        if (section1.getInstructorList().isEmpty() || section2.getInstructorList().isEmpty()) {
            return false;
        }
        if(section1.getInstructorList().equals(section2.getInstructorList())) {
            return section1.getTimeslot().overlaps(section2.getTimeslot());
        }
        return false;
    }

    public Optional<Conflict> generateConflict(Section section1, Section section2) {
        if (twoConflictingCourses(section1, section2)) {
            return Optional.of(new InstructorConflict(section1, section2));
        }
        return Optional.empty();
    }

}
