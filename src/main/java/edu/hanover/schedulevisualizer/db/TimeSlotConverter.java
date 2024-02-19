package edu.hanover.schedulevisualizer.db;

import edu.hanover.schedulevisualizer.core.entity.TimeSlot;
import edu.hanover.schedulevisualizer.core.simpleEntity.SimpleEntityFactory;
import jakarta.persistence.AttributeConverter;

public class TimeSlotConverter implements AttributeConverter<TimeSlot, String> {

    private final SimpleEntityFactory ef = new SimpleEntityFactory();

    public String convertToDatabaseColumn(TimeSlot timeSlot) {
        return timeSlot.getAbbrId();
    }

    public TimeSlot convertToEntityAttribute(String dbData) {
        return ef.makeTimeSlotFromAbbrId(dbData);
    }
}
