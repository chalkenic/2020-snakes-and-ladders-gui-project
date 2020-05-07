package com.cm6123.snl.GUI;

import com.cm6123.snl.GUI.Panels.NewGame.NewGameEastInnerPanel;
import com.cm6123.snl.GUI.Panels.NewGame.NewGamePanel;
import com.cm6123.snl.GUI.Panels.NewGame.NewGameSouthInnerPanel;
import com.cm6123.snl.GUI.Panels.NewGame.NewGameWestInnerPanel;
import com.cm6123.snl.Game;
import com.cm6123.snl.GameBuilder;

import javax.swing.*;
import java.util.Arrays;

public class CreateGame {

    private Integer[] snakes;

    private Integer[] ladders;

    private Integer[] boosts;

    private Integer tempBoardSize;
    private Integer tempPlayerCount;
    private Integer diceCount;
    private Integer diceFaces;

    private Boolean boostGameFeature;
    private Boolean winningSquareOnlyFeature;
    private Boolean recordGame;

    private GUIFrame gameGui;

    private NewGameWestInnerPanel westPanel;
    private NewGameEastInnerPanel eastPanel;
    private NewGameSouthInnerPanel southPanel;


    public CreateGame(final GUIFrame gui) {
        this.gameGui = gui;
    }

    public void getCustomGameData(final NewGamePanel newGamePanel) {
        this.westPanel = newGamePanel.getLeftPanel();
        this.eastPanel = newGamePanel.getRightPanel();
        this.southPanel = newGamePanel.getSouthPanel();
        String[] tempValues;

        //Code adapted from Stef Heylen answer: JtextField to int[] array?
        //Available at: https://stackoverflow.com/questions/17256689/jtextfield-to-int-array
        String snakeValues = westPanel.getSnakeChoiceField();
        tempValues = snakeValues.split(",");
        snakes = new Integer[tempValues.length];

        for (int i = 0; i < tempValues.length; i++) {
            try {
                snakes[i] = Integer.parseInt((tempValues[i].trim()));
            } catch (NumberFormatException additionError) {
            }
        }

        String ladderValues = westPanel.getLadderChoiceField();
        tempValues = ladderValues.split(",");
        ladders = new Integer[tempValues.length];

        for (int i = 0; i < tempValues.length; i++) {
            try {
                ladders[i] = Integer.parseInt((tempValues[i].trim()));
            } catch (NumberFormatException additionError) {
            }
        }
        try {
            tempPlayerCount = westPanel.getPlayerCountField();
        } catch (NumberFormatException pc) {
            tempPlayerCount = 2;
            gameGui.appendTextToPanel("No player count entered. Player count set to 2.\n");

        }
        try {
            diceCount = westPanel.getDiceCountChoiceField();
        } catch (NumberFormatException dc) {
            diceCount = 1;
            gameGui.appendTextToPanel("No die amount entered. Dice count set to 1.\n");
        }
        try {
            diceFaces = westPanel.getDiceFaceChoiceField();
        } catch (NumberFormatException df) {
            diceFaces = 6;
            gameGui.appendTextToPanel("No Dice face count entered. Dice face count set to 6.\n");
        }

        if (snakes.length == 1) {
            gameGui.appendTextToPanel("No snakes have been added into game.\n");
        }

        if (ladders.length == 1) {
            gameGui.appendTextToPanel("No ladders have been added into game.\n");
        }


        if (eastPanel.getBoostChoiceField() != null) {

            String boostValues = eastPanel.getBoostChoiceField();
            tempValues = boostValues.split(",");

            boosts = new Integer[tempValues.length];

            for (int i = 0; i < tempValues.length; i++) {
                try {
                    boosts[i] = Integer.parseInt((tempValues[i].trim()));
                } catch (NumberFormatException additionError) {
                }
            }
        }

        this.recordGame = eastPanel.getRecordCheckBox();
        this.winningSquareOnlyFeature = eastPanel.getWinningCheckBox();

        this.tempBoardSize = southPanel.getBoardSize();
        gameGui.appendTextToPanel("Board size set to " + tempBoardSize + "x" + tempBoardSize + ".\n");

    }
    public Game buildGame() {
        Game newCustomGame = null;
        gameGui.appendTextToPanel("-----------------------------------------------------------------------|\n");
            if (winningSquareOnlyFeature) {
                if (boosts != null) {
                    if (boosts.length > 0) {
                        newCustomGame = new GameBuilder()
                                .withBoardSize(tempBoardSize)
                                .withPlayers(tempPlayerCount)
                                .withSnakes(snakes)
                                .withLadders(ladders)
                                .withBoosts(boosts)
                                .buildWithWinningSquare();
                    }
                } else {
                    newCustomGame = new GameBuilder()
                            .withBoardSize(tempBoardSize)
                            .withPlayers(tempPlayerCount)
                            .withSnakes(snakes)
                            .withLadders(ladders)
                            .buildWithWinningSquare();
                }
            } else if (boosts != null) {
                if (boosts.length > 0) {
                    newCustomGame = new GameBuilder()
                            .withBoardSize(tempBoardSize)
                            .withPlayers(tempPlayerCount)
                            .withSnakes(snakes)
                            .withLadders(ladders)
                            .withBoosts(boosts)
                            .build();
                }
            } else {
                newCustomGame = new GameBuilder()
                        .withBoardSize(tempBoardSize)
                        .withPlayers(tempPlayerCount)
                        .withSnakes(snakes)
                        .withLadders(ladders)
                        .build();
            }

        return newCustomGame;
    }

    public void getLoadedGameData(final GameFile loadGameChoice) {
        snakes = new Integer[loadGameChoice.getGameSnakes().size()];
        ladders = new Integer[]

    }

    public Integer getDiceFaces() {
        return diceFaces;
    }

    public Integer getDiceCount() {
        return diceCount;
    }



}