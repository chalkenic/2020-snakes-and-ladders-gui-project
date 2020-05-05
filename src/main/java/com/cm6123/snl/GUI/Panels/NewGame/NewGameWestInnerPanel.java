package com.cm6123.snl.GUI.Panels.NewGame;

import com.cm6123.snl.GUI.Panels.SidePanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class NewGameWestInnerPanel extends SidePanel {

    private JLabel snakeChoiceLabel;
    private JLabel ladderChoiceLabel;
    private JLabel playerChoiceLabel;
    private JLabel diceCountChoiceLabel;
    private JLabel diceFaceChoiceLabel;

    private JTextField snakeChoiceField;
    private JTextField ladderChoiceField;
    private JTextField playerChoiceField;
    private JTextField diceCountChoiceField;
    private JTextField diceFaceChoiceField;

    private GridBagConstraints gridStructure;



    public NewGameWestInnerPanel(final String borderTitle) {

//        setPanelSize(350, 380);

        playerChoiceLabel = new JLabel("Choose Player count: ");
        playerChoiceLabel.setPreferredSize(new Dimension(250, 50));
        snakeChoiceLabel = new JLabel("Create Snakes: ");
        snakeChoiceLabel.setPreferredSize(new Dimension(250, 50));
        ladderChoiceLabel = new JLabel("Create Ladders: ");
        ladderChoiceLabel.setPreferredSize(new Dimension(250, 50));
        diceCountChoiceLabel = new JLabel("Choose Dice amount: ");
        diceCountChoiceLabel.setPreferredSize(new Dimension(250, 50));
        diceFaceChoiceLabel = new JLabel("Choose dice face count: ");
        diceFaceChoiceLabel.setPreferredSize(new Dimension(250, 50));

        TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder(borderTitle);
        Border outerGameBarBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);

        setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));

        snakeChoiceField = new JTextField(6);
        ladderChoiceField = new JTextField(6);
        playerChoiceField = new JTextField(6);
        diceCountChoiceField = new JTextField(6);
        diceFaceChoiceField = new JTextField(6);

        setLayout(new GridBagLayout());

        gridStructure = new GridBagConstraints();

        //Instructs playerChoiceLabel position on Panel.
        gridStructure.weightx = 3;
        gridStructure.weighty = 1;
        gridStructure.gridx = 0;
        gridStructure.gridy = 0;
        gridStructure.insets = new Insets(0, 5, 0, 0);
        add(playerChoiceLabel, gridStructure);


        gridStructure.gridx = 5;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(snakeChoiceField, gridStructure);

        //Instructs snakeChoiceLabel position on Panel.
        gridStructure.weightx = 3;
        gridStructure.gridx = 0;
        gridStructure.gridy = 1;
        gridStructure.insets = new Insets(0, 5, 0, 0);
        add(snakeChoiceLabel, gridStructure);

        gridStructure.weightx = 0.1;
        gridStructure.gridx = 5;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(ladderChoiceField, gridStructure);

        //Instructs ladderChoiceLabel position on Panel.
        gridStructure.weightx = 3;
        gridStructure.gridx = 0;
        gridStructure.gridy = 2;
        gridStructure.insets = new Insets(0, 5, 0, 0);
        add(ladderChoiceLabel, gridStructure);

        gridStructure.weightx = 0.1;
        gridStructure.gridx = 5;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(playerChoiceField, gridStructure);

        //Instructs diceCountChoiceLabel position on Panel.
        gridStructure.weightx = 3;
        gridStructure.gridx = 0;
        gridStructure.gridy = 3;
        gridStructure.insets = new Insets(0, 5, 0, 0);
        add(diceCountChoiceLabel, gridStructure);

        gridStructure.weightx = 0.1;
        gridStructure.gridx = 5;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(diceCountChoiceField, gridStructure);

        //Instructs diceFaceChoiceLabel position on Panel.
        gridStructure.weightx = 3;
        gridStructure.gridx = 0;
        gridStructure.gridy = 4;
        gridStructure.insets = new Insets(0, 5, 0, 0);
        add(diceFaceChoiceLabel, gridStructure);

        gridStructure.weightx = 0.1;
        gridStructure.gridx = 5;

        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(diceFaceChoiceField, gridStructure);


    }

    public void setPanelSize(final Integer width, final Integer height) {
        Dimension dim = getPreferredSize();
        dim.width = width;
        dim.height = height;
        setPreferredSize(dim);
    };


}
