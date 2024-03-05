package edu.hanover.schedulevisualizer.conflict;

import edu.hanover.schedulevisualizer.core.entity.Instructor;
import edu.hanover.schedulevisualizer.core.entity.Section;

import java.util.Optional;

public class InstructorConstraint {

    /**
     * The instructor associated with this constraint.
     */
    Instructor instructor;

    /**
     * Checks if two sections have conflicting courses based on instructor availability and overlapping timeslots.
     * @param section1 the first section to compare
     * @param section2 the second section to compare
     * @return {@code true} if the sections have conflicting courses, {@code false} otherwise
     */
    public boolean twoConflictingCourses(final Section section1, final Section section2) {
        if (section1.getInstructorList().isEmpty() || section2.getInstructorList().isEmpty()) {
            return false;
        }
        if(section1.getInstructorList().equals(section2.getInstructorList())) {
            return section1.getTimeslot().overlaps(section2.getTimeslot());
        }
        return false;
    }

    /**
     * Generates a conflict between two sections if they have conflicting courses.
     * @param section1 the first section to compare
     * @param section2 the second section to compare
     * @return an {@code Optional} containing the generated conflict if there is a conflict,
     *         or an empty {@code Optional} otherwise
     */
    public Optional<Conflict> generateConflict(final Section section1, final Section section2) {
        if (twoConflictingCourses(section1, section2)) {
            return Optional.of(new InstructorConflict(section1, section2));
        }
        return Optional.empty();
    }
}
