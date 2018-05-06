package com.platformer;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class WindowFunctionsListener implements MouseListener {
    private JMenu exitButton;

    public WindowFunctionsListener(PlatformerControlPanel panel) {
        exitButton = panel.exitButton;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().equals(exitButton)) {
            PlatformerMain.PerformShutdown();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object obj = e.getComponent();
        if (obj instanceof JMenu) {
            JMenu menu = (JMenu) e.getComponent();
            menu.setSelected(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object obj = e.getComponent();
        if (obj instanceof JMenu) {
            JMenu menu = (JMenu) e.getComponent();
            menu.setSelected(false);
        }
    }
}
