package edu.hanover.schedulevisualizer.ui.elements;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SidePanel extends VBox{
    public SidePanel() {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "sidepanel-view.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
