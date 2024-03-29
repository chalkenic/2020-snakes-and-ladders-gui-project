
package com.cm6123.snl.GUI.Panels;
import com.cm6123.snl.GUI.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Panel handles navigation to chosen edit panels.
 */
public class EditorMenuPanel extends SidePanel implements ActionListener {
    /**
     * Handles navigation to EditChoicePanel with snakes as edit choice.
     */
    private JButton createNewSnakeButton;
    /**
     * Handles navigation to EditChoicePanel with ladders as edit choice.
     */
    private JButton createNewLadderButton;
    /**
     * Handles navigation to EditChoicePanel with boosts as edit choice.
     */
    private JButton createNewBoostButton;
    /**
     * Handles navigation to EditChoicePanel with player names as edit choice.
     */
    private JButton createNewPlayerButton;
    /**
     * Handles navigation to EditChoicePanel with dice as edit choice.
     */
    private JButton createNewDieButton;
    /**
     * Controls layout of panel via co-ordinates.
     */
    private GridBagConstraints gridStructure;
    /**
     * GUIFrame object access required in order to call selectWindow method on button prompts.
     */
    private GUIFrame gameGui;
    /**
     * Stores choice of GameEdit as selectWindowe GUIFrame object navigation condition.
     */
    private GameEdit additionChoice;
    /**
     * initialises JPanel with navigation buttons.
     * @param gui - current GUIFrame JFrame.
     */
    public EditorMenuPanel(final GUIFrame gui) {
        this.gameGui = gui;

        setPanelSize(350, 200);

        createNewSnakeButton = new JButton("GameEdit Snake");
        //Code adapted from answer of user Kris: How can I set size of a button?
        //available at: https://stackoverflow.com/questions/2536873/how-can-i-set-size-of-a-button
        createNewSnakeButton.setPreferredSize(new Dimension(200, 60));

        createNewLadderButton = new JButton("GameEdit Ladder");
        createNewLadderButton.setPreferredSize(new Dimension(200, 60));

        createNewBoostButton = new JButton("GameEdit Boost");
        createNewBoostButton.setPreferredSize(new Dimension(200, 60));

        createNewPlayerButton = new JButton("GameEdit Player");
        createNewPlayerButton.setPreferredSize(new Dimension(200, 60));

        createNewDieButton = new JButton("GameEdit Dice");
        createNewDieButton.setPreferredSize(new Dimension(200, 60));

        createNewSnakeButton.addActionListener(this);
        createNewLadderButton.addActionListener(this);
        createNewBoostButton.addActionListener(this);
        createNewPlayerButton.addActionListener(this);
        createNewDieButton.addActionListener(this);
    }
    /**
     * Dictates actions to perform on JButton clicks.
     * @param click - the action made onto Jbuttons.
     */
    public void actionPerformed(final ActionEvent click) {
        if (click.getSource() == createNewSnakeButton) {
            additionChoice = GameEdit.SNAKE;
            gameGui.selectWindow(NavigateTo.NEWEDIT);
        } else if (click.getSource() == createNewLadderButton) {
            additionChoice = GameEdit.LADDER;
            gameGui.selectWindow(NavigateTo.NEWEDIT);
        } else if (click.getSource() == createNewBoostButton) {
            additionChoice = GameEdit.BOOST;
            gameGui.selectWindow(NavigateTo.NEWEDIT);
        } else if (click.getSource() == createNewPlayerButton) {
            additionChoice = GameEdit.PLAYER;
            gameGui.selectWindow(NavigateTo.NEWEDIT);
        } else {
            additionChoice = GameEdit.DIE;
            gameGui.selectWindow(NavigateTo.NEWEDIT);
        }
    }
    /**
     * Places all Object variables onto Panel in dictated locations from layout.
     * @return this - panel for placement onto JFrame.
     */
    public JPanel createCreationPanel() {
        //Code adapted from TitledBorder.CENTER : TitledBorder « javax.swing.border « Java by API
        //Available at: http://www.java2s.com/Code/JavaAPI/javax.swing.border/TitledBorderCENTER.htm
        TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder("Editor tool");
        Border outerGameBarBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);
        //Creates border as a margin around inner game bar.
        setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));

        //Gridbag chosen for more specified layout options compared to other layouts.
        setLayout(new GridBagLayout());
        /* Code layout design originates from CaveOfProgramming tutorial:
        Java Swing: GridBagLayout (Video Tutorial Part 4). available at:
        https://www.caveofprogramming.com/java-swing-gui/java-swing-gridbaglayou-video-tutorial-part-4.html */
        gridStructure = new GridBagConstraints();
        gridStructure.weighty = 1;
        gridStructure.gridx = 0;
        gridStructure.gridy = 0;
        add(createNewSnakeButton, gridStructure);
        gridStructure.gridx = 0;
        gridStructure.gridy = 1;
        add(createNewLadderButton, gridStructure);
        gridStructure.gridx = 0;
        gridStructure.gridy = 2;
        add(createNewBoostButton, gridStructure);
        gridStructure.gridx = 0;
        gridStructure.gridy = 3;
        add(createNewPlayerButton, gridStructure);
        gridStructure.gridx = 0;
        gridStructure.gridy = 4;
        add(createNewDieButton, gridStructure);
        return this;
    }
    /**
     * Sources GameEdit choice from button prompt to discern via which GUIFrame selectWindow switch case to follow.
     * @return GameEdit enum.
     */
    public GameEdit getEditChoice() {
        return additionChoice;
    }
    /**
     * parent class method styles size of panel on east of JFrame.
     * @param width - horizontal size of panel.
     * @param height - vertical size of panel.
     */
    public void setPanelSize(final Integer width, final Integer height) {
    Dimension dim = getPreferredSize();
    dim.width = width;
    dim.height = height;
    setPreferredSize(dim);
    };
}

