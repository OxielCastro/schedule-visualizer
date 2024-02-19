package edu.hanover.schedulevisualizer.core.entity;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public interface Schedule extends Iterable<Section> {
    List<Section> findSectionFor(Instructor instr);

    Collection<Section> getSections();

    void addSection(Section section);

    void removeSection(Section section);

    boolean hasSection(Section section);

    Iterator<Section> iterator();
}
