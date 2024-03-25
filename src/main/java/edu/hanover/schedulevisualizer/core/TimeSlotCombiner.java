package edu.hanover.schedulevisualizer.core;

import edu.hanover.schedulevisualizer.core.entity.TimeSlot;
import edu.hanover.schedulevisualizer.core.entity.Weekday;

import java.util.List;

abstract public class TimeSlotCombiner<T> {

    T combine(TimeSlot ts1, TimeSlot ts2) {
        return ts1.computeWithSlot(
                (List<Weekday> weekdays, Integer slotnum) -> computeWithFirstAssigned(weekdays, slotnum, ts2),
                () -> computeWithFirstUnassigned(ts2));
    }


    T computeWithFirstUnassigned(TimeSlot ts2) {
        return ts2.computeWithSlot(this::combineWithFirstUnassignedSecondAssigned,
                            this::combinedWithBothUnassigned);
    }

    protected T computeWithFirstAssigned(List<Weekday> wkd1, Integer sn1, TimeSlot ts2) {
        return ts2.computeWithSlot(
                (List<Weekday> wkd2, Integer sn2) -> combineWithBothAssigned(wkd1, sn1, wkd2, sn2),
                () -> combineWithFirstAssignedSecondUnassigned(wkd1, sn1));
    }

    protected abstract T combineWithFirstAssignedSecondUnassigned(List<Weekday> wkd1, Integer sn1);

    protected abstract T combineWithBothAssigned(List<Weekday> wkd1, Integer sn1, List<Weekday> wkd2, Integer sn2) ;

    protected abstract T combineWithFirstUnassignedSecondAssigned(List<Weekday> weekdays2, Integer slotnum2);

    protected abstract T combinedWithBothUnassigned();
}
