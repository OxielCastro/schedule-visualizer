package edu.hanover.schedulevisualizer.conflict;

import edu.hanover.schedulevisualizer.core.entity.Section;

import java.util.List;

public interface Constraint {

    /**
     * Generates a list of conflicts based on the given list of course sections.
     * Each pair of sections in the list is checked for conflicts, and a list of optional conflicts is returned
     * @return a list of optional conflicts between course sections
     */

    List<Conflict> getConflicts(List<Section> sectionList);
}
