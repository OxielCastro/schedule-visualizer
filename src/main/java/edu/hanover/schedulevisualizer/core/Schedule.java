package edu.hanover.schedulevisualizer.core;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Schedule {
    private Set<Section> sections;

    public Schedule() {
        this(new HashSet<Section>());
    }

    public Schedule(Collection<Section> collection) {
        this.sections = new HashSet<Section>(collection);
    }

    public Set<Section> getSections(){
        Set<Section> sectionsToReturn = new HashSet<Section>(sections);
        return sectionsToReturn;
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    public void removeSection(Section section) {
        sections.remove(section);
    }

    public boolean hasSection(Section section) { return sections.contains(section); }
}
