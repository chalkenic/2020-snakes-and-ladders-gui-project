package com.cm6123.snl.GUI;

import com.cm6123.snl.GUI.Panels.GameTextPanel;
import com.cm6123.snl.GUI.Panels.NewSquarePanel;
import com.cm6123.snl.GUI.Panels.NewSquare;

import javax.swing.*;
import java.awt.*;

public class GUIFrame extends JFrame {

    private int counter = 0;
    private JLabel label;
    private JFrame frame;
    private JButton button;
//    private JTextArea textArea;
    private GameTextPanel textPanel;
    private GameToolbar toolbar;
    private NewSquarePanel newSquarePanel;

    public GUIFrame(final String windowChoice) {
        super("Snakes & Ladders");

//        setLayout(new BorderLayout());
        toolbar = new GameToolbar();
        textPanel = new GameTextPanel();
//        newSquarePanel = new NewSquarePanel("Snake");

//        toolbar.setTextPanel(textPanel);
        toolbar.setStringListener(new StringListener() {
            public void textEmitted(final String text) {
                textPanel.appendText(text);
            }
        });
//        textArea = new JTextArea();
//
//        frame = new JFrame();


//        button = new JButton(" Click me");
//
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(final ActionEvent e) {
//                counter++;
//                textPanel.appendText("Number of clicks innit: " + counter + "\n");
//            }
//        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Snakes & Ladders Game");
        pack();
        setVisible(true);
        setSize(600, 500);


        switch (windowChoice.toLowerCase()) {
            case "menu":
                add(button, BorderLayout.SOUTH);
                break;

            case "newspecialsquare":

                getContentPane().removeAll();
                newSquarePanel = new NewSquarePanel(NewSquare.SNAKE);

                add(newSquarePanel, BorderLayout.WEST);
                add(textPanel, BorderLayout.CENTER);
                add(toolbar, BorderLayout.NORTH);


                newSquarePanel.setFormListener(new FormListener() {


                    public void appendTextToPanel(final String text) {
                        textPanel.appendText(text);
                    }

                    public void formSpecialSquareEntry(final NewSquareFormEvents data) {

                        Integer squareStart = data.getSpecialSquareStart();
                        Integer squareEnd = data.getSpecialSquareEnd();

                        if (squareStart != null) {
                            if (newSquarePanel.confirmValidSquareEntry(newSquarePanel.getSquareChoice(), squareStart,
                                    squareEnd) == 1) {
                                System.out.println("JDBC LINK TO GO HERE");
                            } else if (newSquarePanel.confirmValidSquareEntry(newSquarePanel.getSquareChoice(),
                                    squareStart, squareEnd) == 2) {
                                System.out.println("JDBC LINK TO GO HERE");
                            } else if (newSquarePanel.confirmValidSquareEntry(newSquarePanel.getSquareChoice(),
                                    squareStart) == 3) {
                                System.out.println("JDBC LINK TO GO HERE");
                            } else {
                                appendTextToPanel("\nERROR - values in incorrect positions or identical.");
                            }
                        } else {
                            appendTextToPanel("\nERROR - no value(s) entered.");
                        }
                    }

//                public void formBoostSquareEntry(final NewSquareFormEvents data) {
//                    Integer boostSquareLocation = Integer.parseInt(data.getBoostSquare());
//                }
                });
                break;
        }


//        label = new JLabel("Number of clicks innit: 0");


//        JPanel panel = new JPanel();
//        panel.
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