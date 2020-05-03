package com.cm6123.snl.GUI.Panels;

import com.cm6123.snl.GUI.NewSquare;
import com.cm6123.snl.GUI.SquareFormListener;

import javax.swing.*;
import java.awt.*;


public class SidePanel extends JPanel {

//    private SquareFormListener formListener;

    public SidePanel() {

        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
    }

    public void setFormListener(final SquareFormListener listener) { }

    public Integer entryValidation(final NewSquare value, final int... othervalues ) {
        return null;
    }

    public NewSquare getSquareChoice() { return null; }

    public void createSidePanel() { }

    public void createMenuPanel() { }
}


