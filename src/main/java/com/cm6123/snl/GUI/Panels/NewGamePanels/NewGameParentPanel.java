package com.cm6123.snl.GUI.Panels.NewGamePanels;

import com.cm6123.snl.GUI.GUIFrame;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
/**
 * Class stores child panels that appear on higher layer of JFrame.
 */
public class NewGameParentPanel extends JPanel {
    /**
     * Contains basic features panel on West of BorderLayout.
     */
    private NewGameWestInnerPanel leftPanel;
    /**
     * Contains additional features panel on East of BorderLayout.
     */
    private NewGameEastInnerPanel rightPanel;
    /**
     * Contains Board & game buttons panel on South of BorderLayout.
     */
    private NewGameSouthInnerPanel bottomPanel;
    /**
     * Stores GUI JFrame.
     */
    private GUIFrame gameGui;
    /**
     * Instantiates parent panel along with child panels for insertion on higher layer.
     * @param gui - JFrame object.
     */
    public NewGameParentPanel(final GUIFrame gui) {
        this.gameGui = gui;
        //Panels take gameGui into constructor in order to parse data into GUIFrame class.
        leftPanel = new NewGameWestInnerPanel("Basic Features");
        rightPanel = new NewGameEastInnerPanel("Additional Features");
        bottomPanel = new NewGameSouthInnerPanel("Board & Game", gameGui);

    }

    /**
     * Constructs Panel with Border & adds Child panels to specified Layout positions.
     * @return Panel to JFrame for implentation onto content pane.
     */
    public JPanel createNewGamePanel() {
        //set title border around entire Panel.
        //Code adapted from TitledBorder.CENTER : TitledBorder - javax.swing.border - Java by API
        //Available at: http://www.java2s.com/Code/JavaAPI/javax.swing.border/TitledBorderCENTER.htm
        TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder("New Game");
        innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);

        setBorder(innerGameBarBorder);
        //Adds child panels to desired locations.
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(bottomPanel, BorderLayout.SOUTH);
        return this;
}

    /**
     * Getter for accessing stored basic features data inside panel.
     * @return leftpanel Object.
     */
    public NewGameWestInnerPanel getLeftPanel() {
        return leftPanel;
    }
    /**
     * Getter for accessing stored additional features data inside panel.
     * @return rightpanel Object.
     */
    public NewGameEastInnerPanel getRightPanel() {
        return rightPanel;
    }
    /**
     * Getter for accessing stored Board & game data inside panel.
     * @return southpanel Object.
     */
    public NewGameSouthInnerPanel getSouthPanel() {
        return bottomPanel;
    }
}
