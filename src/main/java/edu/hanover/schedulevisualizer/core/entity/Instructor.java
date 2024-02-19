package edu.hanover.schedulevisualizer.core.entity;

import java.util.Arrays;

public interface Instructor extends Comparable<Instructor> {
    static void sortInstructors(Instructor[] instructors) {
        Arrays.sort(instructors);
    }

    String getFullName();

    String getId();

    boolean hasTimeSlotOverlap(Section section, Section otherSection);

    @Override
    int compareTo(Instructor other);
}
