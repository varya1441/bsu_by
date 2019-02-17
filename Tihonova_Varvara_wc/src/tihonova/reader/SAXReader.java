package com.tihonova.reader;

import com.tihonova.domain.Coffee;
import com.tihonova.domain.Drink;
import com.tihonova.domain.MyComparator;
import com.tihonova.domain.Tea;
import com.tihonova.enums.CoffeeType;
import com.tihonova.enums.TeaType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class SAXReader extends DefaultHandler {
    private boolean name;
    private boolean caffeineAmount;
    private boolean cost;
    private boolean type;

    private Drink drink;
    private ArrayList<Drink> drinks;

    public ArrayList<Drink> getDrinks() {
        return drinks;
    }

    public SAXReader() {
    }

    @Override
    public void startDocument() throws SAXException {
        drinks = new ArrayList<Drink>();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("coffee")) {
            drink = new Coffee();
        } else if (qName.equals("tea")) {
            drink = new Tea();
        } else if (qName.equals("name")) {
            name = true;
        } else if (qName.equals("amount")) {
            caffeineAmount = true;
        } else if (qName.equals("cost")) {
            cost = true;
        } else if (qName.equals("type")) {
            type = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (drink == null) {
            throw new SAXException();
        } else if (qName.equals("tea")||qName.equals("coffee")) {
            drinks.add(drink);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (name) {
            drink.setName(new String(ch, start, length));
            name=false;
        } else if (caffeineAmount) {
            drink.setCaffeineAmount(Double.parseDouble(new String(ch,start,length)));
            caffeineAmount=false;
        } else if (cost) {
            drink.setCost(Double.parseDouble(new String(ch,start,length)));
            cost=false;
        }  else if (type) {
            if(drink instanceof Tea){
                ((Tea) drink).setTeaType(TeaType.valueOf(new String(ch,start,length).toUpperCase()));
            }
            else if(drink instanceof Coffee){
                ((Coffee)drink).setCoffeeType(CoffeeType.valueOf(new String(ch,start,length).toUpperCase()));
            }
            type = false;
        }

    }
}
