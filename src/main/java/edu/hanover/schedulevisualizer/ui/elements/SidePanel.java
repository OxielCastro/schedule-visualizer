package edu.hanover.schedulevisualizer.ui.elements;

import edu.hanover.schedulevisualizer.ui.controller.SidePanelController;
import javafx.scene.layout.VBox;

public class SidePanel extends VBox {
    private final UnassignedCourseList unassignedCourseList;

    public SidePanel() {
        super();
        this.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-padding: 20");
        unassignedCourseList = new UnassignedCourseList();
        this.getChildren().add(unassignedCourseList);
        SidePanelController controller = new SidePanelController(this, unassignedCourseList);
    }

}
