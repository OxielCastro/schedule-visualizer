package edu.hanover.schedulevisualizer.Conflicts;

import edu.hanover.schedulevisualizer.core.Instructor;
import edu.hanover.schedulevisualizer.core.Section;

public class InstructorFilledTimeSlot implements Conflict {
    Instructor instructor;

    public boolean IsSameInstructor(Section section1, Section section2) {
        if (section1.getInstructorList().isEmpty() || section2.getInstructorList().isEmpty()) {
            return false;
        }
        if(section1.getInstructorList().equals(section2.getInstructorList())) {
            return section1.getTimeslot().overlaps(section2.getTimeslot());
        }
        return false;
    }

}
