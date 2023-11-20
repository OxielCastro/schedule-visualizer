package edu.hanover.schedulevisualizer.ui.elements;

import edu.hanover.schedulevisualizer.core.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DayColumn extends VBox {

    Map<Integer, UITimeSlot> timeSlots = new HashMap<>();

    private Weekday day;
    private Label label;

    public DayColumn() {
        super();

        this.setStyle("-fx-pref-width: 100; -fx-max-width: 100; -fx-min-width: 100;");
        label = new Label();
        label.setStyle("-fx-pref-width: 100; -fx-max-width: 100; -fx-min-width: 100;" +
                               "-fx-pref-height: 30; -fx-max-height: 30; -fx-min-height: 30;" +
                               "-fx-border-width: 1; -fx-border-color: black; -fx-background-color: #90ddff");
        label.setAlignment(Pos.CENTER);
        getChildren().add(label);
    }

    static DayColumn forWeekday(Weekday weekday) {
        DayColumn dayColumn = new DayColumn();
        dayColumn.setDay(weekday);
        dayColumn.setId("column" + weekday);
        return dayColumn;
    }

    void addCourse(Section section) {
        section.getTimeslot().
                ifAssignedSlotNumberDo(
                        (Integer slotnum) -> timeSlots.get(slotnum).addCourse(section));
    }

    public Weekday getDay() {
        return day;
    }

    public void setDay(Weekday day) {
        this.day = day;
        label.setText(day.name());
        addSlots(DayPattern.forDay(day));
    }

    private void addSlots(DayPattern dayPattern) {
        for (int slotNum = 1; slotNum <= dayPattern.numSlots; slotNum++) {
            int offsetTimeslot = slotNum + dayPattern.getOffset();
            // TODO: Not right way, try to use drop executor instead
            TimeSlot timeslot = Context.getInstance().makeHCTimeSlot(List.of(day), offsetTimeslot);
            UITimeSlot uiTimeSlot = UITimeSlot.forDayPattern(dayPattern, makeSlotID(slotNum), timeslot.getId());
            getChildren().add(uiTimeSlot);
            timeSlots.put(offsetTimeslot, uiTimeSlot);

        }
    }

    private String makeSlotID(int slotNum) {
        return "slot" + slotNum + getId();
    }

}
