package edu.hanover.schedulevisualizer.ui.elements;

import edu.hanover.schedulevisualizer.core.entity.Weekday;

public enum DayPattern {
    MWF(6), TR(4);

    public final int numSlots;

    DayPattern(int numSlots) {
        this.numSlots = numSlots;
    }

    static DayPattern forDay(Weekday day) {
        return day.isTR() ? TR : MWF;
    }

    int getOffset() {
        return this == TR ? 6 : 0;
    }
}
