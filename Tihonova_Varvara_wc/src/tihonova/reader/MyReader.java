package com.tihonova.reader;

import com.tihonova.domain.Drink;
import com.tihonova.domain.MyComparator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class MyReader {
    public static ArrayList<Drink> read(File fileReader)throws IOException, ParserConfigurationException, SAXException{
        DefaultHandler handler = new SAXReader();
        SAXParserFactory factory = SAXParserFactory.newInstance();//разборщик
        SAXParser parser = factory.newSAXParser();
        parser.parse(fileReader,handler);
        return ((SAXReader) handler).getDrinks();
    }
}
