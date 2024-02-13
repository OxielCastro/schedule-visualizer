package edu.hanover.schedulevisualizer.conflict;

import edu.hanover.schedulevisualizer.core.Section;

import java.util.Objects;

/**
 * Represents a conflict between two course sections in a schedule.
 * This class implements the Conflict interface and provides methods
 * to check for equality, generate a hash code, and create a string
 * representation of the conflict.
 */
public class TwoCourseConflict implements Conflict {

    /** The first course section involved in the conflict. */
    Section section1;

    /** The second course section involved in the conflict. */
    Section section2;

    /**
     * Constructs a TwoCourseConflict object with the specified course sections.
     *
     * @param section1 The first course section involved in the conflict.
     * @param section2 The second course section involved in the conflict.
     */
    public TwoCourseConflict(final Section section1, final Section section2) {
        this.section1 = section1;
        this.section2 = section2;
    }

    /**
     * Returns a string representation of the TwoCourseConflict object.
     *
     * @return The string representation of the TwoCourseConflict object.
     */
    @Override
    public String toString() {
        return "TwoCourseConflict";
    }

    /**
     * Compares this TwoCourseConflict object with another object for equality.
     *
     * @param o The object to compare with.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    @SuppressWarnings("PMD.ShortVariable")
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TwoCourseConflict that = (TwoCourseConflict) o;
        return Objects.equals(section1, that.section1) && Objects.equals(section2, that.section2);
    }

    /**
     * Generates a hash code for the TwoCourseConflict object based on its content.
     *
     * @return The hash code for the TwoCourseConflict object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(section1, section2);
    }
}
