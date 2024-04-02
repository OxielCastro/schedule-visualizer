package edu.hanover.schedulevisualizer.ui.controller;

import edu.hanover.schedulevisualizer.core.Context;
import edu.hanover.schedulevisualizer.ui.elements.ScheduleView;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ScheduleViewController {
    TextField scheduleName;
    Button newSchedule;
    Button loadSchedules;

    public ScheduleViewController(ScheduleView scheduleView) {
        this.scheduleName = scheduleView.getScheduleNameTextField();
        this.loadSchedules = scheduleView.getLoadSchedulesButton();
        this.newSchedule = scheduleView.getNewScheduleButton();

        newSchedule.setOnAction(this::createNewEmptySchedule);
        loadSchedules.setOnAction(this::loadSchedulesList);
        scheduleName.setOnAction(this::changeScheduleName);
    }

    private void changeScheduleName(ActionEvent actionEvent) {

    }

    private void loadSchedulesList(ActionEvent actionEvent) {

    }

    private void createNewEmptySchedule(ActionEvent actionEvent) {
        final Context context = Context.getInstance();
        context.createNewEmptySchedule();
    }


}
