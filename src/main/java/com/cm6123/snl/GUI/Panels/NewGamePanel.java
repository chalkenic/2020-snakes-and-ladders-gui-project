package com.cm6123.snl.GUI.Panels;

import com.cm6123.snl.GUI.GUIFrame;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class NewGamePanel extends JPanel {

    private GUIFrame gameGui;
    private JLabel playerCountLabel;
    private JLabel boardSizeLabel;
    private JLabel snakeChoiceLabel;
    private JLabel ladderChoiceLabel;
    private JLabel boostOnlabel;
    private JLabel boostChoiceLabel;
    private JLabel winningSquareOnlyOnLabel;
    private JLabel recordGameLabel;
    private JLabel createGameLabel;
    private JLabel defaultGameLabel;

    private JTextField playerCountField;
    private JSlider boardSizeSlider;
    private JList snakeChoiceList;
    private JList ladderChoiceList;
    //Insert boost tickbox here.
    private JList boostChoiceList;
    //Insert winning square tickbox here.

    private JButton createGameButton;
    private JButton defaultGameButton;

    private GridBagConstraints gridStructure;

    private ArrayList<Integer> snakeChoices = new ArrayList<Integer>();

    public NewGamePanel(final GUIFrame gui) {
        this.gameGui = gui;

        for (int i = 0; i < 10; i++) {
            snakeChoices.add(i);
        }
        playerCountLabel = new JLabel("Insert number of players: ");
        boardSizeLabel = new JLabel("Choose board size: ");
        snakeChoiceLabel = new JLabel("Choose snakes to use: ");
        ladderChoiceLabel = new JLabel("Choose ladders to use: ");
        boostOnlabel = new JLabel("Enable Boost Square feature: ");
        boostChoiceLabel = new JLabel("Choose boost squares to use: ");
        winningSquareOnlyOnLabel = new JLabel("Enable Winning Square Only feaature: ");
        recordGameLabel = new JLabel("Record all game player moves.");
        createGameLabel = new JLabel("Create game with parameters");
        defaultGameLabel = new JLabel("Create a default game with set parameters");



        snakeChoiceList = new JList(snakeChoices.toArray());

        createGameButton = new JButton("Create Game");
        defaultGameButton = new JButton("Create Default Game");
    }

public JPanel createNewGamePanel() {

    //Code adapted from TitledBorder.CENTER : TitledBorder « javax.swing.border « Java by API
    //Available at: http://www.java2s.com/Code/JavaAPI/javax.swing.border/TitledBorderCENTER.htm
    TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder("Main Menu");
    Border outerGameBarBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);

    //Creates a border as a margin around inner game bar.
    setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));

    //USED FOR FLEXIBILITY COMPARED TO OTHER LAYOUTS
    setLayout(new GridBagLayout());

    gridStructure = new GridBagConstraints();
    gridStructure.fill = GridBagConstraints.HORIZONTAL;
    gridStructure.anchor = GridBagConstraints.CENTER;

    gridStructure.weightx = 0.1;
    gridStructure.weighty = 0.2;
    gridStructure.gridx = 0;
    gridStructure.gridy = 0;
