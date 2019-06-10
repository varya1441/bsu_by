package com.tihonova.lab12.panel;

import org.omg.CORBA.Bounds;

import javax.swing.*;
import java.awt.*;


public class ThirdPanel {
    public static JPanel getJPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        ImageIcon turtle = new ImageIcon("turtle.png");
        ImageIcon ladybug = new ImageIcon("ladybug.png");
        ImageIcon duck = new ImageIcon("duck.png");
        ImageIcon fish = new ImageIcon("fish2.png");
        ImageIcon elephant = new ImageIcon("elephant.png");

        JRadioButton radioTurtle = new JRadioButton("cat", turtle, false);
        JRadioButton radioLady = new JRadioButton("ladybug", ladybug, false);
        JRadioButton radioDuck = new JRadioButton("duck", duck, false);

        radioDuck.setBackground(Color.PINK);
        radioTurtle.setBackground(Color.PINK);

        JPanel buttonGroup = new JPanel(new GridLayout(3, 1));

        ButtonGroup buttonGroup1 = new ButtonGroup();
        radioTurtle.setSelectedIcon(fish);
        radioTurtle.setRolloverIcon(elephant);

        radioLady.setSelectedIcon(fish);
        radioLady.setRolloverIcon(elephant);

        radioDuck.setSelectedIcon(fish);
        radioDuck.setRolloverIcon(elephant);

        buttonGroup1.add(radioTurtle);
        buttonGroup1.add(radioLady);
        buttonGroup1.add(radioDuck);

        buttonGroup.add(radioTurtle);
        buttonGroup.add(radioLady);
        buttonGroup.add(radioDuck);

        panel.add(buttonGroup, BorderLayout.CENTER);

        return panel;
    }
}

