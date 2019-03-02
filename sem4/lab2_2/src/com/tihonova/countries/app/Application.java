package com.tihonova.countries.app;

import javafx.util.Pair;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application extends JFrame {
    private JFrame app=this;
    private Map<String, Pair<String, ImageIcon>> countries = new HashMap<>();
    private JPanel panel;
    public Application() throws FileNotFoundException {
        super("com.tihonova.countries.app.Countries");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
        this.add(new Countries(countries));
    }

}
