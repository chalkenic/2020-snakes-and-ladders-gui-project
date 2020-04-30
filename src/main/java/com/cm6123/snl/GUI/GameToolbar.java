package com.cm6123.snl.GUI;

import javax.swing.*;
import java.awt.*;

public class GameToolbar extends JPanel {


    private JButton testButton1;
    private JButton testButton2;

    public GameToolbar() {

        testButton1 = new JButton("Who is this?");
        testButton2 = new JButton("It is I.....Nicholas...");

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(testButton1);
        add(testButton2);
    }
}
