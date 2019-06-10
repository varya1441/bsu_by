package com.tihonova.game.first;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Application extends JFrame {
    private PlayPanel playPanel;
    private Timer timer;
    Application(){
        super("te");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());


        playPanel=new PlayPanel();
        add(playPanel, BorderLayout.CENTER);
        ActionListener listener = e -> {
            playPanel.moveDuck();
            repaint();
        };
        timer = new Timer(10,listener);
        timer.start();


        setSize(900, 600);
        setLocationRelativeTo(null);

    }
}
