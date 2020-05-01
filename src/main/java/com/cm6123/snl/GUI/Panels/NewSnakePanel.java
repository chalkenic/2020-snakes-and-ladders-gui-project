package com.cm6123.snl.GUI.Panels;

import com.cm6123.snl.GUI.FormListener;
import com.cm6123.snl.GUI.GameFormEvents;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewSnakePanel extends JPanel {

    private JLabel snakeHeadLabel;
    private JLabel snakeTailLabel;
    private JTextField snakeHeadField;
    private JTextField snakeTailField;
    private JButton createSnakeButton;
    private FormListener formListener;


    /////////////////BEGIN TEST CODE////////////////
    private JList newGame;
    /////////////////END TEST CODE//////////////////



    public NewSnakePanel() {
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        snakeHeadLabel = new JLabel("Snake Head: ");
        snakeTailLabel = new JLabel("Snake Tail: ");

        snakeHeadField = new JTextField(10);
        snakeTailField = new JTextField(10);

        /////////////////BEGIN TEST CODE////////////////
        newGame = new JList();

        DefaultListModel newGameList = new DefaultListModel();
        newGameList.addElement("1x1 Board");
        newGameList.addElement("5x5 Board");
        newGameList.addElement("10X10 Board");
        newGame.setModel(newGameList);

        newGame.setPreferredSize(new Dimension(115, 57));
        newGame.setBorder(BorderFactory.createEtchedBorder());
        newGame.setSelectedIndex(0);

        /////////////////END TEST CODE//////////////////




        createSnakeButton = new JButton("Create snake");
//        rollDiceButton.setPreferredSize(new Dimension(300, 200));

        createSnakeButton.addActionListener(new ActionListener() {
//
//            @Override
            public void actionPerformed(final ActionEvent submission) {
                String snakeHead = snakeHeadField.getText();
                String snakeTail = snakeTailField.getText();

                /////////////////BEGIN TEST CODE////////////////
                String chosenData = (String) newGame.getSelectedValue();



                /////////////////END TEST CODE//////////////////

                GameFormEvents snakeEntry = new GameFormEvents(this, snakeHead, snakeTail);

                if (formListener != null) {
                    formListener.formSpecialSquareEntry(snakeEntry);
                }
            }
        });



        TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder("New Snake");
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
        add(snakeHeadLabel, gc);

        gc.gridx = 1;

        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(snakeHeadField, gc);


        /////////////////SECOND ROW////////////////

        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridy = 1;
        gc.gridx = 0;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(snakeTailLabel, gc);

        gc.gridx = 1;

        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(snakeTailField, gc);

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

}

class BoardCategory {
        private Integer id;
        private String text;
        public BoardCategory(final int newID, final String newText) {
            this.id = newID;
            this.text = newText;
        }
}
