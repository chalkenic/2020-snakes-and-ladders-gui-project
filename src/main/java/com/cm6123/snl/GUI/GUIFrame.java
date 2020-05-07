package com.cm6123.snl.GUI;

import com.cm6123.snl.GUI.Panels.*;
import com.cm6123.snl.GUI.Panels.NewGame.NewGamePanel;
import com.cm6123.snl.Game;
import com.cm6123.snl.GameBuilder;
import com.cm6123.snl.dice.DiceSet;
//import com.cm6123.snl.GUI.Panels.NewAdditionPanel;
//import com.cm6123.snl.GUI.Panels.GameTextPanel;
//import com.cm6123.snl.GUI.Panels.SidePanel;

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
    private GameTextPanel textPanel;
//    private GameToolbarPanel toolbar;
    private JPanel panelContainer;
    private JPanel currentPanel;
//    private LayoutManager layout;
    private CreationMenuPanel creationMenuPanel;
    private MainMenuPanel mainMenuPanel;
    private LoadGamePanel loadGamePanel;
    private NewGamePanel newGamePanel;
    private RunGamePanel runGamePanel;
    private MenuBar gameMenu;

    private Game newGame;
    private DiceSet diceSet;

    public GUIFrame() {
        super("Snakes & Ladders");




//        setLayout(new BorderLayout());
        panelContainer = new JPanel();
//        toolbar = new GameToolbarPanel(this);
        textPanel = new GameTextPanel();

        gameMenu = new MenuBar(this);
//
//
////        setJMenuBar(gameMenu.createMenuBar());
//
        setJMenuBar(gameMenu.getMenuBar());

//        gameMenu = new MenuBar(this);
//
//        setJMenuBar(gameMenu.createMenuBar());
//
//
//        gameMenu.addActionListener(new ActionListener() {
//           @Override
//           public void actionPerformed(final ActionEvent e) {
//               if (e.getSource() == gameMenu.getMainMenu()) {
//                   System.out.println("hello");
//               }
//           }
//       });


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
        frame = this;
//        setSize(800, 650);
        setMinimumSize(new Dimension(800, 600));

//        toolbar.setStringListener(new StringListener() {
//            public void textEmitted(final String text) {
//                textPanel.appendText(text);
//            }
//        });

    }

    public void selectWindow(final String windowChoice) {

//
//        if (currentPanel != null) {
//            getContentPane().remove(currentPanel);
//        }
//        if (windowChoice != "menu" && windowChoice != "loadgame") {


            if (windowChoice != "loadgame" && windowChoice != "newgame") {
                add(textPanel, BorderLayout.CENTER);
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


                getContentPane().remove(textPanel);
                getContentPane().remove(currentPanel);
                revalidate();
                newGamePanel = new NewGamePanel(this);

//                panelContainer.add(creationMenuPanel.createCreationPanel(), "2");
                swapPanel(this,
                        currentPanel,
                        newGamePanel.createNewGamePanel(),
                        BorderLayout.CENTER);
                currentPanel = newGamePanel;
//                cardLayout.show(panelContainer, "2");

                break;

            case "rundefaultgame":
                textPanel.wipeTextBox();
                getContentPane().remove(currentPanel);
                revalidate();

                Game defaultGame = new GameBuilder()
                        .withBoardSize(5)
                        .withPlayers(2)
                        .withSnakes(14, 5, 20, 11)
                        .withLadders(3, 12, 13, 17)
                        .build();

                diceSet = new DiceSet(6, 1);

                runGamePanel = new RunGamePanel(this, defaultGame, diceSet);

//                panelContainer.add(creationMenuPanel.createCreationPanel(), "2");
                swapPanel(this,
                        currentPanel,
                        runGamePanel.createRunGamePanel(),
                        BorderLayout.WEST);
                currentPanel = runGamePanel;

                break;

            case "runcustomgame":
                textPanel.wipeTextBox();
                getContentPane().remove(currentPanel);
                revalidate();

                CreateGame customGame = new CreateGame(this);
//                try {
                    customGame.getCustomGameData(newGamePanel);

                    newGame = customGame.getGame();

                    diceSet = new DiceSet(customGame.getDiceFaces(), customGame.getDiceCount());

                    runGamePanel = new RunGamePanel(this, newGame, diceSet);
                    swapPanel(this,
                            currentPanel,
                            runGamePanel.createRunGamePanel(),
                            BorderLayout.WEST);

                    currentPanel = runGamePanel;
//                } catch (NullPointerException n) {
//                    System.out.println("No game found. returning to main menu.");
//                    selectWindow("menu");
//                }




//
//                runGamePanel = new RunGamePanel(this, customGame.getCustomGame());

//                panelContainer.add(creationMenuPanel.createCreationPanel(), "2");

                break;

            case "runduplicateGame":

                textPanel.wipeTextBox();
                getContentPane().remove(currentPanel);
                revalidate();

                runGamePanel = new RunGamePanel(this, newGame, diceSet);

                swapPanel(this,
                        currentPanel,
                        runGamePanel.createRunGamePanel(),
                        BorderLayout.WEST);

                currentPanel = runGamePanel;

                break;

            case "loadgame":
                getContentPane().remove(textPanel);
                getContentPane().remove(currentPanel);
                revalidate();
                loadGamePanel = new LoadGamePanel(this);

//                panelContainer.add(creationMenuPanel.createCreationPanel(), "2");
                swapPanel(this,
                        currentPanel,
                        loadGamePanel.createloadGamePanel(),
                        BorderLayout.CENTER);
                currentPanel = loadGamePanel;
//                cardLayout.show(panelContainer, "2");

                loadGamePanel.setFormListener(new FormListener() {

                    public void appendTextToPanel(final String text) {
                        textPanel.appendText(text);
                    }

                    public void incorrectEntryMessage() {
                        textPanel.appendText("Cannot find file!\n");
                    }

                    public void formDatabaseEntry(final FormEvents data) {
                        if (data.getLoadGameEntry() != null) {
                            Integer loadGameEntry = data.getLoadGameEntry();
                            appendTextToPanel("loaded game " + loadGameEntry + "!\n");
                        }
                    }
                });

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

                additionPanel.setFormListener(new FormListener() {


//                    public void appendTextToPanel(final String text) {
//                        textPanel.appendText(text);
//                    }

                    public void incorrectEntryMessage() {
                        textPanel.appendText("|------------------------------------------------------|"
                                + "\n| ERROR - INCORRECT ENTRY MADE  |"
                                + "\n|------------------------------------------------------|\n");
                    }

                    public void formDatabaseEntry(final FormEvents data) {

                        if (data.getPlayerNameEntry() == null) {
                            Integer firstFieldEntry;
                            Integer secondFieldEntry;
                            Boolean newSquareType;
                            Boolean secondSquareMissing = true;

                            firstFieldEntry = data.getFirstEntry();
                            secondFieldEntry = data.getSecondEntry();

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

                            } else if (newSquareType && data.getAdditionChoice() == NewAddition.DIE) {
                                appendTextToPanel("New Die choice created:\n"
                                + "Amount of dice: " + firstFieldEntry
                                + "\ndice faces: " + secondFieldEntry + "\n");
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
                        } else {
                            String playerFieldEntry = data.getPlayerNameEntry();
                            appendTextToPanel("New player created: " + playerFieldEntry + "\n");
                            System.out.println("JDBC LINK TO GO HERE");
                        }
                    }

//                public void formBoostSquareEntry(final FormEvents data) {
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

    }

    public void appendTextToPanel(final String text) {
        textPanel.appendText(text);
    }
}
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

//    private JMenuBar createMenuBar() {
//        JMenuBar gameMenu = new JMenuBar();
//
//        JMenu fileMenu = new JMenu("File");
//        JMenuItem mainMenu = new JMenuItem("Main Menu");
//        mainMenu.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(final ActionEvent e) {
//                selectWindow("menu");
//            }
//        });
//        JMenuItem exportData = new JMenuItem("Load Game...");
//        JMenuItem importData = new JMenuItem("Save Game...");
//        JMenuItem exitData = new JMenuItem("Exit Program");
//        fileMenu.add("Main Menu");
//        fileMenu.addSeparator();
//        fileMenu.add(exportData);
//        fileMenu.add(importData);
//        fileMenu.addSeparator();
//        fileMenu.add(exitData);
//
//
//        JMenu windowMenu = new JMenu("Window");
//
//        JMenu showMenu = new JMenu("Navigate to");
//        JMenuItem showEditSnakes = new JMenuItem("Edit Snakes");
//        JMenuItem showEditLadders = new JMenuItem("Edit Ladders");
//        JMenuItem showEditBoosts = new JMenuItem("Edit Boosts");
//        JMenuItem showEditPlayers = new JMenuItem("Edit Players");
//        JMenuItem showEditDice = new JMenuItem("Edit Dice");
//
//        showMenu.add(showEditSnakes);
//        showMenu.add(showEditLadders);
//        showMenu.add(showEditBoosts);
//        showMenu.add(showEditPlayers);
//        showMenu.add(showEditDice);
//        windowMenu.add(showMenu);
//
//        gameMenu.add(fileMenu);
//        gameMenu.add(windowMenu);
//
//
//        return gameMenu;
//
//    }
