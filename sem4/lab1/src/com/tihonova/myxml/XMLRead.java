package com.tihonova.myxml;

import com.tihonova.domain.Cinema;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XMLRead {
    public static ArrayList<Cinema> read(File fileReader)throws IOException, ParserConfigurationException, SAXException {
        DefaultHandler handler = new SAXRead();
        SAXParserFactory factory = SAXParserFactory.newInstance();//разборщик
        SAXParser parser = factory.newSAXParser();
        parser.parse(fileReader,handler);
        return ((SAXRead) handler).getCinemas();
    }
}
