package edu.hanover.schedulevisualizer.ui.elements;

import edu.hanover.schedulevisualizer.core.Section;
import edu.hanover.schedulevisualizer.ui.draganddrop.DragAndDropController;
import edu.hanover.schedulevisualizer.ui.draganddrop.DragSource;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class SectionEntry extends Label implements DragSource {
    private final String style = "-fx-pref-width: 90; -fx-max-width: 90; -fx-min-width: 90;" +
            "-fx-pref-height: 25; -fx-min-height: 25; -fx-max-height: 25;" +
            "-fx-background-radius: 25; ";
    private Color color;

    private long courseId;

    private SectionEntry() {
        super();
        this.setColor(Color.AQUA);
        this.setAlignment(Pos.CENTER);
    }

    public String getDraggedContent() {
        // TODO: Want to return both text and integer
        return String.valueOf(this.courseId);
    }

    public void indicateDragEnded() {
        this.setColor(Color.AQUA);
    }

    public void indicateDragStarted() {
        this.setColor(Color.AZURE);
    }

    public SectionEntry getNode() {
        return this;
    }

    static SectionEntry forCourse(Section section) {
        SectionEntry entry = new SectionEntry();
        entry.setText(section.getCourseCode());
        entry.setColor(Color.AQUA);
        entry.setCourseId(section.getCourseId());
        return entry;
    }

    private void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public void setColor(Color color) {
        this.color = color;
        this.setStyle(style + "-fx-background-color: \"" + color + "\"");
    }

}
