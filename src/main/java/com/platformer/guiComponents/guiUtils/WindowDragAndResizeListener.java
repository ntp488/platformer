package com.platformer.guiComponents.guiUtils;

import com.platformer.guiComponents.PlatformerControlPanel;
import com.platformer.guiComponents.PlatformerGUI;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class WindowDragAndResizeListener implements MouseInputListener{
    private Point offset;
    public WindowDragAndResizeListener(PlatformerControlPanel panel) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        offset = new Point(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        PlatformerControlPanel menuBar = (PlatformerControlPanel) e.getComponent();
        PlatformerGUI window = menuBar.getMainWindow();
        window.setLocation(e.getXOnScreen() - offset.x, e.getYOnScreen() - offset.y);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
