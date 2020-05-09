package com.cm6123.snl.GUI.Panels.NewGamePanels;

import com.cm6123.snl.GUI.GUIFrame;
import com.cm6123.snl.GUI.Panels.SidePanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class NewGameWestInnerPanel extends SidePanel {

    private JLabel snakeChoiceLabel;
    private JLabel ladderChoiceLabel;
    private JLabel playerCountLabel;
    private JLabel diceCountChoiceLabel;
    private JLabel diceFaceChoiceLabel;

    private JTextField snakeChoiceField;
    private JTextField ladderChoiceField;
    private JTextField playerCountField;
    private JTextField diceCountChoiceField;
    private JTextField diceFaceChoiceField;

    private GridBagConstraints gridStructure;




    public NewGameWestInnerPanel(final String borderTitle, final GUIFrame gui) {

//        setPanelSize(350, 380);

        playerCountLabel = new JLabel("Choose Player count (2 - 5): ");
        playerCountLabel.setPreferredSize(new Dimension(250, 50));
        snakeChoiceLabel = new JLabel("Create Snakes (e.g. 14,5,20,11): ");
        snakeChoiceLabel.setPreferredSize(new Dimension(250, 50));
        ladderChoiceLabel = new JLabel("Create Ladders (e.g. 3,12,13,17): ");
        ladderChoiceLabel.setPreferredSize(new Dimension(250, 50));
        diceCountChoiceLabel = new JLabel("Choose Dice amount (e.g. 1): ");
        diceCountChoiceLabel.setPreferredSize(new Dimension(250, 50));
        diceFaceChoiceLabel = new JLabel("Choose dice face count (e.g. 6): ");
        diceFaceChoiceLabel.setPreferredSize(new Dimension(250, 50));

        TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder(borderTitle);
        Border outerGameBarBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);

        setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));

        snakeChoiceField = new JTextField(6);
        ladderChoiceField = new JTextField(6);
        playerCountField = new JTextField(6);
        diceCountChoiceField = new JTextField(6);
        diceFaceChoiceField = new JTextField(6);

        setLayout(new GridBagLayout());

        gridStructure = new GridBagConstraints();

        //Instructs playerCountLabel position on Panel.
        gridStructure.weightx = 3;
        gridStructure.weighty = 1;
        gridStructure.gridx = 0;
        gridStructure.gridy = 0;
        gridStructure.insets = new Insets(0, 5, 0, 0);
        add(playerCountLabel, gridStructure);


        gridStructure.gridx = 5;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(playerCountField, gridStructure);

        //Instructs snakeChoiceLabel position on Panel.
        gridStructure.weightx = 3;
        gridStructure.gridx = 0;
        gridStructure.gridy = 1;
        gridStructure.insets = new Insets(0, 5, 0, 0);
        add(snakeChoiceLabel, gridStructure);

        gridStructure.weightx = 0.1;
        gridStructure.gridx = 5;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(snakeChoiceField, gridStructure);

        //Instructs ladderChoiceLabel position on Panel.
        gridStructure.weightx = 3;
        gridStructure.gridx = 0;
        gridStructure.gridy = 2;
        gridStructure.insets = new Insets(0, 5, 0, 0);
        add(ladderChoiceLabel, gridStructure);

        gridStructure.weightx = 0.1;
        gridStructure.gridx = 5;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(ladderChoiceField, gridStructure);

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

    public String getSnakeChoiceField() {
        return snakeChoiceField.getText();
    }

    public String getLadderChoiceField() {
        return ladderChoiceField.getText();
    }

    public Integer getPlayerCountField() {
        return Integer.parseInt(playerCountField.getText());
    }

    public Integer getDiceCountChoiceField() {
        return Integer.parseInt(diceCountChoiceField.getText());
    }

    public Integer getDiceFaceChoiceField() {
        return Integer.parseInt(diceFaceChoiceField.getText());
    }
}
