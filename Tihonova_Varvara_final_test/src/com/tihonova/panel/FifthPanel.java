package com.tihonova.panel;

import com.tihonova.domain.Animal;
import com.tihonova.domain.Beast;
import com.tihonova.domain.ComparatorByMass;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class FifthPanel {
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
            Animal animal=(Beast)Collections.max(animals,new ComparatorByMass());

            for (Animal el : animals) {
                if (animal.massOfPray() / el.massOfPray() == 1)
                    model.addElement(el.toString());
            }
        } else {
            JOptionPane.showMessageDialog(app, "There are no elements!", "Error!", JOptionPane.PLAIN_MESSAGE);
        }



        return panel;
    }
}
