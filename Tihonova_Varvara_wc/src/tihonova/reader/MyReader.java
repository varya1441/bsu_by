package tihonova.reader;


import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tihonova.domain.VacuumCleaner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MyReader {
    public static ArrayList<VacuumCleaner> read(File fileReader)throws IOException, ParserConfigurationException, SAXException{
        DefaultHandler handler = new SAXReader();
        SAXParserFactory factory = SAXParserFactory.newInstance();//разборщик
        SAXParser parser = factory.newSAXParser();
        parser.parse(fileReader,handler);
        return ((SAXReader) handler).getVacuumCleaners();
    }
}
