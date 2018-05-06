package com.platformer;

import com.platformer.guiComponents.PlatformerGUI;

import java.awt.*;

/**
 * Created by Owner on 7/11/2017.
 */
public class PlatformerMain {

    public static void main(String[] args) {
        new PlatformerGUI();
        new DeviceManager(); // blocking operation, attempts first connection
        CreateSystemTrayIcon();
    }

    private static void CreateSystemTrayIcon() {
        if (SystemTray.isSupported()) {
            Image trayImage = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("PlatformerLogoMerged.png"));
            TrayIcon trayIcon = new TrayIcon(trayImage, "Platformer");
            trayIcon.setImageAutoSize(true);

            PopupMenu popupMenu = new PopupMenu("Popup");

            MenuItem quitMenuItem = new MenuItem("Quit");
            quitMenuItem.addActionListener(e -> PerformShutdown());

            popupMenu.add(quitMenuItem);

            trayIcon.setPopupMenu(popupMenu);

            try {
                SystemTray.getSystemTray().add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }

    public static void PerformShutdown() {
        DeviceManager.CloseConnection();
        System.exit(0);
    }
}
