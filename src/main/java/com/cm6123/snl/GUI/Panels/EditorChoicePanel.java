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

public class EditorChoicePanel extends SidePanel {

    private Edit additionChoice;
    private JLabel additionFirstEntryLabel;
    private JLabel additionSecondEntryLabel;
    private JTextField additionFirstField;
    private JTextField additionSecondField;
    private JButton createAdditionButton;
    private GUIFrame gameGui;

    private GameTextPanel textPanel;
//    private GameToolbarPanel toolbar;

    private FormListener formListener;
    private GridBagConstraints gridStructure;

    /////////////////BEGIN TEST CODE////////////////
    private JList dbEntries;

    /////////////////END TEST CODE//////////////////

    public EditorChoicePanel(final GUIFrame gui, final Edit newAddition) {
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
        }
//        } else if (newAddition == Edit.LADDER) {
//
//            loadDBGames(Edit.LADDER);
//            additionFirstEntryLabel = new JLabel("Ladder Base: ");
//            additionSecondEntryLabel = new JLabel("Ladder Top: ");
//        } else if (newAddition == Edit.BOOST) {
//
//            loadDBGames(Edit.BOOST);
//            additionFirstEntryLabel = new JLabel("Boost location: ");
//        } else if (newAddition == Edit.PLAYER) {
//
//            loadDBGames(Edit.PLAYER);
//            additionFirstEntryLabel = new JLabel("Edit Player name: ");
//        } else if (newAddition == Edit.DIE) {
//
//            loadDBGames(Edit.DIE);
//            additionFirstEntryLabel = new JLabel("Dice Count: ");
//            additionSecondEntryLabel = new JLabel("Die faces: ");
//        }

        additionFirstField = new JTextField(10);

        if (additionChoice != Edit.PLAYER) {
            additionSecondField = new JTextField(10);
        }


        /////////////////BEGIN TEST CODE////////////////
//
//
//            DefaultListModel newGameList = new DefaultListModel();
//            newGameList.addElement(new BoardCategory(0, "1x1 Board"));
//            newGameList.addElement(new BoardCategory(1, "5x5 Board"));
//            newGameList.addElement(new BoardCategory(2, "10X10 Board"));
        dbEntries.setModel(dbEntryList);
//
//        dbEntries.setPreferredSize(new Dimension(400, 60));
        dbEntries.setBorder(BorderFactory.createEtchedBorder());
        dbEntries.setSelectedIndex(0);

        /////////////////END TEST CODE//////////////////

        createAdditionButton = new JButton("Edit " + newAddition.toString().toLowerCase());
//        rollDiceButton.setPreferredSize(new Dimension(300, 200));

        createAdditionButton.addActionListener(new ActionListener() {
            //
//            @Override
            public void actionPerformed(final ActionEvent e) {


                FormEvents newEntry = null;

                if (additionChoice != Edit.PLAYER) {
                    if (! additionFirstField.getText().equals("")) {

                        if (! additionSecondField.getText().equals("")) {
                            try {
                                Integer squareStart = Integer.parseInt(additionFirstField.getText());

                                try {
                                    Integer squareEnd = Integer.parseInt(additionSecondField.getText());

                                    newEntry = new FormEvents(this, squareStart, squareEnd, additionChoice);
                                } catch (NumberFormatException stringEntered) {
                                    newEntry = new FormEvents(this, squareStart, additionChoice);
                                }
                            } catch (NumberFormatException stringEntered) {
                                System.out.println("ERROR - incorrect edit.");
                                formListener.incorrectEntryMessage();
                            }

                        } else {
                            try {
                                Integer squareStart = Integer.parseInt(additionFirstField.getText());
                                newEntry = new FormEvents(this, squareStart, additionChoice);
                            } catch (NumberFormatException stringEntered) {
                                System.out.println("ERROR - incorrect edit.");
                                formListener.incorrectEntryMessage();
                            }
                        }
                    } else {
                        formListener.incorrectEntryMessage();
                    }
                } else {
                    if (! additionFirstField.getText().equals("")) {
                        String newPlayerName = additionFirstField.getText();
                        newEntry = new FormEvents(this, newPlayerName, additionChoice);
                    } else {
                        formListener.incorrectEntryMessage();
                    }
                }
//                    BoardCategory chosenData = (BoardCategory) newGame.getSelectedValue();

                if (formListener != null && newEntry != null) {
                    formListener.formDatabaseEntry(newEntry);
                }
            }
        });

//        gameGui.add(this, BorderLayout.WEST);
    }

//    public void setPanelSize(final Integer width, final Integer height) {
//        Dimension dim = getPreferredSize();
//        dim.width = width;
//        dim.height = height;
//        setPreferredSize(dim);
//    }
    public void setPanelSize(final Integer width, final Integer height) {

    }



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

//        gridStructure.gridx = 1;

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

//            gridStructure.gridx = 1;

            gridStructure.anchor = GridBagConstraints.LINE_END;
            gridStructure.insets = new Insets(0, 0, 0, 0);
            add(additionSecondField, gridStructure);
        }

        /////////////////THIRD ROW////////////////

        /////////////////BEGIN TEST CODE////////////////

        /////////////////END TEST CODE//////////////////

        /////////////////FOURTH ROW////////////////

        gridStructure.weightx = 2;
        gridStructure.weighty = 2.0;
        gridStructure.gridy = 3;

        gridStructure.anchor = GridBagConstraints.LINE_END;
        gridStructure.insets = new Insets(0, 0, 0, 0);
        add(createAdditionButton, gridStructure);

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

            for (Integer i = 0; i < dbLoader.getSelectionSize(); i++) {
                jlist.addElement("|   ID: " + dbLoader.getTableID(i)
                        + "   |   save file: " + dbLoader.getGameInclusionID(i)
                        + "   |   head: " + dbLoader.getTotalFirstEntries(i)
                        + "   |   tail: " + dbLoader.getTotalSecondEntries(i)
                        + "   |");

            }
//            System.out.println(totalentries);
        }
//        } else if (choice == Edit.LADDER) {
//            Integer totalentries = LoadDataDBManager.countLaddersInDatabase(connect);
//            System.out.println(totalentries);
//
//        } else if (choice == Edit.BOOST) {
//            Integer totalentries = LoadDataDBManager.countBoostsInDatabase(connect);
//            System.out.println(totalentries);
//
//
//        } else if (choice == Edit.PLAYER) {
//            Integer totalentries = LoadDataDBManager.countPlayersInDatabase(connect);
//            System.out.println(totalentries);
//
//        } else if (choice == Edit.DIE) {
//            Integer totalentries = LoadDataDBManager.countDiceInDatabase(connect);
//            System.out.println(totalentries);
//        }
    }
}



