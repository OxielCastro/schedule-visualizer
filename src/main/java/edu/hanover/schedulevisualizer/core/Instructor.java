package edu.hanover.schedulevisualizer.core;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Instructor implements Comparable<Instructor> {
    private final String first;
    private final String last;
    private final String Id;
    private List<Instructor> instructorList;

    public Instructor(String first, String last, String Id) {
        this.first = first;
        this.last = last;
        this.Id = Id;
    }

    public String getFullName() {
        String fullName = first + " " + last;
        return fullName;
    }

    public String getId() {
        return Id;
    }

    public boolean hasTimeSlotOverlap(Section section, Section otherSection) {
        TimeSlot timeSlot1 = section.getTimeslot();
        TimeSlot timeSlot2 = otherSection.getTimeslot();

        if (timeSlot1 != null && timeSlot2 != null && timeSlot1.equals(timeSlot2)) {
            if (section.getInstructorList().contains(this) && otherSection.getInstructorList().contains(this)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Instructor other) {
        int compareLast = this.last.compareTo(other.last);
        if (compareLast != 0) {
            return compareLast;
        } else {
            return this.first.compareTo(other.first);
        }
    }

    static void sortInstructors(Instructor[] instructors) {
        Arrays.sort(instructors);
    }
}