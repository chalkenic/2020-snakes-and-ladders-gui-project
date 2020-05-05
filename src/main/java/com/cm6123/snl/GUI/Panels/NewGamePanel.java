package com.cm6123.snl.GUI.Panels;

import com.cm6123.snl.GUI.GUIFrame;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class NewGamePanel extends JPanel {

    private NewGameWestPanel leftPanel;
    private NewGameEastPanel rightPanel;
    private NewGameSouthPanel bottomPanel;

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
        leftPanel = new NewGameWestPanel("Basic Features");
        rightPanel = new NewGameEastPanel("Additional Features");
        bottomPanel = new NewGameSouthPanel("Board & Game");


        for (int i = 0; i < 10; i++) {
            snakeChoices.add(i);
        }
        playerCountLabel = new JLabel("Choose players: ");
//        playerCountLabel.setPreferredSize(new Dimension(10, 10));

        boardSizeLabel = new JLabel("Choose board size: ");
        snakeChoiceLabel = new JLabel("Choose snakes to use: ");
        ladderChoiceLabel = new JLabel("Choose ladders to use: ");
        boostOnlabel = new JLabel("Enable Boost Squares: ");
        boostChoiceLabel = new JLabel("Choose boost squares to use: ");
        winningSquareOnlyOnLabel = new JLabel("Enable Winning Square Only: ");
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
    TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder("New Game");
    innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);

    setBorder(innerGameBarBorder);

//    setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));

    this.add(leftPanel, BorderLayout.WEST);
    this.add(rightPanel, BorderLayout.EAST);
    this.add(bottomPanel, BorderLayout.SOUTH);
    //Creates a border as a margin around inner game bar.


    //USED FOR FLEXIBILITY COMPARED TO OTHER LAYOUTS
//    setLayout(new GridBagLayout());



    JButton testbutton1 = new JButton("test1");
//    playerCountLabel.setPreferredSize(new Dimension(-500, 500));

    JButton testbutton2 = new JButton("test2");
    JButton testbutton3 = new JButton("test3");
    JButton testbutton4 = new JButton("test4");
    JButton testbutton5 = new JButton("test5");
    JButton testbutton6 = new JButton("test6");
    JButton testbutton7 = new JButton("test7");
    JButton testbutton8 = new JButton("test8");
    JButton testbutton9 = new JButton("test9");
    JButton testbutton0 = new JButton("test10");

//
//
//    gridStructure = new GridBagConstraints();
//    gridStructure.fill = GridBagConstraints.NONE;
//
//    gridStructure.weightx = 1;
//    gridStructure.weighty = 1;
//
//    gridStructure.gridx = 0;
//    gridStructure.gridy = 0;
////    gridStructure.fill = GridBagConstraints.NONE;
////
////    gridStructure.anchor = GridBagConstraints.LINE_END;
//    gridStructure.insets = new Insets(0, 0, 0, 5);
//    add(playerCountLabel, gridStructure);
//
//    /////////////BEGIN TEMPORARY FORMATTING CODE//////////////////
////
////    gridStructure.weightx = 0.1;
////    gridStructure.weighty = 0.2;
//    gridStructure.gridx = 2;
//
////    gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
//    gridStructure.insets = new Insets(0, 0, 0, 5);
//    add(testbutton1, gridStructure);
//
//    gridStructure.weightx = 0.1;
//    gridStructure.gridx = 0;
//    gridStructure.gridy = 1;
//
//    gridStructure.insets = new Insets(0, 0, 0, 5);
//    add(snakeChoiceLabel, gridStructure);
//
//    /////////////BEGIN TEMPORARY FORMATTING CODE//////////////////
////
////    gridStructure.weightx = 0.1;
////    gridStructure.weighty = 0.2;
//    gridStructure.gridx = 1;
//
////    gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
//    gridStructure.insets = new Insets(0, 0, 0, 5);
//    add(testbutton2, gridStructure);
//
//    gridStructure.gridx = 2;
//    gridStructure.gridy = 1;
////    gridStructure.fill = GridBagConstraints.NONE;
////
////    gridStructure.anchor = GridBagConstraints.LINE_END;
////    gridStructure.fill = GridBagConstraints.NONE;
////
////    gridStructure.anchor = GridBagConstraints.LINE_END;
//    gridStructure.insets = new Insets(0, 0, 0, 5);
//    add(ladderChoiceLabel, gridStructure);
//
//    /////////////END TEMPORARY FORMATTING CODE//////////////////
//
////    gridStructure.weighty = 0.2;
//    gridStructure.gridx = 3;
//
////    gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
//    gridStructure.insets = new Insets(0, 0, 0, 5);
//    add(testbutton3, gridStructure);
//
//    /////////////END TEMPORARY FORMATTING CODE//////////////////
//
////    gridStructure.weightx = 0.1;
////    gridStructure.weighty = 1;
//
//    gridStructure.gridx = 0;
//    gridStructure.gridy = 2;
////    gridStructure.fill = GridBagConstraints.NONE;
////
////    gridStructure.anchor = GridBagConstraints.LINE_END;
//    gridStructure.insets = new Insets(0, 0, 0, 5);
//    add(boostChoiceLabel, gridStructure);
//    /////////////BEGIN TEMPORARY FORMATTING CODE//////////////////
//
////    gridStructure.weightx = 0.1;
////    gridStructure.weighty = 0.2;
//    gridStructure.gridx = 1;
//
////    gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
//    gridStructure.insets = new Insets(0, 0, 0, 5);
//    add(testbutton4, gridStructure);
////
////    /////////////END TEMPORARY FORMATTING CODE//////////////////
//    gridStructure.weighty = 0.1;
//
//    gridStructure.gridx = 2;
//    gridStructure.gridy = 3;
////    gridStructure.gridy = 0;
//
////    gridStructure.fill = GridBagConstraints.NONE;
////
////    gridStructure.anchor = GridBagConstraints.LINE_END;
//    gridStructure.insets = new Insets(0, 0, 0, 0);
//    add(boostOnlabel, gridStructure);
//
//    ////////////////TEMPORARY FORMATTING CODE/////////////////////
//
////    gridStructure.weightx = 1;
////    gridStructure.weighty = 0.2;
//    gridStructure.gridx = 3;
//
////    gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
//    gridStructure.insets = new Insets(0, 0, 0, 0);
//    add(testbutton5, gridStructure);
//
//    gridStructure.gridx = 2;
//    gridStructure.gridy = 4;
//
//    gridStructure.insets = new Insets(0, 0, 0, 0);
//    add(winningSquareOnlyOnLabel, gridStructure);
//
//    gridStructure.gridx = 3;
//
////    gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
//    gridStructure.insets = new Insets(0, 0, 0, 0);
//    add(testbutton6, gridStructure);
//
//    gridStructure.gridx = 2;
//    gridStructure.gridy = 5;
//
//    gridStructure.insets = new Insets(0, 0, 0, 0);
//    add(recordGameLabel, gridStructure);
//
//    gridStructure.gridx = 3;
//
////    gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
//    gridStructure.insets = new Insets(0, 0, 0, 0);
//    add(testbutton7, gridStructure);
//
//
//
//
//    gridStructure.weightx = 10;
//    gridStructure.weighty = 1;
//    gridStructure.gridx = 1;
//    gridStructure.gridy = 6;
//
//    gridStructure.insets = new Insets(0, 0, 0, 0);
//    add(boardSizeLabel, gridStructure);
//
//    gridStructure.gridy = 7;
//
//    gridStructure.insets = new Insets(0, 0, 0, 0);
//    add(testbutton9, gridStructure);
//

    //
