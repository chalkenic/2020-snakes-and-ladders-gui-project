package com.cm6123.snl.GUI.Panels;

import javax.swing.*;
import java.awt.*;
/**
 * All panels using a side panel must have method that sets panel size.
 */
public abstract class SidePanel extends JPanel {
    /**
     * Dictates sidepanel size (if necessary).
     * @param width - horizontal size of panel.
     * @param height - vertical size of panel.
     */
    public abstract void setPanelSize(Integer width, Integer height);
}


