package edu.hanover.schedulevisualizer.core;

import java.util.*;
import java.util.stream.Collectors;

public class Schedule implements Iterable<Section>{
    private Set<Section> sections;

    public Schedule() {
        this(new HashSet<Section>());
    }

    public Schedule(Collection<Section> collection) {
        this.sections = new HashSet<Section>(collection);
    }

    public List<Section> findSectionFor(Instructor instr) {
        return getSections()
                .stream().filter(section -> section.hasInstructor(instr))
                .collect(Collectors.toList());
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

    public Iterator<Section> iterator() {
        return sections.iterator();
    }

}
