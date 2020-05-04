package com.cm6123.snl.GUI.Panels;

import com.cm6123.snl.GUI.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewAdditionPanel extends SidePanel {

    private NewAddition additionChoice;
    private JLabel specialSquareFirstEntryLabel;
    private JLabel specialSquareSecondEntryLabel;
    private JTextField squareFirstField;
    private JTextField squareSecondField;
    private JButton createSnakeButton;
    private GUIFrame gameGui;

    private GameTextPanel textPanel;
    private GameToolbar toolbar;

    private SquareFormListener formListener;
    private GridBagConstraints gridStructure;

    /////////////////BEGIN TEST CODE////////////////
    private JList newGame;
    /////////////////END TEST CODE//////////////////

    public NewAdditionPanel(final GUIFrame gui, final NewAddition newAddition) {
        this.additionChoice = newAddition;
        this.gameGui = gui;
//        this.setLayout(layout);
//        this.setBackground(Color.WHITE);

        if (newAddition == NewAddition.SNAKE) {
            specialSquareFirstEntryLabel = new JLabel("Snake Head: ");
            specialSquareSecondEntryLabel = new JLabel("Snake Tail: ");
        } else if (newAddition == NewAddition.LADDER) {
            specialSquareFirstEntryLabel = new JLabel("Ladder Base: ");
            specialSquareSecondEntryLabel = new JLabel("Ladder Top: ");
        } else if (newAddition == NewAddition.BOOST) {
            specialSquareFirstEntryLabel = new JLabel("Boost location: ");
        } else if (newAddition == NewAddition.PLAYER) {
            specialSquareFirstEntryLabel = new JLabel("New Player name: ");
        } else if (newAddition == NewAddition.DIE) {
            specialSquareFirstEntryLabel = new JLabel("Dice Count: ");
            specialSquareSecondEntryLabel = new JLabel("Die faces: ");
        }

            squareFirstField = new JTextField(10);
            squareSecondField = new JTextField(10);

            /////////////////BEGIN TEST CODE////////////////
            newGame = new JList();

            DefaultListModel newGameList = new DefaultListModel();
            newGameList.addElement(new BoardCategory(0, "1x1 Board"));
            newGameList.addElement(new BoardCategory(1, "5x5 Board"));
            newGameList.addElement(new BoardCategory(2, "10X10 Board"));
            newGame.setModel(newGameList);

            newGame.setPreferredSize(new Dimension(115, 57));
            newGame.setBorder(BorderFactory.createEtchedBorder());
            newGame.setSelectedIndex(0);

            /////////////////END TEST CODE//////////////////

            createSnakeButton = new JButton("Create " + newAddition.toString().toLowerCase());
//        rollDiceButton.setPreferredSize(new Dimension(300, 200));

            createSnakeButton.addActionListener(new ActionListener() {
                //
//            @Override
                public void actionPerformed(final ActionEvent submission) {


                    NewAdditionFormEvents newEntry = null;
                    if (additionChoice != NewAddition.PLAYER) {
                        if (!squareFirstField.getText().equals("")) {
                            System.out.println("test1");
                            if (! squareSecondField.getText().equals("")) {
                                try {
                                    Integer squareStart = Integer.parseInt(squareFirstField.getText());
                                    System.out.println("test2");
                                    try {
                                        Integer squareEnd = Integer.parseInt(squareSecondField.getText());
                                        System.out.println("test3");
                                        newEntry = new NewAdditionFormEvents(this, squareStart, squareEnd);
                                    } catch (NumberFormatException stringEntered) {
                                        newEntry = new NewAdditionFormEvents(this, squareStart);
                                    }
                                } catch (NumberFormatException stringEntered) {
                                    System.out.println("ERROR - incorrect entry.");
                                    formListener.incorrectEntryMessage();
                                }

                            } else {
                                Integer squareStart = Integer.parseInt(squareFirstField.getText());
                                newEntry = new NewAdditionFormEvents(this, squareStart);
                            }
                        } else {
                            formListener.incorrectEntryMessage();
                        }
                    } else {
                        if (!squareFirstField.getText().equals("")) {
                            String newPlayerName = squareFirstField.getText();
                            newEntry = new NewAdditionFormEvents(this, newPlayerName);
                        }




                    }
                    /////////////////BEGIN TEST CODE////////////////
                    BoardCategory chosenData = (BoardCategory) newGame.getSelectedValue();


                    /////////////////END TEST CODE//////////////////

                    if (formListener != null && newEntry != null) {
                        formListener.formDatabaseEntry(newEntry);
                    }
                }
            });

//        gameGui.add(this, BorderLayout.WEST);
        }



//    @Override
    public JPanel createAdditionPanel() {

        //Code adapted from TitledBorder.CENTER : TitledBorder « javax.swing.border « Java by API
        //Available at: http://www.java2s.com/Code/JavaAPI/javax.swing.border/TitledBorderCENTER.htm
        TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder("New " + additionChoice.toString().toLowerCase());
        Border outerGameBarBorder = BorderFactory.createEmptyBorder(2, 10, 10, 10);
        innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);

        //Creates a border as a margin around inner game bar.
        setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));

        //USED FOR FLEXIBILITY COMPARED TO OTHER LAYOUTS
        setLayout(new GridBagLayout());

        gridStructure = new GridBagConstraints();

        gridStructure.weightx = 1;
        gridStructure.weighty = 0.1;
        gridStructure.gridx = 0;
        gridStructure.gridy = 0;
        gridStructure.fill = GridBagConstraints.NONE;

        gridStructure.anchor = GridBagConstraints.LINE_END;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(specialSquareFirstEntryLabel, gridStructure);

        gridStructure.gridx = 1;

        gridStructure.anchor = GridBagConstraints.LINE_START;
        gridStructure.insets = new Insets(0, 0, 0, 0);
        add(squareFirstField, gridStructure);

        if (specialSquareSecondEntryLabel != null) {

            gridStructure.weightx = 1;
            gridStructure.weighty = 0.1;
            gridStructure.gridy = 1;
            gridStructure.gridx = 0;

            gridStructure.anchor = GridBagConstraints.LINE_END;
            gridStructure.insets = new Insets(0, 0, 0, 5);
            add(specialSquareSecondEntryLabel, gridStructure);

            gridStructure.gridx = 1;

            gridStructure.anchor = GridBagConstraints.LINE_START;
            gridStructure.insets = new Insets(0, 0, 0, 0);
            add(squareSecondField, gridStructure);
        }

        /////////////////THIRD ROW////////////////

        /////////////////BEGIN TEST CODE////////////////
        gridStructure.weightx = 1;
        gridStructure.weighty = 0.2;
        gridStructure.gridy = 2;

        gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(newGame, gridStructure);
        /////////////////END TEST CODE//////////////////

        /////////////////FOURTH ROW////////////////

        gridStructure.weightx = 2;
        gridStructure.weighty = 2.0;
        gridStructure.gridy = 3;

        gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
        gridStructure.insets = new Insets(0, 0, 0, 0);
        add(createSnakeButton, gridStructure);

        return this;
    }

//    @Override
    public void setFormListener(final SquareFormListener listener) {
        this.formListener = listener;
    }


//    @Override
    public Integer entryValidation(final NewAddition newSquare, final int... values) {
        Integer correctEntry = 0;
        try {
            if (newSquare == NewAddition.SNAKE) {
                if (values[0] > values[1]) {
                    correctEntry = 1;
                }
            } else if (newSquare == NewAddition.LADDER) {
                if (values[0] < values[1]) {
                    correctEntry = 2;
                }
            } else if (newSquare == NewAddition.BOOST) {
                correctEntry = 3;
            }
        } catch (ArrayIndexOutOfBoundsException missingEnd) {
            System.out.println("ERROR - missing entry for special square end. Ignoring addition.");
        }
        return correctEntry;
    }

//    @Override
    public final NewAddition getAdditionChoice() {
        return additionChoice;
    }
    }

