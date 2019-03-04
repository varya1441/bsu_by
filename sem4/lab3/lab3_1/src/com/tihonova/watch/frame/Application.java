package com.tihonova.watch.frame;

import com.tihonova.watch.painter.Painter;

import javax.swing.*;

public class Application extends JFrame {
    public Application() {
        super("Timer");

JPanel panel = new JPanel();
        this.add(panel);
        panel.add(new Painter());

        this.setResizable(false);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // this.setSize(900, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
