package edu.hanover.schedulevisualizer.db;

import edu.hanover.schedulevisualizer.core.HCTimeSlot;
import edu.hanover.schedulevisualizer.core.UnassignedTimeSlot;
import edu.hanover.schedulevisualizer.core.Weekday;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TimeSlotConverterTest {

    private TimeSlotConverter converter;

    @BeforeEach
    void setUp() {
        converter = new TimeSlotConverter();
    }

    @Test
    void convertTimeSlotToString() {
        assertThat(converter.convertToDatabaseColumn(new HCTimeSlot(Weekday.MW(), 3)),
                   equalTo("MW 3"));
        assertThat(converter.convertToDatabaseColumn(UnassignedTimeSlot.getInstance()),
                   equalTo("-"));
    }

    @Disabled
    @Test
    void convertStringToTimeslot() {
        assertThat(converter.convertToEntityAttribute("MW 3"),
                   equalTo(new HCTimeSlot(Weekday.MW(), 3)));
        assertThat(converter.convertToEntityAttribute("-"),
                   equalTo(UnassignedTimeSlot.getInstance()));
    }

}
