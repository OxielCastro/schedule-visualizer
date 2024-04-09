package edu.hanover.schedulevisualizer.ui.elements;

import edu.hanover.schedulevisualizer.ui.controller.MainController;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainView extends HBox {
    public MainView() {
        super();
        TimeSlotGrid timeSlotGrid = new TimeSlotGrid();
        Button sidepanelButton = new Button("<<<");
        SidePanel sidePanel = new SidePanel();

        ScheduleView scheduleView = new ScheduleView();
        VBox scheduleViewWithGrid = new VBox();
        scheduleViewWithGrid.getChildren().addAll(scheduleView, timeSlotGrid);

        NewCourseView newCourseView = new NewCourseView();

        this.getChildren().addAll(
                scheduleViewWithGrid, sidepanelButton, sidePanel, newCourseView
                                 );

        MainController controller = new MainController(
                                            sidePanel, sidepanelButton,
                newCourseView.getAddNewCourseButton(), newCourseView.getPrefixTextField(),
                newCourseView.getCourseNumTextField(), newCourseView.getCourseDescriptionTextField());
    }
}



