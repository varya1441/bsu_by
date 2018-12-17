package by.tihonova.javatr.reader;

import by.tihonova.javatr.domain.childrengroup.ChildrenGroup;
import by.tihonova.javatr.domain.toy.Toy;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class ReadFromFile {

    static Scanner scanner = null;

    public static Set<Toy> read(FileReader fileReader) throws IllegalArgumentException, InputMismatchException {
        Set<Toy> myContainer = new TreeSet<>();
        scanner = new Scanner(new BufferedReader(fileReader));
        while (scanner.hasNext()) {

            myContainer.add(new Toy(scanner.next(), scanner.nextDouble(),
                    ChildrenGroup.valueOf(scanner.next().toUpperCase())));
        }

        return myContainer;
    }
    public static Set<Toy>readXML(File fileReader)throws IOException, ParserConfigurationException, SAXException{
        Set<Toy> myContainer = new TreeSet<>();
        DefaultHandler handler = new SAXReader();
        SAXParserFactory factory = SAXParserFactory.newInstance();//разборщик
        SAXParser parser = factory.newSAXParser();
        parser.parse(fileReader,handler);
        myContainer=((SAXReader) handler).getToys();
        return myContainer;
    }
}

