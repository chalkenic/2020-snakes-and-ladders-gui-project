package com.cm6123.snl.GUI.PanelBackgroundLogic;

import com.cm6123.snl.GUI.GUIFrame;
import com.cm6123.snl.GUI.Panels.NewGamePanels.NewGameEastInnerPanel;
import com.cm6123.snl.GUI.Panels.NewGamePanels.NewGameParentPanel;
import com.cm6123.snl.GUI.Panels.NewGamePanels.NewGameSouthInnerPanel;
import com.cm6123.snl.GUI.Panels.NewGamePanels.NewGameWestInnerPanel;
import com.cm6123.snl.Game;
import com.cm6123.snl.GameBuilder;
import com.cm6123.snl.gameDB.DBGameFile;

import java.util.TreeMap;

public class CreateGame {

    private Integer[] snakes;

    private Integer[] ladders;

    private Integer[] boosts;

    private TreeMap<String, Integer[]> allSpecials;

    private Integer[] positions;

    private Integer boardSize;
    private Integer playerCount;
    private Integer diceCount;
    private Integer diceFaces;

    private Boolean boostGameFeature;
    private Boolean winningSquareOnlyFeature;
    private Boolean recordGame;

    private GUIFrame gameGui;
    private Integer gameID;



    private NewGameWestInnerPanel westPanel;
    private NewGameEastInnerPanel eastPanel;
    private NewGameSouthInnerPanel southPanel;
    private Boolean noPlayersGiven = false;
    private Boolean noDiceFacesGiven = false;
    private Boolean noDiceCountgiven = false;
    private DBGameFile gamefile;



    public CreateGame(final GUIFrame gui) {
        this.gameGui = gui;
    }

    public void getCustomGameData(final NewGameParentPanel newGamePanel) {
        this.westPanel = newGamePanel.getLeftPanel();
        this.eastPanel = newGamePanel.getRightPanel();
        this.southPanel = newGamePanel.getSouthPanel();
        String[] tempValues;


        //Code adapted from Stef Heylen answer: JtextField to int[] array?
        //Available at: https://stackoverflow.com/questions/17256689/jtextfield-to-int-array
        String snakeValues = westPanel.getSnakeChoiceField();
        System.out.println(westPanel.getSnakeChoiceField().length());
        tempValues = snakeValues.split(",");
        System.out.println(tempValues);
        snakes = new Integer[tempValues.length];

        for (int i = 0; i < tempValues.length; i++) {
            try {
                snakes[i] = Integer.parseInt((tempValues[i].trim()));
            } catch (NumberFormatException additionError) {
            }
        }

        if (westPanel.getSnakeChoiceField().length() > 0 && snakes.length % 2 != 0) {

             throw new IllegalStateException("Incorrect Snake Entry - missing a tail!");

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

        if (westPanel.getLadderChoiceField().length() > 0 && ladders.length % 2 != 0) {
            throw new IllegalStateException("Incorrect Ladder Entry - missing a top!");
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

        allSpecials = new TreeMap();
        if (snakes.length > 1) {
            allSpecials.put("snakes", snakes);
        }

        if (ladders.length > 1) {
            allSpecials.put("ladders", ladders);
        }

        if (boosts != null) {
            if (boosts.length > 0) {
                allSpecials.put("boosts", boosts);
            }
        }
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
                    try {
                        if (boosts.length > 0) {
                            newCustomGame = new GameBuilder()
                                    .withBoardSize(boardSize)
                                    .withPlayers(playerCount)
                                    .withSnakes(snakes)
                                    .withLadders(ladders)
                                    .withBoosts(boosts)
                                    .buildWithWinningSquare();
                        }
                    }  catch (NullPointerException n) {
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
                try {
                    if (boosts.length > 0) {
                        newCustomGame = new GameBuilder()
                                .withBoardSize(boardSize)
                                .withPlayers(playerCount)
                                .withSnakes(snakes)
                                .withLadders(ladders)
                                .withBoosts(boosts)
                                .build();
                    }

                }  catch (NullPointerException n) {
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

    public CreateGame getLoadedGameData(final DBGameFile loadGameChoice) {
        this.gamefile = loadGameChoice;
        this.gameID = loadGameChoice.getId();
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

        return this;


    }

    public Integer getGameID() {
        return gameID;
    }
    public DBGameFile getGamefile() {
        return gamefile;
    }



    public TreeMap<String, Integer[]> getAllSpecials() {
        return allSpecials;
    }

    public Integer getDiceFaces() {
        return diceFaces;
    }

    public Integer getDiceCount() {
        return diceCount;
    }
    public Integer getBoardSize() { return boardSize; }

//
//    public Object getPlayers() {
//    }

    public Integer[] getPlayerPositions() {
        return positions;
    }
}