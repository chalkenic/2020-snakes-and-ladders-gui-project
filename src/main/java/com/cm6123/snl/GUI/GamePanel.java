package com.cm6123.snl.GUI;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private JTextArea textArea;

    public GamePanel() {
        textArea = new JTextArea();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 100));

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
