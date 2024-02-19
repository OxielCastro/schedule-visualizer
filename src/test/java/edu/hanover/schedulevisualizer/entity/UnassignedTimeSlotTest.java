package edu.hanover.schedulevisualizer.entity;

import edu.hanover.schedulevisualizer.core.entity.UnassignedTimeSlot;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UnassignedTimeSlotTest {

    @Test
    public void weekdayListUnassigned(){
        UnassignedTimeSlot unassignedSlot = UnassignedTimeSlot.getInstance();
        assertThat(unassignedSlot.getWeekdayList(), equalTo(List.of()));
    }

    @Test
    public void overlapsTestUnassigned(){
        UnassignedTimeSlot unassignedSlot = UnassignedTimeSlot.getInstance();
        assertThat(unassignedSlot.overlaps(UnassignedTimeSlot.getInstance()), equalTo(Boolean.FALSE));

    }
}
