package com.cm6123.snl.GUI.Panels;

import com.cm6123.snl.GUI.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewSquarePanel extends SidePanel {

    private NewSquare squareChoice;
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

    public NewSquarePanel(final GUIFrame gui, final NewSquare newSquare, final CardLayout layout) {
        this.squareChoice = newSquare;
        this.gameGui = gui;
//        this.setLayout(layout);

        if (newSquare == NewSquare.SNAKE) {
            specialSquareFirstEntryLabel = new JLabel("Snake Head: ");
            specialSquareSecondEntryLabel = new JLabel("Snake Tail: ");
        } else if (newSquare == NewSquare.LADDER) {
            specialSquareFirstEntryLabel = new JLabel("Ladder Base: ");
            specialSquareSecondEntryLabel = new JLabel("Ladder Top: ");
        } else if (newSquare == NewSquare.BOOST) {
            specialSquareFirstEntryLabel = new JLabel("Boost location: ");
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

        createSnakeButton = new JButton("Create " + newSquare.toString().toLowerCase());
//        rollDiceButton.setPreferredSize(new Dimension(300, 200));

        createSnakeButton.addActionListener(new ActionListener() {
            //
//            @Override
            public void actionPerformed(final ActionEvent submission) {
                NewSquareFormEvents squareEntry = null;
                if (!squareFirstField.getText().equals("")) {
                    if (!squareSecondField.getText().equals("")) {
                        try {
                            Integer squareStart = Integer.parseInt(squareFirstField.getText());
                            try {
                                Integer squareEnd = Integer.parseInt(squareSecondField.getText());
                                squareEntry = new NewSquareFormEvents(this, squareStart, squareEnd);
                            } catch (NumberFormatException stringEntered) {
                                squareEntry = new NewSquareFormEvents(this, squareStart);
                            }
                        } catch (NumberFormatException stringEntered) {
                            System.out.println("ERROR - incorrect entry.");
                            formListener.incorrectEntryMessage();
                        }

                    } else {
                        Integer squareStart = Integer.parseInt(squareFirstField.getText());
                        squareEntry = new NewSquareFormEvents(this, squareStart);
                    }
                } else {
                    formListener.incorrectEntryMessage();
                }


                /////////////////BEGIN TEST CODE////////////////
                BoardCategory chosenData = (BoardCategory) newGame.getSelectedValue();


                /////////////////END TEST CODE//////////////////

                if (formListener != null && squareEntry != null) {
                    System.out.println("Test");
                    formListener.formDatabaseEntry(squareEntry);
                }
            }
        });
//        gameGui.add(this, BorderLayout.WEST);
    }

//    @Override
    public JPanel createSquarePanel() {

        //Code adapted from TitledBorder.CENTER : TitledBorder « javax.swing.border « Java by API
        //Available at: http://www.java2s.com/Code/JavaAPI/javax.swing.border/TitledBorderCENTER.htm
        TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder("New " + squareChoice.toString().toLowerCase());
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
    public Integer entryValidation(final NewSquare newSquare, final int... values) {
        Integer correctEntry = 0;
        System.out.println(values.length);
        try {
            if (newSquare == NewSquare.SNAKE) {
                if (values[0] > values[1]) {
                    correctEntry = 1;
                }
            } else if (newSquare == NewSquare.LADDER) {
                if (values[0] < values[1]) {
                    correctEntry = 2;
                }
            } else if (newSquare == NewSquare.BOOST) {
                correctEntry = 3;
            }
        } catch (ArrayIndexOutOfBoundsException missingEnd) {
            System.out.println("ERROR - missing entry for special square end. Ignoring addition.");
        }
        return correctEntry;
    }

//    @Override
    public final NewSquare getSquareChoice() {
        return squareChoice;
    }
    }

