package com.cm6123.snl.GUI.PanelBackgroundLogic;

import com.cm6123.snl.GUI.GUIFrame;
import com.cm6123.snl.GUI.Panels.NewGame.NewGameEastInnerPanel;
import com.cm6123.snl.GUI.Panels.NewGame.NewGamePanel;
import com.cm6123.snl.GUI.Panels.NewGame.NewGameSouthInnerPanel;
import com.cm6123.snl.GUI.Panels.NewGame.NewGameWestInnerPanel;
import com.cm6123.snl.Game;
import com.cm6123.snl.GameBuilder;

public class CreateGame {

    private Integer[] snakes;

    private Integer[] ladders;

    private Integer[] boosts;

    private Integer[] positions;

    private Integer boardSize;
    private Integer playerCount;
    private Integer diceCount;
    private Integer diceFaces;

    private Boolean boostGameFeature;
    private Boolean winningSquareOnlyFeature;
    private Boolean recordGame;

    private GUIFrame gameGui;

    private NewGameWestInnerPanel westPanel;
    private NewGameEastInnerPanel eastPanel;
    private NewGameSouthInnerPanel southPanel;
    private Boolean noPlayersGiven = false;
    private Boolean noDiceFacesGiven = false;
    private Boolean noDiceCountgiven = false;


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
            playerCount = westPanel.getPlayerCountField();
        } catch (NumberFormatException pc) {
            noPlayersGiven = true;
            playerCount = 2;
        }
        try {
            diceCount = westPanel.getDiceCountChoiceField();
        } catch (NumberFormatException dc) {
            noDiceCountgiven = true;
            diceCount = 1;
        }
        try {
            diceFaces = westPanel.getDiceFaceChoiceField();
        } catch (NumberFormatException df) {
            noDiceFacesGiven = true;
            diceFaces = 6;
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

        this.boardSize = southPanel.getBoardSize();


    }
    public Game buildGame() {

        if (noPlayersGiven) {
            gameGui.appendTextToPanel("No player count entered. Player count set to 2.\n");
        }
        if (snakes.length == 1) {
            gameGui.appendTextToPanel("No snakes have been added into game.\n");
        }

        if (ladders.length == 1) {
            gameGui.appendTextToPanel("No ladders have been added into game.\n");
        }

        if (noDiceCountgiven) {
            gameGui.appendTextToPanel("No die amount entered. Dice count set to 1.\n");
        }

        if (noDiceFacesGiven) {
            gameGui.appendTextToPanel("No Dice face count entered. Dice face count set to 6.\n");
        }
        gameGui.appendTextToPanel("Board size set to " + boardSize + "x" + boardSize + ".\n");


        Game newCustomGame = null;
        gameGui.appendTextToPanel("-----------------------------------------------------------------------|\n");
            if (winningSquareOnlyFeature) {
                if (boosts != null) {
                    if (boosts.length > 0) {
                        newCustomGame = new GameBuilder()
                                .withBoardSize(boardSize)
                                .withPlayers(playerCount)
                                .withSnakes(snakes)
                                .withLadders(ladders)
                                .withBoosts(boosts)
                                .buildWithWinningSquare();
                    }
                } else {
                    newCustomGame = new GameBuilder()
                            .withBoardSize(boardSize)
                            .withPlayers(playerCount)
                            .withSnakes(snakes)
                            .withLadders(ladders)
                            .buildWithWinningSquare();
                }
            } else if (boosts != null) {
                if (boosts.length > 0) {
                    newCustomGame = new GameBuilder()
                            .withBoardSize(boardSize)
                            .withPlayers(playerCount)
                            .withSnakes(snakes)
                            .withLadders(ladders)
                            .withBoosts(boosts)
                            .build();
                }
            } else {
                newCustomGame = new GameBuilder()
                        .withBoardSize(boardSize)
                        .withPlayers(playerCount)
                        .withSnakes(snakes)
                        .withLadders(ladders)
                        .build();
            }

        return newCustomGame;
    }

    public CreateGame getLoadedGameData(final GameFile loadGameChoice) {
        snakes = new Integer[loadGameChoice.getGameSnakes().size()];
        ladders = new Integer[loadGameChoice.getGameLadders().size()];
        positions = new Integer[loadGameChoice.getPlayerPositions().size()];

        for (int i = 0; i < loadGameChoice.getPlayerPositions().size(); i++) {
            positions[i] = loadGameChoice.getPlayerPositions().get(i);
        }

        for (int i = 0; i < loadGameChoice.getGameSnakes().size(); i++) {
            snakes[i] = loadGameChoice.getGameSnakes().get(i);
        }

        for (int i = 0; i < loadGameChoice.getGameLadders().size(); i++) {
            ladders[i] = loadGameChoice.getGameLadders().get(i);
        }

        if (loadGameChoice.getGameBoosts() != null) {
            boosts = new Integer[loadGameChoice.getGameBoosts().size()];

            for (int i = 0; i < loadGameChoice.getGameBoosts().size(); i++) {
               boosts[i] =  loadGameChoice.getGameBoosts().get(i);
            }
        }

        try {
            playerCount = loadGameChoice.getTotalPlayers();
        } catch (NumberFormatException pc) {
            playerCount = 2;
            gameGui.appendTextToPanel("No player count entered. Player count set to 2.\n");

        }
        try {
            diceCount = loadGameChoice.getDiceCount();
        } catch (NumberFormatException dc) {
            diceCount = 1;
            gameGui.appendTextToPanel("No die amount entered. Dice count set to 1.\n");
        }
        try {
            diceFaces = loadGameChoice.getDiceFaces();
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

        this.recordGame = loadGameChoice.getRecordGameFeature();
        this.winningSquareOnlyFeature = loadGameChoice.getWinningSquareFeature();

        this.boardSize = loadGameChoice.getBoardSize();
        gameGui.appendTextToPanel("Board size set to " + boardSize + "x" + boardSize + ".\n");

        System.out.println(snakes.length);
        System.out.println(ladders.length);
//        System.out.println(boosts.length);
        System.out.println(playerCount);
        System.out.println(diceCount);
        System.out.println(diceFaces);
        System.out.println(recordGame);
        System.out.println(winningSquareOnlyFeature);
        System.out.println(boardSize * boardSize);



        return this;


    }

    public Integer getDiceFaces() {
        return diceFaces;
    }

    public Integer getDiceCount() {
        return diceCount;
    }

//
//    public Object getPlayers() {
//    }

    public Integer[] getPlayerPositions() {
        return positions;
    }
}