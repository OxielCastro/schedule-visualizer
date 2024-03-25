package edu.hanover.schedulevisualizer.core.entity;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Interface Timeslot gets implemented by Class {@link HCTimeSlot} (Specified timeslots to just hanover college)
 * The TimeSlot interface essentially sets the groundwork for what a basic timeslot is
 * It has a method to get the list of weekdays the class is on,
 * Has a method to tell you if it overlaps with another timeslot,
 * Tells the code what to do if the time slot is assigned or unassigned,
 * And gets the ID of the class
 */
public interface TimeSlot {
    /**
     * Gets the Weekday List of the class so if a class is Monday ,Wednesday, Friday you will know
     * @return a List of weekdays
     */
    List<Weekday> getWeekdayList();

    /**
     * Method used to determine if a timeslot will have any overlap with other timeslots
     * @param otherSlot is a Timeslot parameter that represents another class period
     * @return a boolean to show whether the timeslot overlaps with another or not
     */
    boolean overlaps(TimeSlot otherSlot);

    /**
     * Executes the function on f if the slot is assigned
     * @param f Runs a function that will use the slotnum
     */
    void ifAssignedSlotNumberDo(Consumer<Integer> f);

    /**
     * Executes the provided runnable if the slot is Unassigned
     * @param runnable function to be executed
     */
    void ifUnassignedSlotDo(Runnable runnable);

    <T> T computeWithSlot(BiFunction<List<Weekday>, Integer, T> f1, Supplier<T> f2);

    /**
     * Used to get the Weekday List and the slotnum combining them to form an ID for the class
     * @return a string version of the class id
     */
    String getId();

    /**
     * An abbreviated version of the timeslot id. This should
     * include a one-character version of the weekdays, followed
     * by the slot number, separated by a dash. E.g. MWF-2
     * @return an abbreviated string version of the class id
     */
    String getAbbrId();

}
