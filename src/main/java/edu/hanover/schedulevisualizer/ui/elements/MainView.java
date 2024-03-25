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
        Button addNewCourseButton = new Button("Add Course");
        SidePanel sidePanel = new SidePanel();
        Button newScheduleButton = new Button("New Schedule");

        VBox textFieldContainer = new VBox();
        TextField prefixTextField = new TextField("");
        TextField courseNumTextField =  new TextField("");
        TextField courseDescriptionTextField =  new TextField("");
        prefixTextField.setPromptText("Prefix");
        courseNumTextField.setPromptText("Course Number");
        courseDescriptionTextField.setPromptText("Course Description");


        textFieldContainer.getChildren().addAll(prefixTextField, courseNumTextField, courseDescriptionTextField);


        this.getChildren().addAll(
                timeSlotGrid, sidepanelButton, sidePanel, newScheduleButton, addNewCourseButton, textFieldContainer
                                 );

        MainController controller = new MainController(
                                            sidePanel, sidepanelButton, newScheduleButton, addNewCourseButton,
                                                prefixTextField, courseNumTextField, courseDescriptionTextField );
    }
}
