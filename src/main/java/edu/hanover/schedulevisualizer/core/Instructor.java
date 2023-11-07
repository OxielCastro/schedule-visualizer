package edu.hanover.schedulevisualizer.core;

import java.util.ArrayList;
import java.util.List;

public class Instructor {
    private final String first;
    private final String last;
    private final String Id;

    private List<Section> sections = new ArrayList<>();

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
        // Check if both sections have the same time slot
        TimeSlot timeSlot1 = section.getTimeslot();
        TimeSlot timeSlot2 = otherSection.getTimeslot();

        if (timeSlot1 != null && timeSlot2 != null && timeSlot1.equals(timeSlot2)) {
            // Check if the sections have different instructors
            if (section.getInstructorList().contains(this) && otherSection.getInstructorList().contains(this)) {
                return true; // Both sections have the same time slot but different instructors
            }
        }
        return false; // Sections do not have the same time slot or have the same instructor
    }
}
