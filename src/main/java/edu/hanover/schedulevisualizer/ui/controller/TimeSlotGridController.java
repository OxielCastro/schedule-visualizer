package edu.hanover.schedulevisualizer.ui.controller;

import edu.hanover.schedulevisualizer.core.Context;
import edu.hanover.schedulevisualizer.ui.elements.TimeSlotGrid;

public class TimeSlotGridController {
    private TimeSlotGrid grid;

    Context context;

    public TimeSlotGridController(final TimeSlotGrid grid) {
        this.grid = grid;
        this.context = Context.getInstance();
        context.addObserver(grid);
    }
}