package edu.hanover.schedulevisualizer.core;

import edu.hanover.schedulevisualizer.core.entity.Weekday;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class WeekdayTest {

    public static final Weekday Monday = Weekday.Monday;
    public static final Weekday Tuesday = Weekday.Tuesday;
    public static final Weekday Wednesday = Weekday.Wednesday;
    public static final Weekday Thursday = Weekday.Thursday;
    public static final Weekday Friday = Weekday.Friday;
    @Test
    public void canBuildEachWeekday() {
        assertNotNull(Monday);
        assertNotNull(Tuesday);
        assertNotNull(Wednesday);
        assertNotNull(Thursday);
        assertNotNull(Friday);
    }

    @Test
    public void eachWeekdayHasAbbreviation() {
        assertAbbreviatesTo(Monday, "M");
        assertAbbreviatesTo(Tuesday, "T");
        assertAbbreviatesTo(Wednesday, "W");
        assertAbbreviatesTo(Thursday, "R");
        assertAbbreviatesTo(Friday, "F");
    }

    @Test
    public void canCreateStandardLists() {
        assertEquals(List.of(Monday, Wednesday, Friday), Weekday.MWF());
        assertEquals(List.of(Monday, Wednesday), Weekday.MW());
        assertEquals(List.of(Tuesday,Thursday), Weekday.TR());
    }

    private void assertAbbreviatesTo(Weekday weekday, String abbr) {
        assertEquals(abbr, weekday.toShortString());
    }

}