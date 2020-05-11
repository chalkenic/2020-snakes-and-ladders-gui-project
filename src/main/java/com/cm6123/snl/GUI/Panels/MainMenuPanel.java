package com.cm6123.snl.GUI.Panels;

import com.cm6123.snl.GUI.*;
import com.cm6123.snl.gameDB.CreateDBManager;
import com.cm6123.snl.gameDB.GameDBUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 * Panel covers all main navigation options along with loading the database.
 */
public class MainMenuPanel extends JPanel implements ActionListener {

    /**
     * Handles newGamePanel navigation on click.
     */
    private JButton newGameButton;
    /**
     * Handles loadGamePanel navigation on click.
     */
    private JButton loadGameButton;
    /**
     * Handles EditorMenuPanel navigation on click.
     */
    private JButton editorButton;
    /**
     * Handles Loading databse into GUI on click.
     */
    private JButton loadDatabaseButton;
    /**
     * Label for title.
     */
    private JLabel welcomeLabel;
    /**
     * Label notifying if databse loaded successfully. Enabled when true.
     */
    private JLabel databaseLoadedLabel;
    /**
     * Handles GridBagLayou constraints for object locations on GUI frame.
     */
    private GridBagConstraints gridStructure;
    /**
     * Main window frame.
     */
    private GUIFrame gameGui;
    /**
     * Object creates default JUnit objects for panel upon instantiation.
     * @param gui - the JFrame window.
     */
    public MainMenuPanel(final GUIFrame gui) {
        this.gameGui = gui;

        welcomeLabel = new JLabel("Welcome to Snakes & Ladders!");
        databaseLoadedLabel = new JLabel("Database not loaded");
        newGameButton = new JButton("New Game");
        loadGameButton = new JButton("Load Game");
        editorButton = new JButton("Game Editor Tool");
        loadDatabaseButton = new JButton("Load Database");

        welcomeLabel.setFont(new Font("Arial Bold", Font.PLAIN, 20)); //Custom font for title give.

        //Default size of button changed to custom.
        //Code adapted from answer of user Kris: How can I set size of a button?
        //available at: https://stackoverflow.com/questions/2536873/how-can-i-set-size-of-a-button
        newGameButton.setPreferredSize(new Dimension(400, 60));
        loadGameButton.setPreferredSize(new Dimension(400, 60));
        editorButton.setPreferredSize(new Dimension(400, 60));
        loadDatabaseButton.setPreferredSize(new Dimension(200, 60));
        //Panels that require database access are disabled until database loaded.
        loadGameButton.setEnabled(false);
        editorButton.setEnabled(false);
        databaseLoadedLabel.setEnabled(false);
        //Actionlisteners added to all buttons.
        newGameButton.addActionListener(this);
        loadGameButton.addActionListener(this);
        editorButton.addActionListener(this);
        loadDatabaseButton.addActionListener(this);
        //Places Panel in middle of window.
        gameGui.add(this, BorderLayout.CENTER);
    }
    /**
     * Places all JUNit objects onto panel at define dlocations.
     * @return this - the Panel to be inserted onto JFrame.
     */
    public JPanel createMenuPanel() {

        //Code adapted from TitledBorder.CENTER : TitledBorder « javax.swing.border « Java by API
        //Available at: http://www.java2s.com/Code/JavaAPI/javax.swing.border/TitledBorderCENTER.htm
        TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder("Main Menu");
        Border outerGameBarBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);

        //Creates a border as a margin around inner game bar.
        setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));

        //GridbagLayout chosen due to more customizing available with specific object positions.
        setLayout(new GridBagLayout());

        gridStructure = new GridBagConstraints();

        gridStructure.weightx = 1;
        gridStructure.weighty = 1;
        gridStructure.gridx = 0;
        gridStructure.gridy = 0;
        add(welcomeLabel, gridStructure);
        gridStructure.gridy = 1;
        add(newGameButton, gridStructure);
        gridStructure.gridy = 2;
        add(loadGameButton, gridStructure);
        gridStructure.gridy = 3;
        add(editorButton, gridStructure);
        gridStructure.gridy = 4;

        //Database button & label kept in center of screen but nudged by specific pixels to match other buttons.
        gridStructure.insets = new Insets(0, 0, 0, 200);
        add(loadDatabaseButton, gridStructure);

       gridStructure.insets = new Insets(0, 300, 0, 0);
        add(databaseLoadedLabel, gridStructure);
        return this;
    }

    /**
     * Enables all buttons on main menu as true when database loaded, & changes JLabel text. JFrame notified of
     * connection for other object method usage.
     */
    public void enableFrontPage() {
        loadGameButton.setEnabled(true);
        editorButton.setEnabled(true);
        loadDatabaseButton.setEnabled(false);
        databaseLoadedLabel.setEnabled(true);
        databaseLoadedLabel.setText("Database Loaded!");
        gameGui.setDataBaseConnection(true);
    }

    /**
     * Dictates actions to perform on JButton clicks.
     * @param click - the click made onto Jbuttons.
     */
    public void actionPerformed(final ActionEvent click) {
        if (click.getSource() == newGameButton) {
            gameGui.selectWindow("newgame");
        } else if (click.getSource() == loadGameButton) {
            gameGui.selectWindow("loadgame");
        } else if (click.getSource() == editorButton) {
            gameGui.selectWindow("editormenu");
        } else {
            Connection connect = GameDBUtils.connectGuiToDatabase();
            CreateDBManager.createDatabase(connect);
            gameGui.getGameMenu().enableDatabaseNavigation();
            enableFrontPage();

        }
    }
}

