
package edu.hanover.schedulevisualizer.core;

import java.util.List;
import java.util.function.Consumer;


class UnassignedTimeSlot implements TimeSlot {
    private final static UnassignedTimeSlot instance = new UnassignedTimeSlot();

    UnassignedTimeSlot(){}

    public static UnassignedTimeSlot getInstance() {
        return instance;
    }

    public String getId() {
        return "-";
    }

    public List<Weekday> getWeekdayList() {
        return List.of();
    }

    public boolean overlaps(final TimeSlot otherSlot) {
        return false;
    }

    public void ifAssignedSlotNumberDo(final Consumer<Integer> f) {
        // Do nothing if timeslot is not assigned number
    }


    public void ifUnassignedSlotDo(final Runnable runnable) {
        runnable.run();
    }
}
