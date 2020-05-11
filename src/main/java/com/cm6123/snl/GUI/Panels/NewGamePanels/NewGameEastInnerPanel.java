package com.cm6123.snl.GUI.Panels.NewGamePanels;
import com.cm6123.snl.GUI.Panels.SidePanel;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Object handles data for additional game features for custom game creation.
 */
public class NewGameEastInnerPanel extends SidePanel {
    /**
     * Holds text for winning square feature; aligns with checkbox.
     */
    private JLabel winningSquareOnlyOnLabel;
    /**
     * Holds text for boost square feature; aligns with checkbox.
     */
    private JLabel boostSquareOnLabel;
    /**
     * Holds text for boost square choices; aligns with JTextfield.
     */
    private JLabel boostSquareChoiceLabel;
    /**
     * Checkbox to confirm if winning square is on.
     */
    private JCheckBox winningCheckBox;
    /**
     * Checkbox to confirm if boost feature is on.
     */
    private JCheckBox boostCheckBox;
    /**
     * Field to intake data once boostCheckBox has been checked.
     */
    private JTextField boostChoiceField;
    /**
     * Controls layout of panel via co-ordinates.
     */
    private GridBagConstraints gridStructure;
    /**
     * Initialisation via constructor creates additonal data to be placed onto subpanel of NewGameParentPanel.
     * @param borderTitle - Title from parent panel that dictates string text for title.
     */
    NewGameEastInnerPanel(final String borderTitle) {

        winningSquareOnlyOnLabel = new JLabel("Winning Square Feature: ");
        winningSquareOnlyOnLabel.setPreferredSize(new Dimension(250, 62));
        boostSquareOnLabel = new JLabel("Boost Square Feature: ");
        boostSquareOnLabel.setPreferredSize(new Dimension(250, 62));
        boostSquareChoiceLabel = new JLabel("Boost Squares (e.g. 7, 15): ");
        boostSquareChoiceLabel.setPreferredSize(new Dimension(250, 62));
        boostCheckBox = new JCheckBox();
        winningCheckBox = new JCheckBox();
        boostChoiceField = new JTextField(6);
        //Label & field for boost JTexfield switched off until boostCheckBox checked.
        boostSquareChoiceLabel.setEnabled(false);
        boostChoiceField.setEnabled(false);

        //Listener handles turning on boost field upon checking box.
        boostCheckBox.addActionListener(new ActionListener() {

            public void actionPerformed(final ActionEvent e) {
                //Boolean attached to checkbox that changes boost data.
                Boolean boostSquareTicked = boostCheckBox.isSelected();
                boostSquareChoiceLabel.setEnabled(boostSquareTicked);
                boostChoiceField.setEnabled(boostSquareTicked);
            }
        });

        //Compound border created to allow space between title border & content at specified sides.
        TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder(borderTitle);
        Border outerGameBarBorder = BorderFactory.createEmptyBorder(2, 10, 0, 0);
        innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);

        setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));
        //GridBagLayout chosen for customisible elements.
        setLayout(new GridBagLayout());

        gridStructure = new GridBagConstraints();

        gridStructure.weightx = 3;
        gridStructure.weighty = 1;

        gridStructure.gridx = 0;
        gridStructure.gridy = 0;
        gridStructure.insets = new Insets(0, 5, 0, 0);
        add(winningSquareOnlyOnLabel, gridStructure);


        gridStructure.gridx = 5;
        //Forces checkbox at end of border but 30 pixels away from edge.
        gridStructure.anchor = GridBagConstraints.LINE_END;
        gridStructure.insets = new Insets(0, 0, 0, 30);
        add(winningCheckBox, gridStructure);

        gridStructure.gridx = 0;
        gridStructure.gridy = 1;
        gridStructure.insets = new Insets(0, 5, 0, 0);
        add(boostSquareOnLabel, gridStructure);


        gridStructure.gridx = 5;
        gridStructure.anchor = GridBagConstraints.LINE_END;
        gridStructure.insets = new Insets(0, 0, 0, 30);
        add(boostCheckBox, gridStructure);

        gridStructure.gridx = 0;
        gridStructure.gridy = 2;
        gridStructure.insets = new Insets(0, 5, 0, 0);
        add(boostSquareChoiceLabel, gridStructure);

        gridStructure.gridx = 5;

        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(boostChoiceField, gridStructure);
    }
    /**
     * Panel implements abstract method but doesn't require usage due to confliction with GridBaglayout object.
     * @param width - horizontal size of panel.
     * @param height - vertical size of panel.
     */
    public void setPanelSize(final Integer width, final Integer height) { }

    /**
     * Getter for CreateGame obejct that confirms if checkbox ticked.
     * @return entry - true if ticked.
     */
    public Boolean getWinningCheckBox() {
        Boolean entry = false;
        if (winningCheckBox.isSelected()) {
            entry = true;
        }
        return entry;
    }
    /**
     * Getter for CreateGame object that confirms if checkbox ticked, and grabs value if true.
     * @return entry - values from the JTexfield boostChoiceField.
     */
    public String getBoostChoiceField() {
        String entry = null;
        if (boostCheckBox.isSelected()) {
            entry = boostChoiceField.getText();
        }
        return entry;
    }
}
