package com.cm6123.snl.GUI.Panels;

import javax.swing.*;
import java.awt.*;


public abstract class SidePanel extends JPanel {

//    private SquareFormListener formListener;

    public SidePanel() {

        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
    }

//    public abstract void setFormListener(SquareFormListener listener);

//    public abstract Integer entryValidation(NewAddition value, int... othervalues);

//    public abstract NewAddition getAdditionChoice();

//    public abstract void createSidePanel();


//    public void createMenuPanel() { }
}


