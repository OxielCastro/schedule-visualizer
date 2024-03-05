package edu.hanover.schedulevisualizer.conflict;

import edu.hanover.schedulevisualizer.core.entity.Section;

import java.util.ArrayList;
import java.util.List;

abstract public class PairwiseConstraint implements Constraint {
    @Override
    public List<Conflict> getConflicts(List<Section> sectionList) {
        List<Conflict> conflictList = new ArrayList<>();
        for (int i = 0; i < sectionList.size(); i++) {
            for (int j = i + 1; j <sectionList.size(); j++) {
                Conflict pairwiseConstraint = getPairwiseConstraint(sectionList.get(i), sectionList.get(j));
                if (pairwiseConstraint != null)
                    conflictList.add(pairwiseConstraint);
            }
        }
        return conflictList;
    }

    public abstract Conflict getPairwiseConstraint(Section s1, Section s2);
}
