package edu.hanover.schedulevisualizer.core;

import edu.hanover.schedulevisualizer.conflict.InstructorPreferenceConstraint;
import edu.hanover.schedulevisualizer.core.entity.HCTimeSlot;
import edu.hanover.schedulevisualizer.core.entity.TimeSlot;
import edu.hanover.schedulevisualizer.core.entity.Weekday;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InstructorPreferenceConstraintTest extends ContextAwareTest {

    @Test
    public void testPreferredTimeSlot() {
        TimeSlot timeSlot = new HCTimeSlot((Weekday.MWF()), 1);

        // Create an InstructorPreferenceConstraint object with the preferred timeSlot
        InstructorPreferenceConstraint instructor = new InstructorPreferenceConstraint(timeSlot);

        assertEquals(timeSlot, instructor.getPreferredTimeSlot());

    }
}
