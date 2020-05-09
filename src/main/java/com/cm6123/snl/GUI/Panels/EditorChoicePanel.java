package com.cm6123.snl.GUI.Panels;

import com.cm6123.snl.GUI.*;
import com.cm6123.snl.gameDB.GameDBUtils;
import com.cm6123.snl.gameDB.LoadDataDBManager;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.HashMap;

public class EditorChoicePanel extends SidePanel {

    private Edit additionChoice;
    private JLabel additionFirstEntryLabel;
    private JLabel additionSecondEntryLabel;
    private JTextField additionFirstField;
    private JTextField additionSecondField;
    private JButton editButton;
    private GUIFrame gameGui;

    private GameTextPanel textPanel;
//    private GameToolbarPanel toolbar;

    private FormListener formListener;
    private GridBagConstraints gridStructure;

    /////////////////BEGIN TEST CODE////////////////
    private JList dbEntries;
    private HashMap<Integer, EditCategory> listEntries;

    /////////////////END TEST CODE//////////////////

    public EditorChoicePanel(final GUIFrame gui, final Edit newAddition) {
        listEntries = new HashMap<Integer, EditCategory>();
        this.additionChoice = newAddition;
        this.gameGui = gui;

        setPanelSize(350, 200);

        dbEntries = new JList();
        DefaultListModel dbEntryList = new DefaultListModel();


//        this.setLayout(layout);
//        this.setBackground(Color.WHITE);

        if (newAddition == Edit.SNAKE) {

            loadDBGames(Edit.SNAKE, dbEntryList);

            additionFirstEntryLabel = new JLabel("Snake Head: ");
            additionSecondEntryLabel = new JLabel("Snake Tail: ");

        } else if (newAddition == Edit.LADDER) {

            loadDBGames(Edit.LADDER, dbEntryList);
            additionFirstEntryLabel = new JLabel("Ladder Base: ");
            additionSecondEntryLabel = new JLabel("Ladder Top: ");
        } else if (newAddition == Edit.BOOST) {

            loadDBGames(Edit.BOOST, dbEntryList);
            additionFirstEntryLabel = new JLabel("Boost location: ");
        } else if (newAddition == Edit.PLAYER) {

            loadDBGames(Edit.PLAYER, dbEntryList);
            additionFirstEntryLabel = new JLabel("Edit Player name: ");
        } else if (newAddition == Edit.DIE) {

            loadDBGames(Edit.DIE, dbEntryList);
            additionFirstEntryLabel = new JLabel("Dice Count: ");
            additionSecondEntryLabel = new JLabel("Die faces: ");
        }

        additionFirstField = new JTextField(10);

        if (additionChoice != Edit.PLAYER) {
            additionSecondField = new JTextField(10);
        }

        dbEntries.setModel(dbEntryList);
//
//        dbEntries.setPreferredSize(new Dimension(400, 60));
        dbEntries.setBorder(BorderFactory.createEtchedBorder());
        dbEntries.setSelectedIndex(0);

        /////////////////END TEST CODE//////////////////

        editButton = new JButton("Edit " + newAddition.toString().toLowerCase());


        editButton.addActionListener(new ActionListener() {

            public void actionPerformed(final ActionEvent e) {

                Integer gameID = dbEntries.getSelectedIndex();
                System.out.println(listEntries.get(gameID).getGameID());


                LoadingFormEvent newEntry = null;

                if (additionChoice == Edit.SNAKE || additionChoice == Edit.LADDER) {
                    if (!additionFirstField.getText().equals("")) {

                        if (!additionSecondField.getText().equals("")) {
                            try {
                                Integer squareStart = Integer.parseInt(additionFirstField.getText());

                                try {
                                    Integer squareEnd = Integer.parseInt(additionSecondField.getText());

                                    newEntry = new LoadingFormEvent(this, squareStart, squareEnd, additionChoice,
                                            listEntries.get(gameID).getGameID(), listEntries.get(gameID).getListID());
                                } catch (NumberFormatException stringEntered) {
                                    formListener.incorrectEntryMessage();
//                                    newEntry = new LoadingFormEvent(this, squareStart, additionChoice,
//                                            listEntries.get(gameID).getGameID());
                                }
                            } catch (NumberFormatException stringEntered) {
                                System.out.println("ERROR - incorrect edit.");
                                formListener.incorrectEntryMessage();
                            }
                        } else {
                            formListener.incorrectEntryMessage();
                        }
                    } else {
                        formListener.incorrectEntryMessage();
                    }

                } else if (additionChoice == Edit.BOOST) {
                    if (!additionFirstField.getText().equals("")) {
                        try {
                            System.out.println("hello mr boosty?");
                            Integer squareStart = Integer.parseInt(additionFirstField.getText());
                            newEntry = new LoadingFormEvent(this, squareStart, additionChoice,
                                    listEntries.get(gameID).getGameID(), listEntries.get(gameID).getListID());
                        } catch (NumberFormatException stringEntered) {
                            System.out.println("ERROR - incorrect edit.");
                            formListener.incorrectEntryMessage();
                        }
                    }

                } else if (additionChoice == Edit.PLAYER) {
                    if (!additionFirstField.getText().equals("")) {
                        String newPlayerName = additionFirstField.getText();
                        newEntry = new LoadingFormEvent(this, newPlayerName, additionChoice, listEntries.get(gameID).getListID());
                    } else {
                        formListener.incorrectEntryMessage();
                    }
                } else if (additionChoice == Edit.DIE){
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
//                Integer listIndex = dbEntries.getSelectedIndex();
//                System.out.println(listIndex);
//                System.out.println(listEntries);
//                System.out.println(listEntries.get(listIndex));




//                EditCategory entry = (EditCategory) dbEntries.getSelectedValue();
//                entry.getGameID();

//                System.out.println("game id: " + gameID);
//                System.out.println("class: " + listEntries.get(gameID).getClass());
//                EditCategory entry = listEntries.get(gameID);
//                System.out.println("game id in entry: " + entry.getListID());

//                    EditCategory chosenData = (EditCategory) newGame.getSelectedValue();

                if (formListener != null && newEntry != null) {
                    formListener.formDatabaseEntry(newEntry);
                }
            }
        });
    }

    public void setPanelSize(final Integer width, final Integer height) { }



    //    @Override
    public JPanel editChoicePanel() {

        //Code adapted from TitledBorder.CENTER : TitledBorder « javax.swing.border « Java by API
        //Available at: http://www.java2s.com/Code/JavaAPI/javax.swing.border/TitledBorderCENTER.htm
        TitledBorder innerGameBarBorder =
                BorderFactory.createTitledBorder("Edit " + additionChoice.toString().toLowerCase());
        Border outerGameBarBorder = BorderFactory.createEmptyBorder(2, 10, 10, 10);
        innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);

        //Creates a border as a margin around inner game bar.
        setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));

        //USED FOR FLEXIBILITY COMPARED TO OTHER LAYOUTS
        setLayout(new GridBagLayout());

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

        if (additionSecondEntryLabel != null) {

            gridStructure.weightx = 1;
            gridStructure.weighty = 0.1;
            gridStructure.gridy = 2;
            gridStructure.gridx = 0;

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

        gridStructure.anchor = GridBagConstraints.LINE_END;
        gridStructure.insets = new Insets(0, 0, 0, 0);
        add(editButton, gridStructure);

        return this;
    }

    //    @Override
    public void setFormListener(final FormListener listener) {
        this.formListener = listener;
    }


    //    @Override
    public Boolean entryValidation(final Edit newSquare, final int... values) {
        Boolean validEntry = false;
        try {
            if (newSquare == Edit.SNAKE) {
                if (values[0] > values[1]) {
                    validEntry = true;
                }
            } else if (newSquare == Edit.LADDER) {
                if (values[0] < values[1]) {
                    validEntry = true;
                }
            } else if (newSquare == Edit.BOOST) {
                validEntry = true;
            } else if (newSquare == Edit.PLAYER) {
                validEntry = true;
            } else if (newSquare == Edit.DIE) {
                validEntry = true;
            }
        } catch (ArrayIndexOutOfBoundsException missingEnd) {
            System.out.println("ERROR - missing entry for special square end. Ignoring addition.");
            return validEntry;
        }
        return validEntry;
    }

    //    @Override
    public final Edit getAdditionChoice() {
        return additionChoice;
    }

    private void loadDBGames(final Edit choice, final DefaultListModel jlist) {

        Connection connect = GameDBUtils.connectGuiToDatabase();

        if (choice == Edit.SNAKE) {
            LoadDataDBManager dbLoader = new LoadDataDBManager();
            dbLoader.countSnakesInDatabase(connect);
            Integer dataSize = dbLoader.getSelectionSize() - 1;
            System.out.println(dataSize);

            for (Integer i = 0; i < dbLoader.getSelectionSize(); i++) {
                EditCategory snake = new EditCategory(dbLoader, i, choice, dbLoader.getGameInclusionID(i));
                System.out.println(dbLoader.getTableID(dataSize - i));
                jlist.addElement("|   ID: " + dbLoader.getTableID(i)
                        + "   |   save file: " + dbLoader.getGameInclusionID(i)
                        + "   |   head: " + dbLoader.getTotalFirstEntries(i)
                        + "   |   tail: " + dbLoader.getTotalSecondEntries(i)
                        + "   |");
                listEntries.put(i, snake);
            }
        } else if (choice == Edit.LADDER) {
            LoadDataDBManager dbLoader = new LoadDataDBManager();
            dbLoader.countLaddersInDatabase(connect);
            Integer dataSize = dbLoader.getSelectionSize() - 1;

            for (Integer i = 0; i < dbLoader.getSelectionSize(); i++) {
                EditCategory ladder = new EditCategory(dbLoader, i, choice, dbLoader.getGameInclusionID(i));
                jlist.addElement("|   ID: " + dbLoader.getTableID(dataSize - i)
                        + "   |   save file: " + dbLoader.getGameInclusionID(i)
                        + "   |   foot: " + dbLoader.getTotalFirstEntries(i)
                        + "   |   top: " + dbLoader.getTotalSecondEntries(i)
                        + "   |");
                listEntries.put(i, ladder);
            }

        } else if (choice == Edit.BOOST) {
            LoadDataDBManager dbLoader = new LoadDataDBManager();
            dbLoader.countBoostsInDatabase(connect);
            Integer dataSize = dbLoader.getSelectionSize() - 1;

            for (Integer i = 0; i < dbLoader.getSelectionSize(); i++) {
                EditCategory boost = new EditCategory(dbLoader, i, choice, dbLoader.getGameInclusionID(i));
                jlist.addElement("|   ID: " + dbLoader.getTableID(dataSize - i)
                        + "   |   save file: " + dbLoader.getGameInclusionID(i)
                        + "   |   location: " + dbLoader.getTotalFirstEntries(i)
                        + "   |");
                listEntries.put(i, boost);
            }

        } else if (choice == Edit.PLAYER) {
            LoadDataDBManager dbLoader = new LoadDataDBManager();
            dbLoader.countPlayersInDatabase(connect);
            Integer dataSize = dbLoader.getSelectionSize() - 1;

            for (Integer i = 0; i < dbLoader.getSelectionSize(); i++) {
                EditCategory player = new EditCategory(dbLoader, i,  choice);
                jlist.addElement("|   ID: " + dbLoader.getTableID(i)
                        + "   |   player: " + dbLoader.getPlayers(i)
                        + "   |");
                listEntries.put(i, player);
                System.out.println("player id: " + player.getListID());
            }

        } else if (choice == Edit.DIE) {
            LoadDataDBManager dbLoader = new LoadDataDBManager();
            dbLoader.countDiceInDatabase(connect);
            Integer dataSize = dbLoader.getSelectionSize() - 1;

            for (Integer i = 0; i < dbLoader.getSelectionSize(); i++) {
                EditCategory dice = new EditCategory(dbLoader, i, choice);
                jlist.add(0, "|   ID: " + dbLoader.getTableID(dataSize - i)
                        + "   |   count: " + dbLoader.getTotalFirstEntries(dataSize - i)
                        + "   |   faces: " + dbLoader.getTotalSecondEntries(dataSize - i)
                        + "   |");
                listEntries.put(i, dice);
                System.out.println("dice id: " + dice.getListID());

//                jlist.addElement(diceEdit);
            }
        }
    }
}


//                EditCategory diceEdit = ;
//                System.out.println(diceEdit.getClass());
//                ArrayList addition = new ArrayList<>();
//                addition.add(diceEdit, diceEdit.printData);
//                jlist.addElement(diceEdit.printData);


