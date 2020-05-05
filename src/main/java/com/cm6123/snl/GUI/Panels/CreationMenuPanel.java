
package com.cm6123.snl.GUI.Panels;

        import com.cm6123.snl.GUI.*;

        import javax.swing.*;
        import javax.swing.border.Border;
        import javax.swing.border.TitledBorder;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

public class CreationMenuPanel extends SidePanel {

    private JButton createNewSnakeButton;
    private JButton createNewLadderButton;
    private JButton createNewBoostButton;
    private JButton createNewPlayerButton;
    private JButton createNewDieButton;

    private JLabel titleLabel;
    private JLabel descriptionLabel;

    private FormListener formListener;
    private GridBagConstraints gridStructure;
    private GUIFrame gameGui;
    private NewAddition additionChoice;

    public CreationMenuPanel(final GUIFrame gui) {
        this.gameGui = gui;

        setPanelSize(350, 200);
//        this.setLayout(layout);

//        titleLabel = new JLabel("Creation tool");
//        titleLabel.setFont(new Font("Arial Bold", Font.PLAIN, 16));

        createNewSnakeButton = new JButton("Edit Snake");
        //Code adapted from answer of user Kris: How can I set size of a button?
        //available at: https://stackoverflow.com/questions/2536873/how-can-i-set-size-of-a-button
        createNewSnakeButton.setPreferredSize(new Dimension(200, 60));

        createNewLadderButton = new JButton("Edit Ladder");
        createNewLadderButton.setPreferredSize(new Dimension(200, 60));

        createNewBoostButton = new JButton("Edit Boost");
        createNewBoostButton.setPreferredSize(new Dimension(200, 60));

        createNewPlayerButton = new JButton("Edit Player");
        createNewPlayerButton.setPreferredSize(new Dimension(200, 60));

        createNewDieButton = new JButton("Edit Dice");
        createNewDieButton.setPreferredSize(new Dimension(200, 60));

        createNewSnakeButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                additionChoice = NewAddition.SNAKE;
                gameGui.selectWindow("newaddition");

            }
        });
        createNewLadderButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                additionChoice = NewAddition.LADDER;
                gameGui.selectWindow("newaddition");

            }
        });

        createNewBoostButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                additionChoice = NewAddition.BOOST;
                gameGui.selectWindow("newaddition");

            }
        });

        createNewPlayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                additionChoice = NewAddition.PLAYER;
                gameGui.selectWindow("newaddition");

            }
        });

        createNewDieButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                additionChoice = NewAddition.DIE;
                gameGui.selectWindow("newaddition");

            }
        });
//        gameGui.add(this, BorderLayout.WEST);
    }
//        @Override
        public JPanel createCreationPanel() {

            //Code adapted from TitledBorder.CENTER : TitledBorder « javax.swing.border « Java by API
            //Available at: http://www.java2s.com/Code/JavaAPI/javax.swing.border/TitledBorderCENTER.htm
            TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder("Creation tool");
            Border outerGameBarBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
            innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);

            //Creates a border as a margin around inner game bar.
            setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));

            //USED FOR FLEXIBILITY COMPARED TO OTHER LAYOUTS
            setLayout(new GridBagLayout());

            gridStructure = new GridBagConstraints();

//            gridStructure.weightx = 1;
//            gridStructure.weighty = 0.5;
//            gridStructure.gridx = 2;
//            gridStructure.gridy = 1;
//
//            gridStructure.insets = new Insets(0, 0, 0, 5);
//            add(titleLabel, gridStructure);

            gridStructure = new GridBagConstraints();

            gridStructure.weightx = 1;
            gridStructure.weighty = 1;
            gridStructure.gridx = 0;
            gridStructure.gridy = 2;

            gridStructure.insets = new Insets(0, 0, 0, 5);
            add(createNewSnakeButton, gridStructure);

            gridStructure.weightx = 1;
            gridStructure.weighty = 1;
            gridStructure.gridx = 0;
            gridStructure.gridy = 3;

            gridStructure.insets = new Insets(0, 0, 0, 5);
            add(createNewLadderButton, gridStructure);


            gridStructure.weightx = 1;
            gridStructure.weighty = 1;
            gridStructure.gridx = 0;
            gridStructure.gridy = 4;


    //        gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
            gridStructure.insets = new Insets(0, 0, 0, 0);

            add(createNewBoostButton, gridStructure);

            gridStructure.weightx = 1;
            gridStructure.weighty = 1;
            gridStructure.gridx = 0;
            gridStructure.gridy = 5;


    //        gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
            gridStructure.insets = new Insets(0, 0, 0, 0);
            add(createNewPlayerButton, gridStructure);

            gridStructure.weightx = 1;
            gridStructure.weighty = 1;
            gridStructure.gridx = 0;
            gridStructure.gridy = 6;


    //        gridStructure.anchor = GridBagConstraints.FIRST_LINE_START;
            gridStructure.insets = new Insets(0, 0, 0, 0);
            add(createNewDieButton, gridStructure);

            return this;
        }

        public NewAddition getAdditionChoice() {
            return additionChoice;
        }

    public void setPanelSize(final Integer width, final Integer height) {
        Dimension dim = getPreferredSize();
        dim.width = width;
        dim.height = height;
        setPreferredSize(dim);
    };
}

