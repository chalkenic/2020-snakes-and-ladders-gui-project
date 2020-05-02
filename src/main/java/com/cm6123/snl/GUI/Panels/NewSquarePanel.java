package com.cm6123.snl.GUI.Panels;

import com.cm6123.snl.GUI.FormListener;
import com.cm6123.snl.GUI.NewSquareFormEvents;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewSquarePanel extends JPanel {

    private NewSquare squareChoice;
    private JLabel specialSquareFirstEntryLabel;
    private JLabel specialSquareSecondEntryLabel;
    private JTextField squareFirstField;
    private JTextField squareSecondField;
    private JButton createSnakeButton;
    private FormListener formListener;


    /////////////////BEGIN TEST CODE////////////////
    private JList newGame;
    /////////////////END TEST CODE//////////////////




    public NewSquarePanel(final NewSquare newSquare) {
        this.squareChoice = newSquare;
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

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
                if (squareFirstField.getText() != null) {
                    if (squareSecondField.getText() != null) {
                        try {
                            Integer squareStart = Integer.parseInt(squareFirstField.getText());
                            Integer squareEnd = Integer.parseInt(squareSecondField.getText());
                            squareEntry = new NewSquareFormEvents(this, squareStart, squareEnd);
                        } catch (NumberFormatException stringEntered) {
                            System.out.println("ERROR - incorrect entry.");
                            formListener.appendTextToPanel(
                                    "---------------------------------------------------"
                                    + "\nERROR - INCORRECT ENTRY MADE"
                                    + "\n---------------------------------------------------");
                        }

                    } else {
                        Integer squareStart = Integer.parseInt(squareFirstField.getText());
                        squareEntry = new NewSquareFormEvents(this, squareStart);
                    }
                } else {
                    System.out.println("ERROR - no value entered.");
                }


                /////////////////BEGIN TEST CODE////////////////
                BoardCategory chosenData = (BoardCategory) newGame.getSelectedValue();


                /////////////////END TEST CODE//////////////////

                if (formListener != null && squareEntry != null) {
                    formListener.formSpecialSquareEntry(squareEntry);
                }
            }
        });


        TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder("New " + newSquare.toString().toLowerCase());
        Border outerGameBarBorder = BorderFactory.createEmptyBorder(2, 10, 10, 10);

        //Code adapted from TitledBorder.CENTER : TitledBorder « javax.swing.border « Java by API
        //Available at: http://www.java2s.com/Code/JavaAPI/javax.swing.border/TitledBorderCENTER.htm
        innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);

        //Creates a border as a margin around inner game bar.
        setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));

        //USED FOR FLEXIBILITY COMPARED TO OTHER LAYOUTS
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();


        /////////////////FIRST ROW////////////////

        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(specialSquareFirstEntryLabel, gc);

        gc.gridx = 1;

        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(squareFirstField, gc);


        /////////////////SECOND ROW////////////////
        if (specialSquareSecondEntryLabel != null) {

            gc.weightx = 1;
            gc.weighty = 0.1;
            gc.gridy = 1;
            gc.gridx = 0;

            gc.anchor = GridBagConstraints.LINE_END;
            gc.insets = new Insets(0, 0, 0, 5);
            add(specialSquareSecondEntryLabel, gc);

            gc.gridx = 1;

            gc.anchor = GridBagConstraints.LINE_START;
            gc.insets = new Insets(0, 0, 0, 0);
            add(squareSecondField, gc);
        }

        /////////////////THIRD ROW////////////////

        /////////////////BEGIN TEST CODE////////////////
        gc.weightx = 1;
        gc.weighty = 0.2;
        gc.gridy = 2;

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        add(newGame, gc);
        /////////////////END TEST CODE//////////////////

        /////////////////FOURTH ROW////////////////

        gc.weightx = 2;
        gc.weighty = 2.0;
        gc.gridy = 3;

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(createSnakeButton, gc);
    }

    public void setFormListener(final FormListener listener) {
        this.formListener = listener;
    }

    public Integer confirmValidSquareEntry(final NewSquare newSquare, final int... values) {
        Integer correctEntry = 0;
        System.out.println(values.length);

        if (newSquare == NewSquare.SNAKE) {
            if (values[0] > values[1]) {
                formListener.appendTextToPanel(
                        "\nNew Snake Head starts at position " + values[0]
                        + "\n" + "New Snake Tail ends at position " + values[1]);
                correctEntry = 1;
            }
        } else if (newSquare == NewSquare.LADDER) {
            System.out.println("test");
            if (values[0] < values[1]) {
                formListener.appendTextToPanel("\nNew Ladder Foot starts at position " + values[0]
                        + "\n" + "New Ladder Top ends at position " + values[1]);
                correctEntry = 2;
            }
        } else if (newSquare == NewSquare.BOOST && values.length == 1) {
            System.out.println("Test2");
            formListener.appendTextToPanel("\nBoost square added at location " + values[0]);
            correctEntry = 3;

        }
        return correctEntry;
    }

    public NewSquare getSquareChoice() {
        return squareChoice;
    }
}


