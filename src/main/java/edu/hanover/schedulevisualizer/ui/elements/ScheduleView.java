package edu.hanover.schedulevisualizer.ui.elements;

import edu.hanover.schedulevisualizer.ui.controller.ScheduleViewController;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ScheduleView extends HBox {

    TextField scheduleName = new TextField("");
    Button newSchedule = new Button("New Schedule");
    Button loadSchedules = new Button("Load Schedules");
    public ScheduleView() {
        super();
        this.getChildren().addAll(scheduleName, newSchedule, loadSchedules);
        ScheduleViewController controller = new ScheduleViewController(this);
    }

    public Button getLoadSchedulesButton() {
        return loadSchedules;
    }

    public Button getNewScheduleButton() {
        return newSchedule;
    }

    public TextField getScheduleNameTextField() {
        return scheduleName;
    }
}
