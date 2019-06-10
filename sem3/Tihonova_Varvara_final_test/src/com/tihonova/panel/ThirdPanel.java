package com.tihonova.panel;

import com.tihonova.domain.Animal;
import com.tihonova.domain.ComparatorByMass;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class ThirdPanel {
    public static JPanel getJPanel(Component app, ArrayList<Animal> animals) {

        JPanel panel = new JPanel();

        JPanel buttonPanel = new JPanel();
        panel.setLayout(new BorderLayout());

        DefaultListModel<String> model = new DefaultListModel<>();
        JList list = new JList(model);
        list.setSelectionBackground(Color.PINK);
        list.setFont(new Font( "Garamond", Font.PLAIN, 24));
        panel.add(new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);

        panel.add(new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
        if (animals != null) {
            Collections.sort(animals,new ComparatorByMass());
            model.clear();
            for (Animal el : animals)
                model.addElement(el.toString());
        } else {
            JOptionPane.showMessageDialog(app, "There are no elements!", "Error!", JOptionPane.PLAIN_MESSAGE);
        }



        return panel;
    }
}
