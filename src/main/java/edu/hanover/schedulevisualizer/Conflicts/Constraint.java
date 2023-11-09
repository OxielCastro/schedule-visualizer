package edu.hanover.schedulevisualizer.Conflicts;

import edu.hanover.schedulevisualizer.core.Section;

import java.util.List;

public abstract class Constraint {
    void generateConflicts(List<Section> sectionlist) {
        for (int i = 0; i < sectionlist.size(); i++) {
            for (int j = i + 1; j < sectionlist.size(); j++) {
                if (sectionlist.get(i).equals(sectionlist.get(j))) {
                    twoConflictingCourses(sectionlist.get(i), sectionlist.get(j));
                }
            }
        }
    }

    protected abstract Boolean twoConflictingCourses(Section section, Section section1);

    //abstract Conflict generateConflict(Section section1, Section section2);
}
