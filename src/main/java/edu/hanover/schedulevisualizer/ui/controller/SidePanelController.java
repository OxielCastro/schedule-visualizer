package edu.hanover.schedulevisualizer.ui.controller;

import edu.hanover.schedulevisualizer.core.Context;
import edu.hanover.schedulevisualizer.ui.elements.SidePanel;
import edu.hanover.schedulevisualizer.ui.elements.UnassignedCourseList;

public class SidePanelController {
    public SidePanel panel;
    public UnassignedCourseList unassignedCourseList;

    public SidePanelController(final SidePanel panel, final UnassignedCourseList unassignedCourseList) {
        this.panel = panel;
        this.unassignedCourseList = unassignedCourseList;
        final Context context = Context.getInstance();
        context.addObserver(unassignedCourseList);
        unassignedCourseList.setAsDropTarget();
    }
}
