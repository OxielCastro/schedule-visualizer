package edu.hanover.schedulevisualizer.core;

import java.util.*;
import java.util.stream.Collectors;

public class Schedule implements Iterable<Section>{
    private Set<Section> sections;

    public Schedule() {
        this(new HashSet<Section>());
    }

    public Schedule(final Collection<Section> collection) {
        this.sections = new HashSet<Section>(collection);
    }

    public List<Section> findSectionFor(final Instructor instr) {
        return getSections()
                .stream().filter(section -> section.hasInstructor(instr))
                .collect(Collectors.toList());
    }

    public Collection<Section> getSections(){
        final HashSet<Section> sections1 = new HashSet<>(sections);
        return sections1;
    }

    void addSection(final Section section) {
        sections.add(section);
    }

    void removeSection(final Section section) {
        sections.remove(section);
    }

    boolean hasSection(final Section section) { return sections.contains(section); }

    public Iterator<Section> iterator() {
        return sections.iterator();
    }

}
