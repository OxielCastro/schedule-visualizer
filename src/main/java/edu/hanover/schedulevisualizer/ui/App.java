package edu.hanover.schedulevisualizer.ui;

import edu.hanover.schedulevisualizer.ui.elements.MainView;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App {
    public void startApp(final Stage stage) throws IOException {
        final Scene scene = new Scene(new MainView());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}