package com.cm6123.snl.GUI;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private JTextArea textArea;

    public GamePanel() {
        textArea = new JTextArea();

        setLayout(new BorderLayout());

        add(textArea, BorderLayout.CENTER);

        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    public void appendText(String text) {
        textArea.append(text);
    }
}
