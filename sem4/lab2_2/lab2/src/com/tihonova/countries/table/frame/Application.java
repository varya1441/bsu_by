package com.tihonova.countries.table.frame;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application extends JFrame {
    private JFrame app=this;
    private Map<String, Pair<String, ImageIcon>> countries = new HashMap<>();
    private JPanel panel;
    private JMenuBar bar;
    private JMenu menu;
    private JMenuItem itemCost,itemAdd;
    public Application() throws FileNotFoundException {
        super("com.tihonova.countries.table.frame.CountriesTour");

        File fileReader=new File("countries.txt");
        Scanner scanner=new Scanner(fileReader);
        while(scanner.hasNext()){
            countries.put(
                    scanner.next(),
                    new Pair<String, ImageIcon>(
                            scanner.next(),
                            new ImageIcon("plain/flag_" + scanner.next() + ".png")));
        }

        panel=new JPanel();
        this.add(panel);

        CountriesTour countriesTour=new CountriesTour(this,countries);
        panel.add(countriesTour);

        bar =new JMenuBar();
        this.setJMenuBar(bar);

        menu=new JMenu("Options");
        bar.add(menu);

        itemCost =new JMenuItem("Get cost");
        itemCost.addActionListener(I->{
            countriesTour.getCost();
        });
        itemAdd =new JMenuItem("Add new tour");
        itemAdd.addActionListener(I->{
            countriesTour.addNewTour();
        });
       // menu.add(itemCost);
        menu.add(itemAdd);
        setPreferredSize(new Dimension(700, 600));
        setMinimumSize(new Dimension(700, 600));
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
