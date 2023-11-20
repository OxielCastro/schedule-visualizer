package edu.hanover.schedulevisualizer.Conflicts;

import edu.hanover.schedulevisualizer.core.Section;

import java.util.Objects;

public class InstructorConflict implements Conflict {
    Section section1;
    Section section2;

    public InstructorConflict(Section section1, Section section2) {
        this.section1 = section1;
        this.section2 = section2;
    }

    @Override
    public String toString() {
        return "InstructorConflict";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstructorConflict that = (InstructorConflict) o;
        return Objects.equals(section1, that.section1) && Objects.equals(section2, that.section2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(section1, section2);
    }
}
