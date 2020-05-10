package com.cm6123.snl.GUI;

import com.cm6123.snl.*;
import com.cm6123.snl.GUI.PanelBackgroundLogic.CreateGame;
import com.cm6123.snl.GUI.Panels.*;
import com.cm6123.snl.GUI.Panels.NewGamePanels.NewGameParentPanel;
import com.cm6123.snl.dice.DiceSet;
import com.cm6123.snl.gameDB.DBGameFile;
import com.cm6123.snl.gameDB.EditDataDBManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * JFrame for entire GUI window. only called during main, data is swapped inside when navigating
 * to keep frame consistency.
 */
public class GUIFrame extends JFrame {
    /**
     * Object holds text panel used across most panels for addition.
     */
    private GameTextPanel textPanel;
    /**
     * Indicates to JFrame and other panels the current panel in use for potential amendments.
     */
    private JPanel currentPanel;
    /**
     * Holds editor Panel, allowing specific edit panel to run based upon Panel choice of Edit enum.
     */
    private EditorMenuPanel editorMenuPanel;
    /**
     * Holds load panel. GUIFrame addition so label on panel can be amended via runloadedgame switch case.
     */
    private LoadGamePanel loadGamePanel;
    /**
     * Holds new game panel. GUIFrame addition for error label on panel to be amended via runcustomgame switch case.
     */
    private NewGameParentPanel newGamePanel;
    /**
     * Hold current running game panel. GUIFrame addition for allowing repeatgame calls.
     */
    private RunGamePanel runGamePanel;
    /**
     * Holds menuBar object on JFrame.
     */
    private MenuBar gameMenu;
    /**
     * Holds current game created on game creation switch cases.
     */
    private Game newGame;
    /**
     * Holds current dice used by game.
     */
    private DiceSet diceSet;



    /**
     * Holds current save data pulled from loadgame for use in repeatgame switch case to restart game.
     */
    private DBGameFile dbGameFile;
    /**
     * Holds logic & details of any custom game created.
     */
    private CreateGame customGame;
    /**
     * Determines whether database has been loaded for JButton enabling options.
     */
    private Boolean dataBaseConnection;
    /**
     * Determines whether a custom game was loaded for saving options.
     */
    private Boolean loaded;



    /**
     * Records the database ID of a loaded game for saving options.
     */
    private Integer loadedGameId;

    /**
     * Frame given default object sets that appear throughout program.
     */
    public GUIFrame() {
        super("Snakes & Ladders Game"); //Window title.
        dataBaseConnection = false; //No buttons which require database connection are enabled when GUI opened.
        textPanel = new GameTextPanel(); //Panel used by all panels to append text to for user.
        gameMenu = new MenuBar(this); //Navigation bar at top of screen.
        setJMenuBar(gameMenu.getMenuBar());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        selectWindow("menu"); //Menu panel automatically chosen upon program launch.
        pack();
        setVisible(true);
        //Size of window cannot be resized below dimensions given in order for panel data to remain visible.
        setMinimumSize(new Dimension(800, 600));
    }

