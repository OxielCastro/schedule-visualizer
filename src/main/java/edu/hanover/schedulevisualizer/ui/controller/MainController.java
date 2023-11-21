package edu.hanover.schedulevisualizer.ui.controller;

import edu.hanover.schedulevisualizer.core.Context;
import edu.hanover.schedulevisualizer.ui.elements.SidePanel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class MainController {
    private final Button newScheduleButton;
    private SidePanel sidePanel;
    private Button sidePanelButton;

    public MainController(SidePanel sidePanel, Button sidePanelButton, Button newScheduleButton) {
        this.sidePanel = sidePanel;
        this.sidePanelButton = sidePanelButton;
        this.newScheduleButton = newScheduleButton;
        setPanelVisible(false);
        sidePanelButton.setOnAction(this::onToggleSidePanel);
        newScheduleButton.setOnAction(this::createNewEmptySchedule);
    }
    private boolean panelVisible = false;

    public void onToggleSidePanel(ActionEvent event) {
        setPanelVisible(!getPanelVisible());
    }

    private void createNewEmptySchedule(ActionEvent event) {
        Context context = Context.getInstance();
        context.createNewEmptySchedule();
        System.out.println("Made New Schedule");
    }

    boolean getPanelVisible() {
        return panelVisible;
    }

    void setPanelVisible(boolean panelVisible) {
        this.panelVisible = panelVisible;
        sidePanel.setVisible(panelVisible);
        sidePanel.setManaged(panelVisible);
        sidePanelButton.setText(panelVisible ? "<<<" : ">>>");
    }
}
