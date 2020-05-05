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
    private GameToolbarPanel toolbar;
    private JPanel panelContainer;
    private JPanel currentPanel;
//    private LayoutManager layout;
    private CreationMenuPanel creationMenuPanel;
    private MainMenuPanel mainMenuPanel;
    private LoadGamePanel loadGamePanel;
    private NewGamePanel newGamePanel;

    public GUIFrame() {
        super("Snakes & Ladders");




//        setLayout(new BorderLayout());
        panelContainer = new JPanel();
        toolbar = new GameToolbarPanel(this);
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
//        setSize(800, 650);
        setMinimumSize(new Dimension(800, 600));

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
//        if (windowChoice != "menu" && windowChoice != "loadgame") {

        if (windowChoice != "menu") {
            add(toolbar, BorderLayout.NORTH);
            if (windowChoice != "loadgame" && windowChoice != "newgame") {
                add(textPanel, BorderLayout.CENTER);
                if (windowChoice != "creationmenu") {
                    toolbar.showCreationButton();
                }
            }

        }
        switch (windowChoice.toLowerCase()) {
            case "menu":

                getContentPane().remove(textPanel);
//                getContentPane().remove(toolbar);
//                revalidate();
                mainMenuPanel = new MainMenuPanel(this);
//               mainMenuPanel.setLayout(sidePanel);

                if (currentPanel == null) {
                    currentPanel = mainMenuPanel;
                    mainMenuPanel.createMenuPanel();
                } else {
                    swapPanel(this,
                            currentPanel,
                            mainMenuPanel.createMenuPanel(),
                            BorderLayout.CENTER);
                    currentPanel = mainMenuPanel;

                }

//                panelContainer.add(mainMenuPanel.createMenuPanel(), "1");
//                cardLayout.show(panelContainer, "1");
                break;

            case "newgame":

                getContentPane().remove(currentPanel);
                revalidate();
                newGamePanel = new NewGamePanel(this);
                System.out.println("Removing panel " + currentPanel);

//                panelContainer.add(creationMenuPanel.createCreationPanel(), "2");
                swapPanel(this,
                        currentPanel,
                        newGamePanel.createNewGamePanel(),
                        BorderLayout.CENTER);
                currentPanel = newGamePanel;
//                cardLayout.show(panelContainer, "2");

                break;


            case "loadgame":

                getContentPane().remove(currentPanel);
                revalidate();
                loadGamePanel = new LoadGamePanel(this);
                System.out.println("Removing panel " + currentPanel);

//                panelContainer.add(creationMenuPanel.createCreationPanel(), "2");
                swapPanel(this,
                        currentPanel,
                        loadGamePanel.createloadGamePanel(),
                        BorderLayout.CENTER);
                currentPanel = loadGamePanel;
//                cardLayout.show(panelContainer, "2");

                break;

            case "creationmenu":

                getContentPane().remove(currentPanel);
                revalidate();
                creationMenuPanel = new CreationMenuPanel(this);

//                panelContainer.add(creationMenuPanel.createCreationPanel(), "2");
                swapPanel(this,
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
                swapPanel(this,
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
                            Boolean newSquareType;
                            Boolean secondSquareMissing = true;

                            firstFieldEntry = data.getFirstFieldEntry();
                            secondFieldEntry = data.getSecondFieldEntry();

                            if (secondFieldEntry == null) {
                                newSquareType = additionPanel.entryValidation(additionPanel.getAdditionChoice(),
                                        firstFieldEntry);
                            } else {
                                newSquareType = additionPanel.entryValidation(additionPanel.getAdditionChoice(),
                                        firstFieldEntry, secondFieldEntry);
                            }

                            if (newSquareType && data.getAdditionChoice() == NewAddition.SNAKE) {
                                appendTextToPanel("New Snake Head starts at position " + firstFieldEntry
                                        + "\n" + "New Snake Tail ends at position " + secondFieldEntry + "\n");

                                System.out.println("JDBC LINK TO GO HERE");
                            } else if (newSquareType && data.getAdditionChoice() == NewAddition.LADDER) {
                                appendTextToPanel("New Ladder Foot starts at position " + firstFieldEntry
                                        + "\n" + "New Ladder Top ends at position " + secondFieldEntry + "\n");

                                System.out.println("JDBC LINK TO GO HERE");
                            } else if (newSquareType && data.getAdditionChoice() == NewAddition.BOOST) {
                                appendTextToPanel("Boost square added at location " + firstFieldEntry + "\n");
                                System.out.println("JDBC LINK TO GO HERE");

                            } else if (newSquareType && data.getAdditionChoice() == NewAddition.PLAYER) {
                                appendTextToPanel("New player created: " + firstFieldEntry);
                                System.out.println("JDBC LINK TO GO HERE");

                            } else if (newSquareType && data.getAdditionChoice() == NewAddition.DIE) {
                                appendTextToPanel("New Dice choice created.\n "
                                + "Dice count: " + firstFieldEntry
                                + "\nDice faces: " + secondFieldEntry + "\n");
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

    public void swapPanel(final JFrame currentFrame,
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