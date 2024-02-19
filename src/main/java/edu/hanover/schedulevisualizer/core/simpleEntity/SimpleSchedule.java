package edu.hanover.schedulevisualizer.core.simpleEntity;

import edu.hanover.schedulevisualizer.core.entity.Instructor;
import edu.hanover.schedulevisualizer.core.entity.Schedule;
import edu.hanover.schedulevisualizer.core.entity.Section;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleSchedule implements Schedule {
    private Set<SimpleSection> sections = new HashSet<>();

    /**
     * Basic Constructor for Schedule class.
     * Creates a blank set and assigns it to the sections variable.
     * Used to create a blank schedule.
     */
    SimpleSchedule() {
    }


    /**
     * Takes an instructor as a parameter and then gets the sections for the schedule.
     * Then it filters the results to only return the ones for the given instructor.
     *
     * @param instr the instructor whose sections your searching for.
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
    public Collection<Section> getSections() {
        final HashSet<Section> sections1 = new HashSet<>(sections);
        return sections1;
    }

    /**
     * Adds a section to the schedule.
     *
     * @param section section you wanted to add.
     */
    public void addSection(final Section section) {
        sections.add((SimpleSection) section);
    }

    /**
     * Removes a section from the Schedule.
     *
     * @param section section you wanted to remove.
     */
    public void removeSection(final Section section) {
        sections.remove(section);
    }

    /**
     * Checks to see if schedule contains given section and returns boolean.
     *
     * @param section section your checking for.
     * @return Boolean
     */
    public boolean hasSection(final Section section) {
        return sections.contains(section);
    }

    /**
     * Returns an iterator for the class Section.
     *
     * @return an Iterator of type Section.
     */
    public Iterator<Section> iterator() {
        Iterator<SimpleSection> it = sections.iterator();
        return new Iterator<>() {
            public boolean hasNext() {
                return it.hasNext();
            }

            public Section next() {
                return it.next();
            }
        };
    }
}
