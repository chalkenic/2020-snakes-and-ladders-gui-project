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

/**
 * Class creates object that holds all data required to create a game.
 * 2 different types of game parsed through - Loaded & new custom.
 */
public class CreateGame {
    /**
     * Holds all snakes required for GameBuilder.
     */
    private Integer[] snakes;
    /**
     * Holds all ladders required for GameBuilder.
     */
    private Integer[] ladders;
    /**
     * Holds all boosts required for GameBuilder.
     */
    private Integer[] boosts;
    /**
     * Collation of all special squares used for creating & saving games.
     */
    private TreeMap<String, Integer[]> allSpecials;
    /**
     * Holds all current player positions for a loaded game.
     */
    private Integer[] positions;
    /**
     * Stores board grid size.
     */
    private Integer boardSize;
    /**
     * stores amount of players.
     */
    private Integer playerCount;
    /**
     * stores amount of die used.
     */
    private Integer diceCount;
    /**
     * stores size of each die.
     */
    private Integer diceFaces;
    /**
     * confirm current player turn in game (TBC).
     */
    private Integer playerTurn;
    /**
     * confirm if winning square feature on.
     */
    private Boolean winningSquareOnlyFeature;
    /**
     * Grab JFrame for textPanel additions.
     */
    private GUIFrame gameGui;
    /**
     * confirm gameID.
     */
    private Integer gameID;

    /**
     * Stores west panel inside object when method called to collect basic features when building custom game.
     */
    private NewGameWestInnerPanel westPanel;
    /**
     * Stores east panel inside object when method called to collect advanced features when building custom game.
     */
    private NewGameEastInnerPanel eastPanel;
    /**
     * Stores south pane inside object when method called to collect board size
     * when building custom game.
     */
    private NewGameSouthInnerPanel southPanel;
    /**
     * Stores Checks if no players provided during custom game building.
     */
    private Boolean noPlayersGiven = false;
    /**
     * checks if dice size provided in textfield.
     */
    private Boolean noDiceFacesGiven = false;
    /**
     * checks if dice count provided in textfield.
     */
    private Boolean noDiceCountgiven = false;
    /**
     * Stores game file when building a loaded game.
     */
    private DBGameFile gamefile;

    /**
     * Builds the object, ready for data intake via methods.
     * @param gui - holds JFrame for textpanel additions.
     */
    public CreateGame(final GUIFrame gui) {
        this.gameGui = gui;
    }

