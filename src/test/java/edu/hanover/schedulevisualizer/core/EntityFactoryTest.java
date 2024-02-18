package edu.hanover.schedulevisualizer.core;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EntityFactoryTest extends ContextAwareTest {
    @Test
    void convertTimeSlotToString() {
        assertThat(ef.makeHCTimeSlot(Weekday.MW(), 3).getAbbrId(),
                   equalTo("MW-3"));
        assertThat(ef.makeUnassignedTimeslot().getAbbrId(),
                   equalTo("-"));
    }

    @Test
    void convertStringToTimeslot() {
        assertThat(ef.makeTimeSlotFromAbbrId("MW-3"),
                   equalTo(ef.makeHCTimeSlot(Weekday.MW(), 3)));
        assertThat(ef.makeTimeSlotFromAbbrId("-"),
                   equalTo(ef.makeUnassignedTimeslot()));
    }

}
