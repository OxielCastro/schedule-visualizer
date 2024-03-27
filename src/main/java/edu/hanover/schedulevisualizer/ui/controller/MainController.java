package edu.hanover.schedulevisualizer.ui.controller;

import edu.hanover.schedulevisualizer.core.Context;
import edu.hanover.schedulevisualizer.ui.elements.SidePanel;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainController {
    private final Button newScheduleButton;
    private final Button loadSchedulesButton;
    private Button addNewCourseButton;
    private SidePanel sidePanel;
    private Button sidePanelButton;
    private TextField prefixTextField;
    private TextField courseNumTextField;
    private TextField courseDescriptionTextField;

    public MainController(final SidePanel sidePanel, final Button sidePanelButton, final Button newScheduleButton, final Button loadSchedulesButton, final Button addNewCourseButton, TextField prefixTextField, TextField courseNumTextField, TextField courseDescriptionTextField) {
        this.sidePanel = sidePanel;
        this.sidePanelButton = sidePanelButton;
        this.newScheduleButton = newScheduleButton;
        this.loadSchedulesButton = loadSchedulesButton;
        this.addNewCourseButton = addNewCourseButton;
        this.prefixTextField = prefixTextField;
        this.courseNumTextField = courseNumTextField;
        this.courseDescriptionTextField = courseDescriptionTextField;
        setPanelVisible(false);
        sidePanelButton.setOnAction(this::onToggleSidePanel);
        newScheduleButton.setOnAction(this::createNewEmptySchedule);
        loadSchedulesButton.setOnAction(this::loadSchedulesList);
        addNewCourseButton.setOnAction(this::createNewCourse);
    }

    private void createNewCourse(final ActionEvent event) {
        final Context context = Context.getInstance();
        String prefix = prefixTextField.getText();
        String courseNum = courseNumTextField.getText();
        String courseDescription = courseDescriptionTextField.getText();

        if (prefix.isEmpty() || (courseNum.isEmpty()) || courseDescription.isEmpty()) {
            // Display an error message in a popup
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields.");

            alert.showAndWait();
            return;
        }

        context.createNewCourse(prefix, courseNum, courseDescription);

        // Update prompt text of text fields
        prefixTextField.setPromptText("Prefix");
        courseNumTextField.setPromptText("Course Number");
        courseDescriptionTextField.setPromptText("Course Description");

        // Clear text fields
        prefixTextField.setText("");
        courseNumTextField.setText("");
        courseDescriptionTextField.setText("");
    }

    private boolean panelVisible = false;

    public void onToggleSidePanel(final ActionEvent event) {
        setPanelVisible(!getPanelVisible());
    }

    private void createNewEmptySchedule(final ActionEvent event) {
        final Context context = Context.getInstance();
        context.createNewEmptySchedule();
    }

    private void loadSchedulesList(ActionEvent actionEvent) {
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
