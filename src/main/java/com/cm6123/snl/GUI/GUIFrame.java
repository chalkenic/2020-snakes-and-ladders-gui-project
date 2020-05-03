package com.cm6123.snl.GUI;

import com.cm6123.snl.GUI.Panels.*;
//import com.cm6123.snl.GUI.Panels.NewSquarePanel;
//import com.cm6123.snl.GUI.Panels.GameTextPanel;
//import com.cm6123.snl.GUI.Panels.SidePanel;

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
    private JPanel currentPanel;
    private LayoutManager layout;

    public GUIFrame() {
        super("Snakes & Ladders");

//        setLayout(new BorderLayout());
        toolbar = new GameToolbar();
        textPanel = new GameTextPanel();
//        newSquarePanel = new SidePanel("Snake");

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
        selectWindow("menu");
        pack();
        setVisible(true);
        setSize(600, 500);

    }

    public void selectWindow(final String windowChoice) {

        if (currentPanel != null) {
//            if (currentPanel.getClass().equals(MainMenuPanel.class)) {
                getContentPane().remove(currentPanel);
//             } else {
//                layout = currentPanel.getLayout();
//                currentPanel.remove(BorderLayout.WEST);
//            }
        }

        add(textPanel, BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);

        switch (windowChoice.toLowerCase()) {
            case "menu":

                getContentPane().removeAll();
                MainMenuPanel mainMenuPanel = new MainMenuPanel(this);
                currentPanel = mainMenuPanel;


                mainMenuPanel.createMenuPanel();



                break;

            case "newspecialsquare":

                SidePanel newSquarePanel = new NewSquarePanel(this, NewSquare.LADDER);
                currentPanel = newSquarePanel;
//                layout = (BorderLayout) newSquarePanel.getLayout();
                newSquarePanel.createSidePanel();



                newSquarePanel.setFormListener(new SquareFormListener() {


                    public void appendTextToPanel(final String text) {
                        textPanel.appendText(text);
                    }

                    public void incorrectEntryMessage() {
                        textPanel.appendText("\n|------------------------------------------------------|"
                                + "\n| ERROR - INCORRECT ENTRY MADE  |"
                                + "\n|------------------------------------------------------|");
                    }

                    public void formDatabaseEntry(final NewSquareFormEvents data) {
                        Integer squareStart;
                        Integer squareEnd;
                        Integer newSquareType;
                        Boolean secondSquareMissing = true;

                        squareStart = data.getSpecialSquareStart();
                        squareEnd = data.getSpecialSquareEnd();


                        if (squareEnd == null) {
                            System.out.println("Test2");
                            newSquareType = newSquarePanel.entryValidation(
                                    newSquarePanel.getSquareChoice(),
                                    squareStart);
                        } else {
                            System.out.println("Do i get here?");
                            newSquareType = newSquarePanel.entryValidation(
                                    newSquarePanel.getSquareChoice(),
                                    squareStart, squareEnd);
                        }


//                        if (squareStart != null) {
                        if (newSquareType == 1) {
                            appendTextToPanel("\nNew Snake Head starts at position " + squareStart
                                    + "\n" + "New Snake Tail ends at position " + squareEnd);

                            System.out.println("JDBC LINK TO GO HERE");
                        } else if (newSquareType == 2) {
                            appendTextToPanel("\nNew Ladder Foot starts at position " + squareStart
                                    + "\n" + "New Ladder Top ends at position " + squareEnd);

                            System.out.println("JDBC LINK TO GO HERE");
                        } else if (newSquareType == 3) {
                            appendTextToPanel("\nBoost square added at location " + squareStart);
                            System.out.println("JDBC LINK TO GO HERE");

                        } else {
                            System.out.println("is this the error?");
                            incorrectEntryMessage();
                        }
//                            else {
////                                appendTextToPanel("\nERROR - values in incorrect positions or identical.");
//                            }
//                        } else {
//                            appendTextToPanel("\nERROR - no value(s) entered.");
//                        }
                    }

//                public void formBoostSquareEntry(final NewSquareFormEvents data) {
//                    Integer boostSquareLocation = Integer.parseInt(data.getBoostSquare());
//                }
                });
                break;
        }
    }

    public void swapPanel(final JFrame currentFrame,
                          final JPanel oldPanel,
                          final JPanel newPanel) {

        currentFrame.getContentPane().remove(oldPanel);
        currentFrame.getContentPane().add(newPanel, BorderLayout.WEST);
        currentFrame.validate();
//    }

//    public JPanel getCurrentPanel() {
//        return currentPanel;
//    }

        //        label = new JLabel("Number of clicks innit: 0");


//        JPanel panel = new JPanel();
//        panel.
//        panel.setLayout(new BorderLayout(0, 1));
//        panel.add(button);
//        panel.add(label);

//        add(panel, BorderLayout.CENTER);


//    public GUIFrame callGUI() {
//        return this;
//    }


//    @Override
//    public void actionPerformed(final ActionEvent e) {
//        counter++;
//        textArea.setText("Number of clicks innit: " + counter);
//    }
    }
}