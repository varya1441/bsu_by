
import javax.swing.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Application extends JFrame {
    private JTable langTable;
    private AlbumTableModel tableModel;
    private JCheckBoxMenuItem validateXML;


    public Application() {

        setSize(800, 560);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem loadFromXML = new JMenuItem("Load XML DOM ...");
        loadFromXML.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int option = chooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    tableModel = new AlbumTableModel(chooser.getSelectedFile(), validateXML.getState());
                    langTable.setModel(tableModel);
                   if (validateXML.getState())
                        JOptionPane.showMessageDialog(this,
                                "XML is valid and matches XSD schema",
                                "Info", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(this, err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        file.add(loadFromXML);

        validateXML = new JCheckBoxMenuItem("XML Schema");
        validateXML.setState(false);
        file.add(validateXML);

        JMenuItem saveToXML = new JMenuItem("to XML");
        saveToXML.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int option = chooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    tableModel.saveToXML(chooser.getSelectedFile());
                } catch (FileNotFoundException err) {
                    JOptionPane.showMessageDialog(this, err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        file.add(saveToXML);

        JMenuItem calcXml = new JMenuItem("Do calculations SAX");
        calcXml.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int option = chooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    SAXParserFactory factory = SAXParserFactory.newInstance();
                    SAXParser parser = factory.newSAXParser();
                    MySAX handler = new MySAX();
                    parser.parse(chooser.getSelectedFile(), handler);
                    JOptionPane.showMessageDialog(this,
                            "Alboms count: " + handler.getAlbCount() +
                                    "\nTotal words: " + handler.getTotalWords() +
                                    "\nAverage words count: " + handler.getAvgWords()+
                                    "\nThe oldest language is: " + handler.getOldestLang()                                    ,
                            "Info", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(this, "Invalid XML file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        file.add(calcXml);


        JMenuItem saveToBinary = new JMenuItem("to binary");
        saveToBinary.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int option = chooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    tableModel.saveToBinary(chooser.getSelectedFile());
                } catch (IOException err) {
                    JOptionPane.showMessageDialog(this, "Invalid file path", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        file.add(saveToBinary);

        JMenuItem loadFromBinary = new JMenuItem("from binary");
        loadFromBinary.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int option = chooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    tableModel = AlbumTableModel.loadFromBinary(chooser.getSelectedFile());
                    langTable.setModel(tableModel);
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(this, err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        file.add(loadFromBinary);


        JMenuItem convertToHTML = new JMenuItem("to HTML");
        convertToHTML.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int option = chooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    TransformerFactory factory = TransformerFactory.newInstance();
                    Transformer transformer = factory.newTransformer(new StreamSource(new File("html.xsl")));
                    transformer.transform(new StreamSource(chooser.getSelectedFile()), new StreamResult(new File("output.html")));
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(this, err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        file.add(convertToHTML);

        JMenuItem convertToTXT = new JMenuItem(" to TXT");
        convertToTXT.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int option = chooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    TransformerFactory factory = TransformerFactory.newInstance();
                    Transformer transformer = factory.newTransformer(new StreamSource(new File("txt.xsl")));
                    transformer.transform(new StreamSource(chooser.getSelectedFile()), new StreamResult(new File("output.txt")));
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(this, err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        file.add(convertToTXT);



        //JMenu langMenu = new JMenu("Music");
        JMenuItem addLanguage = new JMenuItem("add album");
        addLanguage.addActionListener(e -> {
            tableModel.getItems().add(new Music());
            langTable.updateUI();
        });
        file.add(addLanguage);

        JMenuItem deleteLanguage = new JMenuItem("delete selected ");
        deleteLanguage.addActionListener(e -> {
            tableModel.deleteRows(langTable.getSelectedRows());
            langTable.updateUI();
        });
        file.add(deleteLanguage);

        menuBar.add(file);
        add(menuBar, BorderLayout.NORTH);


        tableModel = new AlbumTableModel();
        langTable = new JTable(tableModel);
        add(new JScrollPane(langTable), BorderLayout.CENTER);
    }
    public void runSplashScreen() {

        SplashScreen splash = SplashScreen.getSplashScreen();

        if (splash != null) {

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            splash.close();
        }
    }



}