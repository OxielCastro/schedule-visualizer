package edu.hanover.schedulevisualizer.ui.elements;

import edu.hanover.schedulevisualizer.core.Section;
import edu.hanover.schedulevisualizer.ui.draganddrop.DragAndDropController;
import edu.hanover.schedulevisualizer.ui.draganddrop.DropTarget;
import javafx.scene.Node;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static edu.hanover.schedulevisualizer.ui.elements.DayPattern.MWF;


public class UITimeSlot extends VBox implements DropTarget {

    private DayPattern dayPattern;
    private final Border defaultBorder = Border.stroke(Color.BLACK);
    private String timeslotId;

    public UITimeSlot() {
        super();
        this.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-padding: 5");
        DragAndDropController.getInstance().setupDropTarget(this);
    }

    static UITimeSlot forDayPattern(DayPattern dayPattern, String slotID, String timeslotId) {
        UITimeSlot uiTimeSlot = new UITimeSlot();
        uiTimeSlot.setDayPattern(dayPattern);
        uiTimeSlot.timeslotId = timeslotId;
        uiTimeSlot.setId(slotID);
        return uiTimeSlot;
    }

    public DayPattern getDayPattern() {
        return dayPattern;
    }

    public void setDayPattern(DayPattern dayPattern) {
        this.dayPattern = dayPattern;
        setPrefHeight(dayPattern == MWF ? 100 : 150);
    }

    void addCourse(Section section) {
        getChildren().add(SectionEntry.forCourse(section));
    }

    public void signalValidDropTarget() {
        setBorder(Border.stroke(Color.BLUEVIOLET));
    }

    public String getTimeslotId() {
        return timeslotId;
    }

    public void clearValidDropTarget() {
        setBorder(defaultBorder);
    }

    public Node getNode() {
        return this;
    }

    public void clear() {
        getChildren().clear();
    }
}
