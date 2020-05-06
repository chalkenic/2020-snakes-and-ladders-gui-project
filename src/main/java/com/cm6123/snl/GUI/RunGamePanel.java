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
    private JLabel playerColourTurnLabel;
    private JLabel playerPositionLabel;
//    private JTextField
    private JButton rollDiceButton;
    private GridBagConstraints gridStructure;
    private Border currentPlayerBorder;
    private BoardMove boardMovement;
    private DiceSet dice;

    public RunGamePanel(final GUIFrame gui, final Game newGame) {
        this.currentGame = newGame;
        this.gameGui = gui;


        setPanelSize(350, 200);
        currentPlayerBorder = BorderFactory.createLineBorder(Color.BLACK);

        gamePlayerList = new JList();
        gamePlayerList.setFixedCellHeight(30);
        gamePlayerList.setFixedCellWidth(100);
        gamePlayerList.setBorder(currentPlayerBorder);


        currentPlayers = new DefaultListModel();

        currentPlayerLabel = new JLabel("Current Player turn");
        playerColourTurnLabel = new JLabel();
//        playerColourTurnLabel.setBorder(currentPlayerBorder);
        playerPositionLabel = new JLabel();
//        playerPositionLabel.setBorder(currentPlayerBorder);

        for (int i = 0; i < currentGame.numberOfPlayers(); i++) {
            currentPlayers.addElement(currentGame.getPlayerData(i).getColour().toString());
        }
        gamePlayerList.setModel(currentPlayers);
        gamePlayerList.setSelectedIndex(0);
        gamePlayerList.setSelectionBackground(Color.CYAN);
//        playerColourTurnLabel.setText("player turn: " + currentGame.getPlayerData(0).getColour().toString());
//        gamePlayerList.setBorder(currentPlayerBorder);

        rollDiceButton = new JButton("Roll Dice");

        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!currentGame.isGameOver()) {
                    settlePlayerMove();
                }
            }
        });
        boardMovement = new BoardMove(currentGame);

    }

    public JPanel createRunGamePanel() {

        TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder("Current Game");
        Border outerGameBarBorder = BorderFactory.createEmptyBorder(2, 10, 10, 10);
        innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);

        setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));

        setLayout(new GridBagLayout());

        gridStructure = new GridBagConstraints();

        gridStructure.weightx = 1;
        gridStructure.weighty = 0.1;
        gridStructure.gridx = 0;
        gridStructure.gridy = 0;
        gridStructure.fill = GridBagConstraints.NONE;

//        gridStructure.anchor = GridBagConstraints.LINE_END;

        add(currentPlayerLabel, gridStructure);

        gridStructure.gridy = 1;
//        gridStructure.fill = GridBagConstraints.NONE;

//        gridStructure.anchor = GridBagConstraints.LINE_START;

        add(gamePlayerList, gridStructure);

        gridStructure.gridy = 2;

        add(playerColourTurnLabel, gridStructure);

        gridStructure.gridy = 3;

        add(playerPositionLabel, gridStructure);

        gridStructure.weightx = 2;
        gridStructure.weighty = 1;
        gridStructure.gridy = 4;

        gridStructure.ipadx = 150;
        gridStructure.ipady = 150;

//        gridStructure.anchor = GridBagConstraints.LINE_END;


        add(rollDiceButton, gridStructure);


        launchGame();
        return this;
    }

    public void setPanelSize(final Integer width, final Integer height) {
        Dimension dim = getPreferredSize();
        dim.width = width;
        dim.height = height;
        setPreferredSize(dim);
    }

    public void launchGame() {
         dice = new DiceSet(6, 1);
        playerTurnStart();
    }

    private void settlePlayerMove() {
        Integer diceRoll = dice.roll().getValue();
        Player currentPlayer = currentGame.getCurrentPlayer();

        gameGui.appendTextToPanel(currentPlayer.getColour() + " player has rolled a " + diceRoll + "!\n");

        boardMovement.movePlayer(diceRoll);

        if (!currentGame.isGameOver()) {
            //Code adapted from Wayan Saryada - How do I get the items of a JList components?
            //Available at: https://kodejava.org/how-do-i-get-the-items-of-a-jlist-components/
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
                    gamePlayerList.setSelectionBackground(Color.ORANGE);

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
                playerColourTurnLabel.setText("Player turn: " + currentPlayer.getColour());
                playerPositionLabel.setText("Player position: " + (position + 1));

            }
        }
    }
}
