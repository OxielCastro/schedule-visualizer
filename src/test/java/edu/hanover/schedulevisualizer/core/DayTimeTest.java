package edu.hanover.schedulevisualizer.core;

import edu.hanover.schedulevisualizer.core.entity.DayTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DayTimeTest {

    @Test
    public void canMakeDayTimeFromHoursAndMinutes() {
        createDayTimeAndCheckFields(2, 15);
        createDayTimeAndCheckFields(3, 20);
    }

    @Test
    public void DayTimesWithSameHoursMinutesAreEqual() {
        DayTime dayTime1 = new DayTime(4, 15);
        DayTime dayTime2 = new DayTime(4, 15);
        DayTime dayTime3 = new DayTime(5,15);
        DayTime dayTime4 = new DayTime(4,25);
        assertEquals(dayTime1, dayTime2);
        assertNotEquals(dayTime1, dayTime3);
        assertNotEquals(dayTime1,dayTime4);
    }

    private void assertProperOrder(DayTime earlierTime, DayTime laterTime) {
        assertTrue(earlierTime.isEarlier(laterTime));
        assertFalse(laterTime.isEarlier(earlierTime));
    }

    @Test
    public void daytimeIsEarlierWorksCorrectly() {
        assertProperOrder(new DayTime(2, 15),
                new DayTime(6, 15));
        assertProperOrder(new DayTime(6, 45),
                new DayTime(7, 15));
        assertProperOrder(new DayTime(2, 15),
                new DayTime(2, 45));
    }

    @Test
    public void daytimeToStringWorksForMorningDayTimes() {
        assertDayTimePrintAs(9, 30, "9:30am");
        assertDayTimePrintAs(10,14,"10:14am");
        assertDayTimePrintAs(0,4,"0:04am");
    }

    @Test
    public void daytimeToStringWorksForAfternoonDayTimes() {
        assertDayTimePrintAs(21, 30, "9:30pm");
        assertDayTimePrintAs(22,14,"10:14pm");
        assertDayTimePrintAs(12,4,"12:04pm");
    }

    private void assertDayTimePrintAs(int hours, int minutes, String expected) {
        assertEquals (expected, new DayTime(hours, minutes).toString());
    }

    private void createDayTimeAndCheckFields(int hours, int minutes) {
        DayTime dayTime = new DayTime(hours, minutes);
        assertEquals(hours, dayTime.hours);
        assertEquals(minutes, dayTime.minutes);
    }
}