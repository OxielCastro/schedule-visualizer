package edu.hanover.schedulevisualizer.conflict;

import edu.hanover.schedulevisualizer.core.Section;

import java.util.Objects;

public class TwoCourseConflict implements Conflict {

    Section section1;
    Section section2;

    public TwoCourseConflict(final Section section1, final Section section2) {
        this.section1 = section1;
        this.section2 = section2;
    }

    @Override
    public String toString() {
        return "TwoCourseConflict";
    }

    @Override
    @SuppressWarnings("PMD.ShortVariable")
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TwoCourseConflict that = (TwoCourseConflict) o;
        return Objects.equals(section1, that.section1) && Objects.equals(section2, that.section2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(section1, section2);
    }
}
