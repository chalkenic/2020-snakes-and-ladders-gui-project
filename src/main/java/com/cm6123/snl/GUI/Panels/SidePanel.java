package com.cm6123.snl.GUI.Panels;

import com.cm6123.snl.GUI.NewSquare;
import com.cm6123.snl.GUI.SquareFormListener;

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

//    public abstract Integer entryValidation(NewSquare value, int... othervalues);

//    public abstract NewSquare getSquareChoice();

//    public abstract void createSidePanel();


//    public void createMenuPanel() { }
}


