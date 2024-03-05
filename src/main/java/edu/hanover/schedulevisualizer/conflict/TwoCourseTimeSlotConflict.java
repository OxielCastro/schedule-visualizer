package edu.hanover.schedulevisualizer.conflict;

import edu.hanover.schedulevisualizer.core.entity.Section;

import java.util.Objects;

public class TwoCourseTimeSlotConflict implements Conflict {
    private final Section s1;
    private final Section s2;

    public TwoCourseTimeSlotConflict(Section s1, Section s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public String toString() {
        return "TwoCourseConflict";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwoCourseTimeSlotConflict that = (TwoCourseTimeSlotConflict) o;
        return Objects.equals(s1, that.s1) && Objects.equals(s2, that.s2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(s1, s2);
    }
}
