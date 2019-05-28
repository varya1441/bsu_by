package com.tihonova.duck.game;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter@Setter
public class Application extends JFrame {
    private PlayPanel playPanel=new PlayPanel();
  //  private MenuPanel menu=new MenuPanel();
  private int diraction = 2;
    private Timer timer;
    Application(){
        super("te");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        String[] action = { "  >        ", "<       ", "    -       "};
        final JList list=new JList(action);
        //JList<String> list = new JList<>(action);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setSelectedIndex(2);
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                diraction = list.getSelectedIndex();
            }
        });
        list.setSize(100,100);

        setLayout(new BorderLayout());
       // add(slider, BorderLayout.NORTH);
        add(playPanel, BorderLayout.CENTER);

        timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playPanel.moveDuck();
                repaint();
            }
        });
        timer.start();


        setSize(900, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
