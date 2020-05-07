package com.cm6123.snl.GUI.Panels.NewGame;

import com.cm6123.snl.GUI.GUIFrame;
import com.cm6123.snl.GUI.Panels.SidePanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameSouthInnerPanel extends SidePanel implements ActionListener {


    private JLabel defaultGameLabel;
    private JLabel boardSizeLabel;
    private JButton defaultGameButton;
    private JButton customGameButton;
    private JComboBox boardSizeBox;
    private GridBagConstraints gridStructure;
    private GUIFrame gameGui;
    private JSlider boardSize;

    public NewGameSouthInnerPanel(final String borderTitle, final GUIFrame gui) {
        this.gameGui = gui;

        boardSizeLabel = new JLabel("Board Size ");
        boardSizeLabel.setPreferredSize(new Dimension(400, 30));

        defaultGameLabel = new JLabel("Start default game (2 players, example snakes/ladders/dice, no features)");
        defaultGameLabel.setPreferredSize(new Dimension(400, 30));


        TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder(borderTitle);
        Border outerGameBarBorder = BorderFactory.createEmptyBorder(2, 10, 20, 10);
        innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);

        setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));

        boardSizeBox = new JComboBox();

//        JButton boardSize = new JButton("Board Size");
        boardSize = new JSlider(4, 15, 4);
        boardSize.setPreferredSize(new Dimension(600, 50));
        boardSize.setPaintLabels(true);
        boardSize.setPaintTicks(true);
        boardSize.setPaintTrack(true);

        boardSize.setMajorTickSpacing(1);
//        boardSize.setMinorTickSpacing(1);


        defaultGameButton = new JButton("Start Default Game");
        defaultGameButton.addActionListener(this);
//        JButton testButton4 = new JButton("Default Game");
        customGameButton = new JButton("Start Game With Options");
        customGameButton.setBackground(Color.PINK);
        customGameButton.addActionListener(this);
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
        add(boardSize, gridStructure);

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
        add(defaultGameButton, gridStructure);
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
        add(customGameButton, gridStructure);

    }

    public void setPanelSize(final Integer width, final Integer height) {
        Dimension dim = getPreferredSize();
        dim.width = width;
        dim.height = height;
        setPreferredSize(dim);
    };

    public void actionPerformed(final ActionEvent click) {
        if (click.getSource().equals(defaultGameButton)) {
           gameGui.selectWindow("rundefaultgame");
        } else if (click.getSource().equals(customGameButton)) {
            gameGui.selectWindow("runcustomgame");
        }
    }

    public Integer getBoardSize() {
        return boardSize.getValue();
    }
}
