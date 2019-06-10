package com.tihonova.reader;

import com.tihonova.domain.Animal;
import com.tihonova.domain.Beast;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class SAXReader extends DefaultHandler {
    private boolean name;
    private boolean place;
    private boolean pray;

    public SAXReader() {
    }

    private Animal animal;
    private ArrayList<Animal> animals;

    public ArrayList<Animal> getAnimals() {
        return animals;
    }


    @Override
    public void startDocument() throws SAXException {
        animals = new ArrayList<Animal>();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("beast")) {
            animal = new Beast();
        } else if (qName.equals("name")) {
            name = true;
        } else if (qName.equals("place")) {
            place = true;
        } else if (qName.equals("pray")) {
            pray = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (animals == null) {
            throw new SAXException();
        } else if (qName.equals("beast")) {
            animals.add(animal);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (name) {
            animal.setName(new String(ch, start, length));
            name = false;
        } else if (place) {
            animal.setPlace(new String(ch, start, length));
            place = false;
        } else if (pray) {
            ((Beast) animal).setPrey(new String(ch, start, length));
            pray = false;
        }
    }
}
