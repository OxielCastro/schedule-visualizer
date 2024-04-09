package edu.hanover.schedulevisualizer.conflict;

import edu.hanover.schedulevisualizer.core.entity.Section;

import java.util.*;

public class GroupConstraint implements Constraint {

    public List<Constraint> constraintList;

    public GroupConstraint(List<Constraint> constraintList) {
        this.constraintList = constraintList;
    }

    public List<Conflict> getConflicts(List<Section> sectionList) {
        List<Conflict> conflictList = new ArrayList<>();
        for (int i = 0; i < constraintList.size(); i++) {
            conflictList.addAll(constraintList.get(i).getConflicts(sectionList));
        } return conflictList;
    }

    public void addConstraint(Constraint constraint) {
        constraintList.add(constraint);
    }

    public void removeConstraint(Constraint constraint) {
        constraintList.remove(constraint);
    }

}