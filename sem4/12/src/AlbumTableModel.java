import jdk.internal.org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.table.AbstractTableModel;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumTableModel extends AbstractTableModel implements Serializable {
    private List<Music> musicList;
    private final String COL_NAMES[] = {"Name", "Author", "Extension", "Year", "Words"};

    public AlbumTableModel(File file, boolean validate) throws IOException, SAXException, org.xml.sax.SAXException {
        musicList = new ArrayList<>();
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setValidating(false);

          if (validate) {
              SchemaFactory factory =  SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(this.getClass().getResource("music.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(file));
         }

        try {
            DocumentBuilder builder = f.newDocumentBuilder();
            Document doc = builder.parse(file);
            Node root = doc.getFirstChild();
            if (!root.getNodeName().equals("music"))
                throw new SAXException();
            NodeList items = root.getChildNodes();
            for (int i = 0; i < items.getLength(); i++) {
                Node node = items.item(i);
                if (!node.getNodeName().equals("albom"))
                    continue;
                Music curMusic = new Music();
                curMusic.setYear(Integer.parseInt(node.getAttributes().getNamedItem("year").getTextContent()));
                curMusic.setExtension(node.getAttributes().getNamedItem("extension").getTextContent());
                NodeList fields = node.getChildNodes();
                for(int j = 0; j < fields.getLength(); j++) {
                    switch (fields.item(j).getNodeName()) {
                        case "name":
                            curMusic.setName(fields.item(j).getTextContent());
                            break;
                        case "author":
                            curMusic.setAuthor(fields.item(j).getTextContent());
                            break;
                        case "words":
                            curMusic.setWords(Integer.parseInt(fields.item(j).getTextContent()));
                            break;
                    }
                }
                musicList.add(curMusic);
            }
        } catch (ParserConfigurationException ignored) {}
    }

    public AlbumTableModel() {
        musicList = new ArrayList<>();
    }

    public List<Music> getItems() {
        return musicList;
    }

    public void setItems(List<Music> list) {
        this.musicList = list;
    }

    @Override
    public int getRowCount() {
        return musicList.size();
    }

    @Override
    public int getColumnCount() {
        return COL_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return musicList.get(rowIndex).getName();
            case 1:
                return musicList.get(rowIndex).getAuthor();
            case 2:
                return musicList.get(rowIndex).getExtension();
            case 3:
                return musicList.get(rowIndex).getYear();
            case 4:
                return musicList.get(rowIndex).getWords();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return COL_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int column) {
        if (column < 3)
            return String.class;
        else
            return Integer.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                musicList.get(rowIndex).setName((String) aValue);
                break;
            case 1:
                musicList.get(rowIndex).setAuthor((String) aValue);
                break;
            case 2:
                musicList.get(rowIndex).setExtension((String) aValue);
                break;
            case 3:
                musicList.get(rowIndex).setYear((Integer) aValue);
                break;
            case 4:
                musicList.get(rowIndex).setWords((Integer) aValue);
                break;
        }
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<music>\n");
        for (Music music : musicList){
            sb.append(music.toXML());
            sb.append("\n");
        }
        sb.append("</music>\n");
        return sb.toString();
    }

    public void saveToXML(File file) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(file);
        pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        pw.println(toXML());
        pw.close();
    }

    public void saveToBinary(File file) throws IOException {
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
        stream.writeObject(musicList);
        stream.close();
    }

    public static AlbumTableModel loadFromBinary(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
        AlbumTableModel model = new AlbumTableModel();
        model.setItems((List<Music>) stream.readObject());
        stream.close();
        return model;
    }

    public void deleteRows(int[] rows) {
        for (int i = rows.length - 1; i >= 0; --i)
            if (rows[i] < musicList.size())
                musicList.remove(rows[i]);
    }

}