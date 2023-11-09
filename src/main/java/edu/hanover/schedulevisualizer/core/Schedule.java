package edu.hanover.schedulevisualizer.core;

import java.util.*;

public class Schedule {
    private Set<Section> sections;

    public Schedule() {
        this(new HashSet<Section>());
    }

    public Schedule(Collection<Section> collection) {
        this.sections = new HashSet<Section>(collection);
    }

    public Collection<Section> getSections(){
        Collection<Section> sectionsToReturn = new HashSet<Section>(sections);
        return sectionsToReturn;
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    public void removeSection(Section section) {
        sections.remove(section);
    }

    public boolean hasSection(Section section) { return sections.contains(section); }

    public static String getInstructorSchedule(String instrID, Schedule schedule) {
        for (Section i : schedule.getSections()) {
            for (Instructor j :i.getInstructorList()) {
                if (j.getId() == instrID) {
                    return j.getId();
                }
            }
        }
        return "";
    }
}
