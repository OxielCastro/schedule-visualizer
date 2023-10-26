package edu.hanover.schedulevisualizer.core;

import java.util.Collection;
import java.util.HashSet;

public class Schedule {
    private Collection<Section> sections;

    public Schedule() {
        this(new HashSet<Section>());
    }

    public Schedule(Collection<Section> collection) {
        this.sections = new HashSet<Section>(collection);
    }

    public Collection<Section> getSections(){
        return sections;
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    public void removeSection(Section section) {
        sections.remove(section);
    }
}
