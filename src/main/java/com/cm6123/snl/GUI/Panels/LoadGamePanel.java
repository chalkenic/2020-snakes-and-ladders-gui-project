package com.cm6123.snl.GUI.Panels;

import com.cm6123.snl.GUI.*;
import com.cm6123.snl.GUI.PanelBackgroundLogic.CreateGame;
import com.cm6123.snl.gameDB.ConstantDatabaseName;
import com.cm6123.snl.gameDB.DBGameFile;
import com.cm6123.snl.gameDB.GameDBUtils;
import com.cm6123.snl.gameDB.LoadDataDBManager;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class that handles data being loaded from database and presented to application user.
 */
public class LoadGamePanel extends JPanel implements ActionListener {
    /**
     * Implements data collated from database into sets that can be applied into JList.
     */
    private DefaultListModel savedGames;
    /**
     * Required for JFrame method call for loading specific game from database into GUI.
     */
    private GUIFrame gameGui;
    /**
     *
     */
    private JList gameList;
    /**
     * Scrollpane for if too many games to fit into JList frame.
     */
    private JScrollPane scrollPane;
    /**
     * Button loads chosen game from JList and actions a game creation.
     */
    private JButton loadGameButton;
    /**
     * Empty label appended to if file error has occurred (e.g. squares clashing).
     */
    private JLabel errorLabel;
    /**
     * Controls layout of panel via co-ordinates.
     */
    private GridBagConstraints gridStructure;
    /**
     * Holds string that changes button text depending on highlighted JList entry.
     */
    private String currentSelection;
    /**
     * tracks highlighted choice on list for when JButton pressed to edit selected data.
     */
    private FormListener formListener;
    /**
     * Manager used as method to access database in panel.
     */
    private LoadDataDBManager loadDatabase;

