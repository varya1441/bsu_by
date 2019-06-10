package tihonova.application;


import org.xml.sax.SAXException;
import tihonova.domain.VacuumCleaner;
import tihonova.reader.MyReader;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Application extends JFrame {
    private JFrame app = this;
    private JPanel inputPanel, uniquePanel, panel, caffeinePanel;



    private JLabel inputPanelLabel, uniquePanelLabel, caffeinePanelLabel;

    private ArrayList<VacuumCleaner> cleaners;
  //  private Drink drink;
    private MenuBar menuBar;
    private Menu menu, menuAdd;
    private MenuItem file, quit, edit, fileXML, save, showItems;

    private JDialog dialog;
    private JButton teaBottom, coffeeBottom, caffeineBottom,uniqueBottom;
    private JList inputList, uniqueList,caffeineList;

    public Application() {
        setSize(700, 500);
        setTitle("Lab 12   :3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));
        this.add(panel);
        inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanelLabel=new JLabel("Input");
        inputPanel.add(inputPanelLabel,BorderLayout.NORTH);

        uniquePanel = new JPanel();
        uniquePanel.setLayout(new BorderLayout());
        uniquePanelLabel=new JLabel("Unique names");
        uniquePanel.add(uniquePanelLabel,BorderLayout.NORTH);

        caffeinePanel = new JPanel();
        caffeinePanel.setLayout(new BorderLayout());
        caffeinePanelLabel=new JLabel("Caffeine amount");
        caffeinePanel.add(caffeinePanelLabel,BorderLayout.NORTH);

        panel.add(inputPanel);
        panel.add(uniquePanel);
        panel.add(caffeinePanel);
        menuBar = new MenuBar();
        this.setMenuBar(menuBar);

        menu = new Menu("Open");

        menuBar.add(menu);
        menu.add(fileXML = new MenuItem("OpenXML..."));
        menu.add(save = new MenuItem("Save as..."));


        DefaultListModel listModel = new DefaultListModel();
        DefaultListModel listModel2 = new DefaultListModel();
        DefaultListModel listModel3 = new DefaultListModel();
        inputList = new JList();
        uniqueList = new JList();
        caffeineList=new JList();
        inputList.setModel(listModel);
        uniqueList.setModel(listModel2);
        caffeineList.setModel(listModel3);
        uniqueList.setFont(new Font("Garaga", Font.ROMAN_BASELINE, 16));
        inputList.setFont(new Font("Garaga", Font.ROMAN_BASELINE, 16));
        caffeineList.setFont(new Font("Garaga", Font.ROMAN_BASELINE, 16));
        inputPanel.add(new JScrollPane(inputList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
        uniquePanel.add(new JScrollPane(uniqueList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
        caffeinePanel.add(new JScrollPane(caffeineList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);

        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((e.getActionCommand().equals(fileXML.getActionCommand())))) {
                    JFileChooser fileChooser = new JFileChooser("C:\\Users\\varvara\\Documents\\GitHub\\bsu_by");
                    if (fileChooser.showOpenDialog(app) == JFileChooser.APPROVE_OPTION) {
                        File inputFile = fileChooser.getSelectedFile();
                        try {
                            cleaners = MyReader.read(inputFile);
                            show(listModel, cleaners);
                            Set<String> firms=new TreeSet<>();

                            for (VacuumCleaner vacuumCleaner:cleaners){
                                firms.add(vacuumCleaner.getFirmName());
                            }
                            showUniqueFirms(listModel2,firms);
                            MyDialog myDialog=new MyDialog(app);
                            myDialog.setVisible(true);

                           Set<String> firmsByColor=new TreeSet<>();
                            for (VacuumCleaner vacuumCleaner:cleaners){
                                if(vacuumCleaner.getColor().equals(myDialog.getColor())){
                                        firmsByColor.add(vacuumCleaner.getFirmName());
                                }
                            }
                            for (String firm :
                                    firmsByColor) {
                                listModel3.addElement(firm);
                            }


                        } catch (IllegalArgumentException | InputMismatchException ex) {
                            JOptionPane.showMessageDialog(null, "Wrong arguments");
                        } catch (ParserConfigurationException | SAXException ex) {
                            JOptionPane.showMessageDialog(null, "Wrong params in XML");
                        } catch (IOException e1) {
                            JOptionPane.showMessageDialog(app, "File cannot be opened");
                        }
                    }
//                } else if (e.getActionCommand().equals(save.getActionCommand())) {
//                    JFileChooser fileChooser = new JFileChooser("C:\\Users\\varvara\\Documents\\GitHub\\bsu_by");
//                    fileChooser.setSelectedFile(new File("cleaners.xml"));
//                    if (fileChooser.showSaveDialog(app) == JFileChooser.APPROVE_OPTION) {
//                        File outputFile = fileChooser.getSelectedFile();
//                        try {
//                            toXMLToString(outputFile, cleaners);
//                        } catch (IOException ex) {
//                            JOptionPane.showMessageDialog(null, "File cannot be saved to this location");
//                        }
//                    }
                }
            }

        });

//        caffeineBottom.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                MyDialog costDialog=new MyDialog(app);
//                costDialog.setVisible(true);
//                ArrayList<VacuumCleaner> drinksByCost=new ArrayList<>();
//                if(cleaners !=null){
//                    for(VacuumCleaner drink: cleaners){
//                        if(Double.compare(drink.getCost(),costDialog.getCost())==0){
//                            drinksByCost.add(drink);
//                        }
//                    }
//                    if(!drinksByCost.isEmpty()){
//                    showByCost(listModel2,drinksByCost);
//                    }else{
//                        JOptionPane.showMessageDialog(app, "There is no such elements!", "Error!", JOptionPane.PLAIN_MESSAGE);
//                    }
//                }else {
//                    JOptionPane.showMessageDialog(app, "There are no elements!", "Error!", JOptionPane.PLAIN_MESSAGE);
//
//                }
//
//            }
//        });
//        uniqueBottom.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Set<String> names=new TreeSet<>();
//                if(cleaners !=null){
//                for (VacuumCleaner drink: cleaners) {
//                       // names.add(drink.getName());
//                }
//                listModel2.clear();
//                for(String name:names){
//
//                    listModel2.addElement(name);
//                }
//            }else{
//                    JOptionPane.showMessageDialog(app, "There are no elements!", "Error!", JOptionPane.PLAIN_MESSAGE);
//                }
//            }
//        });
    }

//    public void toXMLToString(File outputFile, ArrayList<VacuumCleaner> toys) throws FileNotFoundException {
//        PrintWriter out = new PrintWriter(outputFile);
//        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<cleaners>");
//        if (toys != null)
//            for (VacuumCleaner toy : toys) {
//                if (toy instanceof Tea) {
//                    out.println(((WashingVacuumCleaner) toy).toXML());
//                } else if (toy instanceof Coffee) {
//                    out.println((((Coffee) toy).toXML()));
//                }
//            }
//        out.println("</cleaners>");
//        out.close();
//    }

    private void show(DefaultListModel list, ArrayList<VacuumCleaner> cleaners) {
        if (cleaners != null) {
            Collections.sort(cleaners);
            list.clear();
            for (VacuumCleaner el : cleaners)
                list.addElement(el.toString());
        } else {
            JOptionPane.showMessageDialog(this, "There are no elements!", "Error!", JOptionPane.PLAIN_MESSAGE);
        }
    }
    private void showUniqueFirms(DefaultListModel list,Set<String>firms){
        if (firms!= null) {
            list.clear();
            for (String el : firms)
                list.addElement(el);
        } else {
            JOptionPane.showMessageDialog(this, "There are no elements!", "Error!", JOptionPane.PLAIN_MESSAGE);
        }
    }
//    private void showByCost(DefaultListModel list, ArrayList<VacuumCleaner> drinks) {
//        if (drinks != null) {
//            list.clear();
//            list.addElement("Cost"+Collections.max(drinks,new MyComparator()));
//        } else {
//            JOptionPane.showMessageDialog(this, "There are no elements!", "Error!", JOptionPane.PLAIN_MESSAGE);
//        }
//    }

}

