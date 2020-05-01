package com.cm6123.snl.GUI.Frames;

import com.cm6123.snl.GUI.FormListener;
import com.cm6123.snl.GUI.GameFormEvents;
import com.cm6123.snl.GUI.GameToolbar;
import com.cm6123.snl.GUI.Panels.GameTextPanel;
import com.cm6123.snl.GUI.Panels.NewSnakePanel;
import com.cm6123.snl.GUI.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewSquareFrame extends JFrame {

    private int counter = 0;
    private JLabel label;
    private JFrame frame;
    private JButton button;
//    private JTextArea textArea;
    private GameTextPanel textPanel;
    private GameToolbar toolbar;
    private NewSnakePanel formPanel;

    public NewSquareFrame() {
        super("Snakes & Ladders");

        setLayout(new BorderLayout());
        toolbar = new GameToolbar();
        textPanel = new GameTextPanel();
        formPanel = new NewSnakePanel();

//        toolbar.setTextPanel(textPanel);
        toolbar.setStringListener(new StringListener() {
            public void textEmitted(final String text) {
                textPanel.appendText(text);
            }
        });

        formPanel.setFormListener(new FormListener() {

            public void formSpecialSquareEntry(final GameFormEvents data) {
                Integer specialSquareStart = Integer.parseInt(data.getSpecialSquareStart());
                Integer specialSquareEnd = Integer.parseInt(data.getSpecialSquareEnd());

                if (specialSquareStart > specialSquareEnd) {
                    textPanel.appendText("New Snake Head starts at position " + specialSquareStart
                            + "\n" + "New Snake Tail ends at position "
                            + specialSquareEnd + "\n");
                } else if (specialSquareEnd > specialSquareStart) {
                    textPanel.appendText("New Ladder Foot starts at position " + specialSquareStart
                            + "\n" + "New Ladder Top ends at position "
                            + specialSquareEnd + "\n");
                } else {
                    textPanel.appendText("ERROR - values must be different from one another.");
                }
            }

            public void formBoostSquareEntry(final GameFormEvents data) {
                Integer boostSquareLocation = Integer.parseInt(data.getBoostSquare());

                textPanel.appendText("Boost square added at location " + boostSquareLocation);
            }
        });

//        textArea = new JTextArea();

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
        setSize(600, 500);
        setVisible(true);



        add(formPanel, BorderLayout.WEST);
//        add(button, BorderLayout.SOUTH);
        add(textPanel, BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);



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