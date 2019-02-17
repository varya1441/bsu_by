package com.tihonova.reader;

import com.tihonova.domain.Drink;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class MyReader {
    public static Set<Drink> read(File fileReader)throws IOException, ParserConfigurationException, SAXException{
        Set<Drink> drinks=new TreeSet<>();
        DefaultHandler handler = new SAXReader();
        SAXParserFactory factory = SAXParserFactory.newInstance();//разборщик
        SAXParser parser = factory.newSAXParser();
        parser.parse(fileReader,handler);
        drinks=((SAXReader) handler).getToys();
        return drinks;
    }
}
