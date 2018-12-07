package com.tihonova.lab12.application;

import com.tihonova.lab12.panel.FirstPanel;
import com.tihonova.lab12.panel.SecondPanel;
import com.tihonova.lab12.panel.ThirdPanel;

import javax.swing.*;

public class Application extends JFrame {
    private JPanel jPanel;

    public Application() {
        setTitle("Lab 12   :3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        JTabbedPane tabbedPane=new JTabbedPane();
        this.add(tabbedPane);
        JPanel panel = FirstPanel.getJPanel();
        JPanel panel1= SecondPanel.getPanel();
        JPanel panel2= ThirdPanel.getJPanel();
        ImageIcon icon=new ImageIcon("cat2.png");
        tabbedPane.addTab("Lists",icon,panel,"lets look at lists");
        tabbedPane.addTab("Bottoms",icon,panel1,"bottoms");
        tabbedPane.addTab("RadioBottoms",icon,panel2,"radio bottoms");



    }


}
