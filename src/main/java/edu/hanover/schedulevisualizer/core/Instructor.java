package edu.hanover.schedulevisualizer.core;

import java.util.Arrays;

public class Instructor implements Comparable<Instructor> {
    private final String first;
    private final String last;
    private final String Id;

    Instructor(String first, String last, String Id) {
        this.first = first;
        this.last = last;
        this.Id = Id;
    }

     public String getFullName() {
        String fullName = first + " " + last;
        return fullName;
    }

     String getId() {
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