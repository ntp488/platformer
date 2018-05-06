package com.platformer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Owner on 7/11/2017.
 */
public class PlatformerControlPanel extends JMenuBar{
    public JMenu optionMenu, refreshButton, exitButton;
    public PlatformerGUI window;
    private JLabel logoImage;
    private BoxLayout layout;
    private WindowFunctionsListener windowFunctionsListener;
    private WindowDragAndResizeListener dragListener;
    private Font panelFont = new Font("regFont", Font.BOLD, 15);
    private Color fontColor = Color.decode("#0a9900");

    public PlatformerControlPanel(PlatformerGUI parentWindow) {
        window = parentWindow;
        InitializeComponents();

        setPreferredSize(new Dimension(window.getWidth(), 30));
        setBackground(Color.DARK_GRAY);
        setBorder(BorderFactory.createEtchedBorder());

        layout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(layout);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("PlatformerLogoMerged.png"));
        Image newimg = icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        logoImage = new JLabel(icon);
        logoImage.setAlignmentY(.5f);

        AddListeners();

        AddComponents();
    }

    private void InitializeComponents() {
        optionMenu = new JMenu("Platformer" + '\u25B4');
        optionMenu.setFont(panelFont);
        optionMenu.setForeground(fontColor);

        refreshButton = new JMenu();
        SetMenuIconWithScale(refreshButton, "refreshIcon2.png", 30,20);
        DecorateMenu(refreshButton);

        exitButton = new JMenu();
        //remember to add link to site for icon https://icons8.com/
        SetMenuIcon(exitButton, "closeIcon3.png");
        DecorateMenu(exitButton);
    }

    private void AddComponents() {
        add(logoImage);
        add(optionMenu);
        add(Box.createHorizontalGlue());
        add(refreshButton);
        add(exitButton);
    }

    public PlatformerGUI getMainWindow() {
        return window;
    }

    private void SetMenuIcon(JMenu menu, String filename) {
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(filename));
        Image newimg = icon.getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        menu.setIcon(icon);
    }

    private void SetMenuIconWithScale(JMenu menu, String filename, int width, int height) {
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(filename));
        Image newimg = icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        menu.setIcon(icon);
    }

    private void DecorateMenu(JMenu menu) {
        menu.setForeground(Color.white);
        menu.setBackground(Color.DARK_GRAY);
    }

    private void AddListeners() {
        windowFunctionsListener = new WindowFunctionsListener(this);
        dragListener = new WindowDragAndResizeListener(this);

        refreshButton.addMouseListener(windowFunctionsListener);
        exitButton.addMouseListener(windowFunctionsListener);
        addMouseListener(dragListener);
        addMouseMotionListener(dragListener);
    }

}
