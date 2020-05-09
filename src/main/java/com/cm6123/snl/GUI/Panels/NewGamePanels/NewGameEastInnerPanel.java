package com.cm6123.snl.GUI.Panels.NewGamePanels;

import com.cm6123.snl.GUI.GUIFrame;
import com.cm6123.snl.GUI.Panels.SidePanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameEastInnerPanel extends SidePanel {

    private JLabel winningSquareOnlyOnLabel;
    private JLabel boostSquareOnLabel;
//    private JLabel recordGameLabel;

    private GridBagConstraints gridStructure;

    private JCheckBox boostCheckBox;
    private JLabel boostSquareChoiceLabel;
    private JTextField boostChoiceField;

    private JCheckBox winningCheckBox;
//    private JCheckBox recordCheckBox;
    private Boolean dataBaseEnabled;




    public NewGameEastInnerPanel(final String borderTitle, final GUIFrame gui) {
        this.dataBaseEnabled = gui.getDatabaseConnection();

        winningSquareOnlyOnLabel = new JLabel("Winning Square Feature: ");
        winningSquareOnlyOnLabel.setPreferredSize(new Dimension(250, 62));
        boostSquareOnLabel = new JLabel("Boost Square Feature: ");
        boostSquareOnLabel.setPreferredSize(new Dimension(250, 62));
        boostSquareChoiceLabel = new JLabel("Boost Squares (e.g. 7, 15): ");
        boostSquareChoiceLabel.setPreferredSize(new Dimension(250, 62));
//        recordGameLabel = new JLabel("Check to record game ");
//        recordGameLabel.setPreferredSize(new Dimension(250, 64));


        boostCheckBox = new JCheckBox();
        winningCheckBox = new JCheckBox();


        boostChoiceField = new JTextField(6);

        boostSquareChoiceLabel.setEnabled(false);
        boostChoiceField.setEnabled(false);

        boostCheckBox.addActionListener(new ActionListener() {

            public void actionPerformed(final ActionEvent e) {
                Boolean boostSquareTicked = boostCheckBox.isSelected();
                boostSquareChoiceLabel.setEnabled(boostSquareTicked);
                boostChoiceField.setEnabled(boostSquareTicked);

            }
        });



        TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder(borderTitle);
        Border outerGameBarBorder = BorderFactory.createEmptyBorder(2, 10, 0, 0);
        innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);

        setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));

        JButton testButton1 = new JButton("test1");
        JButton testButton2 = new JButton("test1");
        JButton testButton3 = new JButton("test1");


        setLayout(new GridBagLayout());

        gridStructure = new GridBagConstraints();

        gridStructure.weightx = 3;
        gridStructure.weighty = 1;
//        gridStructure.gridx = 0;
//        gridStructure.gridy = 0;
//        gridStructure.insets = new Insets(0, 5, 0, 0);
//        add(recordGameLabel, gridStructure);
//
//
//        gridStructure.gridx = 5;
//        gridStructure.anchor = GridBagConstraints.LINE_END;
//        gridStructure.insets = new Insets(0, 0, 0, 30);
//        add(recordCheckBox, gridStructure);


        gridStructure.gridx = 0;
        gridStructure.gridy = 0;
        gridStructure.insets = new Insets(0, 5, 0, 0);
        add(winningSquareOnlyOnLabel, gridStructure);


        gridStructure.gridx = 5;
        gridStructure.anchor = GridBagConstraints.LINE_END;
        gridStructure.insets = new Insets(0, 0, 0, 30);
        add(winningCheckBox, gridStructure);

        gridStructure.gridx = 0;
        gridStructure.gridy = 1;
        gridStructure.insets = new Insets(0, 5, 0, 0);
        add(boostSquareOnLabel, gridStructure);


        gridStructure.gridx = 5;
        gridStructure.anchor = GridBagConstraints.LINE_END;
        gridStructure.insets = new Insets(0, 0, 0, 30);
        add(boostCheckBox, gridStructure);

        gridStructure.gridx = 0;
        gridStructure.gridy = 2;
        gridStructure.insets = new Insets(0, 5, 0, 0);
        add(boostSquareChoiceLabel, gridStructure);

        gridStructure.gridx = 5;

        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(boostChoiceField, gridStructure);
    }

    public void setPanelSize(final Integer width, final Integer height) {
        Dimension dim = getPreferredSize();
        dim.width = width;
        dim.height = height;
        setPreferredSize(dim);
    };

    public Boolean getWinningCheckBox() {
        Boolean entry = false;
        if (winningCheckBox.isSelected()) {
            entry = true;
        }
        return entry;
    }

    public String getBoostChoiceField() {
        String entry = null;
        if (boostCheckBox.isSelected()) {
            entry = boostChoiceField.getText();
        }
        return entry;
    }
}