    /**
     * Chooses panel to show based upon choice made. Defaults to menu upon startup.
     * @param windowChoice - window choice made by various panels & object.
     */
    public void selectWindow(final String windowChoice) {

         //Textpanel addition ignored on loadGamePanel & NewGamepanel. MainMenuPanel removes at switch case.
        if (windowChoice != "loadgame" && windowChoice != "newgame") {
            add(textPanel, BorderLayout.CENTER);
        }
        switch (windowChoice.toLowerCase()) { //Additional validation.
            case "menu"://Main menu choice.
                getContentPane().remove(textPanel); //Removes the textPanel from JFrame if present.
                MainMenuPanel mainMenuPanel = new MainMenuPanel(this);
                if (dataBaseConnection) { //method called to enable frontpage buttons if connection already on.
                    mainMenuPanel.enableFrontPage();
                }

                if (currentPanel == null) { //MenuPanel created if loading for first time, otherwise use existing.
                    currentPanel = mainMenuPanel;
                    mainMenuPanel.createMenuPanel();
                } else {
                    //Call method that swaps current panel loaded in with new panel. parses in location of new panel.
                    swapPanel(this, currentPanel, mainMenuPanel.createMenuPanel(), BorderLayout.CENTER);
                    //Indicates current GUIFrame panel in use is mainMenuPanel.
                    currentPanel = mainMenuPanel;
                }
                break;
            //Navigation to new Game Panel.
            case "newgame":
                customGame = null; //Removes any custom game currently saved into the JFrame.
                getContentPane().remove(textPanel); //remove text panel from JFrame if present.
                getContentPane().remove(currentPanel); //remove the current panel from JFrame.
                revalidate();
                newGamePanel = new NewGameParentPanel(this);
                //Swap new panel into JFrame. Assigns currentPanel to a newGamePanel.
                swapPanel(this, currentPanel, newGamePanel.createNewGamePanel(), BorderLayout.CENTER);
                currentPanel = newGamePanel;
                break;

            case "rundefaultgame":
                //Wipes text box to ensure a clean panel for user readability.
                textPanel.wipeTextBox();
                getContentPane().remove(currentPanel);
                revalidate();
                loaded = false; //Signifies that game was not loaded for saving purposes.
                Integer defaultGridChoice = 5; //Variable assigned number for later RunGamePanel creation.
                //Stores default values into treemap to provide identical data layout for creating game.
                TreeMap defaultSpecials = addTestDefaultValues();
                // Default game uses set values & default build constructor.
                newGame = new GameBuilder()
                        .withBoardSize(5)
                        .withPlayers(2)
                        .withSnakes(14, 5, 20, 11)
                        .withLadders(3, 12, 13, 17)
                        .build();
                //Defines dice.
                diceSet = new DiceSet(6, 1);
                //Constructor passes all values made into panel to design & load the choices made.
                runGamePanel = new RunGamePanel(this, newGame, diceSet, defaultGridChoice, defaultSpecials, loaded,
                        loadedGameId);
                // Swaps panel.
                swapPanel(this, currentPanel, runGamePanel.createRunGamePanel(), BorderLayout.WEST);
                currentPanel = runGamePanel;
                break;
            //Navigation to RunGamePanel along with creation of Game object.
            case "runcustomgame":
                textPanel.wipeTextBox();
                getContentPane().remove(currentPanel);
                revalidate();
                Integer customGridChoice;
                TreeMap customSpecials;
                loaded = false;
                //CreateGame object created to build the game from data provided.
                customGame = new CreateGame(this);
                try { //validation to ensure parsed data fit the defined game creation design tools.
                    customGame.getCustomGameData(newGamePanel); //Pulls all data inserted into the NewGamePanel fields.
                    customGridChoice = customGame.getBoardSize(); //Separately grabs board size.
                    customSpecials = customGame.getAllSpecials(); //separately grabs all special squares.

                    try { //additional validation to confirm data matches gamebuilder tools (e.g. no square clashes)/
                        newGame = customGame.buildGame();

                        //Dice must both be integers to be accepted.
                        diceSet = new DiceSet(customGame.getDiceFaces(), customGame.getDiceCount());

                        runGamePanel = new RunGamePanel(this, newGame, diceSet, customGridChoice, customSpecials,
                                loaded, loadedGameId);
                        swapPanel(this, currentPanel, runGamePanel.createRunGamePanel(), BorderLayout.WEST);
                        //Swaps current panel.
                        currentPanel = runGamePanel;

                    } catch (NullPointerException n) {
                        // Catches error when boost checkbox ticked but no values added to boost JTextField.
                        selectWindow("newgame");
                        newGamePanel.getSouthPanel().setErrorLabel("Boost ticked with no entry! Please try again.");
                    } catch (IllegalStateException i) {
                        //Catches errors relating to incorrect data.
                        selectWindow("newgame");
                        newGamePanel.getSouthPanel().setErrorLabel("Entry error! Please try again.");
                    }
                } catch (IllegalStateException e) {
                    //Catches square classes from gamebuilder object instantiation inside customGame.BuildGame method.
                    selectWindow("newgame");
                    newGamePanel.getSouthPanel().setErrorLabel("Illegal square entry! Please try again.");
                }
                break;
            //Reloads RunGamePanel with identical Game data as a new object.
            case "runrepeatgame":
                textPanel.wipeTextBox();
                getContentPane().remove(currentPanel);
                revalidate();
                RunGamePanel duplicateGamePanel; //Object initialised for holding a game repeat.
                Integer repeatGridChoice = null;
                TreeMap repeatSpecials = null;
                loaded = false;

                if (customGame == null) { //repeat game will intake a game with default values if not custom.
                    repeatGridChoice = 5;
                    repeatSpecials = addTestDefaultValues();
                    Game loadGame = new GameBuilder()
                            .withBoardSize(repeatGridChoice)
                            .withPlayers(2)
                            .withSnakes(14, 5, 20, 11)
                            .withLadders(3, 12, 13, 17)
                            .build();
                } else {
                    try { //Validation for if a custom game is available to use.
                        try { //Attempts to grab a custom game made from panel.
                            customGame.getCustomGameData(newGamePanel);
                        } catch (NullPointerException f) {
                            //If custom game not found, game pulls data from current DBGameFile & rebuilds.
                            customGame.getLoadedGameData(dbGameFile);
                            //Frame marked as using loaded game for saving purposes.
                            loaded = true;
                        }
                        newGame = customGame.buildGame();
                        repeatGridChoice = customGame.getBoardSize(); //Current game size.
                        repeatSpecials = customGame.getAllSpecials(); //Current specials used.
                    } catch (NullPointerException n) {
                        n.printStackTrace();
                    }
                }
                duplicateGamePanel = new RunGamePanel(this, newGame, diceSet, repeatGridChoice, repeatSpecials,
                        loaded, loadedGameId);
                swapPanel(this, currentPanel, duplicateGamePanel.createRunGamePanel(), BorderLayout.WEST);
                //Swaps current panel to the duplicate game.
                currentPanel = duplicateGamePanel;
                break;

            case "loadgame": //Navigation to load game panel.
                getContentPane().remove(textPanel);
                getContentPane().remove(currentPanel);
                revalidate();
                //Instantiation of new panel.
                loadGamePanel = new LoadGamePanel(this);

                swapPanel(this,
                        currentPanel,
                        loadGamePanel.createloadGamePanel(),
                        BorderLayout.CENTER);
                //Swaps current panel to game load panel.
                currentPanel = loadGamePanel;
                break;
            //Navigation to RunGamePanel along with Game Object creation based upon database information.
            case "runloadedgame":
                textPanel.wipeTextBox();
                getContentPane().remove(currentPanel);
                revalidate();
                Integer loadedGridChoice;
                TreeMap loadedSpecials;
                //Marks JFrame as using a loaded game for saving purposes.
                loaded = true;

                try {
                    newGame = customGame.buildGame(); //Loaded game converted into custom game.
                    loadedGridChoice = customGame.getBoardSize();
                    loadedSpecials = customGame.getAllSpecials();

                    diceSet = new DiceSet(customGame.getDiceFaces(), customGame.getDiceCount());

                    runGamePanel = new RunGamePanel(this, newGame, diceSet, loadedGridChoice, loadedSpecials, loaded,
                            loadedGameId);
    //              //Loaded game includes stored database player position. Method obtains these for each player.
                    runGamePanel.addLoadedPlayerPositions(customGame.getPlayerPositions());
                    swapPanel(this, currentPanel, runGamePanel.createRunGamePanel(), BorderLayout.WEST);
                    //Swaps current panel to run game panel.
                    currentPanel = runGamePanel;
                } catch (IllegalStateException error) {
                    //GameBuilder errors reload the loadGamePanel and updates the error label.
                    selectWindow("loadgame");

                    loadGamePanel.setErrorLabel("There is an illegal square clash in the file. "
                            + "Please amend using editor tool.");
                } catch (IndexOutOfBoundsException oob) {
                    //Catches any snake/ladder edited in editor choice menu that falls beyond board size.
                    selectWindow("loadgame");
                    loadGamePanel.setErrorLabel("There is a square landing outside of board size in the file. "
                            + "Please amend using editor tool.");
                }
                break;
            //Navigation to the editor submenu.
            case "editormenu":
                textPanel.wipeTextBox();
                getContentPane().remove(currentPanel);
                revalidate();
                editorMenuPanel = new EditorMenuPanel(this);

                swapPanel(this, currentPanel, editorMenuPanel.createCreationPanel(), BorderLayout.WEST);
                //Swaps current panel for the editor menu.
                currentPanel = editorMenuPanel;
                break;
            //Navigation to edit Panels depending on edit JButton choice on EditorMenuPanel.
            case "newedit":
                getContentPane().remove(currentPanel);
                revalidate();
                //labels, panels & data appended to panel based upon edit choice.
                Edit newAdditionChoice = editorMenuPanel.getEditChoice();
                //Confirms to panel the choice of edit.
                EditorChoicePanel editPanel = new EditorChoicePanel(this, newAdditionChoice);

                swapPanel(this, currentPanel, editPanel.editChoicePanel(), BorderLayout.WEST);
                //Changes current panel to edit choice.
                currentPanel = editPanel;

                handleDbEdit(editPanel);
                break;
            default:
                break;
        }
    }

