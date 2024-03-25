package edu.hanover.schedulevisualizer.ui.controller;

import edu.hanover.schedulevisualizer.core.Context;
import edu.hanover.schedulevisualizer.ui.elements.SidePanel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainController {
    private final Button newScheduleButton;
    private Button addNewCourseButton;
    private SidePanel sidePanel;
    private Button sidePanelButton;
    private TextField prefixTextFeld;
    private TextField courseNumTextField;
    private TextField courseDescriptionTextField;

    public MainController(final SidePanel sidePanel, final Button sidePanelButton, final Button newScheduleButton, final Button addNewCourseButton, TextField prefixTextFeld, TextField courseNumTextField, TextField courseDescriptionTextField) {
        this.sidePanel = sidePanel;
        this.sidePanelButton = sidePanelButton;
        this.newScheduleButton = newScheduleButton;
        this.addNewCourseButton = addNewCourseButton;
        this.prefixTextFeld = prefixTextFeld;
        this.courseNumTextField = courseNumTextField;
        this.courseDescriptionTextField = courseDescriptionTextField;
        setPanelVisible(false);
        sidePanelButton.setOnAction(this::onToggleSidePanel);
        newScheduleButton.setOnAction(this::createNewEmptySchedule);
        addNewCourseButton.setOnAction(this::createNewCourse);
    }

    private void createNewCourse(final ActionEvent event) {
        final Context context = Context.getInstance();
        String prefix = prefixTextFeld.getText();
        String courseNum = courseNumTextField.getText();
        String description = courseDescriptionTextField.getText();
        context.createNewCourse(prefix, courseNum, description);
    }

    private boolean panelVisible = false;

    public void onToggleSidePanel(final ActionEvent event) {
        setPanelVisible(!getPanelVisible());
    }

    private void createNewEmptySchedule(final ActionEvent event) {
        final Context context = Context.getInstance();
        context.createNewEmptySchedule();
    }

    boolean getPanelVisible() {
        return panelVisible;
    }

    void setPanelVisible(final boolean panelVisible) {
        this.panelVisible = panelVisible;
        sidePanel.setVisible(panelVisible);
        sidePanel.setManaged(panelVisible);
        sidePanelButton.setText(panelVisible ? "<<<" : ">>>");
    }
}
