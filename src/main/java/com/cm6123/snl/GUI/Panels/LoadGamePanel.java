package com.cm6123.snl.GUI.Panels;

import com.cm6123.snl.GUI.GUIFrame;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class LoadGamePanel extends JPanel {

    private DefaultListModel savedGames;
    private GUIFrame gameGui;
    private JList gameList;
    private GridBagConstraints gridStructure;
    private JButton loadGame;
    private String currentSelection;

    public LoadGamePanel(final GUIFrame gui) {
        this.gameGui = gui;


        gameList = new JList();
        savedGames = new DefaultListModel();

        for (int i = 0; i < 5; i++) {
            savedGames.addElement(i);
        }
        savedGames.addElement("Testing");

        gameList.setModel(savedGames);

        gameList.setPreferredSize(new Dimension(1000, 1000));
        gameList.setBorder(BorderFactory.createBevelBorder(1));
        gameList.setSelectedIndex(0);

        loadGame = new JButton("<html>Load Game</br>");
        loadGame.setHorizontalTextPosition(SwingConstants.CENTER);

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

                    loadGame.setText("<html>Load Game <br />" + currentSelection + "</html>");
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
        add(gameList, gridStructure);

        gridStructure.weightx = 1;
        gridStructure.weighty = 1;
        gridStructure.gridx = 0;
        gridStructure.gridy = 2;
//        gridStructure.fill = GridBagConstraints.NONE;
//
//        gridStructure.anchor = GridBagConstraints.LINE_END;
        gridStructure.insets = new Insets(5, 5, 5, 5);
        add(loadGame, gridStructure);



        return this;
    }
}
