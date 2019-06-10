package com.tihonova.panel;

import com.tihonova.domain.Animal;
import com.tihonova.reader.MyReader;
import javafx.scene.Parent;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;

public class FirstPanel {
    public static JPanel getJPanel(Component app, ArrayList< Animal > animals) {

        JPanel panel = new JPanel();

        panel.setLayout(new BorderLayout());

        DefaultListModel<String> model = new DefaultListModel<>();
        JList list = new JList(model);
        list.setSelectionBackground(Color.PINK);
        list.setFont(new Font( "Garamond", Font.PLAIN, 24));
        panel.add(new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);

            if (animals != null) {
                model.clear();
                for (Animal el : animals)
                    model.addElement(el.toString());
            } else {
                JOptionPane.showMessageDialog(app, "There are no elements!", "Error!", JOptionPane.PLAIN_MESSAGE);
            }

        return panel;
    }
}