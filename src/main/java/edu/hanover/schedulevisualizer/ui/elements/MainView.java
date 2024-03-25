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
        TextField prefixTextField = new TextField("Prefix");
        TextField courseNumTextField =  new TextField("Course Number");
        TextField courseDescriptionTextField =  new TextField("Course Description");
        prefixTextField.setOnMouseClicked(e -> prefixTextField.setText(""));
        courseNumTextField.setOnMouseClicked(e -> courseNumTextField.setText(""));
        courseDescriptionTextField.setOnMouseClicked(e -> courseDescriptionTextField.setText(""));

        textFieldContainer.getChildren().addAll(prefixTextField, courseNumTextField, courseDescriptionTextField);

        this.getChildren().addAll(
                timeSlotGrid, sidepanelButton, sidePanel, newScheduleButton, addNewCourseButton, textFieldContainer
                                 );

        MainController controller = new MainController(
                                            sidePanel, sidepanelButton, newScheduleButton, addNewCourseButton,
                                                prefixTextField, courseNumTextField, courseDescriptionTextField );
    }
}
