package com.cm6123.snl.GUI.Panels;

import com.cm6123.snl.gameDB.ConstantDatabaseName;
import com.cm6123.snl.GUI.PanelBackgroundLogic.BoardMove;
import com.cm6123.snl.GUI.GUIFrame;
import com.cm6123.snl.Game;
import com.cm6123.snl.Player;
import com.cm6123.snl.dice.DiceSet;
import com.cm6123.snl.gameDB.GameDBUtils;
import com.cm6123.snl.gameDB.SaveDataDBManager;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.TreeMap;
/**
 * Class handles the window panel for playing a game of snakes & ladders.
 */
public class RunGamePanel extends SidePanel implements ActionListener {
    /**
     * Was the game on initial Panel instantiation loaded from database file?
     */
    private Boolean loaded;
    /**
     * GUIFrame access required for method navigation calls.
     */
    private GUIFrame gameGui;
    /**
     * current Game object loaded into panel.
     */
    private Game currentGame;
    /**
     * List of players in game participating in game.
     */
    private JList gamePlayerList;
    /**
     * Players pulled from data that are to be added onto JList.
     */
    private DefaultListModel currentPlayers;
    /**
     * Label holds text to confirm if winning square on or off.
     */
    private JLabel winningSquareLabel;
    /**
     * Label above Jlist that informs of JList purpose (showing player order).
     */
    private JLabel playerOrderLabel;
    /**
     * Inform window of who's current turn it is (if not looking at JList).
     */
    private JLabel playerColourTurnLabel;
    /**
     * Inform window of where the player currently resides (if not looking at JTextArea).
     */
    private JLabel playerPositionLabel;
    /**
     * Inform window of whether the boost feature is being used.
     */
    private JLabel boostFeatureLabel;
    /**
     * Inform window of whether the winning square feature is being used.
     */
    private JLabel winningFeatureLabel;
    /**
     * Coloured label that changes depending on if boost feature is/was true when creating game.
     */
    private JLabel boostFeatureResultLabel;
    /**
     * Coloured label that changes depending on if winning square feature is/was true when creating game.
     */
    private JLabel winningFeatureResultLabel;
    /**
     * shows confirmation of which player is currently having their turn.
     */
    private JLabel playerColourTurnResultLabel;
    /**
     * shows confirmation of the player's current position.
     */
    private JLabel playerPositionResultLabel;
    /**
     * Button used to roll dice & complete a player's turn.
     */
    private JButton rollDiceButton;
    /**
     * Button used to navigate to NewGamePanel and start a new game.
     */
    private JButton newGameButton;
    /**
     * Button used to save the game state into database.
     */
    private JButton saveGameButton;
    /**
     * Button used a prerequisite to restart the current game.
     */
    private JButton restartGameButton;
    /**
     * Confirmation button appearing on restartgame JButton action that will restart the game.
     */
    private JButton confirmRestartGameButton;
    /**
     * Decline button appearing on restartgame JButton action that will hide itself & confirmRestart button again.
     */
    private JButton declineRestartGameButton;
    /**
     * GUIFrame object access required in order to call selectWindow method on button prompts.
     */
    private GridBagConstraints gridStructure;
    /**
     * Border around current player JList that provides more visual clarity & separation.
     */
    private Border currentPlayerBorder;
    /**
     * Object that handles player hypothetical move logic inside board & appends results to JTextArea.
     */
    private BoardMove boardMovement;
    /**
     * The dice chosen.
     */
    private DiceSet dice;
    /**
     * Sources current player from GameObject to handle hypothetical & real moves in game.
     */
    private Player currentPlayer;
    /**
     * Stores gameID from database to be referenced when a game sourced from database is to be overwritten.
     */
    private Integer gameID;
    /**
     * All player positions sourced from a saved game load.
     */
    private Integer[] loadedPlayerPostions;
    /**
     * Size of the grid required from Board object for saving new game into database purpose.
     */
    private Integer gridSize;
    /**
     * All special squares sourced from CreateGame object.
     */
    private TreeMap<String, Integer[]> allSpecialSquares;

