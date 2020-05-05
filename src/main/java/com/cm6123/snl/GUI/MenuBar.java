package com.cm6123.snl.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenu implements ActionListener {

    private GUIFrame gameGui;

    private JMenuBar gameMenu;
    private JMenu fileMenu;
    private JMenu menuNav;
    private JMenu showMenu;

    private JMenuItem mainMenu;
    private JMenuItem newGame;
    private JMenuItem loadGame;
    private JMenuItem saveGame;
    private JMenuItem exitGame;

    private JMenuItem creationMenu;


    public MenuBar(final GUIFrame gui) {
        this.gameGui = gui;

        gameMenu = new JMenuBar();

        fileMenu = new JMenu("File");
        menuNav = new JMenu("Menus");
//        fileMenu.addMenuListener(this);
        mainMenu = new JMenuItem("Main Menu");
        mainMenu.addActionListener(this);
//        mainMenu.setPreferredSize(new Dimension(50, 30));

        creationMenu = new JMenuItem("Creation Menu");
        creationMenu.addActionListener(this);
//        creationMenu.setPreferredSize(new Dimension(50, 30));

        newGame = new JMenuItem("New game");
        newGame.addActionListener(this);

        loadGame = new JMenuItem("Load Game...");
        loadGame.addActionListener(this);

        saveGame = new JMenuItem("Save Game...");
        saveGame.addActionListener(this);

        exitGame = new JMenuItem("Exit Program");
        exitGame.addActionListener(this);


        fileMenu.addSeparator();
        fileMenu.add(newGame);
        fileMenu.add(loadGame);
        fileMenu.add(saveGame);
        fileMenu.addSeparator();
        fileMenu.add(exitGame);

        menuNav.add(mainMenu);
        menuNav.add(creationMenu);


        gameMenu.add(fileMenu);
        gameMenu.add(menuNav);


//        exportData.addActionListener(this);
//        saveGame.addActionListener(this);
//        exitData.addActionListener(this);


//
//        showMenu = new JMenu("Navigate to");


//       showEditSnakes.addActionListener(this);
//        showEditLadders.addActionListener(this);
//        showEditBoosts.addActionListener(this);
//        showEditPlayers.addActionListener(this);
//        showEditDice.addActionListener(this);
//        showMenu.add(mainMenu);
//        showMenu.add(creationMenu);
//        fileMenu.addSeparator();
//        showMenu.add(newGame);
//        showMenu.add(loadGame);
//        fileMenu.addSeparator();
//        showMenu.add(exitGame);

//        windowMenu.add(showMenu);


//        gameMenu.add(windowMenu);

    }
    //Code adapted from Eric Leibenguth: JFrame Action Listener that listens to all menu items?
    //https://stackoverflow.com/questions/31229899/jframe-action-listener-that-listens-to-all-menu-items

    public JMenuBar getMenuBar() {
        return gameMenu;
    }

    public void actionPerformed(final ActionEvent click) {

        if (click.getSource().equals(mainMenu)) {
            gameGui.selectWindow("menu");

        } else if (click.getSource().equals(creationMenu)) {
            gameGui.selectWindow("creationmenu");

        } else if (click.getSource().equals(newGame)) {
            gameGui.selectWindow("newgame");

        } else if (click.getSource().equals(loadGame)) {
            gameGui.selectWindow("loadgame");

        } else if (click.getSource().equals(saveGame)) {
//            gameGui.selectWindow("menu");

        } else if (click.getSource().equals(exitGame)) {
            System.exit(0);
        }

    }
}
