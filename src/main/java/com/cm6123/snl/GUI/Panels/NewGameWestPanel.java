package com.cm6123.snl.GUI.Panels;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicLabelUI;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class NewGameWestPanel extends SidePanel {

    private JLabel snakeChoiceLabel;
    private JLabel ladderChoiceLabel;
    private JLabel playerChoiceLabel;
    private GridBagConstraints gridStructure;



    public NewGameWestPanel(final String borderTitle) {

//        setPanelSize(350, 380);

        playerChoiceLabel = new JLabel("Choose Players: ");
        playerChoiceLabel.setPreferredSize(new Dimension(250, 90));
        snakeChoiceLabel = new JLabel("Choose Snakes: ");
        snakeChoiceLabel.setPreferredSize(new Dimension(250, 90));
        ladderChoiceLabel = new JLabel("Choose Ladders: ");
        ladderChoiceLabel.setPreferredSize(new Dimension(250, 90));



        TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder(borderTitle);
        Border outerGameBarBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);

        setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));


        JButton testButton1 = new JButton("Confirm");
        JButton testButton2 = new JButton("Confirm");
        JButton testButton3 = new JButton("Confirm");


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
    add(playerChoiceLabel, gridStructure);

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
        add(snakeChoiceLabel, gridStructure);

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
        add(ladderChoiceLabel, gridStructure);

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