    /**
     * Method removes panel currently in use (typically currentPanel) with the panel of choice from switch case.
     * @param currentFrame - this (GUIFrame)
     * @param oldPanel - panel currently in use.
     * @param newPanel - panel being created for appending onto JFrame.
     * @param borderLayout - Position of the panel.
     */
    public void swapPanel(final JFrame currentFrame,
                          final JPanel oldPanel,
                          final JPanel newPanel,
                          final String borderLayout) {

        currentFrame.remove(oldPanel);
        currentFrame.add(newPanel, borderLayout);
        currentFrame.revalidate();
    }
    /**
     * TreeMap created of values used by default game, passed into createGame object to mimic method of customgame.
     * @return values - TreeMap of values as integer arrays.
     */
    private TreeMap<String, Integer[]> addTestDefaultValues() {
        TreeMap<String, Integer[]> values = new TreeMap<>();

        values.put("snakes", new Integer[]{14, 5, 20, 11});
        values.put("ladders", new Integer[]{3, 12, 13, 17});

        return values;
    }

    /**
     * Add text to panel - used by most panels to access the TextPanel object on the JFrame. main reason for JFrame
     * parameter in most objects.
     * @param text - String data to add into JTextArea.
     */
    public void appendTextToPanel(final String text) {
        textPanel.appendText(text);
    }

