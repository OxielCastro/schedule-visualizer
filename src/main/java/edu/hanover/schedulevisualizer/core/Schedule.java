package edu.hanover.schedulevisualizer.core;

import java.util.*;
import java.util.stream.Collectors;

public class Schedule implements Iterable<Section>{
    private Set<Section> sections;

    /**
     * Basic Constructor for Schedule class.
     * Creates a blank set and assigns it to the sections variable.
     * Used to create a blank schedule.
     */
    public Schedule() {
        this(new HashSet<Section>());
    }

    /**
     * Constructor for the Schedule class that takes a collection of Sections
     * as a parameter. This is how you create a Schedule with sections in it.
     *
     * @param collection
     */
    public Schedule(final Collection<Section> collection) {
        this.sections = new HashSet<Section>(collection);
    }

    /**
     * Takes an instructor as a parameter and then gets the sections for the schedule.
     * Then it filters the results to only return the ones for the given instructor.
     *
     * @param instr the instructor whose sections your searching for.
     *
     * @return the sections for the instructor given as a collection of sections
     */
    public List<Section> findSectionFor(final Instructor instr) {
        return getSections()
                .stream().filter(section -> section.hasInstructor(instr))
                .collect(Collectors.toList());
    }

    /**
     * Creates a new variable sections1 and assigns it to the field sections.
     * This is then returned.
     *
     * @return the sections for a schedule as a set of sections.
     */
    public Collection<Section> getSections(){
        final HashSet<Section> sections1 = new HashSet<>(sections);
        return sections1;
    }

    /**
     * Adds a section to the schedule.
     *
     * @param section section you wanted to add.
     */
    void addSection(final Section section) {
        sections.add(section);
    }

    /**
     * Removes a section from the Schedule.
     *
     * @param section section you wanted to remove.
     */
    void removeSection(final Section section) {
        sections.remove(section);
    }

    /**
     * Checks to see if schedule contains given section and returns boolean.
     *
     * @param section section your checking for.
     *
     * @return Boolean
     */
    boolean hasSection(final Section section) { return sections.contains(section); }

    /**
     * Returns an iterator for the class Section.
     *
     * @return an Iterator of type Section.
     */
    public Iterator<Section> iterator() {
        return sections.iterator();
    }

}
