package com.cm6123.snl.GUI;

import com.cm6123.snl.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIFrame extends JFrame {

    private int counter = 0;
    private JLabel label;
    private JFrame frame;
    private JButton button;
//    private JTextArea textArea;
    private GamePanel textPanel;

    public GUIFrame() {
        super("Snakes & Ladders");

        setLayout(new BorderLayout());
        textPanel = new GamePanel();

//        textArea = new JTextArea();

//        frame = new JFrame();


        button = new JButton(" Click me");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Snakes & Ladders Game");
        pack();
        setVisible(true);
        add(button, BorderLayout.SOUTH);
        add(textPanel, BorderLayout.CENTER);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                counter++;
                textPanel.appendText("\nNumber of clicks innit: " + counter);
            }
        });

//        label = new JLabel("Number of clicks innit: 0");


//        JPanel panel = new JPanel();
//        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 100));
//        panel.setLayout(new BorderLayout(0, 1));
//        panel.add(button);
//        panel.add(label);

//        add(panel, BorderLayout.CENTER);



    }

//    @Override
//    public void actionPerformed(final ActionEvent e) {
//        counter++;
//        textArea.setText("Number of clicks innit: " + counter);
//    }
}