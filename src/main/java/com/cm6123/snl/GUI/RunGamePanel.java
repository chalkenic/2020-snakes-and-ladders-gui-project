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
    private JLabel winningSquareLabel;
    private JLabel currentPlayerLabel;
    private JLabel playerColourTurnLabel;
    private JLabel playerPositionLabel;
    private JLabel boostFeatureLabel;
    private JLabel winningFeatureLabel;
    private JLabel boostFeatureResultLabel;
    private JLabel winningFeatureResultLabel;

    private JLabel playerColourTurnResultLabel;
    private JLabel playerPositionResultLabel;
//    private JTextField
    private JButton rollDiceButton;
    private JButton newGameButton;
    private JButton saveGameButton;
    private GridBagConstraints gridStructure;
    private Border currentPlayerBorder;
    private BoardMove boardMovement;
    private DiceSet dice;
    private String winningSquareFeature;
    private String boostFeature;

    public RunGamePanel(final GUIFrame gui, final Game newGame, final DiceSet diceChoice) {
        this.currentGame = newGame;
        this.gameGui = gui;
        this.dice = diceChoice;



        setPanelSize(350, 200);
        currentPlayerBorder = BorderFactory.createLineBorder(Color.BLACK);

        gamePlayerList = new JList();
        gamePlayerList.setFixedCellHeight(30);
        gamePlayerList.setFixedCellWidth(100);
        gamePlayerList.setBorder(currentPlayerBorder);

        boostFeatureLabel = new JLabel("Boost feature: ");
        winningFeatureLabel = new JLabel("Winning Square feature: ");
        boostFeatureResultLabel = new JLabel();
        boostFeatureResultLabel.setSize(new Dimension(20, 20));
        //Code adapted from Peter lang answer: How do I set a JLabel's background color?
        //Available at: https://stackoverflow.com/questions/2380314/how-do-i-set-a-jlabels-background-color

        boostFeatureResultLabel.setOpaque(true);
        winningFeatureResultLabel = new JLabel();
        winningFeatureResultLabel.setSize(new Dimension(20, 20));
        winningFeatureResultLabel.setOpaque(true);

        if (currentGame.isWinningSquareOn()) {
            winningFeatureResultLabel.setText("ON");
            winningFeatureResultLabel.setBackground(Color.GREEN);
        } else {
            winningFeatureResultLabel.setText("OFF");
            winningFeatureResultLabel.setBackground(Color.RED);
        }

        if (currentGame.getBoostSquareOn()) {
            boostFeatureResultLabel.setText("ON");
            boostFeatureResultLabel.setBackground(Color.GREEN);
        } else {
            boostFeatureResultLabel.setText("OFF");
            boostFeatureResultLabel.setBackground(Color.RED);
        }

        currentPlayers = new DefaultListModel();

        winningSquareLabel = new JLabel("WINNING SQUARE: " + currentGame.getBoard().getWinningSquare());
        winningSquareLabel.setFont(new Font("Arial Bold", Font.PLAIN, 20));

        currentPlayerLabel = new JLabel("Player Order");
        playerColourTurnLabel = new JLabel("Player turn: ");
        playerPositionLabel = new JLabel("Player position: ");
        playerColourTurnResultLabel = new JLabel();
        playerPositionResultLabel = new JLabel();

        for (int i = 0; i < currentGame.numberOfPlayers(); i++) {
            currentPlayers.addElement(currentGame.getPlayerData(i).getColour().toString());
        }
        gamePlayerList.setModel(currentPlayers);
        gamePlayerList.setSelectedIndex(0);
        gamePlayerList.setSelectionBackground(Color.CYAN);
//        playerColourTurnLabel.setText("player turn: " + currentGame.getPlayerData(0).getColour().toString());
//        gamePlayerList.setBorder(currentPlayerBorder);

        rollDiceButton = new JButton("Roll Dice");
        newGameButton = new JButton("Start new Game");
        newGameButton.setEnabled(false);
        saveGameButton = new JButton("Save current game");

        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!currentGame.isGameOver()) {
                    settlePlayerMove();
                }
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                gameGui.selectWindow("newgame");
                }
        });

        saveGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                gameGui.selectWindow("runduplicateGame");
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

        gridStructure.weightx = 1;
        gridStructure.weighty = 0.1;
        gridStructure.gridx = 0;
        gridStructure.gridy = 0;

        add(winningSquareLabel, gridStructure);

        gridStructure.fill = GridBagConstraints.NONE;

        gridStructure.gridy = 1;
        add(currentPlayerLabel, gridStructure);

        gridStructure.gridy = 2;
        add(gamePlayerList, gridStructure);

        gridStructure.gridy = 3;
        gridStructure.anchor = GridBagConstraints.LINE_START;
        add(playerColourTurnLabel, gridStructure);
        gridStructure.anchor = GridBagConstraints.CENTER;
        add(playerColourTurnResultLabel, gridStructure);

        gridStructure.gridy = 4;

        gridStructure.anchor = GridBagConstraints.LINE_START;
        add(playerPositionLabel, gridStructure);
        gridStructure.anchor = GridBagConstraints.CENTER;
        add(playerPositionResultLabel, gridStructure);

        gridStructure.weightx = 2;
        gridStructure.weighty = 1;
        gridStructure.gridy = 5;
        gridStructure.ipadx = 150;
        gridStructure.ipady = 150;
        add(rollDiceButton, gridStructure);

        gridStructure.ipadx = 1;
        gridStructure.ipady = 1;
        gridStructure.gridx = 0;
        gridStructure.weighty = 0.1;

        gridStructure.gridy = 6;
        gridStructure.anchor = GridBagConstraints.LINE_START;
        add(boostFeatureLabel, gridStructure);

        gridStructure.anchor = GridBagConstraints.CENTER;
        add(boostFeatureResultLabel, gridStructure);

        gridStructure.gridy = 7;
        gridStructure.anchor = GridBagConstraints.LINE_START;
        add(winningFeatureLabel, gridStructure);


        gridStructure.anchor = GridBagConstraints.CENTER;
        add(winningFeatureResultLabel, gridStructure);

        gridStructure.weighty = 1;
        gridStructure.gridy = 8;

        gridStructure.anchor = GridBagConstraints.LINE_START;
        add(saveGameButton, gridStructure);

        gridStructure.anchor = GridBagConstraints.LINE_END;
        add(newGameButton, gridStructure);


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
        System.out.println(currentGame.isWinningSquareOn());
        boardMovement = new BoardMove(currentGame, gameGui);
        playerTurnStart();
    }

    private void settlePlayerMove() {
        Integer diceRoll = dice.roll().getValue();
        Player currentPlayer = currentGame.getCurrentPlayer();
        Integer currentPosition = currentPlayer.getPosition().get();

        gameGui.appendTextToPanel(currentPlayer.getColour() + " player starts their turn at position "
                + currentPosition + ".\n");
        gameGui.appendTextToPanel(currentPlayer.getColour() + " player has rolled a " + diceRoll + "!\n");

        boardMovement.movePlayer(diceRoll, currentPlayer, currentPosition);

        if (!currentGame.isGameOver()) {
            //Code adapted from Wayan Saryada - How do I get the items of a JList components?
            //Available at: https://kodejava.org/how-do-i-get-the-items-of-a-jlist-components/
            for (int i = 0; i < gamePlayerList.getModel().getSize(); i++) {
                Object player = gamePlayerList.getModel().getElementAt(i);
                if (player.toString() == currentPlayer.getColour().toString()) {

                    gameGui.appendTextToPanel(currentPlayer.getColour() + " player ends their turn at "
                            + "position " + currentPlayer.getPosition().get() + "\n\n");

                    gameGui.appendTextToPanel(currentPlayer.getColour() + " player distance covered: "
                            + (currentPlayer.getPosition().get() - currentPosition) + " squares\n");

                    gameGui.appendTextToPanel("|---------------------------------------------------------|\n");

                }
            }

            playerTurnStart();
        } else {
            for (int i = 0; i < gamePlayerList.getModel().getSize(); i++) {
                Object player = gamePlayerList.getModel().getElementAt(i);
                if (player.toString() == currentPlayer.getColour().toString()) {
                    gamePlayerList.setSelectedIndex(i);
                    gamePlayerList.setSelectionBackground(Color.ORANGE);
                    newGameButton.setEnabled(true);
                    gameGui.appendTextToPanel(currentPlayer.getColour() + " player ends their turn at "
                                    + "position " + currentPlayer.getPosition().get() + "\n\n");
                    gameGui.appendTextToPanel(currentPlayer.getColour() + " player wins the "
                            + "game!\n");
                    gameGui.appendTextToPanel("|---------------------------------------------------------|\n");
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
                playerColourTurnResultLabel.setText(currentPlayer.getColour().toString());
                playerPositionResultLabel.setText(position.toString());

            }
        }
    }
}
