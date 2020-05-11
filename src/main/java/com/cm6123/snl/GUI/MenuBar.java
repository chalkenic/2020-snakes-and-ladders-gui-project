package com.cm6123.snl.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class covers JMenuBar for navigation at top of window. ACtionListener must be implemented in code.
 */
public class MenuBar extends JMenu implements ActionListener {

    /**
     * Main window frame.
     */
    private GUIFrame gameGui;

    /**
     * MenuBar at top of window.
     */
    private JMenuBar gameMenu;

    /**
     * Menu that holds file commands.
     */
    private JMenu fileMenu;

    /**
     * Menu holds all GUI menu navigation commands.
     */
    private JMenu menuNav;

    /**
     * Navigate to main menu panel on click.
     */
    private JMenuItem mainMenu;

    /**
     * Navigate to editor menu panel on click.
     */
    private JMenuItem editorMenu;

    /**
     * Navigate to new game parent panel on click.
     */
    private JMenuItem newGame;

    /**
     * Navigate to load Game panel on click.
     */
    private JMenuItem loadGame;

    /**
     * Close the game on click.
     */
    private JMenuItem exitGame;


    /**
     * Creates menu bar on top of window upon GUI JFrame instantiation.
     * @param gui JFrame that runs the game parsed for access to methods.
     */
    MenuBar(final GUIFrame gui) {
        this.gameGui = gui;

        gameMenu = new JMenuBar();

        fileMenu = new JMenu("File");
        menuNav = new JMenu("Menus");
        mainMenu = new JMenuItem("Main Menu");
        editorMenu = new JMenuItem("Editor Menu");
        newGame = new JMenuItem("New game");
        loadGame = new JMenuItem("Load Game...");
        exitGame = new JMenuItem("Exit Program");
        //Listeners added to all buttons.
        mainMenu.addActionListener(this);
        editorMenu.addActionListener(this);
        newGame.addActionListener(this);
        loadGame.addActionListener(this);
        exitGame.addActionListener(this);
        //Load Game & Editor Menu cannot be accessed unless database has been loaded.
        loadGame.setEnabled(false);
        editorMenu.setEnabled(false);
        //Styling & poisitioning:
        fileMenu.addSeparator();
        fileMenu.add(newGame);
        fileMenu.add(loadGame);
        fileMenu.addSeparator();
        fileMenu.add(exitGame);

        menuNav.add(mainMenu);
        menuNav.add(editorMenu);


        gameMenu.add(fileMenu);
        gameMenu.add(menuNav);
    }
    //Code adapted from Eric Leibenguth: JFrame Action Listener that listens to all menu items?
    //https://stackoverflow.com/questions/31229899/jframe-action-listener-that-listens-to-all-menu-items

    /**
     * Getter pulls all menubar data and parses into JFrame.
     * @return game menu as JMenuBar.
     */
    public JMenuBar getMenuBar() {
        return gameMenu;
    }

    /**
     * Handles clicks on menu items and redirects to specific JFrame locations.
     * @param click - location on bar where click was registered.
     */
    public void actionPerformed(final ActionEvent click) {

        if (click.getSource().equals(mainMenu)) {
            gameGui.selectWindow("menu"); //Redirects to main menu via JFrame method.

        } else if (click.getSource().equals(editorMenu)) {
            gameGui.selectWindow("editormenu"); //Redirects to editor menu via JFrame method.

        } else if (click.getSource().equals(newGame)) {
            gameGui.selectWindow("newgame"); //Redirects to new game menu via JFrame method.

        } else if (click.getSource().equals(loadGame)) {
            gameGui.selectWindow("loadgame"); //Redirects to load menu via JFrame method.

        } else if (click.getSource().equals(exitGame)) {
            System.exit(0); //Closes game and returns successful.
        }
    }

    /**
     * method called when database switched on. Both menu items switched on.
     */
    public void enableDatabaseNavigation() {
            editorMenu.setEnabled(true);
            loadGame.setEnabled(true);

        }
    }

