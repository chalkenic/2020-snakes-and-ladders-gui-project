package com.cm6123.snl.GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class GameTextPanel extends JPanel {

    private JTextArea textArea;

    public GameTextPanel() {
        textArea = new JTextArea();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(textArea, BorderLayout.CENTER);

        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    /**
     * INSERT DATA HERE
     * @param text
     */
    public void appendText(String text) {
        textArea.append(text);
    }
}
