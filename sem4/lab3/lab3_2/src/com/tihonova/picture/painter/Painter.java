package com.tihonova.picture.painter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class Painter extends JFrame{
    private int diraction = 2;
    private Timer timer;

    public Painter(){
        super("Timer");

        JPanel panel = new JPanel();
        add(panel, BorderLayout.CENTER);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 30, 10);
                Hashtable labelTable = new Hashtable();
        labelTable.put( 0 , new JLabel("Stop") );
        labelTable.put(10, new JLabel("Slow") );
        labelTable.put(30, new JLabel("Fast") );
        slider.setLabelTable( labelTable );

        slider.setPaintLabels(true);
        String[] action = { "  >        ", "<       ", "    -       "};
        JList<String> list = new JList<>(action);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setSelectedIndex(2);
        list.addListSelectionListener(e -> diraction = list.getSelectedIndex());
        list.setSize(100,100);

        setLayout(new BorderLayout());
        add(slider, BorderLayout.NORTH);

        JPanel listPanel=new JPanel();
        JPanel p=new JPanel();
        add(listPanel,BorderLayout.SOUTH);
        listPanel.setLayout(new GridLayout(1,2));
        listPanel.add(list);
        listPanel.add(p);

        CircleImage circlePanel = new CircleImage();

        add(circlePanel , BorderLayout.CENTER);

        ActionListener listener = e -> {
            circlePanel.setAngle(slider.getValue(), diraction);
            repaint();
        };

        timer = new Timer(10, listener);
        timer.start();

        setSize(900, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
