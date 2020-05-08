package com.cm6123.snl.GUI.Panels;

import com.cm6123.snl.GUI.*;
import com.cm6123.snl.GUI.PanelBackgroundLogic.CreateGame;
import com.cm6123.snl.gameDB.DBGameFile;
import com.cm6123.snl.gameDB.GameDBUtils;
import com.cm6123.snl.gameDB.LoadDataDBManager;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class LoadGamePanel extends JPanel {

    private DefaultListModel savedGames;
    private GUIFrame gameGui;
    private JList gameList;
    private GridBagConstraints gridStructure;
    private JButton loadGameButton;
    private String currentSelection;
    private FormListener formListener;
    private JScrollPane scrollPane;
    private LoadDataDBManager loadDatabase;

    public LoadGamePanel(final GUIFrame gui) {
        this.gameGui = gui;
        this.loadDatabase = new LoadDataDBManager();
        savedGames = new DefaultListModel();
        loadDBGames();

        gameList = new JList();
        gameList.setModel(savedGames);

        scrollPane = new JScrollPane(gameList);

//        savedGames.addElement(new DBGameFile(gameList.getSelectedIndex(0).);



        scrollPane.setPreferredSize(new Dimension(200, 300));
        gameList.setBorder(BorderFactory.createEtchedBorder(1));
        gameList.setSelectedIndex(0);

        loadGameButton = new JButton("<html>Load Game</br>");
        loadGameButton.setHorizontalTextPosition(SwingConstants.CENTER);

        gameList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(final ListSelectionEvent e) {
                //Code adapted from mKorbel: repaint JPanel with every click at Jlist.
                //Available at: https://stackoverflow.com/questions/19510789/repaint-jpanel-with-every-click-at-jlist
                if (!e.getValueIsAdjusting()) {
                    currentSelection = gameList.getSelectedValue().toString();
                    //Code adapted from Sebastien: changing a JButton text when clicked.
                    //Available at: https://stackoverflow.com/questions/9412620/changing-a-jbutton-text-when-clicked

                    //Code adapted from K0pernikus: New Line \n is not working in JButton.setText(“fnord\nfoo”) ; [duplicate]
                    //available at: https://stackoverflow.com/questions/13503280/new-line-n-is-not-working-in-jbutton-settextfnord-nfoo

                    loadGameButton.setText("<html>Load Game <br />" + currentSelection + "</html>");
                }
            }
        });

        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {

                FormEvents loadGameEvent = null;

                DBGameFile loadGameChoice = (DBGameFile) gameList.getSelectedValue();
//                loadGameEvent = new FormEvents(this, loadGameChoice.getId());

                if (formListener != null) {
//                    formListener.formDatabaseEntry(loadGameEvent);
                    CreateGame loadedGame = new CreateGame(gameGui);
                    gameGui.setCreatedGame(loadedGame.getLoadedGameData(loadGameChoice));
                    gameGui.selectWindow("runloadedgame");
                }



            }
        });

    }

    public JPanel createloadGamePanel() {

        TitledBorder innerGameBarBorder = BorderFactory.createTitledBorder("Load Game");
        Border outerGameBarBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        innerGameBarBorder.setTitleJustification(TitledBorder.CENTER);

        setBorder(BorderFactory.createCompoundBorder(outerGameBarBorder, innerGameBarBorder));

        //USED FOR FLEXIBILITY COMPARED TO OTHER LAYOUTS
        setLayout(new GridBagLayout());

        gridStructure = new GridBagConstraints();

        gridStructure.weightx = 1;
        gridStructure.weighty = 1;
        gridStructure.gridx = 0;
        gridStructure.gridy = 1;
//        gridStructure.fill = GridBagConstraints.NONE;
//
//        gridStructure.anchor = GridBagConstraints.LINE_END;
        gridStructure.insets = new Insets(5, 5, 5, 5);
        add(scrollPane, gridStructure);

        gridStructure.weightx = 1;
        gridStructure.weighty = 1;
        gridStructure.gridx = 0;
        gridStructure.gridy = 2;
//        gridStructure.fill = GridBagConstraints.NONE;
//
//        gridStructure.anchor = GridBagConstraints.LINE_END;
        gridStructure.insets = new Insets(5, 5, 5, 5);
        add(loadGameButton, gridStructure);



        return this;
    }



    private void loadDBGames() {
        Connection connect = GameDBUtils.connectGuiToDatabase();
        Integer totalGames = LoadDataDBManager.countGamesInDatabase(connect);
        for (Integer i = 1; i < (totalGames + 1); i++) {
            savedGames.addElement(new DBGameFile(i, "File " + i));
        }
    }
    public void setFormListener(final FormListener listener) {
        this.formListener = listener;
    }
}

