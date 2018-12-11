package com.tihonova.lab12.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SecondPanel {
    private static final int BOTTOM = 25;


    public static JPanel getPanel() {

        JPanel panel = new JPanel(new GridLayout((int) Math.sqrt(BOTTOM), (int) Math.sqrt(BOTTOM)));
MyMouseListener myMouseListener=new MyMouseListener();
        for (int i = 0; i < BOTTOM; i++) {

            JButton button = new JButton(Integer.toString(1 + i));

            button.addMouseListener(myMouseListener);
            panel.add(button);
        }

        return panel;
    }


}

class MyMouseListener extends MouseAdapter {
    private String text;

    @Override
    public void mouseEntered(MouseEvent e) {
        ((JButton) e.getSource()).setBackground(Color.PINK);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        ((JButton) e.getSource()).setBackground(null);
    }

    @Override
    public void mousePressed(MouseEvent e) {
//if(e.getButton()=)
        text = ((JButton) e.getSource()).getText();
        ((JButton) e.getSource()).setText("Clicked!");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ((JButton) e.getSource()).setText(text);
    }
}

