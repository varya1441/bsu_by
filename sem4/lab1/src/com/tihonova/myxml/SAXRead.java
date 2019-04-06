package com.tihonova.myxml;

import com.tihonova.domain.Cinema;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class SAXRead extends DefaultHandler {
    private boolean film;
    private ArrayList<String>films;
    private boolean seats;
    private boolean filmAdd;
    public SAXRead() {
    }
    private Cinema cinema;
    private ArrayList<Cinema> cinemas;

    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }

    @Override
    public void startDocument() throws SAXException {

        cinemas = new ArrayList<Cinema>();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equals("cinema")){
            cinema=new Cinema();
            cinema.setName(attributes.getValue("name"));
            cinema.setSeats(Integer.parseInt(attributes.getValue("seats")));
        }else if(qName.equals("films")){
            films=new ArrayList<>();

        }else if(qName.equals("film")){
            filmAdd=true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(cinema==null){
            throw new SAXException();
        }else if(qName.equals("cinema")){
            cinema.setFilms(films);
            cinemas.add(cinema);
        }else if(qName.equals("films")){
            filmAdd=false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(filmAdd){
            films.add(new String(ch, start, length));
            filmAdd=false;
        }
    }

}