////
//    gridStructure.weightx = 0.1;
//    gridStructure.weighty = 1;


    /////////////END TEMPORARY FORMATTING CODE//////////////////
//
//    gridStructure.weightx = 0.1;
//    gridStructure.weighty = 1;
//    gridStructure.gridx = 0;
//    gridStructure.gridy = 6;
//    //        gridStructure.fill = GridBagConstraints.NONE;
//    //
//    //        gridStructure.anchor = GridBagConstraints.LINE_END;
//    gridStructure.insets = new Insets(0, 0, 0, 5);
//    add(boostChoiceLabel, gridStructure);
//
//    gridStructure.weightx = 0.1;
//    gridStructure.weighty = 1;
//    gridStructure.gridx = 0;
//    gridStructure.gridy = 7;
//    //        gridStructure.fill = GridBagConstraints.NONE;
//    //
//    //        gridStructure.anchor = GridBagConstraints.LINE_END;
//    gridStructure.insets = new Insets(0, 0, 0, 5);
//    add(winningSquareOnlyOnLabel, gridStructure);
//
//    gridStructure.weightx = 0.1;
//    gridStructure.weighty = 1;
//    gridStructure.gridx = 0;
//    gridStructure.gridy = 8;
//    //        gridStructure.fill = GridBagConstraints.NONE;
//    //
//    //        gridStructure.anchor = GridBagConstraints.LINE_END;
//    gridStructure.insets = new Insets(0, 0, 0, 5);
//    add(recordGameLabel, gridStructure);
//
//    gridStructure.weightx = 0.1;
//    gridStructure.weighty = 1;
//    gridStructure.gridx = 0;
//    gridStructure.gridy = 9;
//    //        gridStructure.fill = GridBagConstraints.NONE;
//    //
//    //        gridStructure.anchor = GridBagConstraints.LINE_END;
//    gridStructure.insets = new Insets(0, 0, 0, 5);
//    add(createGameLabel, gridStructure);
//
//    gridStructure.weightx = 0.1;
//    gridStructure.weighty = 1;
//    gridStructure.gridx = 0;
//    gridStructure.gridy = 10;
//    //        gridStructure.fill = GridBagConstraints.NONE;
//    //
//    //        gridStructure.anchor = GridBagConstraints.LINE_END;
//    gridStructure.insets = new Insets(0, 0, 0, 5);
//    add(defaultGameLabel, gridStructure);
//
//    gridStructure.weightx = 0.1;
//    gridStructure.weighty = 0.2;
//    gridStructure.gridx = 0;
//    gridStructure.gridy = 11;
//    gridStructure.fill = GridBagConstraints.NONE;
//
//    gridStructure.anchor = GridBagConstraints.LINE_END;
//    gridStructure.insets = new Insets(0, 0, 0, 5);
//    add(boardSizeLabel, gridStructure);
//
//    /////////////END TEMPORARY FORMATTING CODE//////////////////
//
//    gridStructure.weightx = 1;
////    gridStructure.weighty = 0.2;
//    gridStructure.gridx = 1;
//
//    gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
//    gridStructure.insets = new Insets(0, 0, 0, 5);
//    add(testbutton2, gridStructure);

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
