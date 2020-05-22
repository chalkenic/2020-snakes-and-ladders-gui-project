package com.cm6123.snl.GUI.Panels;

import com.cm6123.snl.GUI.*;
import com.cm6123.snl.gameDB.ConstantDatabaseName;
import com.cm6123.snl.gameDB.GameDBUtils;
import com.cm6123.snl.gameDB.LoadDataDBManager;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
/**
 * Panel handles load of specific edit choice & fields to replace current save items.
 */
public class EditorChoicePanel extends SidePanel implements ActionListener {
    /**
     * Dynamic label that handles first edit choice text depending on choice.
     */
    private JLabel additionFirstEntryLabel;
    /**
     * Dynamic label that handles second edit choice text depending on choice.
     */
    private JLabel additionSecondEntryLabel;
    /**
     * JTextField for changing first data point inside Jlist.
     */
    private JTextField additionFirstField;
    /**
     * JTextField for changing second data point inside Jlist.
     */
    private JTextField additionSecondField;
    /**
     * Confirmation button of edit request to push onto database.
     */
    private JButton editButton;
    /**
     * Choice of GameEdit sourced from prior EditorMenu & parsed from GUIFrame. Dictates labels & JList data to be shown.
     */
    private GameEdit additionChoice;
    /**
     * Listens to choice made on list for when JButton pressed to edit selected data.
     */
    private FormListener formListener;
    /**
     * GUIFrame object access required in order to call selectWindow method on button prompts.
     */
    private GridBagConstraints gridStructure;
    /**
     * Dynamic list containing data sourced from database depending on edit choice.
     */
    private JList dbEntries;
    /**
     * HashMap for storing data onto from database & inserting into JList using for loop.
     */
    private HashMap<Integer, EditCategory> listEntries;
    /**
     * Panel for changing current game data within database depending upon GameEdit enum choice made.
     * @param newAddition - enum that dictates data shown & available to edit.
     */
    public EditorChoicePanel(final GameEdit newAddition) {
        listEntries = new HashMap<Integer, EditCategory>();
        this.additionChoice = newAddition;
        additionFirstField = new JTextField(10);
         //Panel size dictated to keep a frame minimum & show all data required.
        setPanelSize(350, 200);

        dbEntries = new JList();
        editButton = new JButton("GameEdit " + newAddition.toString().toLowerCase());
        editButton.addActionListener(this);
        DefaultListModel dbEntryList = new DefaultListModel();
        //Jlabels show text depending on GameEdit enum choice made on menu.
        if (newAddition == GameEdit.SNAKE) {
            //Calls method that fills dbEntryList with database relating to Enum GameEdit choice.
            loadDBGames(GameEdit.SNAKE, dbEntryList);
            additionFirstEntryLabel = new JLabel("Snake Head: ");
            additionSecondEntryLabel = new JLabel("Snake Tail: ");
        } else if (newAddition == GameEdit.LADDER) {
            loadDBGames(GameEdit.LADDER, dbEntryList);
            additionFirstEntryLabel = new JLabel("Ladder Base: ");
            additionSecondEntryLabel = new JLabel("Ladder Top: ");
        } else if (newAddition == GameEdit.BOOST) {
            loadDBGames(GameEdit.BOOST, dbEntryList);
            //Only 1 label needed for changing boost location.
            additionFirstEntryLabel = new JLabel("Boost location: ");
        } else if (newAddition == GameEdit.PLAYER) {
            loadDBGames(GameEdit.PLAYER, dbEntryList);
            //Only 1 label needed for changing a player name/colour.
            additionFirstEntryLabel = new JLabel("GameEdit name: ");
        } else if (newAddition == GameEdit.DIE) {
            loadDBGames(GameEdit.DIE, dbEntryList);
            additionFirstEntryLabel = new JLabel("Dice Count: ");
            additionSecondEntryLabel = new JLabel("Die faces: ");
        }
        //PlAYER & BOOST choices do not require second field for data entry.
        if (additionChoice != GameEdit.PLAYER || additionChoice != GameEdit.BOOST) {
            additionSecondField = new JTextField(10);
        }
        //Model set upon retrieving all data from database relating to GameEdit choice.
        dbEntries.setModel(dbEntryList);
        dbEntries.setBorder(BorderFactory.createEtchedBorder());
        //Defaults JList to position 1.
        dbEntries.setSelectedIndex(0);
    }
    /**
     * Dictates actions to perform on edit button click.
     * @param click - the action made onto Jbuttons.
     */
    public void actionPerformed(final ActionEvent click) {
            //Parses JList number & sets as game id for database amendment link.
            Integer gameID = dbEntries.getSelectedIndex();
            //Initiliases newEntry form that intakes data from TextFields.
            LoadingFormEvent newEntry = null;

            //Both SNAKE & LADDER require gameID & 2 JTextFields.
            if (additionChoice == GameEdit.SNAKE || additionChoice == GameEdit.LADDER) {
                //Confirms if first field has had data entered.
                if (!additionFirstField.getText().equals("")) {
                    //Confirms if second field has had data entered.
                    if (!additionSecondField.getText().equals("")) {
                        try { //Attempts to convert first field's string value to integer.
                            Integer squareStart = Integer.parseInt(additionFirstField.getText());
                            try { //Attempts to convert first field's string value to integer.
                                Integer squareEnd = Integer.parseInt(additionSecondField.getText());
                                //Attempts to parse JTextField data into constructor. Sources gameID & table row ID
                                // from listEntries TreeMap.
                                newEntry = new LoadingFormEvent(this, squareStart, squareEnd, additionChoice,
                                        listEntries.get(gameID).getGameID(), listEntries.get(gameID).getListID());
                                //Catches incorrect entries in second field.
                            } catch (NumberFormatException stringEntered) {
                                formListener.incorrectEntryMessage();
                            }
                            //Catches incorrect entries in 1st field.
                        } catch (NumberFormatException stringEntered) {
                            formListener.incorrectEntryMessage();
                        }
                    } else { //Catches null entry inside second field.
                        formListener.incorrectEntryMessage();
                    }
                } else { //Catches null entry inside second field.
                    formListener.incorrectEntryMessage();
                }
            //BOOST requires gameID but only 1 JTextField.
            } else if (additionChoice == GameEdit.BOOST) {
                if (!additionFirstField.getText().equals("")) {
                    try {
                        Integer squareStart = Integer.parseInt(additionFirstField.getText());
                        //Attempts to parse JTextField data into constructor. Sources gameID & table row ID
                        // from listEntries TreeMap.
                        newEntry = new LoadingFormEvent(this, squareStart, additionChoice,
                                listEntries.get(gameID).getGameID(), listEntries.get(gameID).getListID());
                    } catch (NumberFormatException stringEntered) { //Catches null entry inside field.
                        formListener.incorrectEntryMessage();
                    }
                }
            //PLAYER only requires 1 JTextField and passes field as string into constructor.
            } else if (additionChoice == GameEdit.PLAYER) {
                if (!additionFirstField.getText().equals("")) {
                    //Try not required due to String also allowing integer entries as name/colour.
                    String newPlayerName = additionFirstField.getText();
                    newEntry = new LoadingFormEvent(this, newPlayerName, additionChoice, listEntries.get(gameID).getListID());
                } else {
                    formListener.incorrectEntryMessage();
                }
            //DICE requires 2 JTextField but no gameID required.
            } else if (additionChoice == GameEdit.DIE) {
                if (!additionFirstField.getText().equals("")) {
                    try {
                        Integer diceCount = Integer.parseInt(additionFirstField.getText());
                        try {
                            Integer diceFaces = Integer.parseInt(additionSecondField.getText());

                            newEntry = new LoadingFormEvent(this, diceCount, diceFaces, additionChoice,
                                    listEntries.get(gameID).getListID());
                        } catch (NumberFormatException incorrect) {
                            formListener.incorrectEntryMessage();
                        }
                    } catch (NumberFormatException incorrect) {
                        formListener.incorrectEntryMessage();
                    }
                }
            }
            //Data aquired only added if form choice made & formEvent object creation was a success.
            if (formListener != null && newEntry != null) {
                //Database entry handled via GUIFrame method call.
                formListener.formDatabaseEntry(newEntry);
            }

        }
//    }
    /**
     * parent class method styles size of panel on east of JFrame.
     * @param width - horizontal size of panel.
     * @param height - vertical size of panel.
     */
    public void setPanelSize(final Integer width, final Integer height) { }
    /**
     * Places all Object variables onto Panel in dictated locations from layout.
     * @return this - panel for placement onto JFrame.
     */
    public JPanel editChoicePanel() {
        //Code adapted from TitledBorder.CENTER : TitledBorder « javax.swing.border « Java by API
        //Available at: http://www.java2s.com/Code/JavaAPI/javax.swing.border/TitledBorderCENTER.htm
        TitledBorder innerGameBarBorder =
                BorderFactory.createTitledBorder("GameEdit " + additionChoice.toString().toLowerCase());
        Border outerGameBarBorder = BorderFactory.createEmptyBorder(2, 10, 10, 10);
        innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);
        //Creates border as a margin around inner game bar.
        setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));
        //Gridbag chosen for layout strengths compared to other layouts.
        setLayout(new GridBagLayout());
        /* Code layout design originates from CaveOfProgramming tutorial:
        Java Swing: GridBagLayout (Video Tutorial Part 4). available at:
        https://www.caveofprogramming.com/java-swing-gui/java-swing-gridbaglayou-video-tutorial-part-4.html */
        gridStructure = new GridBagConstraints();

        gridStructure.weightx = 1;
        gridStructure.weighty = 0.1;
        gridStructure.gridy = 0;

        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(dbEntries, gridStructure);

        gridStructure.weightx = 1;
        gridStructure.weighty = 0.1;
        gridStructure.gridx = 0;
        gridStructure.gridy = 1;
        gridStructure.fill = GridBagConstraints.NONE;

        gridStructure.anchor = GridBagConstraints.LINE_START;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(additionFirstEntryLabel, gridStructure);

        gridStructure.anchor = GridBagConstraints.LINE_END;
        gridStructure.insets = new Insets(0, 0, 0, 0);
        add(additionFirstField, gridStructure);
        //Additional JTextField & JLabel only added if label contains data (special square edits only).
        if (additionSecondEntryLabel != null) {
            gridStructure.weightx = 1;
            gridStructure.gridy = 2;
            gridStructure.anchor = GridBagConstraints.LINE_START;
            gridStructure.insets = new Insets(0, 0, 0, 5);
            add(additionSecondEntryLabel, gridStructure);

            gridStructure.anchor = GridBagConstraints.LINE_END;
            gridStructure.insets = new Insets(0, 0, 0, 0);
            add(additionSecondField, gridStructure);
        }

        gridStructure.weightx = 2;
        gridStructure.weighty = 2.0;
        gridStructure.gridy = 3;
        //Button forced into top right position of grid.
        gridStructure.anchor = GridBagConstraints.FIRST_LINE_END;
        gridStructure.insets = new Insets(0, 0, 0, 0);
        add(editButton, gridStructure);
        return this;
    }

    /**
     * Listener for GUIFrame link to Panel.
     * @param listener - allows GUIFrame to listen to Form choice when method called for appending into database.
     */
    public void setFormListener(final FormListener listener) {
        this.formListener = listener;
    }


    /**
     * validates data from JTextFields to ensure game integrity.
     * @param newSquare GameEdit choice
     * @param values - all values parsed into method.
     * @return Boolean - confirms if values can be accepted.
     */
    public Boolean entryValidation(final GameEdit newSquare, final int... values) {
        Boolean validEntry = false;
        try {
            //Snake Head must be larger than Snake tail for method to return true.
            if (newSquare == GameEdit.SNAKE) {
                if (values[0] > values[1]) {
                    validEntry = true;
                }
            //Ladder Foot must be smaller than ladder top for method to return true.
            } else if (newSquare == GameEdit.LADDER) {
                if (values[0] < values[1]) {
                    validEntry = true;
                }
                //Confirms data is related to boost.
            } else if (newSquare == GameEdit.BOOST) {
                validEntry = true;
                //Confirms data related to player.
            } else if (newSquare == GameEdit.PLAYER) {
                validEntry = true;
                //confirms data related to dice.
            } else if (newSquare == GameEdit.DIE) {
                validEntry = true;
            }
            //Catches any entries made outside of database's stored board size.
        } catch (ArrayIndexOutOfBoundsException missingEnd) {
            //returns false if error.
            return validEntry;
        }
        return validEntry;
    }

    /**
     * Source which GameEdit was use for entryValidation method.
     * @return GameEdit enum choice.
     */
    public final GameEdit getAdditionChoice() {
        return additionChoice;
    }
    /**
     * Load database entries from specific table depending on GameEdit enum entry.
     * @param choice - Table of choice.
     * @param jlist - list model to collate data into.
     */
    private void loadDBGames(final GameEdit choice, final DefaultListModel jlist) {

        Connection connect = GameDBUtils.connectGuiToDatabase(ConstantDatabaseName.DATABASENAME);

        if (choice == GameEdit.SNAKE) {
            LoadDataDBManager dbLoader = new LoadDataDBManager(); //Manager opened for loading data.
            dbLoader.countSnakesInDatabase(connect); //Method parses all snake data into dbLoader from database.
            Integer dataSize = dbLoader.getSelectionSize() - 1; //Database is not zero-indexed.
            for (Integer i = 0; i < dbLoader.getSelectionSize(); i++) {
                //Object to hold a specfic snake from database.
                EditCategory snake = new EditCategory(i, choice, dbLoader.getGameInclusionID(i));
                //List given data related to snake (which game it's in/which table row/current values).
                jlist.addElement("|   ID: " + dbLoader.getTableID(i)
                        + "   |   save file: " + dbLoader.getGameInclusionID(i)
                        + "   |   head: " + dbLoader.getTotalFirstEntries(i)
                        + "   -   tail: " + dbLoader.getTotalSecondEntries(i)
                        + "    ");
                //Places object into panel TreeMap.
                listEntries.put(i, snake);
            }
        } else if (choice == GameEdit.LADDER) {
            LoadDataDBManager dbLoader = new LoadDataDBManager();
            dbLoader.countLaddersInDatabase(connect);
            Integer dataSize = dbLoader.getSelectionSize() - 1;
            for (Integer i = 0; i < dbLoader.getSelectionSize(); i++) {
                //Object to hold a specfic snake from database.
                EditCategory ladder = new EditCategory(i, choice, dbLoader.getGameInclusionID(i));
                jlist.addElement("|   ID: " + dbLoader.getTableID(i)
                        + "   |   save file: " + dbLoader.getGameInclusionID(i)
                        + "   |   foot: " + dbLoader.getTotalFirstEntries(i)
                        + "   -   top: " + dbLoader.getTotalSecondEntries(i)
                        + "    ");
                listEntries.put(i, ladder);
            }

        } else if (choice == GameEdit.BOOST) {
            LoadDataDBManager dbLoader = new LoadDataDBManager();
            dbLoader.countBoostsInDatabase(connect);
            Integer dataSize = dbLoader.getSelectionSize() - 1;

            for (Integer i = 0; i < dbLoader.getSelectionSize(); i++) {
                //Object to hold a specfic boost square from database.
                EditCategory boost = new EditCategory(i, choice, dbLoader.getGameInclusionID(i));
                jlist.addElement("|   ID: " + dbLoader.getTableID(i)
                        + "   |   save file: " + dbLoader.getGameInclusionID(i)
                        + "   |   location: " + dbLoader.getTotalFirstEntries(i)
                        + "    ");
                listEntries.put(i, boost);
            }

        } else if (choice == GameEdit.PLAYER) {
            LoadDataDBManager dbLoader = new LoadDataDBManager();
            dbLoader.countPlayersInDatabase(connect);
            Integer dataSize = dbLoader.getSelectionSize() - 1;

            for (Integer i = 0; i < dbLoader.getSelectionSize(); i++) {
                //Object to hold a specfic player from database.
                EditCategory player = new EditCategory(i, choice);
                jlist.addElement("|     ID: " + dbLoader.getTableID(i)
                        + "     |      player: " + dbLoader.getPlayers(i)
                        + "    ");
                listEntries.put(i, player);
            }

        } else if (choice == GameEdit.DIE) {
            LoadDataDBManager dbLoader = new LoadDataDBManager();
            dbLoader.countDiceInDatabase(connect);
            Integer dataSize = dbLoader.getSelectionSize() - 1;

            for (Integer i = 0; i < dbLoader.getSelectionSize(); i++) {
                //Object to hold a specfic dice from database.
                EditCategory dice = new EditCategory(i, choice);
                jlist.add(0, "|   ID: " + dbLoader.getTableID(dataSize - i)
                        + "   |   count: " + dbLoader.getTotalFirstEntries(dataSize - i)
                        + " --- faces: " + dbLoader.getTotalSecondEntries(dataSize - i)
                        + "    ");
                listEntries.put(i, dice);
            }
        }
        if (connect != null) { //Closes connection upon method finish.
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