    /**
     * Instances panel with data passed from multiple sources.
     * @param gui - current JFrame.
     * @param newGame - Game object created prior.
     * @param diceChoice - Dice object created prior.
     * @param boardGridSize - grid size for saving into database.
     * @param specialList - all special squares from new/loaded game.
     * @param isLoaded - confirms if game was loaded for saving purposes.
     * @param id - ID given to game on instantiation if loaded to reaffirm link between RunGamePanel game & database
     *        game. begins as null if brand new game created.
     */
    public RunGamePanel(final GUIFrame gui, final Game newGame, final DiceSet diceChoice,
                        final Integer boardGridSize, final TreeMap specialList, final Boolean isLoaded,
                        final Integer id) {
        this.currentGame = newGame;
        this.gameGui = gui;
        this.dice = diceChoice;
        this.gridSize = boardGridSize;
        this.allSpecialSquares = specialList;
        this.loaded = isLoaded;
        this.gameID = id;
        //Panel given defined size as to make sure data fits correctly.
        setPanelSize(350, 200);
        //Additional visual flair given to JList.
        currentPlayerBorder = BorderFactory.createLineBorder(Color.BLACK);
        //List of players will not go above a certain size.
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
        //Background colour of labels change depending on boolean values returned from method call inside Game object.
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

        playerOrderLabel = new JLabel("Player Order");
        playerColourTurnLabel = new JLabel("Player turn: ");
        playerPositionLabel = new JLabel("Player position: ");
        playerColourTurnResultLabel = new JLabel();
        playerPositionResultLabel = new JLabel();

        for (int i = 0; i < currentGame.numberOfPlayers(); i++) {
            currentPlayers.addElement(currentGame.getPlayerData(i).getColour().toString());
        }
        gamePlayerList.setModel(currentPlayers);
        //Player one (and subsequent current players) background on JList given visual flair to easily discern the
        // current player in game.
        gamePlayerList.setSelectedIndex(0);
        gamePlayerList.setSelectionBackground(Color.CYAN);

        rollDiceButton = new JButton("Roll Dice");
        newGameButton = new JButton("Start new Game");
        newGameButton.setEnabled(false);
        saveGameButton = new JButton("Save current game");
        saveGameButton.setEnabled(false);

        restartGameButton = new JButton("Restart Game");
        //Buttons only visible if restartGame is clicked.
        confirmRestartGameButton = new JButton("Yes");
        declineRestartGameButton = new JButton("No");
        confirmRestartGameButton.setVisible(false);
        declineRestartGameButton.setVisible(false);

        rollDiceButton.addActionListener(this);
        newGameButton.addActionListener(this);
        saveGameButton.addActionListener(this);
        restartGameButton.addActionListener(this);
        confirmRestartGameButton.addActionListener(this);
        declineRestartGameButton.addActionListener(this);
    }
    /**
     * Places panel along with all instanced objects onto JFrame.
     * @return this - Jpanel back to JFrame for addition onto window.
     */
    public JPanel createRunGamePanel() {

        TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder("Current Game");
        Border outerGameBarBorder = BorderFactory.createEmptyBorder(2, 10, 10, 10);
        innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);
        //Creates border as a margin around inner game bar.
        setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));
        //Gridbag chosen for more specified layout options compared to other layouts.
        setLayout(new GridBagLayout());

        gridStructure = new GridBagConstraints();
        gridStructure.weightx = 1;
        gridStructure.weighty = 0.1;
        //Specifies location of large winning square label.
        gridStructure.gridx = 0;
        gridStructure.gridy = 0;
        add(winningSquareLabel, gridStructure);
        //Specifies location of player order JList.
        gridStructure.fill = GridBagConstraints.NONE;
        gridStructure.gridy = 1;
        add(playerOrderLabel, gridStructure);
        gridStructure.gridy = 2;
        add(gamePlayerList, gridStructure);
        //Specifies location of player turn label at left of panel.
        gridStructure.gridy = 3;
        gridStructure.anchor = GridBagConstraints.LINE_START;
        add(playerColourTurnLabel, gridStructure);
        //specifies colour at center of panel.
        gridStructure.anchor = GridBagConstraints.CENTER;
        add(playerColourTurnResultLabel, gridStructure);
        //Specifies location of player positoin label at left of panel.
        gridStructure.gridy = 4;
        gridStructure.anchor = GridBagConstraints.LINE_START;
        add(playerPositionLabel, gridStructure);
        //specifies player location result at center of panel.
        gridStructure.anchor = GridBagConstraints.CENTER;
        add(playerPositionResultLabel, gridStructure);
        //Resizes dice button to fill larger portion of screen and places onto panel.
        gridStructure.weightx = 2;
        gridStructure.weighty = 1;
        gridStructure.gridy = 5;
        gridStructure.ipadx = 150;
        gridStructure.ipady = 150;
        add(rollDiceButton, gridStructure);
        //Resizes remaining GUI elements to normal size.
        gridStructure.ipadx = 1;
        gridStructure.ipady = 1;
        gridStructure.weighty = 0.1;
        //Text to specify if boost square on.
        gridStructure.gridx = 0;
        gridStructure.gridy = 6;
        gridStructure.anchor = GridBagConstraints.LINE_START;
        add(boostFeatureLabel, gridStructure);
        //Coloured label with background to produce result of prior JLabel query.
        gridStructure.anchor = GridBagConstraints.CENTER;
        add(boostFeatureResultLabel, gridStructure);
        //Text to specify if winning square square on.
        gridStructure.gridy = 7;
        gridStructure.anchor = GridBagConstraints.LINE_START;
        add(winningFeatureLabel, gridStructure);
        //Coloured label with background to produce result of prior JLabel query.
        gridStructure.anchor = GridBagConstraints.CENTER;
        add(winningFeatureResultLabel, gridStructure);
        //Restart button position abse position.
        gridStructure.weighty = 1;
        gridStructure.gridy = 8;
        gridStructure.anchor = GridBagConstraints.LINE_START;
        add(restartGameButton, gridStructure);
        //new game button base position.
        gridStructure.anchor = GridBagConstraints.LINE_END;
        add(newGameButton, gridStructure);
        //save game button base position.
        gridStructure.gridy = 9;
        gridStructure.anchor = GridBagConstraints.LINE_END;
        add(saveGameButton, gridStructure);
        //Begins runnning of game.
        launchGame();
        //returns panel to JFrame.
        return this;
    }

    /**
     * Defines size of panel on west of window.
     * @param width - horizontal size of panel.
     * @param height - vertical size of panel.
     */
    public void setPanelSize(final Integer width, final Integer height) {
        Dimension dim = getPreferredSize();
        dim.width = width;
        dim.height = height;
        setPreferredSize(dim);
    }

    /**
     * Initial launch of game that confirms whether new or saved game.
     */
    public void launchGame() {
        //Object that handles any movements made on the Board or Game object.
        boardMovement = new BoardMove(currentGame, gameGui);
        //Accesses board & moves players to predefined positions from database locations.
        if (loadedPlayerPostions != null) {
            for (int i = 0; i < loadedPlayerPostions.length; i++) {
                boardMovement.moveLoadedGamePlayer(currentGame.getPlayer(i), loadedPlayerPostions[i]);
            }
        }
        //Player 1 always starts first in game. Method highlights them on board.
        highLightCurrentPlayer(currentGame.getPlayer(0));

    }

    /**
     * Appends visual text to textpanel for window viewing. Confirms if any moves made have ended the game.
     * @param movingPlayer - current player.
     */
    private void settlePlayerMove(final Player movingPlayer) {
        //Sources dice roll.
        Integer diceRoll = dice.roll().getValue();
        //Assigns temporary object for printing data to screen.
        currentPlayer = movingPlayer;
        //Current position on Board object.
        Integer currentPosition = movingPlayer.getPosition().get();

        gameGui.appendTextToPanel(currentPlayer.getColour() + " player starts their turn at position "
                + currentPosition + ".\n");
        gameGui.appendTextToPanel(currentPlayer.getColour() + " player has rolled a " + diceRoll + "!\n");
        //Method goes through all hypothetical player moves for textPanel entries.
        // Moves player on board for final call.
        boardMovement.movePlayer(diceRoll, currentPlayer, currentPosition);
        //Game continues with additional textPanel additions.
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
            //Current player in Game object has now moved to the next player. method highlights this to panel/
            highLightCurrentPlayer(currentGame.getCurrentPlayer());
            //Changes Panel object player to Game Object's player.
            currentPlayer = currentGame.getCurrentPlayer();
            //Save game button is only avaiable at the start of a game round (i.e. player 1's turn).
            if (gameGui.getDatabaseConnection()) {
                if (currentPlayer == currentGame.getPlayer(0)) {
                    saveGameButton.setEnabled(true);
                } else {
                    saveGameButton.setEnabled(false);
                }
            }
        //The game has ended.
        } else {
            for (int i = 0; i < gamePlayerList.getModel().getSize(); i++) {
                Object player = gamePlayerList.getModel().getElementAt(i);
                //Matches player in JList with winning player.
                if (player.toString() == currentPlayer.getColour().toString()) {
                    gamePlayerList.setSelectedIndex(i);
                    //Changes background of JList from regular cyan to orange to indicate a win.
                    gamePlayerList.setSelectionBackground(Color.ORANGE);
                    //Disables buttons relating to the current game.
                    restartGameButton.setEnabled(false);
                    //Newgame button allowed to function now.
                    newGameButton.setEnabled(true);
                    saveGameButton.setEnabled(false);
                    gameGui.appendTextToPanel(currentPlayer.getColour() + " player ends their turn at "
                            + "position " + currentPlayer.getPosition().get() + "\n\n");
                    gameGui.appendTextToPanel(currentPlayer.getColour() + " player wins the "
                            + "game!\n");
                    gameGui.appendTextToPanel("|---------------------------------------------------------|\n");
                    playerPositionResultLabel.setText(currentPlayer.getPosition().get().toString());
                }
            }
            //Marks game as ended inside database if database enabled & game exists inside databse.
            if (gameGui.getDatabaseConnection() && gameGui.getLoaded()) {
                Connection connect = GameDBUtils.connectGuiToDatabase(ConstantDatabaseName.DATABASENAME);
                SaveDataDBManager finishedGame = new SaveDataDBManager(gameID);
                finishedGame.markGameAsEnded(connect);
                if (connect != null) {
                    try {
                        connect.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Change gui JLabels & list to reflect the current player's turn.
     * @param player - the current player's turn.
     */
    private void highLightCurrentPlayer(final Player player) {
        currentPlayer = player;
        Integer position = player.getPosition().get();

        for (int i = 0; i < gamePlayerList.getModel().getSize(); i++) {
            //Grabs object from JList.
            Object playerTurn = gamePlayerList.getModel().getElementAt(i);
            //Checks against player colour.
            if (playerTurn.toString() == currentPlayer.getColour().toString()) {
                gamePlayerList.setSelectedIndex(i);
                //Visually adds data onto panel relating to position, colour & Jlist visual flair.
                gamePlayerList.setSelectionBackground(Color.CYAN);
                playerColourTurnResultLabel.setText(currentPlayer.getColour().toString());
                playerPositionResultLabel.setText(position.toString());
            }
        }
    }

    /**
     * GUIFrame adds all player positions sourced from loaded game file into panel array.
     * @param playerPositions - all player positions from previously saved database game.
     */
    public void addLoadedPlayerPositions(final Integer[] playerPositions) {
        loadedPlayerPostions = new Integer[playerPositions.length];
        for (int i = 0; i < playerPositions.length; i++) {
            loadedPlayerPostions[i] = playerPositions[i];
        }
    };

    /**
     * Dictates actions to perform on JButton clicks.
     * @param click - the action made onto Jbuttons.
     */
    public void actionPerformed(final ActionEvent click) {
        //Roll Dice button will complete a player's board move.
        if (click.getSource() == rollDiceButton) {
            if (!currentGame.isGameOver()) {
                settlePlayerMove(currentPlayer);
            }
        //New game will simply create a newgame window & close the current game in progress.
        } else if (click.getSource() == newGameButton) {
            gameGui.selectWindow("newgame");
        //Savegame connections to database with data sourced from current panel. CANNOT SAVE FINISHED GAME.
        }  else if (click.getSource() == saveGameButton) {
            if (!currentGame.isGameOver()) {
                //All current data from game parsed.
                SaveDataDBManager saveGame = new SaveDataDBManager(currentGame, dice, gridSize,
                        allSpecialSquares, loaded, gameID);
                Connection connect = GameDBUtils.connectGuiToDatabase(ConstantDatabaseName.DATABASENAME);
                saveGame.saveCurrentGame(connect);
                saveGameButton.setEnabled(false);
                //GameID is the same as getGameID if already loaded - new games initialise a new game here.
                gameID = saveGame.getGameID();
                //loaded boolean already true if game came from database. new games change value to true from null.
                loaded = true;
                if (connect != null) {
                    try {
                        connect.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }  else if (click.getSource() == restartGameButton) {
            //Add new buttons onto GridBagLayout.temporarily disable default resart button.
            restartGameButton.setEnabled(false);
            restartGameButton.setText("Really restart?");
            gridStructure.weighty = 1;
            gridStructure.gridy = 9;
            gridStructure.anchor = GridBagConstraints.LINE_START;
            //Enable both new buttons, provide visual flair.
            confirmRestartGameButton.setVisible(true);
            confirmRestartGameButton.setBackground(Color.green);
            declineRestartGameButton.setVisible(true);
            declineRestartGameButton.setBackground(Color.red);
            add(confirmRestartGameButton, gridStructure);
            gridStructure.insets = new Insets(0, 0, 0, 100);
            gridStructure.anchor = GridBagConstraints.CENTER;
            add(declineRestartGameButton, gridStructure);
        //Reset player positions in current game with identical Board datapoints.
        }  else if (click.getSource() == confirmRestartGameButton) {
            gameGui.selectWindow("runrepeatgame");
        } else {
            //Decline restart action - undo all changes made when originally clicking restartGameButton JButton.
            confirmRestartGameButton.setVisible(false);
            declineRestartGameButton.setVisible(false);
            restartGameButton.setEnabled(true);
            restartGameButton.setText("Restart Game");
        }
    }
}
