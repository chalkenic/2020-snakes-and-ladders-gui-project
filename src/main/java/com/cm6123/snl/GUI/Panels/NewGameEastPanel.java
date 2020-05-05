package com.cm6123.snl.GUI.Panels;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class NewGameEastPanel extends SidePanel {

    private JLabel winningSquareOnlyOnLabel;
    private JLabel boostSquareOnLabel;
    private JLabel boostSquareChoiceLabel;
    private GridBagConstraints gridStructure;



    public NewGameEastPanel(final String borderTitle) {

        winningSquareOnlyOnLabel = new JLabel("Winning Square Feature: ");
        winningSquareOnlyOnLabel.setPreferredSize(new Dimension(250, 90));
        boostSquareOnLabel = new JLabel("Boost Square Feature: ");
        boostSquareOnLabel.setPreferredSize(new Dimension(250, 90));
        boostSquareChoiceLabel = new JLabel("Boost Squares: ");
        boostSquareChoiceLabel.setPreferredSize(new Dimension(250, 90));

        TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder(borderTitle);
        Border outerGameBarBorder = BorderFactory.createEmptyBorder(2, 10, 10, 10);
        innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);

        setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));

        JButton testButton1 = new JButton("test1");
        JButton testButton2 = new JButton("test1");
        JButton testButton3 = new JButton("test1");

        setLayout(new GridBagLayout());

        gridStructure = new GridBagConstraints();
//    gridStructure.fill = GridBagConstraints.NONE;

        gridStructure.weightx = 3;
        gridStructure.weighty = 1;

        gridStructure.gridx = 0;
        gridStructure.gridy = 0;
//    gridStructure.fill = GridBagConstraints.NONE;
//
//    gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(winningSquareOnlyOnLabel, gridStructure);

        /////////////BEGIN TEMPORARY FORMATTING CODE//////////////////
//
//    gridStructure.weighty = 0.2;
        gridStructure.gridx = 5;

//    gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(testButton1, gridStructure);

        gridStructure.weightx = 3;
        gridStructure.gridx = 0;
        gridStructure.gridy = 1;
//    gridStructure.fill = GridBagConstraints.NONE;
//
//    gridStructure.anchor = GridBagConstraints.LINE_END;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(boostSquareOnLabel, gridStructure);

        /////////////BEGIN TEMPORARY FORMATTING CODE//////////////////
        gridStructure.weightx = 0.1;
//    gridStructure.weightx = 0.1;
//    gridStructure.weighty = 0.2;
        gridStructure.gridx = 5;

//    gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(testButton2, gridStructure);

        gridStructure.weightx = 3;
        gridStructure.gridx = 0;
        gridStructure.gridy = 2;
//    gridStructure.fill = GridBagConstraints.NONE;
//
//    gridStructure.anchor = GridBagConstraints.LINE_END;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(boostSquareChoiceLabel, gridStructure);

        /////////////BEGIN TEMPORARY FORMATTING CODE//////////////////
        gridStructure.weightx = 0.1;
//    gridStructure.weightx = 0.1;
//    gridStructure.weighty = 0.2;
        gridStructure.gridx = 5;

//    gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(testButton3, gridStructure);


    }

    public void setPanelSize(final Integer width, final Integer height) {
        Dimension dim = getPreferredSize();
        dim.width = width;
        dim.height = height;
        setPreferredSize(dim);
    };
}
