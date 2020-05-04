package com.cm6123.snl.GUI.Panels;

import com.cm6123.snl.GUI.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {

    private JButton newGameButton;
    private JButton loadGameButton;
    private JButton creationButton;
    private JLabel welcomeLabel;

    private SquareFormListener formListener;
    private GridBagConstraints gridStructure;
    private GUIFrame gameGui;

    public MainMenuPanel(final GUIFrame gui) {
        this.gameGui = gui;

        welcomeLabel = new JLabel("Welcome to Snakes & Ladders!");

        welcomeLabel.setFont(new Font("Arial Bold", Font.PLAIN, 20));

        newGameButton = new JButton("New Game");
        //Code adapted from answer of user Kris: How can I set size of a button?
        //available at: https://stackoverflow.com/questions/2536873/how-can-i-set-size-of-a-button
        newGameButton.setPreferredSize(new Dimension(400, 60));

        loadGameButton = new JButton("Load Game");
        loadGameButton.setPreferredSize(new Dimension(400, 60));

        creationButton = new JButton("Creation Tool");
        creationButton.setPreferredSize(new Dimension(400, 60));

        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                gameGui.selectWindow("newgame");

            }
        });
        loadGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                gameGui.selectWindow("loadgame");

            }
        });

        creationButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                gameGui.selectWindow("creationmenu");

            }
        });
        gameGui.add(this, BorderLayout.CENTER);
    }

//    @Override
    public JPanel createMenuPanel() {

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

        gridStructure.weightx = 1;
        gridStructure.weighty = 0.5;
        gridStructure.gridx = 0;
        gridStructure.gridy = 1;
//        gridStructure.fill = GridBagConstraints.NONE;
//
//        gridStructure.anchor = GridBagConstraints.LINE_END;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(welcomeLabel, gridStructure);

        gridStructure = new GridBagConstraints();

        gridStructure.weightx = 1;
        gridStructure.weighty = 1;
        gridStructure.gridx = 0;
        gridStructure.gridy = 2;
//        gridStructure.fill = GridBagConstraints.NONE;
//
//        gridStructure.anchor = GridBagConstraints.LINE_END;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(newGameButton, gridStructure);

//        gridStructure.gridx = 1;
//
//        gridStructure.anchor = GridBagConstraints.LINE_START;
//        gridStructure.insets = new Insets(0, 0, 0, 0);
//        add(squareFirstField, gridStructure);

//        if (specialSquareSecondEntryLabel != null) {

            gridStructure.weightx = 1;
            gridStructure.weighty = 1;
            gridStructure.gridx = 0;
            gridStructure.gridy = 3;


//            gridStructure.anchor = GridBagConstraints.LINE_END;
            gridStructure.insets = new Insets(0, 0, 0, 5);
            add(loadGameButton, gridStructure);

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

        gridStructure.weightx = 1;
        gridStructure.weighty = 1;
        gridStructure.gridx = 0;
        gridStructure.gridy = 4;


//        gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
        gridStructure.insets = new Insets(0, 0, 0, 0);
        add(creationButton, gridStructure);

        return this;
    }
}

