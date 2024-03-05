package edu.hanover.schedulevisualizer.conflict;

import edu.hanover.schedulevisualizer.core.entity.TimeSlot;

public class InstructorPreferenceConstraint {
    private TimeSlot preferredTimeSlot;

    public InstructorPreferenceConstraint(TimeSlot preferredTimeSlot) {
        this.preferredTimeSlot = preferredTimeSlot;
    }

    public TimeSlot getPreferredTimeSlot() {
        return preferredTimeSlot;
    }

    public void setPreferredTimeSlot(TimeSlot preferredTimeSlot) {
        this.preferredTimeSlot = preferredTimeSlot;
    }
}
