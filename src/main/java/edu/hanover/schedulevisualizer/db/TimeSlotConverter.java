package edu.hanover.schedulevisualizer.db;

import edu.hanover.schedulevisualizer.core.HCTimeSlot;
import edu.hanover.schedulevisualizer.core.TimeSlot;
import edu.hanover.schedulevisualizer.core.UnassignedTimeSlot;
import edu.hanover.schedulevisualizer.core.Weekday;
import jakarta.persistence.AttributeConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TimeSlotConverter implements AttributeConverter<TimeSlot, String> {

    public String convertToDatabaseColumn(TimeSlot timeSlot) {
        if (timeSlot instanceof HCTimeSlot) {
            HCTimeSlot hcSlot = (HCTimeSlot) timeSlot;
            String weekdayAbbr = hcSlot.getWeekdayList()
                    .stream()
                    .map(Weekday::toShortString)
                    .collect(Collectors.joining());
            return weekdayAbbr + " " + hcSlot.slotnum;
        }
        return "-";
    }

    public TimeSlot convertToEntityAttribute(String dbData) {
        if (dbData.equals("-")) return UnassignedTimeSlot.getInstance();
        String[] matches = dbData.split("\\s+");
        int slotnum = Integer.parseInt(matches[1]);
        String[] weekdays = matches[0].split("");
        List<Weekday> weekdayList = new ArrayList<>();
        for (String wkd : weekdays) {
            switch (wkd) {
                case "M" -> weekdayList.add(Weekday.Monday);
                case "T" -> weekdayList.add(Weekday.Tuesday);
                case "W" -> weekdayList.add(Weekday.Wednesday);
                case "R" -> weekdayList.add(Weekday.Thursday);
                case "F" -> weekdayList.add(Weekday.Friday);
            }
        }
        return new HCTimeSlot(weekdayList, slotnum);
    }
}
