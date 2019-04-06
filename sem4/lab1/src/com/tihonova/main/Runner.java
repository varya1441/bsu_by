package com.tihonova.main;

import com.tihonova.domain.Cinema;
import com.tihonova.domain.MyComparator;
import com.tihonova.myxml.XMLRead;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Runner {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        File file=new File("cinema.xml");
        ArrayList<Cinema> cinemas= XMLRead.read(file);
        System.out.println(cinemas);
        Collections.sort(cinemas);
        System.out.println("By name\n");
        System.out.println(cinemas);
        Set<String> films=new TreeSet<>();
        for (Cinema cinema:cinemas
             ) {
            for (String film:
                 cinema.getFilms()) {
                films.add(film);
            }

        }
        System.out.println("Films\n");
        System.out.println(films.toString());
        System.out.println("Max\n");
        System.out.println(Collections.max(cinemas, Comparator.comparing(Cinema::getSeats)));
        Collections.sort(cinemas,new MyComparator());
        Cinema c=new Cinema(23,"b",new ArrayList<String>());
        System.out.println("BinarySearch\n");
        System.out.println(cinemas.get(Collections.binarySearch(cinemas,c,new MyComparator())));

    }
//    public static TreeSet<Cinema> myFilms( ArrayList<Cinema> cinemas){
//        for (Cinema cinema:cinemas
//        ) {
//            for (String film:
//                    cinema.getFilms()) {
//                films.add(film);
//            }
//
//        }
//    }
}
