package com.cm6123.snl.GUI;

import com.cm6123.snl.GUI.Panels.*;
//import com.cm6123.snl.GUI.Panels.NewAdditionPanel;
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
    private JPanel panelContainer;
    private JPanel currentPanel;
//    private LayoutManager layout;
    private CreationMenuPanel creationMenuPanel;
    private MainMenuPanel mainMenuPanel;

    public GUIFrame() {
        super("Snakes & Ladders");




//        setLayout(new BorderLayout());
        panelContainer = new JPanel();
        toolbar = new GameToolbar(this);
        textPanel = new GameTextPanel();


//        newSquarePanel = new SidePanel("Snake");

//        toolbar.setTextPanel(textPanel);

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
//        cardLayout = new CardLayout();
//        panelContainer.setLayout(cardLayout);
//        add(panelContainer);

        selectWindow("menu");
        pack();
        setVisible(true);
        setSize(600, 500);

        toolbar.setStringListener(new StringListener() {
            public void textEmitted(final String text) {
                textPanel.appendText(text);
            }
        });

    }

    public void selectWindow(final String windowChoice) {
        System.out.println("Current window choice: " + windowChoice);
        System.out.println(currentPanel);
//
//        if (currentPanel != null) {
//            getContentPane().remove(currentPanel);
//        }

        if (windowChoice != "menu") {
            add(textPanel, BorderLayout.CENTER);
            add(toolbar, BorderLayout.NORTH);
        }



        switch (windowChoice.toLowerCase()) {
            case "menu":

                getContentPane().removeAll();
                revalidate();
                mainMenuPanel = new MainMenuPanel(this);
//               mainMenuPanel.setLayout(sidePanel);

                if (currentPanel == null) {
                    currentPanel = mainMenuPanel;
                    mainMenuPanel.createMenuPanel();
                } else {
                    swapSidePanel(this,
                            currentPanel,
                            mainMenuPanel.createMenuPanel(),
                            BorderLayout.CENTER);
                    currentPanel = mainMenuPanel;
                }

//                panelContainer.add(mainMenuPanel.createMenuPanel(), "1");
//                cardLayout.show(panelContainer, "1");
                break;

            case "creationmenu":


                getContentPane().remove(currentPanel);
                revalidate();
                creationMenuPanel = new CreationMenuPanel(this);

//                panelContainer.add(creationMenuPanel.createCreationPanel(), "2");
                swapSidePanel(this,
                        currentPanel,
                        creationMenuPanel.createCreationPanel(),
                        BorderLayout.WEST);
                currentPanel = creationMenuPanel;
//                cardLayout.show(panelContainer, "2");
                break;


            case "newaddition":
                getContentPane().remove(currentPanel);
                revalidate();
                NewAddition newAdditionChoice = creationMenuPanel.getAdditionChoice();
                NewAdditionPanel additionPanel = new NewAdditionPanel(this, newAdditionChoice);

//                layout = (BorderLayout) additionPanel.getLayout();

//                panelContainer.add(additionPanel, "3");
                swapSidePanel(this,
                        currentPanel,
                        additionPanel.createAdditionPanel(),
                        BorderLayout.WEST);

                currentPanel = additionPanel;
//                cardLayout.show(panelContainer, "3");



                additionPanel.setFormListener(new SquareFormListener() {


                    public void appendTextToPanel(final String text) {
                        textPanel.appendText(text);
                    }

                    public void incorrectEntryMessage() {
                        textPanel.appendText("\n|------------------------------------------------------|"
                                + "\n| ERROR - INCORRECT ENTRY MADE  |"
                                + "\n|------------------------------------------------------|");
                    }

                    public void formDatabaseEntry(final NewAdditionFormEvents data) {

                        if (data.getPlayerNameEntry() == null) {
                            Integer firstFieldEntry;
                            Integer secondFieldEntry;
                            Integer newSquareType;
                            Boolean secondSquareMissing = true;

                            firstFieldEntry = data.getFirstFieldEntry();
                            secondFieldEntry = data.getSecondFieldEntry();


                            if (secondFieldEntry == null) {
                                newSquareType = additionPanel.entryValidation(
                                        additionPanel.getAdditionChoice(),
                                        firstFieldEntry);
                            } else {
                                newSquareType = additionPanel.entryValidation(
                                        additionPanel.getAdditionChoice(),
                                        firstFieldEntry, secondFieldEntry);
                            }


//                        if (firstFieldEntry != null) {
                            if (newSquareType == 1) {
                                appendTextToPanel("New Snake Head starts at position " + firstFieldEntry
                                        + "\n" + "New Snake Tail ends at position " + secondFieldEntry + "\n");

                                System.out.println("JDBC LINK TO GO HERE");
                            } else if (newSquareType == 2) {
                                appendTextToPanel("New Ladder Foot starts at position " + firstFieldEntry
                                        + "\n" + "New Ladder Top ends at position " + secondFieldEntry + "\n");

                                System.out.println("JDBC LINK TO GO HERE");
                            } else if (newSquareType == 3) {
                                appendTextToPanel("Boost square added at location " + firstFieldEntry + "\n");
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
                    }

//                public void formBoostSquareEntry(final NewAdditionFormEvents data) {
//                    Integer boostSquareLocation = Integer.parseInt(data.getBoostSquare());
//                }
                });
                break;
        }
    }

    public void swapSidePanel(final JFrame currentFrame,
                          final JPanel oldPanel,
                          final JPanel newPanel,
                              final String borderLayout) {

        currentFrame.remove(oldPanel);
        currentFrame.add(newPanel, borderLayout);
        currentFrame.revalidate();
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