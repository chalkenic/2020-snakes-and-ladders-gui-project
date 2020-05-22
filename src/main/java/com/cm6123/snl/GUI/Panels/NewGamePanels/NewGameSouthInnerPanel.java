package com.cm6123.snl.GUI.Panels.NewGamePanels;
import com.cm6123.snl.GUI.GUIFrame;
import com.cm6123.snl.GUI.NavigateTo;
import com.cm6123.snl.GUI.Panels.SidePanel;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Object handles data for board size & game creation options for custom game creation.
 */
public class NewGameSouthInnerPanel extends SidePanel implements ActionListener {
    /**
     * Holds text for board size JSlider ; aligns with slider.
     */
    private JLabel boardSizeLabel;
    /**
     * Holds text for defining a default game; aligns with JButton.
     */
    private JLabel defaultGameLabel;
    /**
     * Holds text for errorLabel, appending upon entry error. default as not enabled.
     */
    private JLabel errorLabel;
    /**
     *  Slider for choosing from set board grid sizes.
     */
    private JSlider boardSize;
    /**
     * Button handles navigation to defaultgame switch case on GUIFrame.
     */
    private JButton defaultGameButton;
    /**
     * Button handles navigation to customgame switch case on GUIFrame.
     */
    private JButton customGameButton;
    /**
     * Controls layout of panel via co-ordinates.
     */
    private GridBagConstraints gridStructure;
    /**
     * GUIFrame object access required in order to call selectWindow method on button prompts.
     */
    private GUIFrame gameGui;
    /**
     * Initialisation via constructor creates board size data to be placed onto subpanel of NewGameParentPanel.
     * handles navigation for newGame panels.
     * @param borderTitle - Title from parent panel that dictates string text for title.
     * @param gui - main JFrame window.
     */
    NewGameSouthInnerPanel(final String borderTitle, final GUIFrame gui) {
        this.gameGui = gui;
        boardSizeLabel = new JLabel("Board Size ");
        defaultGameLabel = new JLabel("Start default game (2 players, ex. basic features, no extra features)");
        errorLabel = new JLabel("____________________________");
        //Large default sizes given to labels in order to match size of south panel.
        boardSizeLabel.setPreferredSize(new Dimension(400, 30));
        defaultGameLabel.setPreferredSize(new Dimension(400, 30));
        //Error not enabled or required at object initialisation.
        errorLabel.setEnabled(false);
        //JSlider will only create a board at minimum 5x5 size, going up to 15x15.
        boardSize = new JSlider(5, 15, 5);
        boardSize.setPreferredSize(new Dimension(600, 50));
        //Enables additional flair to board size slider.

        defaultGameButton = new JButton("Start Default Game");
        customGameButton = new JButton("Start Game With Options");

        boardSize.setPaintLabels(true);
        boardSize.setPaintTicks(true);
        boardSize.setPaintTrack(true);
        //Ticks on board JSlider to appear at increments of 1.
        boardSize.setMajorTickSpacing(1);
        defaultGameButton.addActionListener(this);
        //Custom game button given additional styling to set apart from panel.
        customGameButton.setBackground(Color.PINK);
        customGameButton.addActionListener(this);


        TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder(borderTitle);
        Border outerGameBarBorder = BorderFactory.createEmptyBorder(2, 10, 20, 10);
        innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);

        setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));

        setLayout(new GridBagLayout());
        /* Code layout design originates from CaveOfProgramming tutorial:
        Java Swing: GridBagLayout (Video Tutorial Part 4). available at:
        https://www.caveofprogramming.com/java-swing-gui/java-swing-gridbaglayou-video-tutorial-part-4.html */
        gridStructure = new GridBagConstraints();
        //Board size positioning.
        gridStructure.weightx = 1;
        gridStructure.weighty = 0.1;
        gridStructure.gridx = 0;
        gridStructure.gridy = 0;
        //Layout additions kept on same line in order to keep consistency on south panel.
        // additions pushed to start & end of each line.
        gridStructure.anchor = GridBagConstraints.LINE_START;
        gridStructure.insets = new Insets(0, 5, 0, 0);
        add(boardSizeLabel, gridStructure);
        gridStructure.anchor = GridBagConstraints.LINE_END;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(boardSize, gridStructure);
        //Default game positioning.
        gridStructure.gridy = 1;
        gridStructure.anchor = GridBagConstraints.LINE_START;
        gridStructure.insets = new Insets(0, 5, 0, 0);
        add(defaultGameLabel, gridStructure);
        gridStructure.anchor = GridBagConstraints.LINE_END;
        gridStructure.insets = new Insets(0, 0, 0, 5);
        add(defaultGameButton, gridStructure);
        //Custom game button size expanded for additional setting apart from default panel data.
        //Code adapted from VGR Answer: Java - Resize buttons in GridBagLayout.
        //Available at: https://stackoverflow.com/questions/40491835/java-resize-buttons-in-gridbaglayout
        gridStructure.ipadx = 500;
        gridStructure.ipady = 20;
        gridStructure.gridy = 2;
        gridStructure.anchor = GridBagConstraints.LINE_START;
        gridStructure.insets = new Insets(5, 5, 5, 5);
        add(customGameButton, gridStructure);
        //Error label positioning.
        gridStructure.ipadx = 1;
        gridStructure.ipady = 1;
        gridStructure.gridy = 3;
        gridStructure.anchor = GridBagConstraints.CENTER;
        add(errorLabel, gridStructure);
    }
    /**
     * Error label changes content as well as being enabled with method called by other program areas.
     * @param s - String parameter to be inserted into label.
     */
    public void setErrorLabel(final String s) {
        errorLabel.setEnabled(true);
        errorLabel.setText(s);
    }
    /**
     * Panel implements abstract method but doesn't require usage due to confliction with GridBaglayout object.
     * @param width - horizontal size of panel.
     * @param height - vertical size of panel.
     */
    public void setPanelSize(final Integer width, final Integer height) {
        Dimension dim = getPreferredSize();
        dim.width = width;
        dim.height = height;
        setPreferredSize(dim);
    };
    /**
     * handles button clicks on custom & default game JButtons.
     * @param click - click action.
     */
    public void actionPerformed(final ActionEvent click) {
        if (click.getSource().equals(defaultGameButton)) {
            //Game calls selectWindow method and attempts to run a default game.
           gameGui.selectWindow(NavigateTo.RUNDEFAULTGAME);
        } else if (click.getSource().equals(customGameButton)) {
            //Game calls selectWindow method and attempts to run a custom game with panel parameters.
            gameGui.selectWindow(NavigateTo.RUNCUSTOMGAME);
        }
    }
    /**
     * Getter for chosen board size on panel.
     * @return Integer value of JSlider choice.
     */
    public Integer getBoardSize() {
        return boardSize.getValue();
    }
}
