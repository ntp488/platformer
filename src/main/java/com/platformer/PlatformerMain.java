package com.platformer;

import java.awt.*;

/**
 * Created by Owner on 7/11/2017.
 */
public class PlatformerMain {
    private static PlatformerGUI gui;
    //private static DeviceManager deviceManager;

    public static void main(String[] args) {
        gui = new PlatformerGUI();
        //deviceManager = new DeviceManager();
        // will perform static code block to initialize values and attempt first connection
        new DeviceManager();
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
        //perform any necessary steps for shutdown here.
        DeviceManager.CloseConnection();
        System.exit(0);
    }
}
