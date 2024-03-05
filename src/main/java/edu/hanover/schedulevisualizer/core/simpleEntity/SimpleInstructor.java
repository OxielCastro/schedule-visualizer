package edu.hanover.schedulevisualizer.core.simpleEntity;

import edu.hanover.schedulevisualizer.core.entity.TimeSlot;
import edu.hanover.schedulevisualizer.core.entity.Instructor;
import edu.hanover.schedulevisualizer.core.entity.Section;

public class SimpleInstructor implements Instructor {
    private String first;
    private String last;
    private String id;

    protected SimpleInstructor() {}

    SimpleInstructor(String first, String last, String Id) {
        this.first = first;
        this.last = last;
        this.id = Id;
    }

     public String getFullName() {
        String fullName = first + " " + last;
        return fullName;
    }

    public String getId() {
        return id;
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
        SimpleInstructor si = (SimpleInstructor) other;
        int compareLast = this.last.compareTo(si.last);
        if (compareLast != 0) {
            return compareLast;
        } else {
            return this.first.compareTo(si.first);
        }
    }

}