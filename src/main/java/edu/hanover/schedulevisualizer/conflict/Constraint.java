package edu.hanover.schedulevisualizer.conflict;

import edu.hanover.schedulevisualizer.core.Section;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Constraint {
    default List<Optional<Conflict>> generateConflicts(final List<Section> sectionlist) {
        final List<Optional<Conflict>> conflictlist = new ArrayList<>();
        for (int i = 0; i < sectionlist.size(); i++) {
            for (int j = i + 1; j < sectionlist.size(); j++) {
                conflictlist.add(generateConflict(sectionlist.get(i), sectionlist.get(j)));
            }
        }
        return conflictlist;
    }

    boolean twoConflictingCourses(Section section, Section section1);

    Optional<Conflict> generateConflict(Section section1, Section section2);
}
