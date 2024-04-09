package edu.hanover.schedulevisualizer.ui.elements;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NewCourseView extends VBox {
    private TextField prefixTextField;
    private TextField courseNumTextField;
    private TextField courseDescriptionTextField;
    private Button addNewCourseButton;

    public NewCourseView() {
        super();
        addNewCourseButton = new Button("Add Course");
        prefixTextField = new TextField("");
        courseNumTextField = new TextField("");
        courseDescriptionTextField = new TextField("");
        prefixTextField.setPromptText("Prefix");
        courseNumTextField.setPromptText("Course Number");
        courseDescriptionTextField.setPromptText("Course Description");

        prefixTextField.setPrefWidth(200); // Adjust the width as needed
        courseNumTextField.setPrefWidth(200);
        courseDescriptionTextField.setPrefWidth(200);

        addNewCourseButton = new Button("Add Course");
        addNewCourseButton.setPrefWidth(200); // Set the same width as text fields

        HBox buttonContainer = new HBox(addNewCourseButton);
        buttonContainer.setSpacing(5); // Adjust spacing between button and text fields

        this.getChildren().addAll(addNewCourseButton, prefixTextField, courseNumTextField, courseDescriptionTextField);
    }

    public TextField getPrefixTextField() {
        return prefixTextField;
    }

    public TextField getCourseNumTextField() {
        return courseNumTextField;
    }

    public TextField getCourseDescriptionTextField() {
        return courseDescriptionTextField;
    }

    public Button getAddNewCourseButton() {
        return addNewCourseButton;
    }
}
