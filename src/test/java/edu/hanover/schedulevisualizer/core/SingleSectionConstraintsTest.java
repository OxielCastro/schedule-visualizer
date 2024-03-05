package edu.hanover.schedulevisualizer.core;

import edu.hanover.schedulevisualizer.conflict.SingleSectionConstraints;
import edu.hanover.schedulevisualizer.core.ContextAwareTest;
import edu.hanover.schedulevisualizer.core.entity.HCTimeSlot;
import edu.hanover.schedulevisualizer.core.entity.Section;
import edu.hanover.schedulevisualizer.core.entity.TimeSlot;
import edu.hanover.schedulevisualizer.core.entity.Weekday;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingleSectionConstraintsTest extends ContextAwareTest {
    @Test
    public void testAddSection() {
        TimeSlot timeSlot1 = new HCTimeSlot((Weekday.MWF()), 1);
        Section section1 = ef.makeSection(ef.makeCourse("CS", "220", "Fundamentals of Computer Science"), ef.makeHCTimeSlot(Weekday.MWF(), 1));

        SingleSectionConstraints constraints = new SingleSectionConstraints(section1);
        assertFalse(constraints.addSection(section1));


        Section section2 = ef.makeSection(ef.makeCourse("CS", "220", "Fundamentals of Computer Science"), ef.makeHCTimeSlot(Weekday.MWF(), 1));
        assertTrue(constraints.addSection(section2));
        assertFalse(constraints.addSection(section2));
        assertEquals(section2, constraints.getSection());
    }

}