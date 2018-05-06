package com.platformer;

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
        InitializeComponents();

        setTitle("Platformer");
        setUndecorated(true);
        setPreferredSize(windowDimensions);
        setMinimumSize(windowDimensions);

        BoxLayout layout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        getContentPane().setLayout(layout);

        AddComponents();

        setVisible(true);
    }

    private void InitializeComponents(){
        controlPanel = new PlatformerControlPanel(this);
        informationPanel = new PlatformerInformationPanel(this);
    }

    private void AddComponents() {
        add(controlPanel);
        add(informationPanel);
    }

    public Dimension GetWindowDimensions(){
        return windowDimensions;
    }

    public PlatformerControlPanel GetControlPanel() {
        return controlPanel;
    }
}
