package com.tihonova.application;

import com.tihonova.domain.Coffee;
import com.tihonova.domain.Drink;
import com.tihonova.domain.MyComparator;
import com.tihonova.domain.Tea;
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
    private JPanel toolPanel, bottomPanel, panel;

    private ArrayList<Drink> drinks;

    final String FILE_1 = "input.txt";
    private Drink drink;
    private MenuBar menuBar;
    private Menu menu, menuAdd;
    private MenuItem file, quit, edit, fileXML, save, showItems;
    private JList inputList, list;
    private JDialog dialog;
    private JButton teaBottom, coffeeBottom, caffeineBottom,uniqueBottom;

    public Application() {
        setSize(700, 500);
        setTitle("Lab 12   :3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        toolPanel = new JPanel();
        toolPanel.setLayout(new BorderLayout());

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(3, 1));

        panel.add(toolPanel, BorderLayout.CENTER);
        toolPanel.add(bottomPanel, BorderLayout.NORTH);
        teaBottom = new JButton("Show Tea");
        coffeeBottom = new JButton("Show Coffee");
        caffeineBottom =new JButton("Max caffeine by cost...");
        uniqueBottom=new JButton("Unique name");

        bottomPanel.add(teaBottom);
        bottomPanel.add(coffeeBottom);
        bottomPanel.add(uniqueBottom);

        toolPanel.add(caffeineBottom,BorderLayout.SOUTH);

        menuBar = new MenuBar();
        this.setMenuBar(menuBar);

        menu = new Menu("Open");

        menuBar.add(menu);
        menu.add(fileXML = new MenuItem("OpenXML..."));
        menu.add(save = new MenuItem("Save as..."));


        DefaultListModel listModel = new DefaultListModel();
        DefaultListModel listModel2 = new DefaultListModel();
        inputList = new JList();
        list = new JList();
        inputList.setModel(listModel);
        list.setModel(listModel2);
        list.setFont(new Font("Garaga", Font.ROMAN_BASELINE, 16));
        inputList.setFont(new Font("Garaga", Font.ROMAN_BASELINE, 16));

        panel.add(new JScrollPane(inputList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.WEST);
        panel.add(new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.EAST);

        this.add(panel, BorderLayout.CENTER);
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((e.getActionCommand().equals(fileXML.getActionCommand())))) {
                    JFileChooser fileChooser = new JFileChooser("C:\\Users\\varvara\\Documents\\GitHub\\bsu_by");
                    if (fileChooser.showOpenDialog(app) == JFileChooser.APPROVE_OPTION) {
                        File inputFile = fileChooser.getSelectedFile();
                        try {
                            drinks = MyReader.read(inputFile);
                            show(listModel, drinks);

                        } catch (IllegalArgumentException | InputMismatchException ex) {
                            JOptionPane.showMessageDialog(null, "Wrong arguments");
                        } catch (ParserConfigurationException | SAXException ex) {
                            JOptionPane.showMessageDialog(null, "Wrong params in XML");
                        } catch (IOException e1) {
                            JOptionPane.showMessageDialog(app, "File cannot be opened");
                        }
                    }
                } else if (e.getActionCommand().equals(save.getActionCommand())) {
                    JFileChooser fileChooser = new JFileChooser("C:\\Users\\varvara\\Documents\\GitHub\\bsu_by");
                    fileChooser.setSelectedFile(new File("drinks.xml"));
                    if (fileChooser.showSaveDialog(app) == JFileChooser.APPROVE_OPTION) {
                        File outputFile = fileChooser.getSelectedFile();
                        try {
                            toXMLToString(outputFile, drinks);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "File cannot be saved to this location");
                        }
                    }
                }
            }

        });
        teaBottom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (drinks != null) {
                    Collections.sort(drinks, new MyComparator());
                    listModel2.clear();
                    for (Drink el : drinks) {
                        if (el instanceof Tea)
                            listModel2.addElement(el.toString());
                    }
                } else {
                    JOptionPane.showMessageDialog(app, "There are no elements!", "Error!", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        coffeeBottom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (drinks != null) {
                    Collections.sort(drinks, new MyComparator());
                    listModel2.clear();
                    for (Drink el : drinks) {
                        if (el instanceof Coffee)
                            listModel2.addElement(el.toString());
                    }
                } else {
                    JOptionPane.showMessageDialog(app, "There are no elements!", "Error!", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        caffeineBottom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CostDialog costDialog=new CostDialog(app);
                costDialog.setVisible(true);
                ArrayList<Drink>drinksByCost=new ArrayList<>();
                if(drinks!=null){
                    for(Drink drink:drinks){
                        if(Double.compare(drink.getCost(),costDialog.getCost())==0){
                            drinksByCost.add(drink);
                        }
                    }
                    if(!drinksByCost.isEmpty()){
                    showByCost(listModel2,drinksByCost);
                    }else{
                        JOptionPane.showMessageDialog(app, "There is no such elements!", "Error!", JOptionPane.PLAIN_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(app, "There are no elements!", "Error!", JOptionPane.PLAIN_MESSAGE);

                }

            }
        });
        uniqueBottom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Set<String> names=new TreeSet<>();
                if(drinks!=null){
                for (Drink drink:drinks) {
                        names.add(drink.getName());
                }
                listModel2.clear();
                for(String name:names){

                    listModel2.addElement(name);
                }
            }else{
                    JOptionPane.showMessageDialog(app, "There are no elements!", "Error!", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }

    public void toXMLToString(File outputFile, ArrayList<Drink> toys) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(outputFile);
        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<drinks>");
        if (toys != null)
            for (Drink toy : toys) {
                if (toy instanceof Tea) {
                    out.println(((Tea) toy).toXML());
                } else if (toy instanceof Coffee) {
                    out.println((((Coffee) toy).toXML()));
                }
            }
        out.println("</drinks>");
        out.close();
    }

    private void show(DefaultListModel list, ArrayList<Drink> drinks) {
        if (drinks != null) {
            list.clear();
            for (Drink el : drinks)
                list.addElement(el.toString());
        } else {
            JOptionPane.showMessageDialog(this, "There are no elements!", "Error!", JOptionPane.PLAIN_MESSAGE);
        }
    }
    private void showByCost(DefaultListModel list, ArrayList<Drink> drinks) {
        if (drinks != null) {
            list.clear();
            list.addElement("Cost"+Collections.max(drinks,new MyComparator()));
        } else {
            JOptionPane.showMessageDialog(this, "There are no elements!", "Error!", JOptionPane.PLAIN_MESSAGE);
        }
    }

}

