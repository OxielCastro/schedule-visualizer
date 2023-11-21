package edu.hanover.schedulevisualizer.ui.elements;

import edu.hanover.schedulevisualizer.ui.controller.MainController;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class MainView extends HBox {
    public MainView() {
        super();
        TimeSlotGrid timeSlotGrid = new TimeSlotGrid();
        Button sidepanelButton = new Button("<<<");
        SidePanel sidePanel = new SidePanel();
        Button newScheduleButton = new Button("+ New Schedule");
        this.getChildren().addAll(
                timeSlotGrid, sidepanelButton, sidePanel, newScheduleButton
                                 );
        MainController controller = new MainController(sidePanel, sidepanelButton, newScheduleButton);
    }
}
