package edu.hanover.schedulevisualizer.ui.controller;

import edu.hanover.schedulevisualizer.core.Context;
import edu.hanover.schedulevisualizer.ui.elements.SidePanel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class MainController {
    private final Button newScheduleButton;
    private SidePanel sidePanel;
    private Button sidePanelButton;

    public MainController(final SidePanel sidePanel, final Button sidePanelButton, final Button newScheduleButton) {
        this.sidePanel = sidePanel;
        this.sidePanelButton = sidePanelButton;
        this.newScheduleButton = newScheduleButton;
        setPanelVisible(false);
        sidePanelButton.setOnAction(this::onToggleSidePanel);
        newScheduleButton.setOnAction(this::createNewEmptySchedule);
    }
    private boolean panelVisible = false;

    public void onToggleSidePanel(final ActionEvent event) {
        setPanelVisible(!getPanelVisible());
    }

    private void createNewEmptySchedule(final ActionEvent event) {
        final Context context = Context.getInstance();
        context.createNewEmptySchedule();
    }

    boolean getPanelVisible() {
        return panelVisible;
    }

    void setPanelVisible(final boolean panelVisible) {
        this.panelVisible = panelVisible;
        sidePanel.setVisible(panelVisible);
        sidePanel.setManaged(panelVisible);
        sidePanelButton.setText(panelVisible ? "<<<" : ">>>");
    }
}
