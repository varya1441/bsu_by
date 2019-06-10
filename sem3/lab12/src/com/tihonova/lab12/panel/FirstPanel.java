package com.tihonova.lab12.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstPanel {
    public static JPanel getJPanel() {

        JPanel panel = new JPanel();

        JPanel buttonPanel = new JPanel();
        panel.setLayout(new BorderLayout());

        DefaultListModel<String> modelWest = new DefaultListModel<>();
        JList listWest = new JList(modelWest);
        listWest.setSelectionBackground(Color.PINK);

        DefaultListModel<String> modelEast = new DefaultListModel<>();
        JList listEast = new JList(modelEast);
        listEast.setFont(new Font( "Garamond", Font.PLAIN, 24));
        listWest.setFont(new Font( "Garamond",  Font.PLAIN , 24));


        listEast.setSelectionBackground(Color.YELLOW);


        buttonPanel.setLayout(new BorderLayout());

        panel.add(new JScrollPane(listEast, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.EAST);

        panel.add(new JScrollPane(listWest, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.WEST);

        panel.add(buttonPanel, BorderLayout.CENTER);

        JButton buttonWest = new JButton("<");
        JButton buttonEast = new JButton(">");

        buttonPanel.add(buttonEast, BorderLayout.NORTH);
        buttonPanel.add(buttonWest, BorderLayout.SOUTH);

        for (int i = 0; i < 15; i++)
            modelWest.addElement("Element "+i);

        for (int i = 15; i < 30; i++)
            modelEast.addElement("Element " + i);

        buttonEast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] listSelection = listWest.getSelectedIndices();

                for (int i = listSelection.length - 1; i >= 0; i--) {
                    modelEast.addElement(modelWest.get(listSelection[i]));
                    modelWest.removeElementAt(listSelection[i]);
                }

            }
        });
        buttonWest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] listSelection = listEast.getSelectedIndices();

                for (int i = listSelection.length - 1; i >= 0; i--) {
                    modelWest.addElement(modelEast.getElementAt(listSelection[i]));
                    modelEast.removeElementAt(listSelection[i]);
                }
            }
        });

        return panel;
    }
}
