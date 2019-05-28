package bsu.sinytim;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.swing.*;
import javax.xml.XMLConstants;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class App extends JFrame {

    private static final String PRODUCTS_XSD_PATH = "resources/products.xsd";
    private static final String TXT_XSL_PATH = "resources/txt.xsl";
    private static final String HTML_XSL_PATH = "resources/html.xsl";
    private static final String PRODUCTS_PATH = "products.xml";
    private static final String TXT_OUT_PATH = "out.txt";
    private static final String HTML_OUT_PATH = "out.html";

    private ProductsModel productsModel;
    private JTable productsTable;

    private Validator validator;
    private JCheckBoxMenuItem validateXMLCheckBox;

    private App() {
        runSplashScreen();
        setFrameParams();
        createLayout();
        createValidator();
    }

    private void runSplashScreen() {

        var splash = SplashScreen.getSplashScreen();

        if (splash != null) {

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            splash.close();
        }
    }

    private void setFrameParams() {
        var layout = new BorderLayout();
        setLayout(layout);
        setTitle("Products");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createValidator() {

        try {

            var factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//            var xsdFile = new File(PRODUCTS_XSD_PATH);
            var xsdFile = ProductsModel.class.getClassLoader().getResource(PRODUCTS_XSD_PATH);
            var schema = factory.newSchema(xsdFile);
            validator = schema.newValidator();

        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    private void createLayout() {
        createTable();
        createMenu();
    }

    private void createTable() {

        var products = IOManager.readProducts(PRODUCTS_PATH);
        productsModel = new ProductsModel(products);

        productsTable = new JTable(productsModel);
        var scrollTable = new JScrollPane(productsTable);
        add(scrollTable, BorderLayout.CENTER);
    }

    private void createMenu() {

        var menuBar = new JMenuBar();
        add(menuBar, BorderLayout.NORTH);

        {
            var menuFile = new JMenu("File");
            menuBar.add(menuFile);

            var itemLoadXML = new JMenuItem("Load XML");
            menuFile.add(itemLoadXML);
            itemLoadXML.addActionListener(event -> loadProductsXML());

            var itemSaveXML = new JMenuItem("Save XML");
            menuFile.add(itemSaveXML);
            itemSaveXML.addActionListener(event -> saveProductsXML());

            validateXMLCheckBox = new JCheckBoxMenuItem("Validate XML");
            menuFile.add(validateXMLCheckBox);
            validateXMLCheckBox.setState(false);

            menuFile.addSeparator();

            var itemLoadBinary = new JMenuItem("Load binary");
            menuFile.add(itemLoadBinary);
            itemLoadBinary.addActionListener(event -> loadProductsBinary());

            var itemSaveBinary = new JMenuItem("Save binary");
            menuFile.add(itemSaveBinary);
            itemSaveBinary.addActionListener(event -> saveProductsBinary());

            menuFile.addSeparator();

            var itemCalculate = new JMenuItem("Calculate");
            menuFile.add(itemCalculate);
            itemCalculate.addActionListener(event -> openWithCalculations());

            menuFile.addSeparator();

            var itemToHtml = new JMenuItem("To HTML");
            menuFile.add(itemToHtml);
            itemToHtml.addActionListener(event -> saveAs(HTML_XSL_PATH, HTML_OUT_PATH));

            var itemToTxt = new JMenuItem("To txt");
            menuFile.add(itemToTxt);
            itemToTxt.addActionListener(event -> saveAs(TXT_XSL_PATH, TXT_OUT_PATH));
        }
        {
            var menuProducts = new JMenu("Products");
            menuBar.add(menuProducts);

            var itemAdd = new JMenuItem("Add");
            menuProducts.add(itemAdd);
            itemAdd.addActionListener(event -> addProduct());

            var itemDelete = new JMenuItem("Delete selected");
            menuProducts.add(itemDelete);
            itemDelete.addActionListener(event -> eraseProduct());
        }
    }

    private void loadProductsXML() {

        var fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int option = fileChooser.showOpenDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            try {

                var file = fileChooser.getSelectedFile();

                if (!validateXMLCheckBox.getState() || isValidXML(file)) {
                    var products = IOManager.readProducts(file);
                    productsModel = new ProductsModel(products);
                    productsTable.setModel(productsModel);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveProductsXML() {

        var fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int option = fileChooser.showSaveDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            var file = fileChooser.getSelectedFile();
            var xml = productsModel.toXML();
            IOManager.writeXML(file, xml);
        }
    }

    private void loadProductsBinary() {

        var fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int option = fileChooser.showOpenDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            try {

                var file = fileChooser.getSelectedFile();
                var products = IOManager.readBinary(file);
                productsModel = new ProductsModel(products);
                productsTable.setModel(productsModel);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveProductsBinary() {

        var fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int option = fileChooser.showSaveDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            var file = fileChooser.getSelectedFile();
            var products = productsModel.getProducts();
            IOManager.writeBinary(file, products);
        }
    }

    private void openWithCalculations() {

        var fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int option = fileChooser.showOpenDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            try {

                var file = fileChooser.getSelectedFile();

                IOManager.openWithCalculations(file);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean isValidXML(File xmlFile) {

        try {

            var source = new StreamSource(xmlFile);
            validator.validate(source);

        } catch (SAXException e) {
            var parseException = (SAXParseException) e;

            var format = "Invalid XML!\nLine number: %s\nColumn number: %s";

            var message = String.format(format,
                    parseException.getLineNumber(),
                    parseException.getColumnNumber());

            JOptionPane.showMessageDialog(this, message);

            return false;

        } catch (IOException e) {
            return false;
        }

        return true;
    }

    private void saveAs(String xslPath, String outPath) {

        try {

//            var xslFile = new File(xslPath);
            var xslFile = ProductsModel.class.getClassLoader().getResourceAsStream(xslPath);
            var xslSource = new StreamSource(xslFile);
            var factory = TransformerFactory.newInstance();
            var transformer = factory.newTransformer(xslSource);

            var xml = productsModel.toXML();
            var xmlStream = stringToInputStream(xml);
            var xmlSource = new StreamSource(xmlStream);

            var resultFile = new File(outPath);
            var outputTarget = new StreamResult(resultFile);

            transformer.transform(xmlSource, outputTarget);

        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private InputStream stringToInputStream(String string) {
        var bytes = string.getBytes();
        return new ByteArrayInputStream(bytes);
    }

    private void addProduct() {
        var product = new Product();
        productsModel.addProduct(product);
        productsTable.updateUI();
    }

    private void eraseProduct() {
        var selectedRows = productsTable.getSelectedRows();
        productsModel.removeProducts(selectedRows);
        productsTable.updateUI();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(App::new);
    }
}
