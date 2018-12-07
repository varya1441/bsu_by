package com.tihonova.lab12.panel;

import javax.swing.*;
import java.awt.*;

public class SecondPanel {
    private static  final int BOTTOM =25;
    public static JPanel getPanel(){
        JButton button;
       JPanel panel=new JPanel(new GridLayout((int)Math.sqrt(BOTTOM),(int)Math.sqrt(BOTTOM)));
        for (int i = 0; i< BOTTOM; i++){
            button=new JButton(Integer.toString(i+1));
            panel.add(button);
        }
        return panel;

    }
}
