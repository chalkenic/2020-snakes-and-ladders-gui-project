package com.cm6123.snl.GUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuBar extends JMenu{

    private GUIFrame gameGui;

    private JMenuBar gameMenu;
    private JMenu fileMenu;
    private JMenu windowMenu;
    private JMenu showMenu;

    private JMenuItem mainMenu;
    private JMenuItem exportData;
    private JMenuItem importData;
    private JMenuItem exitData;

    private JMenuItem showEditSnakes;
    private JMenuItem showEditLadders;
    private JMenuItem showEditBoosts;
    private JMenuItem showEditPlayers;
    private JMenuItem showEditDice;

    public MenuBar() {

        mainMenu.addActionListener(final GUIFrame gui) {
            this.gameGui = gui;
        };


    }

    public JMenuBar createMenuBar() {

        gameMenu = new JMenuBar();

        fileMenu = new JMenu("File");
        mainMenu = new JMenuItem("Main Menu");
        exportData = new JMenuItem("Load Game...");

        importData = new JMenuItem("Save Game...");
        exitData = new JMenuItem("Exit Program");

        fileMenu.add("Main Menu");
        fileMenu.addSeparator();
        fileMenu.add(exportData);
        fileMenu.add(importData);
        fileMenu.addSeparator();
        fileMenu.add(exitData);


        windowMenu = new JMenu("Window");

        showMenu = new JMenu("Navigate to");
        showEditSnakes = new JMenuItem("Edit Snakes");
        showEditLadders = new JMenuItem("Edit Ladders");
        showEditBoosts = new JMenuItem("Edit Boosts");
        showEditPlayers = new JMenuItem("Edit Players");
        showEditDice = new JMenuItem("Edit Dice");

        showMenu.add(showEditSnakes);
        showMenu.add(showEditLadders);
        showMenu.add(showEditBoosts);
        showMenu.add(showEditPlayers);
        showMenu.add(showEditDice);
        windowMenu.add(showMenu);

        gameMenu.add(fileMenu);
        gameMenu.add(windowMenu);

        return gameMenu;


    }
}
