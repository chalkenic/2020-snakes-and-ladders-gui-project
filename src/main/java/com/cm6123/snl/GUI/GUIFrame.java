package com.cm6123.snl.GUI;

import com.cm6123.snl.*;
import com.cm6123.snl.GUI.PanelBackgroundLogic.CreateGame;
import com.cm6123.snl.GUI.Panels.*;
import com.cm6123.snl.GUI.Panels.NewGamePanels.NewGameParentPanel;
import com.cm6123.snl.dice.DiceSet;
import com.cm6123.snl.gameDB.DBGameFile;
import com.cm6123.snl.gameDB.EditDataDBManager;
//import com.cm6123.snl.GUI.Panels.EditorChoicePanel;
//import com.cm6123.snl.GUI.Panels.GameTextPanel;
//import com.cm6123.snl.GUI.Panels.SidePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class GUIFrame extends JFrame {

    private int counter = 0;
    private JLabel label;
    private JFrame frame;
    private JButton button;
    //    private JTextArea textArea;
    private GameTextPanel textPanel;
    //    private GameToolbarPanel toolbar;
    private JPanel panelContainer;
    private JPanel currentPanel;
    //    private LayoutManager layout;
    private EditorMenuPanel creationMenuPanel;
    private MainMenuPanel mainMenuPanel;
    private LoadGamePanel loadGamePanel;
    private NewGameParentPanel newGamePanel;
    private RunGamePanel runGamePanel;
    private MenuBar gameMenu;

    private Game newGame;
    private DiceSet diceSet;
    private DBGameFile dbGameFile;

    private CreateGame customGame;

    private Boolean dataBaseConnection;
    private Boolean loaded;
    private Integer loadedGameId;

    public GUIFrame() {
        super("Snakes & Ladders");
        dataBaseConnection = false;


        panelContainer = new JPanel();

        textPanel = new GameTextPanel();

        gameMenu = new MenuBar(this);

        setJMenuBar(gameMenu.getMenuBar());



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Snakes & Ladders Game");

        selectWindow("menu");
        pack();
        setVisible(true);
        frame = this;
        setMinimumSize(new Dimension(800, 600));

    }

    public void selectWindow(final String windowChoice) {

        if (windowChoice != "loadgame" && windowChoice != "newgame") {
            add(textPanel, BorderLayout.CENTER);
        }
        switch (windowChoice.toLowerCase()) {
            case "menu":

                getContentPane().remove(textPanel);
//                getContentPane().remove(toolbar);
//                revalidate();
                mainMenuPanel = new MainMenuPanel(this);

                if (dataBaseConnection) {
                    mainMenuPanel.enableFrontPage();
                }

                if (currentPanel == null) {
                    currentPanel = mainMenuPanel;
                    mainMenuPanel.createMenuPanel();
                } else {
                    swapPanel(this,
                            currentPanel,
                            mainMenuPanel.createMenuPanel(),
                            BorderLayout.CENTER);
                    currentPanel = mainMenuPanel;

                }
                break;

            case "newgame":
                customGame = null;

                getContentPane().remove(textPanel);
                getContentPane().remove(currentPanel);
                revalidate();
                newGamePanel = new NewGameParentPanel(this);

                swapPanel(this,
                        currentPanel,
                        newGamePanel.createNewGamePanel(),
                        BorderLayout.CENTER);
                currentPanel = newGamePanel;
//                cardLayout.show(panelContainer, "2");

                break;

            case "rundefaultgame":
                textPanel.wipeTextBox();
                getContentPane().remove(currentPanel);
                revalidate();
                loaded = false;


                Integer defaultGridChoice = 5;
                TreeMap defaultSpecials = addTestDefaultValues();
//                TreeMap<Integer, Integer[]> defaultSpecials = new TreeMap<>();
//                defaultSpecials.put(0, snakes);
//                defaultSpecials.put(1, ladders);

                newGame = new GameBuilder()
                        .withBoardSize(defaultGridChoice)
                        .withPlayers(2)
                        .withSnakes(14, 5, 20, 11)
                        .withLadders(3, 12, 13, 17)
                        .build();

                diceSet = new DiceSet(6, 1);

                runGamePanel = new RunGamePanel(this, newGame, diceSet, defaultGridChoice, defaultSpecials, loaded,
                        loadedGameId);

//                panelContainer.add(creationMenuPanel.createCreationPanel(), "2");
                swapPanel(this,
                        currentPanel,
                        runGamePanel.createRunGamePanel(),
                        BorderLayout.WEST);
                currentPanel = runGamePanel;

                break;

            case "runcustomgame":
                textPanel.wipeTextBox();
                getContentPane().remove(currentPanel);
                revalidate();
                Integer customGridChoice;
                TreeMap customSpecials;
                loaded = false;

                customGame = new CreateGame(this);
                try {
                    customGame.getCustomGameData(newGamePanel);
                    customGridChoice = customGame.getBoardSize();
                    customSpecials = customGame.getAllSpecials();

                    try {
                        newGame = customGame.buildGame();

                        diceSet = new DiceSet(customGame.getDiceFaces(), customGame.getDiceCount());

                        runGamePanel = new RunGamePanel(this, newGame, diceSet, customGridChoice, customSpecials, loaded,
                                loadedGameId);
                        swapPanel(this,
                                currentPanel,
                                runGamePanel.createRunGamePanel(),
                                BorderLayout.WEST);

                        currentPanel = runGamePanel;

                    } catch (NullPointerException n) {
                        selectWindow("newgame");
                        newGamePanel.getSouthPanel().setErrorLabel("Boost ticked with no entry! Please try again.");
                    } catch (IllegalStateException i) {
                        selectWindow("newgame");
                        newGamePanel.getSouthPanel().setErrorLabel("Entry error! Please try again.");
                    }
                } catch (IllegalStateException e) {
                    selectWindow("newgame");
                    newGamePanel.getSouthPanel().setErrorLabel("Illegal square entry! Please try again.");
                }







                break;

            case "runrepeatgame":

                System.out.println("am i working?");

                textPanel.wipeTextBox();
                getContentPane().remove(currentPanel);
                revalidate();
                RunGamePanel duplicateGamePanel;
                Integer repeatGridChoice = null;
                TreeMap repeatSpecials = null;
                loaded = false;


//                for (int i = 0; i < newGame.numberOfPlayers(); i++) {
//                    Player player = newGame.getCurrentPlayer();
//                    player.moveTo(newGame.getBoard().start());
//                }

                if (customGame == null) {
                    repeatGridChoice = 5;
                    repeatSpecials = addTestDefaultValues();
                    Game loadGame = new GameBuilder()
                            .withBoardSize(repeatGridChoice)
                            .withPlayers(2)
                            .withSnakes(14, 5, 20, 11)
                            .withLadders(3, 12, 13, 17)
                            .build();
                } else {
                    try {
                        try {
                            customGame.getCustomGameData(newGamePanel);
                        } catch (NullPointerException f) {
                            customGame.getLoadedGameData(dbGameFile);
                            loaded = true;
                        }
                        newGame = customGame.buildGame();
                        repeatGridChoice = customGame.getBoardSize();
                        System.out.println(repeatGridChoice);
                        repeatSpecials = customGame.getAllSpecials();
                    }
                    catch (NullPointerException n) {
                        n.printStackTrace();
                    }
                }

                duplicateGamePanel = new RunGamePanel(this, newGame, diceSet, repeatGridChoice, repeatSpecials,
                        loaded, loadedGameId);

                swapPanel(this,
                        currentPanel,
                        duplicateGamePanel.createRunGamePanel(),
                        BorderLayout.WEST);

                currentPanel = duplicateGamePanel;

                break;

            case "loadgame":
                getContentPane().remove(textPanel);
                getContentPane().remove(currentPanel);
                revalidate();
                loadGamePanel = new LoadGamePanel(this);

//                panelContainer.add(creationMenuPanel.createCreationPanel(), "2");
                swapPanel(this,
                        currentPanel,
                        loadGamePanel.createloadGamePanel(),
                        BorderLayout.CENTER);
                currentPanel = loadGamePanel;
//                cardLayout.show(panelContainer, "2");

                loadGamePanel.setFormListener(new FormListener() {

                    public void appendTextToPanel(final String text) {
                        textPanel.appendText(text);
                    }

                    public void incorrectEntryMessage() {
                        textPanel.appendText("Cannot find file!\n");
                    }

                    public void formDatabaseEntry(final LoadingFormEvent data) {
                        if (data.getLoadGameEntry() != null) {
                            Integer loadGameEntry = data.getLoadGameEntry();
                            appendTextToPanel("loaded game " + loadGameEntry + "!\n");
                        }
                    }
                });

                break;

            case "runloadedgame":
                textPanel.wipeTextBox();
                getContentPane().remove(currentPanel);
                revalidate();
                Integer loadedGridChoice;
                TreeMap loadedSpecials;
                loaded = true;
                System.out.println("#2 - file number:" + loadedGameId);

                try {
                    newGame = customGame.buildGame();
                    loadedGridChoice = customGame.getBoardSize();
                    loadedSpecials = customGame.getAllSpecials();

                    diceSet = new DiceSet(customGame.getDiceFaces(), customGame.getDiceCount());

                    runGamePanel = new RunGamePanel(this, newGame, diceSet, loadedGridChoice, loadedSpecials, loaded,
                            loadedGameId);
//                runGamePanel.addLoadedPlayers(customGame.getPlayers());
                    runGamePanel.addLoadedPlayerPositions(customGame.getPlayerPositions());
                    swapPanel(this,
                            currentPanel,
                            runGamePanel.createRunGamePanel(),
                            BorderLayout.WEST);

                    currentPanel = runGamePanel;
                } catch (IllegalStateException error) {

                    selectWindow("loadgame");
                    loadGamePanel.setErrorLabel("There is an illegal square clash in the file. "
                            + "Please amend in the editor tool.");

                }


                break;

            case "editormenu":
                textPanel.wipeTextBox();
                getContentPane().remove(currentPanel);
                revalidate();
                creationMenuPanel = new EditorMenuPanel(this);

                swapPanel(this,
                        currentPanel,
                        creationMenuPanel.createCreationPanel(),
                        BorderLayout.WEST);
                currentPanel = creationMenuPanel;

                break;

            case "newedit":
                getContentPane().remove(currentPanel);
                revalidate();
                Edit newAdditionChoice = creationMenuPanel.getAdditionChoice();
                EditorChoicePanel additionPanel = new EditorChoicePanel(this, newAdditionChoice);


                swapPanel(this,
                        currentPanel,
                        additionPanel.editChoicePanel(),
                        BorderLayout.WEST);

                currentPanel = additionPanel;



                handleDbEdit(additionPanel);


                break;
        }
    }

    public void swapPanel(final JFrame currentFrame,
                          final JPanel oldPanel,
                          final JPanel newPanel,
                          final String borderLayout) {

        currentFrame.remove(oldPanel);
        currentFrame.add(newPanel, borderLayout);
        currentFrame.revalidate();

    }

    public ArrayList addDefaultValues() {
        ArrayList<Integer[]> values = new ArrayList<>();

        values.add(new Integer[] {14, 5, 20, 11});
        values.add(new Integer[] {3, 12, 13, 17});
        return values;
    }

    public TreeMap<String, Integer[]> addTestDefaultValues() {
        TreeMap<String, Integer[]> values = new TreeMap<>();

        values.put("snakes", new Integer[]{14, 5, 20, 11});
        values.put("ladders", new Integer[]{3, 12, 13, 17});

        return values;
    }


    public void appendTextToPanel(final String text) {
        textPanel.appendText(text);
    }

    public void setDataBaseConnection(final Boolean b) {
        dataBaseConnection = true;
    }

    public Boolean getDatabaseConnection() {
        return dataBaseConnection;
    }

    public MenuBar getGameMenu() {
        return gameMenu;
    }

    public void setCreatedGame(final CreateGame loadedGameData) {
        customGame = loadedGameData;
    }

    public void setID(final Integer gameID) {
        loadedGameId = gameID;
    }

    public void handleDbEdit(final EditorChoicePanel additionPanel) {

        additionPanel.setFormListener(new FormListener() {

            public void incorrectEntryMessage() {
                textPanel.appendText("|------------------------------------------------------|"
                        + "\n| ERROR - INCORRECT ENTRY MADE  |"
                        + "\n|------------------------------------------------------|\n");
            }

            public void formDatabaseEntry(final LoadingFormEvent data) {

                Integer firstFieldEntry;
                Integer secondFieldEntry;
                Boolean newSquareType;
                Boolean secondSquareMissing = true;

                if (data.getGameID() != null) {

                    firstFieldEntry = data.getFirstEntry();
                    secondFieldEntry = data.getSecondEntry();

                    if (secondFieldEntry == null) {
                        newSquareType = additionPanel.entryValidation(additionPanel.getAdditionChoice(),
                                firstFieldEntry);
                    } else {
                        newSquareType = additionPanel.entryValidation(additionPanel.getAdditionChoice(),
                                firstFieldEntry, secondFieldEntry);
                    }

                    if (newSquareType && data.getEditChoice() == Edit.SNAKE) {
//                                EditDataDBManager.editSaveData(data);
                        appendTextToPanel("Snake Head changed to position " + firstFieldEntry
                                + "\n" + "Snake Tail changed to position " + secondFieldEntry + "\n");
                        EditDataDBManager.editSnakeOrLadderData(data);
                        selectWindow("newedit");

                    } else if (newSquareType && data.getEditChoice() == Edit.LADDER) {
                        appendTextToPanel("Ladder Foot changed to position " + firstFieldEntry
                                + "\n" + "Ladder Top changed to position " + secondFieldEntry + "\n");
                        EditDataDBManager.editSnakeOrLadderData(data);
                        selectWindow("newedit");

                        System.out.println("JDBC LADDER TO GO HERE");
                    } else if (newSquareType && data.getEditChoice() == Edit.BOOST) {
                        appendTextToPanel("Boost square changed to position " + firstFieldEntry + "\n");
                        EditDataDBManager.editBoostData(data);
                        selectWindow("newedit");

                    } else {
                        incorrectEntryMessage();
                    }

                } else if (data.getPlayerNameEntry() == null) {

                    firstFieldEntry = data.getFirstEntry();
                    secondFieldEntry = data.getSecondEntry();

                    appendTextToPanel("Die Changed:\n"
                            + "Die amount: " + firstFieldEntry
                            + "\nDie faces: " + secondFieldEntry + "\n");
                    EditDataDBManager.editDiceData(data);
                    selectWindow("newedit");


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
//    }

//    public JPanel getCurrentPanel() {
//        return currentPanel;
//    }

//        label = new JLabel("Number of clicks innit: 0");


//        JPanel panel = new JPanel();
//        panel.
//        panel.setLayout(new BorderLayout(0, 1));
//        panel.add(button);
//        panel.add(label);

//        add(panel, BorderLayout.CENTER);


//    public GUIFrame callGUI() {
//        return this;
//    }


//    @Override
//    public void actionPerformed(final ActionEvent e) {
//        counter++;
//        textArea.setText("Number of clicks innit: " + counter);
//    }

//    private JMenuBar createMenuBar() {
//        JMenuBar gameMenu = new JMenuBar();
//
//        JMenu fileMenu = new JMenu("File");
//        JMenuItem mainMenu = new JMenuItem("Main Menu");
//        mainMenu.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(final ActionEvent e) {
//                selectWindow("menu");
//            }
//        });
//        JMenuItem exportData = new JMenuItem("Load Game...");
//        JMenuItem importData = new JMenuItem("Save Game...");
//        JMenuItem exitData = new JMenuItem("Exit Program");
//        fileMenu.add("Main Menu");
//        fileMenu.addSeparator();
//        fileMenu.add(exportData);
//        fileMenu.add(importData);
//        fileMenu.addSeparator();
//        fileMenu.add(exitData);
//
//
//        JMenu windowMenu = new JMenu("Window");
//
//        JMenu showMenu = new JMenu("Navigate to");
//        JMenuItem showEditSnakes = new JMenuItem("Edit Snakes");
//        JMenuItem showEditLadders = new JMenuItem("Edit Ladders");
//        JMenuItem showEditBoosts = new JMenuItem("Edit Boosts");
//        JMenuItem showEditPlayers = new JMenuItem("Edit Players");
//        JMenuItem showEditDice = new JMenuItem("Edit Dice");
//
//        showMenu.add(showEditSnakes);
//        showMenu.add(showEditLadders);
//        showMenu.add(showEditBoosts);
//        showMenu.add(showEditPlayers);
//        showMenu.add(showEditDice);
//        windowMenu.add(showMenu);
//
//        gameMenu.add(fileMenu);
//        gameMenu.add(windowMenu);
//
//
//        return gameMenu;
//
//    }
