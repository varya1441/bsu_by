package by.tihonova.javatr.application;


import by.tihonova.javatr.domain.childrengroup.ChildrenGroup;
import by.tihonova.javatr.domain.toy.Toy;
import by.tihonova.javatr.reader.ReadFromFile;
import org.xml.sax.SAXException;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Set;
import java.util.TreeSet;

public class Application extends JFrame {
    private JFrame app = this;
    private JPanel toolPanel, buttonPanel;

    private Set<Toy> toys;

    final String FILE_1 = "input.txt";
    private Toy toy;
    private MenuBar menuBar;
    private Menu menu, menuAdd;
    private MenuItem file, quit, edit, fileXML, save,showItems;
    private JList jList;
    private JDialog dialog;
    private JButton button;

    public Application() {
        setSize(500, 500);
        setTitle("Lab 12   :3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        panel.setLayout(new BorderLayout());
        menuBar = new MenuBar();
        button= new JButton("choose group");
        this.setMenuBar(menuBar);


        menuAdd = new Menu("Refactoring");
        menuAdd.add(edit = new MenuItem("Add..."));
        menuAdd.add(showItems=new MenuItem("Show group..."));
        menuAdd.add(quit = new MenuItem("Quit"));


        menu = new Menu("File");

        menu.add(file = new MenuItem("Open..."));
        menu.add(fileXML = new MenuItem("OpenXML..."));
        menu.add(save = new MenuItem("Save as..."));



        menuBar.add(menu);
        menuBar.add(menuAdd);
        DefaultListModel listModel = new DefaultListModel();
        jList = new JList();
        jList.setModel(listModel);
        jList.setFont(new Font("Garaga", Font.ROMAN_BASELINE, 20));

        panel.add(new JScrollPane(jList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);

        this.add(panel, BorderLayout.CENTER);
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((e.getActionCommand().equals(file.getActionCommand()))) {
                    JFileChooser fileChooser = new JFileChooser("C:\\Users\\varvara\\Documents\\GitHub\\bsu_by\\lab13");
                    if (fileChooser.showOpenDialog(app) == JFileChooser.APPROVE_OPTION) {
                        File inputFile = fileChooser.getSelectedFile();
                        try {
                            listModel.clear();
                            FileReader fileReader = new FileReader(inputFile);
                            toys = ReadFromFile.read(fileReader);
                            show(listModel, toys);

                        } catch (IllegalArgumentException | InputMismatchException ex) {
                            JOptionPane.showMessageDialog(null, "Wrong params");
                        } catch (IOException e1) {
                            JOptionPane.showMessageDialog(app, "File cannot be opened");
                        }
                    }
                } else if (((e.getActionCommand().equals(fileXML.getActionCommand())))) {
                    JFileChooser fileChooser = new JFileChooser("C:\\Users\\varvara\\Documents\\GitHub\\bsu_by\\lab13");
                    if (fileChooser.showOpenDialog(app) == JFileChooser.APPROVE_OPTION) {
                        File inputFile = fileChooser.getSelectedFile();
                        try {
                            toys = ReadFromFile.readXML(inputFile);
                            show(listModel, toys);

                        } catch (IllegalArgumentException | InputMismatchException ex) {
                            JOptionPane.showMessageDialog(null, "Wrong arguments");
                        } catch (ParserConfigurationException | SAXException ex) {
                            JOptionPane.showMessageDialog(null, "Wrong params in XML");
                        } catch (IOException e1) {
                            JOptionPane.showMessageDialog(app, "File cannot be opened");
                        }
                    }
                }  else if(e.getActionCommand().equals(save.getActionCommand())){
                    JFileChooser fileChooser = new JFileChooser("C:\\Users\\varvara\\Documents\\GitHub\\bsu_by\\lab13");
                    fileChooser.setSelectedFile(new File("toys.xml"));
                    if (fileChooser.showSaveDialog(app) == JFileChooser.APPROVE_OPTION) {
                        File outputFile = fileChooser.getSelectedFile();
                        try{
                            toXMLToString(outputFile, toys);
                        }catch (IOException ex){
                            JOptionPane.showMessageDialog(null, "File cannot be saved to this location");
                        }
                    }
                }
            }

        });
        menuAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals(edit.getActionCommand())) {
                    toy = new Toy();
                    dialog = new InputDialog(app, toy);
                    dialog.setVisible(true);
                    if (!toy.getName().equals("null")) {
                        if (toys == null)
                            toys = new TreeSet<Toy>();
                        toys.add(toy);
                        show(listModel, toys);

                    }
                }else if(e.getActionCommand().equals(showItems.getActionCommand())){
                    if(toys!=null) {
                        Set<Toy> items = new TreeSet<>();
                        if (toys != null) {
                            dialog = new Group(app);
                            dialog.setVisible(true);
                            for (Toy toy : toys) {
                                if (toy.getChildrenGroup().equals((Group.getChildrenGroup()))) {
                                    items.add(toy);
                                }
                            }
                        }
                        if (!items.isEmpty()) {
                            show(listModel, items);
                        }
                    }
                }else if (e.getActionCommand().equals(quit.getActionCommand())) {
                    app.dispose();
                }

            }
        });
    }
    public void toXMLToString(File outputFile, Set<Toy> toys) throws FileNotFoundException {
        PrintWriter out=new PrintWriter(outputFile);
        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<toys>");
        if(toys!=null)
            for(Toy toy:toys){
                out.println(toy.toXML());
            }
        out.println("</toys>");
        out.close();
    }
    private void show(DefaultListModel list, Set<Toy> toys1) {
        if (toys != null) {
            list.clear();
            for (Toy el : toys1)
                list.addElement(el.toString());
        } else {
            JOptionPane.showMessageDialog(this, "There are no elements!", "Error!", JOptionPane.PLAIN_MESSAGE);
        }
    }
}

