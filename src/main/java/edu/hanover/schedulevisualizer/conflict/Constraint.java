package edu.hanover.schedulevisualizer.conflict;

import edu.hanover.schedulevisualizer.core.Section;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * An interface representing a constraint between course sections.
 * Constraints define rules or conditions under which two course sections are considered conflicting.
 */

/**
 *
 *
 * This interface is going to contain the logic for creating a constraint and storing it
 * in a list. This interface does not return anything excpet for the gnerated conflict list
 * after it has determined the conflicts.
 *
 *
 *
 */

public interface Constraint {

    /**
     * Generates a list of conflicts based on the given list of course sections.
     * Each pair of sections in the list is checked for conflicts, and a list of optional conflicts is returned.
     * @param sectionlist the list of course sections to generate conflicts for
     * @return a list of optional conflicts between course sections
     */
    default List<Optional<Conflict>> generateConflicts(final List<Section> sectionlist) {
        final List<Optional<Conflict>> conflictlist = new ArrayList<>();
        for (int i = 0; i < sectionlist.size(); i++) {
            for (int j = i + 1; j < sectionlist.size(); j++) {
                conflictlist.add(generateConflict(sectionlist.get(i), sectionlist.get(j)));
            }
        }
        return conflictlist;
    }

    /**
     * Checks if two course sections conflict with each other based on the constraint.
     *
     * @param section the first course section
     * @param section1 the second course section
     * @return true if the two sections conflict, false otherwise
     */
    boolean twoConflictingCourses(Section section, Section section1);

    /**
     * Generates a conflict between two course sections based on the constraint.
     *
     * @param section1 the first course section
     * @param section2 the second course section
     * @return an optional conflict between the two sections if they conflict, or empty if they don't
     */
    Optional<Conflict> generateConflict(Section section1, Section section2);
}
