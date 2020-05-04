package com.cm6123.snl.GUI.Panels;

import com.cm6123.snl.GUI.GUIFrame;
import com.cm6123.snl.GUI.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;

public class GameToolbarPanel extends JPanel implements ActionListener {

    private GUIFrame gameGui;
    private JButton testButton1;
    private JButton testButton2;

    private StringListener textListener;

    public GameToolbarPanel(final GUIFrame gui) {
        this.gameGui = gui;

        setBorder(BorderFactory.createEtchedBorder());

        testButton1 = new JButton("Main Menu");
        testButton2 = new JButton("Creation menu");

        testButton1.addActionListener(this);
        testButton2.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.RIGHT));

        add(testButton2);
        add(testButton1);

        testButton2.setVisible(false);
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
                testButton2.setVisible(false);
                gameGui.selectWindow("menu");
            }
        }
            System.out.println("Test2");

        if (clicked == testButton2) {
            if (textListener != null) {
                gameGui.selectWindow("creationmenu");
            }
        }


//            textPanel.appendText("button 1 pressed!\n");
//        } else if (clicked == testButton2) {
//            if (textListener != null) {
//                gameGui.selectWindow("menu");
////                textListener.textEmitted("button 2 has been pressed.\n");
//            }
//            textPanel.appendText("button 2 pressed!\n");
    }



    public void showCreationButton() {
        testButton2.setVisible(true);
    }
}