    /**
     * Panel for instantiation objects for panel, used to load games from database.
     * @param gui - GUIFrame JFrame - used for method access.
     */
    public LoadGamePanel(final GUIFrame gui) {
        this.gameGui = gui;
        this.loadDatabase = new LoadDataDBManager();
        savedGames = new DefaultListModel();
        //Method used to source all games inside database.
        loadDBGames();

        gameList = new JList();
        gameList.setModel(savedGames);

        scrollPane = new JScrollPane(gameList);
        errorLabel = new JLabel("");
        //Error label not enabled until an error made on window.
        errorLabel.setEnabled(false);

        scrollPane.setPreferredSize(new Dimension(200, 300));
        gameList.setBorder(BorderFactory.createEtchedBorder(1));
        //List selection starts at 0.
        gameList.setSelectedIndex(0);
        //HTML added to add additional line spacing into button when more information added (via </br>).
        loadGameButton = new JButton("<html>Load Game</br>");
        loadGameButton.setHorizontalTextPosition(SwingConstants.CENTER);
        //Button text changed depending on Jlist choice hovered.
        gameList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(final ListSelectionEvent e) {
                //Code adapted from mKorbel: repaint JPanel with every click at Jlist.
                //Available at: https://stackoverflow.com/questions/19510789/repaint-jpanel-with-every-click-at-jlist
                if (!e.getValueIsAdjusting()) {
                    currentSelection = gameList.getSelectedValue().toString();
                    //Code adapted from Sebastien: changing a JButton text when clicked.
                    //Available at: https://stackoverflow.com/questions/9412620/changing-a-jbutton-text-when-clicked

                    //Code adapted from K0pernikus: New Line \n is not working in JButton.setText; [duplicate]
                    //available at: https://stackoverflow.com/questions/13503280/new-line-n-is-not-working-in-jbutton-settextfnord-nfoo
                    loadGameButton.setText("<html>Load Game <br />" + currentSelection + "</html>");
                }
            }
        });
        loadGameButton.addActionListener(this);
        //Form listener connects to  interface methods - however only requires error message.
        this.setFormListener(new FormListener() {
            public void appendTextToPanel(final String text) { }
            //Changes label text.
            public void incorrectEntryMessage() {
                setErrorLabel("Cannot find file!");
            }
            public void formDatabaseEntry(final LoadingFormEvent data) { }
        });
    }

    /**
     * Places panel & all objects from panel instantiation onto JFrame windoe.
     * @return this - the panel onto JFrame.
     */
    public JPanel createloadGamePanel() {
        TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder("Load Game");
        Border outerGameBarBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);

        setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));

        //Gridbag chosen for more specified layout options compared to other layouts.
        setLayout(new GridBagLayout());
        /* Code layout design originates from CaveOfProgramming tutorial:
        Java Swing: GridBagLayout (Video Tutorial Part 4). available at:
        https://www.caveofprogramming.com/java-swing-gui/java-swing-gridbaglayou-video-tutorial-part-4.html */
        gridStructure = new GridBagConstraints();
        //Add scroll pane JList onto Panel.
        gridStructure.weightx = 1;
        gridStructure.weighty = 1;
        gridStructure.gridx = 0;
        gridStructure.gridy = 1;
        gridStructure.insets = new Insets(5, 5, 5, 5);
        add(scrollPane, gridStructure);
        //Add load button onto panel.
        gridStructure.gridx = 0;
        gridStructure.gridy = 2;
        gridStructure.insets = new Insets(5, 5, 5, 5);
        add(loadGameButton, gridStructure);
        //Add error label onto panel (defaults disabled).
        gridStructure.gridy = 3;
        gridStructure.insets = new Insets(5, 5, 5, 5);
        add(errorLabel, gridStructure);
        return this;
    }

    /**
     * Appends text onto error label if file catches logged exception.
     * @param error - string to print.
     */
    public void setErrorLabel(final String error) {
        errorLabel.setEnabled(true);
        errorLabel.setText(error);
    }

    /**
     * Instances game files based on number of games inside Game table in database. Assigns IDs based on for loop.
     * Loop starts at 1 to match database not being zero-indexed.
     */
    private void loadDBGames() {
        Connection connect = GameDBUtils.connectGuiToDatabase(ConstantDatabaseName.DATABASENAME);
        System.out.println(connect);
        Integer totalGames = LoadDataDBManager.countGamesInDatabase(connect);
        for (Integer i = 1; i < (totalGames + 1); i++) {
            DBGameFile newFile = new DBGameFile(i, "File " + i);
            if (!newFile.getGameOver()) {
                savedGames.addElement(newFile);
            }
        }
        if (connect != null) {
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Tracks choice on JList.
     * @param listener - selection made on JList that will be grabbed when Action performed on panel.
     */
    private void setFormListener(final FormListener listener) {
        this.formListener = listener;
    }
    /**
     * Tracks action made on load game button.
     * @param click - trigger for button.
     */
    public void actionPerformed(final ActionEvent click) {
        if (click.getSource() == loadGameButton) {
            //Load file chosen based on JList highlighted choice.
            DBGameFile loadGameChoice = (DBGameFile) gameList.getSelectedValue();
            //Checks if formlistener has an entry (should never fail due to first index being highlighted at start).
            //Validation made via if condition which prints to error label if nothing selected.
            if (formListener != null) {
                //New object created for handling the chosen game.
                CreateGame loadedGame = new CreateGame(gameGui);
                //CreateGame object sources relevant data from DBGameFile object made in method and converts.
                gameGui.setCreatedGame(loadedGame.getLoadedGameData(loadGameChoice));
                //GUIFrame notified of game file choice in case a game restart is requested on RunGamePanel.
                gameGui.setDbGameFile(loadGameChoice);
                //GUIFrame notified of game id in case of game reload required.
                gameGui.setID(loadedGame.getGameID());
                //NavigateTo made to loaded game to parse data into RunGamePanel.
                gameGui.selectWindow(NavigateTo.RUNLOADEDGAME);
            } else {
                setErrorLabel("no list choice made!");
            }
        }
    }
}

