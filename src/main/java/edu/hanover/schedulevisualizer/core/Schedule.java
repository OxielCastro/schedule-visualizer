package edu.hanover.schedulevisualizer.core;

import java.util.*;

public class Schedule implements Iterable<Section>{
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

    public static List<Section> findSectionFor(Instructor instr, Schedule schedule) {
//        return sections.filter(s ->s.)
        List<Section> acc = new ArrayList<>();
        for (Section currSection : schedule.getSections()) {
            for (Instructor currInstr : currSection.getInstructorList()) {
                if (currInstr.equals(instr)) {
                    acc.add(currSection);
                }
            }
        }
        return acc;
    }

    public Iterator<Section> iterator() {
        return sections.iterator();
    }

}