    /**
     * Boolean that informs JFrame that database has been connect to.
     * @param databaseConnected - boolean from CreateDBManager that confirms loaded.
     */
    public void setDataBaseConnection(final Boolean databaseConnected) {
        dataBaseConnection = true;
    }

    /**
     * Changes current game file held within the JFrame to the parsed parameter.
     * @param gameFile
     */
    public void setDbGameFile(final DBGameFile gameFile) {
        this.dbGameFile = gameFile;
    }

    /**
     * Checks JFrame to confirm whether database is loaded - used for enabling JButtons & JMenuItems in respective
     * panels.
     * @return Boolean of confirmation.
     */
    public Boolean getDatabaseConnection() {
        return dataBaseConnection;
    }

    /**
     * Accessed by MainMenuPanel to activate Menubar navigation relating to database.
     * @return the game menu bar.
     */
    public MenuBar getGameMenu() {
        return gameMenu;
    }

    /**
     * Used by RunGamePanel to determiner whether to update gameover into database.
     * @return loaded - was the game loaded from file?
     */
    public Boolean getLoaded() {
        return loaded;
    }

    /**
     * Assigns current game Creation object data from a loaded save.
     * @param loadedGameData - game data from database.
     */
    public void setCreatedGame(final CreateGame loadedGameData) {
        customGame = loadedGameData;
    }

