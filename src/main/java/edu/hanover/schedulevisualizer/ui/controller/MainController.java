package edu.hanover.schedulevisualizer.ui.controller;

import edu.hanover.schedulevisualizer.ui.elements.SidePanel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class MainController {
    private SidePanel sidePanel;
    private Button sidePanelButton;

    public MainController(SidePanel sidePanel, Button sidePanelButton) {
        this.sidePanel = sidePanel;
        this.sidePanelButton = sidePanelButton;
        setPanelVisible(false);
        sidePanelButton.setOnAction(this::onToggleSidePanel);
    }

    private boolean panelVisible = false;

    public void onToggleSidePanel(ActionEvent event) {
        setPanelVisible(!getPanelVisible());
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
