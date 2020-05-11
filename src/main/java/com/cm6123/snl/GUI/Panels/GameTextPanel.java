package com.cm6123.snl.GUI.Panels;

import javax.swing.*;
import java.awt.*;

/**
 * Object handles JTextarea that appears on center  of screen when required by other panels for appending.
 */
public class GameTextPanel extends JPanel {
    /**
     * Textbox for panel that holds all entries.
     */
    private JTextArea textArea;

    /**
     * Create new TextPanel on JFrame initialisation for allowing data in game to be printed back to user.
     */
    public GameTextPanel() {
        textArea = new JTextArea();

        setLayout(new BorderLayout());
        //Spacing from side of main JFrame window size.
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(textArea, BorderLayout.CENTER);
        //Scrollbar used for when text area moves outside of screen boundaries.
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }
    /**
     * Method used to append data onto JTextArea. accessed mainly through method on JFrame.
     * @param text - String text to be displayed into area.
     */
    public void appendText(final String text) {
        textArea.append(text);
    }
    /**
     * Wipes all text from JTextArea - used mainly when launching a new game.
     */
    public void wipeTextBox() {
        textArea.setText(null);
    }
}
