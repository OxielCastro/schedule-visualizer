package edu.hanover.schedulevisualizer.db;

import edu.hanover.schedulevisualizer.core.*;
import jakarta.persistence.AttributeConverter;

public class TimeSlotConverter implements AttributeConverter<TimeSlot, String> {

    private final EntityFactory ef = new EntityFactory();

    public String convertToDatabaseColumn(TimeSlot timeSlot) {
        return timeSlot.getAbbrId();
    }

    public TimeSlot convertToEntityAttribute(String dbData) {
        return ef.makeTimeSlotFromAbbrId(dbData);
    }
}
