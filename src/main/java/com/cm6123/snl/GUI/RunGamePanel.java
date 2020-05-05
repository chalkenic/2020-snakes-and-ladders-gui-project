package com.cm6123.snl.GUI;

import com.cm6123.snl.GUI.Panels.SidePanel;
import com.cm6123.snl.Game;
import com.cm6123.snl.Player;
import com.cm6123.snl.dice.DiceSet;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunGamePanel extends SidePanel {

    private GUIFrame gameGui;
    private Game currentGame;
    private JList gamePlayerList;
    private DefaultListModel currentPlayers;
    private JLabel currentPlayerLabel;
//    private JTextField
    private JButton rollDiceButton;
    private GridBagConstraints gridStructure;


    public RunGamePanel(final GUIFrame gui, final Game theGame) {
        this.currentGame = theGame;
        this.gameGui = gui;

        setPanelSize(350, 200);
        gamePlayerList = new JList();
        gamePlayerList.setFixedCellHeight(30);
        gamePlayerList.setFixedCellWidth(100);

        currentPlayers = new DefaultListModel();
        DiceSet dice = new DiceSet(6, 1);
        currentPlayerLabel = new JLabel("Current Players");

        for (int i = 0; i < currentGame.numberOfPlayers(); i++) {
            currentPlayers.addElement(currentGame.getPlayerData(i).getColour().toString());
        }
        gamePlayerList.setModel(currentPlayers);
        gamePlayerList.setSelectedIndex(0);
        gamePlayerList.setSelectionBackground(Color.CYAN);



        rollDiceButton = new JButton("Roll Dice");

        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!currentGame.isGameOver()) {
                    settlePlayerMove(dice);
                }
            }
        });
    }

    public JPanel createRunGamePanel() {

        TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder("Current Game");
        Border outerGameBarBorder = BorderFactory.createEmptyBorder(2, 10, 10, 10);
        innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);

        setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));

        setLayout(new GridBagLayout());

        gridStructure = new GridBagConstraints();

        gridStructure.weightx = 0.1;
        gridStructure.weighty = 0.1;
        gridStructure.gridx = 0;
        gridStructure.gridy = 0;
        gridStructure.fill = GridBagConstraints.NONE;

//        gridStructure.anchor = GridBagConstraints.LINE_END;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(currentPlayerLabel, gridStructure);


        gridStructure.gridx = 0;
        gridStructure.gridy = 1;
        gridStructure.fill = GridBagConstraints.NONE;

//        gridStructure.anchor = GridBagConstraints.LINE_START;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(gamePlayerList, gridStructure);

        gridStructure.gridy = 0;
        gridStructure.gridy = 2;

//        gridStructure.anchor = GridBagConstraints.LINE_END;

        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(rollDiceButton, gridStructure);


        playerTurnStart();
        return this;

    }

    public void setPanelSize(final Integer width, final Integer height) {
        Dimension dim = getPreferredSize();
        dim.width = width;
        dim.height = height;
        setPreferredSize(dim);
    };

    private void settlePlayerMove(final DiceSet dice) {
        Integer diceRoll = dice.roll().getValue();


        Player currentPlayer = currentGame.getCurrentPlayer();


//        gameGui.appendTextToPanel(Integer.toString(diceRoll));

        gameGui.appendTextToPanel(currentPlayer.getColour() + " player has rolled a " + diceRoll + "!\n");
        currentGame.moveCurrentPlayer(diceRoll);

        if (!currentGame.isGameOver()) {
            for (int i = 0; i < gamePlayerList.getModel().getSize(); i++) {
                Object player = gamePlayerList.getModel().getElementAt(i);
                if (player.toString() == currentPlayer.getColour().toString()) {

                    gameGui.appendTextToPanel(currentPlayer.getColour() + " player ends their turn at "
                           + "position " + (currentPlayer.getPosition().get() + 1) + "\n\n");

                }
            }


            playerTurnStart();
        } else {
            for (int i = 0; i < gamePlayerList.getModel().getSize(); i++) {
                Object player = gamePlayerList.getModel().getElementAt(i);
                if (player.toString() == currentPlayer.getColour().toString()) {
                    gamePlayerList.setSelectedIndex(i);
                    gamePlayerList.setSelectionBackground(Color.YELLOW);

                    gameGui.appendTextToPanel(currentPlayer.getColour().toString() + " player wins the "
                            + "game!");
                }
            }
        }


//        gameGui.appendTextToPanel("\nplayer " + currentGame.getCurrentPlayer() + " has moved to position "
//                + currentGame.getCurrentPlayer().getPosition().get());
    }

    private void playerTurnStart() {
        Player currentPlayer = currentGame.getCurrentPlayer();
        Integer position = currentGame.getCurrentPlayer().getPosition().get();

        for (int i = 0; i < gamePlayerList.getModel().getSize(); i++) {
            Object player = gamePlayerList.getModel().getElementAt(i);
            if (player.toString() == currentPlayer.getColour().toString()) {
                gamePlayerList.setSelectedIndex(i);
                gamePlayerList.setSelectionBackground(Color.CYAN);

            }
        }

        gameGui.appendTextToPanel(currentPlayer.getColour() + " player's turn, beginning at position "
                + (position + 1) + "\n");
    }
}
