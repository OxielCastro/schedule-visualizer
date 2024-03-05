package edu.hanover.schedulevisualizer.conflict;

import edu.hanover.schedulevisualizer.core.entity.Section;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Constraint {

    /**
     * Generates a list of conflicts based on the given list of course sections.
     * Each pair of sections in the list is checked for conflicts, and a list of optional conflicts is returned
     * @return a list of optional conflicts between course sections
     */

    List<Conflict> getConflicts(List<Section> sectionList);

    /**
     * Checks if two course sections conflict with each other based on the constraint.
     *
     * @param section the first course section
     * @param section1 the second course section
     * @return true if the two sections conflict, false otherwise
     */
//    boolean twoConflictingCourses(Section section, Section section1);

    /**
     * Generates a conflict between two course sections based on the constraint.
     *
     * @param section1 the first course section
     * @param section2 the second course section
     * @return an optional conflict between the two sections if they conflict, or empty if they don't
     */
//    Optional<Conflict> generateConflict(Section section1, Section section2);
}
