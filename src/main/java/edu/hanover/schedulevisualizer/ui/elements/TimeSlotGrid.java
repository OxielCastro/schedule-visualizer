package edu.hanover.schedulevisualizer.ui.elements;

import edu.hanover.schedulevisualizer.core.Section;
import edu.hanover.schedulevisualizer.core.Weekday;
import edu.hanover.schedulevisualizer.observable.MyObserver;
import edu.hanover.schedulevisualizer.ui.controller.TimeSlotGridController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeSlotGrid extends HBox implements MyObserver<List<Section>> {
    Map< Weekday, DayColumn> dayColumns = new HashMap<>();

    public TimeSlotGrid() {
        super();
        addDayColumns();
        new TimeSlotGridController(this);
    }

    private void addDayColumns() {
        for (Weekday weekday : Weekday.values()) {
            DayColumn dayColumn = DayColumn.forWeekday(weekday);
            getChildren().add(dayColumn);
            dayColumns.put(weekday,dayColumn);
        }
    }

    private void displayData(List<Section> data) {
        dayColumns.values().forEach(DayColumn::clear);
        data.forEach(this::displayCourse);
    }

    private void displayCourse(Section section) {
        for (Weekday weekday : section.getWeekdays()) {
            dayColumns.get(weekday).addCourse(section);
        }
    }

    public void update(List<Section> sections) {
        Platform.runLater(() -> displayData(sections));
    }
}
