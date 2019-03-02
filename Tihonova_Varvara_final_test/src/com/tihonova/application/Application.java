package com.tihonova.application;

import com.tihonova.domain.Animal;
import com.tihonova.panel.*;
import com.tihonova.reader.MyReader;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class Application extends JFrame {
    private JFrame app = this;
    private JPanel inputPanel, panel;

    private ArrayList<Animal> animals;
    private JTabbedPane jTabbedPane;
    private JLabel inputPanelLabel;

    private MenuBar menuBar;
    private Menu menu, menuData;
    private MenuItem data, fileXML;


    public Application() {
        setSize(700, 500);
        setTitle("Lab 12   :3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.add(panel);
        inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanelLabel = new JLabel("Input");
        inputPanel.add(inputPanelLabel, BorderLayout.NORTH);

        panel.add(inputPanel);

        menuBar = new MenuBar();
        this.setMenuBar(menuBar);

        menu = new Menu("File");
        menuData = new Menu("Data");

        menuBar.add(menu);
        menuBar.add(menuData);
        menuData.add(data = new MenuItem("data"));
        menu.add(fileXML = new MenuItem("Open..."));

        jTabbedPane = new JTabbedPane();
        this.add(jTabbedPane);

        JTabbedPane tabbedPane = new JTabbedPane();
        this.add(tabbedPane);
       // jTabbedPane.addTab("Lists", firstPanel);
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((e.getActionCommand().equals(fileXML.getActionCommand())))) {
                    JFileChooser fileChooser = new JFileChooser();
                    if (fileChooser.showOpenDialog(app) == JFileChooser.APPROVE_OPTION) {
                        File inputFile = fileChooser.getSelectedFile();
                        try {
                            animals = MyReader.read(inputFile);
                            JPanel panel = FirstPanel.getJPanel(app, animals);
                            tabbedPane.addTab("Lists", panel);


                        } catch (IllegalArgumentException | InputMismatchException ex) {
                            JOptionPane.showMessageDialog(null, "Wrong arguments");
                        } catch (ParserConfigurationException | SAXException ex) {
                            JOptionPane.showMessageDialog(null, "Wrong params in XML");
                        } catch (IOException e1) {
                            JOptionPane.showMessageDialog(app, "File cannot be opened");
                        }
                    }
                }
            }

        });

        data.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel1 = SecondPanel.getJPanel(app, animals);
                JPanel panel2 = ThirdPanel.getJPanel(app, animals);
                JPanel panel3 = ForthPanel.getJPanel(app, animals);
                JPanel panel4 = FifthPanel.getJPanel(app, animals);

                tabbedPane.addTab("Place", panel1);
                tabbedPane.addTab("Mass", panel2);
                tabbedPane.addTab("All Place", panel3);
                tabbedPane.addTab("Mass/2", panel4);


            }
        });

    }

}

