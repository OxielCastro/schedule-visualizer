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

    public void assignSection(Section section) {
        section.addInstructor(this);
    }

    public void reassignSection(Section section, Instructor newInstructor) {
        section.removeInstructor(this);
        newInstructor.assignSection(section);
    }

    public boolean hasTimeSlotOverlap() {
        List<TimeSlot> assignedTimeSlots = new ArrayList<>();

        // Get the list of time slots assigned to the instructor's sections
        for (Section section : sections) {
            TimeSlot timeSlot = section.getTimeslot();
            if (timeSlot != null && timeSlot != UnassignedTimeSlot.getInstance()) {
                assignedTimeSlots.add(timeSlot);
            }
        }

        // Check for time slot overlaps among the assigned time slots
        for (int i = 0; i < assignedTimeSlots.size(); i++) {
            for (int j = i + 1; j < assignedTimeSlots.size(); j++) {
                if (assignedTimeSlots.get(i).overlaps(assignedTimeSlots.get(j))) {
                    return true; // There is an overlap
                }
            }
        }

        return false; // No overlaps found
    }


}
