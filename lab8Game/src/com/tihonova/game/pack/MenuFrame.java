package com.tihonova.game.pack;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {
    MenuFrame(){
        super("KriaProduction");

       JLabel label = new JLabel("Level");
        label.setFont(new Font("Serif", Font.PLAIN, 14));



        setSize(500,500);

        setDefaultCloseOperation(Window.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        setVisible(true);

    }
}
