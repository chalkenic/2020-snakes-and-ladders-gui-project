package com.cm6123.snl.GUI.Panels.NewGame;

import com.cm6123.snl.GUI.Panels.SidePanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class NewGameSouthInnerPanel extends SidePanel {


    private JLabel defaultGameLabel;
    private JLabel boardSizeLabel;
    private JComboBox boardSizeBox;
    private GridBagConstraints gridStructure;

    public NewGameSouthInnerPanel(final String borderTitle) {

        boardSizeLabel = new JLabel("Board Size ");
        boardSizeLabel.setPreferredSize(new Dimension(400, 30));

        defaultGameLabel = new JLabel("Start a default game (2 players, set snakes/ladders, no features)");
        defaultGameLabel.setPreferredSize(new Dimension(400, 30));


        TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder(borderTitle);
        Border outerGameBarBorder = BorderFactory.createEmptyBorder(2, 10, 20, 10);
        innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);

        setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));

        boardSizeBox = new JComboBox();

        JButton testButton1 = new JButton("test1");
        JButton testButton3 = new JButton("Default Game");
//        JButton testButton4 = new JButton("Default Game");
        JButton testButton5 = new JButton("Start Game With Options");
        testButton5.setBackground(Color.PINK);
//        testButton5.setPreferredSize(new Dimension(300, 60));


        setLayout(new GridBagLayout());

        gridStructure = new GridBagConstraints();
//    gridStructure.fill = GridBagConstraints.NONE;
//        gridStructure.gridwidth = 1;

        gridStructure.weightx = 1;
        gridStructure.weighty = 0.1;

        gridStructure.gridx = 0;
        gridStructure.gridy = 0;
//    gridStructure.fill = GridBagConstraints.NONE;
//
    gridStructure.anchor = GridBagConstraints.LINE_START;
        gridStructure.insets = new Insets(0, 5, 0, 0);
        add(boardSizeLabel, gridStructure);

//        gridStructure.gridwidth = 50;


        /////////////BEGIN TEMPORARY FORMATTING CODE//////////////////
//        gridStructure.gridwidth = 1;
//        gridStructure.weightx = 6;
        gridStructure.gridx = 0;
        gridStructure.gridy = 0;

        gridStructure.anchor = GridBagConstraints.LINE_END;
//    gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(testButton1, gridStructure);

//        gridStructure.weightx = 1;
        gridStructure.gridx = 0;
        gridStructure.gridy = 2;
//    gridStructure.fill = GridBagConstraints.NONE;
        gridStructure.anchor = GridBagConstraints.LINE_START;
//    gridStructure.anchor = GridBagConstraints.LINE_END;
        gridStructure.insets = new Insets(0, 5, 0, 0);
        add(defaultGameLabel, gridStructure);

        /////////////BEGIN TEMPORARY FORMATTING CODE//////////////////
//        gridStructure.weightx = 0.1;
//    gridStructure.weightx = 0.1;
//    gridStructure.weighty = 0.2;
        gridStructure.gridx = 0;
        gridStructure.gridy = 2;

        gridStructure.anchor = GridBagConstraints.LINE_END;
//    gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(testButton3, gridStructure);
//        gridStructure.gridwidth = 1;
//
//        gridStructure.weightx = 1;
//        gridStructure.weightx = 3;

        //Code adapted from VGR Answer: Java - Resize buttons in GridBagLayout.
        //Available at: https://stackoverflow.com/questions/40491835/java-resize-buttons-in-gridbaglayout
        gridStructure.ipadx = 500;
        gridStructure.ipady = 20;


        gridStructure.gridx = 0;
        gridStructure.gridy = 3;
        gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
//        Insets westInsets = new Insets (5, 0, 5, 5);
//        Insets eastInsets = new Insets (5, 5, 5, 0);

        gridStructure.insets = new Insets(5, 5, 5, 5);

//        gridStructure.anchor = (gridStructure.gridx == 0) ? GridBagConstraints.WEST : GridBagConstraints.EAST;
//        gridStructure.fill = (gridStructure.gridx == 0) ? GridBagConstraints.BOTH : GridBagConstraints.HORIZONTAL;
//        gridStructure.insets = (gridStructure.gridx == 0) ? westInsets : eastInsets;
//        gridStructure.weightx = (gridStructure.gridx == 0) ? 0.1 : 1.0;
//        gridStructure.weighty = 1.0;

//        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(testButton5, gridStructure);

    }

    public void setPanelSize(final Integer width, final Integer height) {
        Dimension dim = getPreferredSize();
        dim.width = width;
        dim.height = height;
        setPreferredSize(dim);
    };
}
