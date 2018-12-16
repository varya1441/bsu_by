package by.tihonova.javatr.reader;

import by.tihonova.javatr.domain.childrengroup.ChildrenGroup;
import by.tihonova.javatr.domain.toy.Toy;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Set;
import java.util.TreeSet;

public class SAXReader extends DefaultHandler {
    private boolean name;
    private boolean cost;
    private boolean group;
    private Toy toy;
    private Set<Toy> toys;

    public Set<Toy> getToys() {
        return toys;
    }

    public SAXReader() {
    }

    @Override
    public void startDocument() throws SAXException {
        toys = new TreeSet<>();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("toy")) {
            toy = new Toy();
        } else if (qName.equals("name")) {
            name = true;
        } else if (qName.equals("cost")) {
            cost = true;
        } else if (qName.equals("group")) {
            group = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (toy==null) {
          throw new SAXException();
        } else if (qName.equals("toy")) {
            toys.add(toy);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(name){
            toy.setName(new String(ch,start,length));
            name=false;
        }else if(cost){
            toy.setCost(Double.parseDouble(new String(ch,start,length)));
            cost=false;
        }else if(group){
            toy.setChildrenGroup(ChildrenGroup.valueOf(new String(ch,start,length).toUpperCase()));
            group=false;
        }
    }
}
