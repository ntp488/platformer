package com.platformer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by Owner on 7/12/2017.
 */
public class PlatformerInformationPanel extends JPanel {
    private PlatformerGUI window;
    private Color fontColor = Color.decode("#0a9900");

    public PlatformerInformationPanel(PlatformerGUI parentWindow) {
        window = parentWindow;

        Dimension dimensions = new Dimension(
                window.GetWindowDimensions().width,
                window.GetWindowDimensions().height - window.GetControlPanel().getHeight());
        setPreferredSize(dimensions);

        setBackground(Color.DARK_GRAY);
        Border etchedBorder = BorderFactory.createEtchedBorder();
        TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, "Disconnected");
        titledBorder.setTitleColor(fontColor);
        setBorder(titledBorder);
    }
}
