package com.cm6123.snl.GUI.Panels.NewGamePanels;
import com.cm6123.snl.GUI.Panels.SidePanel;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
/**
 * Object handles data for basic game features for custom game creation.
 */
public class NewGameWestInnerPanel extends SidePanel {
    /**
     * Holds text for player count; aligns with JTextField.
     */
    private JLabel playerCountLabel;
    /**
     * Holds text for snake choices; aligns with JTextField.
     */
    private JLabel snakeChoiceLabel;
    /**
     * Holds text for ladder choices; aligns with JTextField.
     */
    private JLabel ladderChoiceLabel;
    /**
     * Holds text for amount of dice to use ; aligns with JTextField.
     */
    private JLabel diceCountChoiceLabel;
    /**
     * Holds text for dice size ; aligns with JTextField.
     */
    private JLabel diceFaceChoiceLabel;
    /**
     * Field to intake player count by user.
     */
    private JTextField playerCountField;
    /**
     * Field to intake snake choices by user.
     */
    private JTextField snakeChoiceField;
    /**
     * Field to intake ladder choices by user.
     */
    private JTextField ladderChoiceField;
    /**
     * Field to intake amount of dice to use by user.
     */
    private JTextField diceCountChoiceField;
    /**
     * Field to intake dice size by user.
     */
    private JTextField diceFaceChoiceField;
    /**
     * Controls layout of panel via co-ordinates.
     */
    private GridBagConstraints gridStructure;

    /**
     * Initialisation via constructor creates basic data to be placed onto subpanel of NewGameParentPanel.
     * @param borderTitle - Title from parent panel that dictates string text for title.
     */
    NewGameWestInnerPanel(final String borderTitle) {
        //Basic Jlabels & JTextFields. Sizes defined to ensure consistency on panel.
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
        //TextFields given specific column sizes as to not be different length on panel (consistency).
        snakeChoiceField = new JTextField(6);
        ladderChoiceField = new JTextField(6);
        playerCountField = new JTextField(6);
        diceCountChoiceField = new JTextField(6);
        diceFaceChoiceField = new JTextField(6);

        //Compound border created to allow space between title border & content at specified sides.
        //Title intakes from constructor parameter.
        TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder(borderTitle);
        Border outerGameBarBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);

        setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));


        setLayout(new GridBagLayout());
        /* Code layout design originates from CaveOfProgramming tutorial:
        Java Swing: GridBagLayout (Video Tutorial Part 4). available at:
        https://www.caveofprogramming.com/java-swing-gui/java-swing-gridbaglayou-video-tutorial-part-4.html */
        gridStructure = new GridBagConstraints();

        //Instructs playerCountLabel position on Panel.
        gridStructure.weightx = 3;
        gridStructure.weighty = 1;
        gridStructure.gridx = 0;
        gridStructure.gridy = 0;
        //Insets used on all additions to ensure sapce between themselves & border.
        gridStructure.insets = new Insets(0, 5, 0, 0);
        add(playerCountLabel, gridStructure);
        gridStructure.gridx = 5;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(playerCountField, gridStructure);

        //Instructs snakeChoiceLabel position on Panel.
        gridStructure.gridx = 0;
        gridStructure.gridy = 1;
        gridStructure.insets = new Insets(0, 5, 0, 0);
        add(snakeChoiceLabel, gridStructure);
        gridStructure.gridx = 5;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(snakeChoiceField, gridStructure);
        //Instructs ladderChoiceLabel position on Panel.
        gridStructure.gridx = 0;
        gridStructure.gridy = 2;
        gridStructure.insets = new Insets(0, 5, 0, 0);
        add(ladderChoiceLabel, gridStructure);
        gridStructure.gridx = 5;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(ladderChoiceField, gridStructure);
        //Instructs diceCountChoiceLabel position on Panel.
        gridStructure.gridx = 0;
        gridStructure.gridy = 3;
        gridStructure.insets = new Insets(0, 5, 0, 0);
        add(diceCountChoiceLabel, gridStructure);
        gridStructure.gridx = 5;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(diceCountChoiceField, gridStructure);

        //Instructs diceFaceChoiceLabel position on Panel.
        gridStructure.gridx = 0;
        gridStructure.gridy = 4;
        gridStructure.insets = new Insets(0, 5, 0, 0);
        add(diceFaceChoiceLabel, gridStructure);
        gridStructure.gridx = 5;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(diceFaceChoiceField, gridStructure);
    }
    /**
     * Panel implements abstract method but doesn't require usage due to confliction with GridBaglayout object.
     * @param width - horizontal size of panel.
     * @param height - vertical size of panel.
     */
    public void setPanelSize(final Integer width, final Integer height) {
    }

    /**
     *  Getter used by CreateGame Obejct to source JTextField player count.
     * @return Integer - amount of players.
     */
    public Integer getPlayerCountField() {
        return Integer.parseInt(playerCountField.getText());
    }
    /**
     * Getter used by CreateGame Object to source JTextField snake data.
     * @return all snakes as type String.
     */
    public String getSnakeChoiceField() {
        return snakeChoiceField.getText();
    }

    /**
     * Getter used by CreateGame Object to source JTextField ladder data.
     * @return all ladders as type String.
     */
    public String getLadderChoiceField() {
        return ladderChoiceField.getText();
    }

    /**
     * Getter used by CreateGame Object to source JTextField dice count data.
     * @return Integer - amount of dice to be played with.
     */
    public Integer getDiceCountChoiceField() {
        return Integer.parseInt(diceCountChoiceField.getText());
    }

    /**
     * Getter used by CreateGame Object to source JTextField dice face data.
     * @return Integer - size of each die.
     */
    public Integer getDiceFaceChoiceField() {
        return Integer.parseInt(diceFaceChoiceField.getText());
    }
}
