package com.cm6123.snl.GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGame implements ActionListener {

    private int counter = 0;
    private JLabel label;
    private JFrame frame;
    private JButton button;

    public NewGame() {

        frame = new JFrame();

        button = new JButton(" Click me");

        button.addActionListener(this);

        label = new JLabel("Number of clicks innit: 0");


        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 100));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snakes & Ladders Game");
        frame.pack();
        frame.setVisible(true);
    }

    /**
     *
     * @param args
     */
    public static void main(final String[] args) {
        new NewGame();
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        counter++;
        label.setText("Number of clicks innit: " + counter);

    }
}