//        gridStructure.fill = GridBagConstraints.NONE;
//
    gridStructure.anchor = GridBagConstraints.LINE_END;
    gridStructure.insets = new Insets(0, 0, 0, 5);
    add(playerCountLabel, gridStructure);

    gridStructure.weightx = 1;
    gridStructure.weighty = 0.2;
    gridStructure.gridx = 1;
    gridStructure.gridy = 0;

    gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
    gridStructure.insets = new Insets(0, 0, 0, 5);
    add(createGameButton, gridStructure);

    gridStructure.weightx = 0.1;
    gridStructure.weighty = 0.2;
    gridStructure.gridx = 0;
    gridStructure.gridy = 2;
    //        gridStructure.fill = GridBagConstraints.NONE;
    //
    //        gridStructure.anchor = GridBagConstraints.LINE_END;
    gridStructure.insets = new Insets(0, 0, 0, 5);
    add(boardSizeLabel, gridStructure);

    gridStructure.weightx = 0.1;
    gridStructure.weighty = 0.2;
    gridStructure.gridx = 0;
    gridStructure.gridy = 3;
    //        gridStructure.fill = GridBagConstraints.NONE;
    //
    //        gridStructure.anchor = GridBagConstraints.LINE_END;
    gridStructure.insets = new Insets(0, 0, 0, 5);
    add(snakeChoiceLabel, gridStructure);

    gridStructure.weightx = 0.1;
    gridStructure.weighty = 0.2;
    gridStructure.gridx = 0;
    gridStructure.gridy = 4;
    //        gridStructure.fill = GridBagConstraints.NONE;
    //
    //        gridStructure.anchor = GridBagConstraints.LINE_END;
    gridStructure.insets = new Insets(0, 0, 0, 5);
    add(ladderChoiceLabel, gridStructure);

    gridStructure.weightx = 0.1;
    gridStructure.weighty = 1;
    gridStructure.gridx = 0;
    gridStructure.gridy = 5;
    //        gridStructure.fill = GridBagConstraints.NONE;
    //
    //        gridStructure.anchor = GridBagConstraints.LINE_END;
    gridStructure.insets = new Insets(0, 0, 0, 5);
    add(boostOnlabel, gridStructure);

    gridStructure.weightx = 0.1;
    gridStructure.weighty = 1;
    gridStructure.gridx = 0;
    gridStructure.gridy = 6;
    //        gridStructure.fill = GridBagConstraints.NONE;
    //
    //        gridStructure.anchor = GridBagConstraints.LINE_END;
    gridStructure.insets = new Insets(0, 0, 0, 5);
    add(boostChoiceLabel, gridStructure);

    gridStructure.weightx = 0.1;
    gridStructure.weighty = 1;
    gridStructure.gridx = 0;
    gridStructure.gridy = 7;
    //        gridStructure.fill = GridBagConstraints.NONE;
    //
    //        gridStructure.anchor = GridBagConstraints.LINE_END;
    gridStructure.insets = new Insets(0, 0, 0, 5);
    add(winningSquareOnlyOnLabel, gridStructure);

    gridStructure.weightx = 0.1;
    gridStructure.weighty = 1;
    gridStructure.gridx = 0;
    gridStructure.gridy = 8;
    //        gridStructure.fill = GridBagConstraints.NONE;
    //
    //        gridStructure.anchor = GridBagConstraints.LINE_END;
    gridStructure.insets = new Insets(0, 0, 0, 5);
    add(recordGameLabel, gridStructure);

    gridStructure.weightx = 0.1;
    gridStructure.weighty = 1;
    gridStructure.gridx = 0;
    gridStructure.gridy = 9;
    //        gridStructure.fill = GridBagConstraints.NONE;
    //
    //        gridStructure.anchor = GridBagConstraints.LINE_END;
    gridStructure.insets = new Insets(0, 0, 0, 5);
    add(createGameLabel, gridStructure);

    gridStructure.weightx = 0.1;
    gridStructure.weighty = 1;
    gridStructure.gridx = 0;
    gridStructure.gridy = 10;
    //        gridStructure.fill = GridBagConstraints.NONE;
    //
    //        gridStructure.anchor = GridBagConstraints.LINE_END;
    gridStructure.insets = new Insets(0, 0, 0, 5);
    add(defaultGameLabel, gridStructure);

//            gridStructure.gridx = 1;
//
//            gridStructure.anchor = GridBagConstraints.LINE_START;
//            gridStructure.insets = new Insets(0, 0, 0, 0);
//            add(squareSecondField, gridStructure);
//        }

    /////////////////THIRD ROW////////////////

    /////////////////BEGIN TEST CODE////////////////
//        gridStructure.weightx = 1;
//        gridStructure.weighty = 0.2;
//        gridStructure.gridy = 2;
//
//        gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
//        gridStructure.insets = new Insets(0, 0, 0, 5);
//        add(newGame, gridStructure);
    /////////////////END TEST CODE//////////////////

    /////////////////FOURTH ROW////////////////

//        gridStructure.weightx = 1;
//        gridStructure.weighty = 1;
//        gridStructure.gridx = 0;
//        gridStructure.gridy = 4;
//
//
////        gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
//        gridStructure.insets = new Insets(0, 0, 0, 0);
//        add(creationButton, gridStructure);

    return this;
}

}
