package edu.hanover.schedulevisualizer.conflict;

import edu.hanover.schedulevisualizer.core.entity.Section;

import java.util.Objects;

/**
 * Represents a conflict between two course sections based on instructor availability.
 * Two sections are considered to have an instructor  conflict if they are taught by the same instructor in the same time.
 */
public class InstructorConflict implements Conflict {
    Section section1;
    Section section2;

    /**
     * Constructs an InstructorConflict with the given course sections.
     * @param section1 the first course section
     * @param section2 the second course section
     */
    public InstructorConflict(final Section section1, final Section section2) {
        this.section1 = section1;
        this.section2 = section2;
    }

    /**
     * @return a string representation of the InstructorConflict
     */
    @Override
    public String toString() {
        return "InstructorConflict";
    }


    /**
     * Checks if this InstructorConflict object is equal to another object.
     * Two InstructorConflict objects are considered equal if they have the same course sections.
     * @param o the object to compare with
     * @return {@code true} if the objects are equal, {@code false} otherwise
     */
    @Override
    @SuppressWarnings("PMD.ShortVariable")
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final InstructorConflict that = (InstructorConflict) o;
        return Objects.equals(section1, that.section1) && Objects.equals(section2, that.section2);
    }


    /**
     * @return the hash code of this InstructorConflict object
     */
    @Override
    public int hashCode() {
        return Objects.hash(section1, section2);
    }
}