    /**
     * Builds datasets for GameBuilder class to accept.
     * @param newGamePanel - Panel holding all data from custom game builder.
     */
    public void getCustomGameData(final NewGameParentPanel newGamePanel) {
        this.westPanel = newGamePanel.getLeftPanel();
        this.eastPanel = newGamePanel.getRightPanel();
        this.southPanel = newGamePanel.getSouthPanel();
        //Temporary array for holding values in JTextfields.
        String[] tempValues;

        //Pulls data from specific field/
        //Code adapted from Stef Heylen answer: JtextField to int[] array?
        //Available at: https://stackoverflow.com/questions/17256689/jtextfield-to-int-array
        String snakeValues = westPanel.getSnakeChoiceField();
        //Commas only allowed in field, data ignored if not used. splits integers based on commas.
        tempValues = snakeValues.split(",");
        //Builds array based on amount of values in tempValue array.
        snakes = new Integer[tempValues.length];
        for (int i = 0; i < tempValues.length; i++) {
            try {
                //Converts string numbers into Integers, appends to object square value array.
                snakes[i] = Integer.parseInt((tempValues[i].trim()));
                //Any incorrect value halts game from being build.
            } catch (NumberFormatException additionError) {
            }
        }
        //Ensures an odd number of snakes cannot be entered. indicates to JFrame the below error has occurred.
        if (westPanel.getSnakeChoiceField().length() > 0 && snakes.length % 2 != 0) {

            throw new IllegalStateException("Incorrect snake entry - missing a tail!");

        }
        //Identical to snakeChoiceField addition.
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
            throw new IllegalStateException("Incorrect ladder entry - missing a top!");
        }
        //Checks if player count entered. null/incorrect value defaults entry to 2.
        try {
            playerCount = westPanel.getPlayerCountField();
            //Player count must be between 2 and 5.
            if (playerCount < 2 || playerCount > 5) {
                throw new IllegalStateException(("incorrect player entry - must be between 2 and 5!"));
            }
        } catch (NumberFormatException pc) {
            noPlayersGiven = true;
            playerCount = 2;
        }
        //Checks if dice count entered. nul//incorrect value defaults entry to 1.
        try {
            diceCount = westPanel.getDiceCountChoiceField();
        } catch (NumberFormatException dc) {
            noDiceCountgiven = true;
            diceCount = 1;
        }
        //Checks if dice face entered. nul//incorrect value defaults entry to 6.
        try {
            diceFaces = westPanel.getDiceFaceChoiceField();
        } catch (NumberFormatException df) {
            noDiceFacesGiven = true;
            diceFaces = 6;
        }
        //Determines whether boost feature switched on - counts values in field if true.
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
        //Checks if winning square is on - adds result to field.
        this.winningSquareOnlyFeature = eastPanel.getWinningCheckBox();
        //Determines board size.
        this.boardSize = southPanel.getBoardSize();
        //Appends all new special square values into TreeMap for saving & building game.
        allSpecials = new TreeMap();
        //Integer must have at elast 1 snake head & snake tail to continue.
        if (snakes.length > 1) {
            allSpecials.put("snakes", snakes);
        }
        if (ladders.length > 1) {
            allSpecials.put("ladders", ladders);
        }
        //Boosts must be enabled for values to be entered.
        if (boosts != null) {
            if (boosts.length > 0) {
                allSpecials.put("boosts", boosts);
            }
        }
    }

    /**
     * Builds game based upon data passed into object.
     * @return build game object.
     */
    public Game buildGame() {
        //GameTextPanel messages passed for player to check if additions made via GUI & changes made.
        if (noPlayersGiven) {
            gameGui.appendTextToPanel("No player count number entered. Player count set to 2.\n");
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
        //Game built as per basic GameBuilder rules. different versions exist:
        //1. Winning square & boost square on.
        //2. Winning square on.
        //3. Boost Square on.
        //4. No features on.
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

    /**
     * Builds game data base upon data provided from database file.
     * @param loadGameChoice - game file containing data sourced from database.
     * @return CreateGame object.
     */
    public CreateGame getLoadedGameData(final DBGameFile loadGameChoice) {
        this.gamefile = loadGameChoice;
        this.gameID = loadGameChoice.getGameID();
        this.playerTurn = loadGameChoice.getGamePlayerTurn();
        //Arrays determined by amount of squares grabbed from each game.
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
        //TextPanel appends similar to customgame depending on whether certain data available.
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
        this.winningSquareOnlyFeature = loadGameChoice.getWinningSquareFeature();
        this.boardSize = loadGameChoice.getBoardSize();
        gameGui.appendTextToPanel("Board size set to " + boardSize + "x" + boardSize + ".\n");
        return this;
    }
    /**
     * Sources gameID for inserting into RunGamePanel, determining which row to save any data into in database.
     * @return gameID for saving.
     */
    public Integer getGameID() {
        return gameID;
    }
    /**
     * Sources all specials for when repeating an existing game.
     * @return TreeMap of all specials.
     */
    public TreeMap<String, Integer[]> getAllSpecials() {
        return allSpecials;
    }

    /**
     * Get size of dice.
     * @return diceFace - size fo dice.
     */
    public Integer getDiceFaces() {
        return diceFaces;
    }

    /**
     * Get amount of dice used.
     * @return diceCount - amount used.
     */
    public Integer getDiceCount() {
        return diceCount;
    }
    /**
     * Source boardSize for RunGamePanel usage saving games.
     * @return grid size of the board.
     */
    public Integer getBoardSize() {
        return boardSize;
    }
    /**
     * Get all player positions for when moving loaded players on board.
     * @return Array containing all current loaded player positions.
     */
    public Integer[] getPlayerPositions() {
        return positions;
    }
}
