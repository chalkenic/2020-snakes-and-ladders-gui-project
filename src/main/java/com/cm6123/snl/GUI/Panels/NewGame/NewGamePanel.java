package com.cm6123.snl.GUI.Panels.NewGame;

import com.cm6123.snl.GUI.GUIFrame;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class NewGamePanel extends JPanel {

    private NewGameWestInnerPanel leftPanel;
    private NewGameEastInnerPanel rightPanel;
    private NewGameSouthInnerPanel bottomPanel;

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
        leftPanel = new NewGameWestInnerPanel("Basic Features");
        rightPanel = new NewGameEastInnerPanel("Additional Features");
        bottomPanel = new NewGameSouthInnerPanel("Board & Game");


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


    return this;
}

}
