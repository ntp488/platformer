package com.platformer.guiComponents;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Owner on 7/11/2017.
 */
public class PlatformerGUI extends JFrame {
    private PlatformerControlPanel controlPanel;
    private PlatformerInformationPanel informationPanel;
    private Dimension windowDimensions = new Dimension(250, 150);

    public PlatformerGUI() {
        this.setTitle("Platformer");
        this.setUndecorated(true);
        this.setPreferredSize(windowDimensions);
        this.setMinimumSize(windowDimensions);

        BoxLayout layout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        this.getContentPane().setLayout(layout);

        InitializeComponents();
        AddComponents();

        this.setVisible(true);
    }

    private void InitializeComponents(){
        controlPanel = new PlatformerControlPanel(this);
        informationPanel = new PlatformerInformationPanel(this);
    }

    private void AddComponents() {
        this.add(controlPanel);
        this.add(informationPanel);
    }

    public Dimension GetWindowDimensions(){
        return windowDimensions;
    }

    public PlatformerControlPanel GetControlPanel() {
        return controlPanel;
    }
}
