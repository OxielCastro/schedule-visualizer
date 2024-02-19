package edu.hanover.schedulevisualizer.ui.elements;

import edu.hanover.schedulevisualizer.core.Context;
import edu.hanover.schedulevisualizer.core.entity.Section;
import edu.hanover.schedulevisualizer.observable.MyObserver;
import edu.hanover.schedulevisualizer.ui.draganddrop.DragAndDropController;
import edu.hanover.schedulevisualizer.ui.draganddrop.DropTarget;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.List;

public class UnassignedCourseList extends VBox implements MyObserver<List<Section>>, DropTarget {

    void addCourse(Section section) {
        getChildren().add(SectionEntry.forCourse(section));
    }

    public void update(List<Section> sections) {
        Platform.runLater(() -> displayData(sections));
    }

    private void displayData(List<Section> sections) {
        getChildren().clear();
        sections.forEach(this::displayCourse);
    }

    private void displayCourse(Section section) {
        section.getTimeslot().ifUnassignedSlotDo(() -> addCourse(section));
    }

    public void setAsDropTarget() {
        DragAndDropController.getInstance().setupDropTarget(this);
    }

    public void clearValidDropTarget() {
        setBorder(Border.EMPTY);
    }

    public void signalValidDropTarget() {
        setBorder(Border.stroke(Color.BLUEVIOLET));
    }

    public String getTimeslotId() {
        return Context.getInstance().ef.makeUnassignedTimeslot().getId();
    }

    public Node getNode() {
        return this;
    }
}