    /**
     * Sets current ID to gameID from database, ensuring the link never breaks while in use.
     * @param gameID
     */
    public void setID(final Integer gameID) {
        loadedGameId = gameID;
    }

    /**
     * Changes game data depending on Edit enum choice for each edit window.
     * @param editPanel
     */
    public void handleDbEdit(final EditorChoicePanel editPanel) {

        editPanel.setFormListener(new FormListener() {

            /**
             * Appends text to GameTextPanel if incorrect entry.
             */
            public void incorrectEntryMessage() {
                textPanel.appendText("|------------------------------------------------------|"
                        + "\n| ERROR - INCORRECT ENTRY MADE  |"
                        + "\n|------------------------------------------------------|\n");
            }
            /**
             * Sources data from entered JTextFields and changes data in database depending on given locatoin in
             * LoadingFormEvent.
             * @param data data sourced from EditChoicePanel.
             */
            public void formDatabaseEntry(final LoadingFormEvent data) {
                //All entries held. from JTextfields.
                Integer firstFieldEntry;
                Integer secondFieldEntry;
                Boolean newSquareType;

                //Confirms if data sourced is part of existing game (applicable to snakes/ladders/boosts).
                if (data.getGameID() != null) {
                    //Grabs data from both fields.
                    firstFieldEntry = data.getFirstEntry();
                    secondFieldEntry = data.getSecondEntry();
                    //Boosts & players do not have a second field - condition covered here.
                    if (secondFieldEntry == null) {
                        newSquareType = editPanel.entryValidation(editPanel.getAdditionChoice(),
                                firstFieldEntry);
                    } else {
                        newSquareType = editPanel.entryValidation(editPanel.getAdditionChoice(),
                                firstFieldEntry, secondFieldEntry);
                    }
                    //Data added depending on enum given in FormEvent object.
                    if (newSquareType && data.getEditChoice() == Edit.SNAKE) {
                        //Confirmation of addition passed to GameTextPanel's JTextArea.
                        appendTextToPanel("Snake Head changed to position " + firstFieldEntry
                                + "\n" + "Snake Tail changed to position " + secondFieldEntry + "\n");
                        //Static method to add data depending on entry.
                        EditDataDBManager.editSnakeOrLadderData(data);
                        selectWindow("newedit");

                    } else if (newSquareType && data.getEditChoice() == Edit.LADDER) {
                        appendTextToPanel("Ladder Foot changed to position " + firstFieldEntry
                                + "\n" + "Ladder Top changed to position " + secondFieldEntry + "\n");
                        EditDataDBManager.editSnakeOrLadderData(data);
                        selectWindow("newedit");

                    } else if (newSquareType && data.getEditChoice() == Edit.BOOST) {
                        appendTextToPanel("Boost square changed to position " + firstFieldEntry + "\n");
                        EditDataDBManager.editBoostData(data);
                        selectWindow("newedit");

                    } else {
                        incorrectEntryMessage();
                    }
                //Confirms if data passed into object contains a playername string, confirming player name change.
                } else if (data.getPlayerNameEntry() == null) {

                    firstFieldEntry = data.getFirstEntry();
                    secondFieldEntry = data.getSecondEntry();

                    appendTextToPanel("Die Changed:\n"
                            + "Die amount: " + firstFieldEntry
                            + "\nDie faces: " + secondFieldEntry + "\n");
                    EditDataDBManager.editDiceData(data);
                    selectWindow("newedit");

                //Object can only be a dice if other conditions not met.
                } else {
                    String playerFieldEntry = data.getPlayerNameEntry();
                    appendTextToPanel("player changed to name: " + playerFieldEntry + "\n");
                    EditDataDBManager.editPlayerData(data);
                    selectWindow("newedit");
                }
            }
        });
    }
}
