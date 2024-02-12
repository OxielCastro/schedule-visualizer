module edu.hanover.schedulevisualizer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires jakarta.persistence;
//    requires annotations;
//    requires junit;

    opens edu.hanover.schedulevisualizer to javafx.fxml, javafx.graphics;
    exports edu.hanover.schedulevisualizer;
    exports edu.hanover.schedulevisualizer.core;
    exports edu.hanover.schedulevisualizer.ui;
    opens edu.hanover.schedulevisualizer.ui to javafx.fxml, javafx.graphics;
    exports edu.hanover.schedulevisualizer.ui.elements;
    opens edu.hanover.schedulevisualizer.ui.elements to javafx.fxml, javafx.graphics;
    exports edu.hanover.schedulevisualizer.ui.controller;
    opens edu.hanover.schedulevisualizer.ui.controller to javafx.fxml, javafx.graphics;
    exports edu.hanover.schedulevisualizer.ui.draganddrop;
    opens edu.hanover.schedulevisualizer.ui.draganddrop to javafx.fxml, javafx.graphics;
}