package com.cm6123.snl.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameToolbar extends JPanel implements ActionListener {

    private GUIFrame gameGui;
    private JButton testButton1;
    private JButton testButton2;

    private StringListener textListener;

    public GameToolbar(final GUIFrame gui) {
        this.gameGui = gui;

        setBorder(BorderFactory.createEtchedBorder());

        testButton1 = new JButton("Main Menu");
        testButton2 = new JButton("button 2");

        testButton1.addActionListener(this);
        testButton2.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(testButton1);
        add(testButton2);
    }

//    public void setTextPanel(final GameTextPanel panel) {
//        this.textPanel = panel;

    public void setStringListener(final StringListener listener) {
        this.textListener = listener;
    }

//    @Override
    public void actionPerformed(final ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (clicked == testButton1) {
            if (textListener != null) {
                gameGui.selectWindow("menu");
            }

//            textPanel.appendText("button 1 pressed!\n");
        } else if (clicked == testButton2) {
            if (textListener != null) {
                textListener.textEmitted("button 2 has been pressed.\n");
            }
//            textPanel.appendText("button 2 pressed!\n");
        }
    }
}
