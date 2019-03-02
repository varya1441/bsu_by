package tihonova.reader;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tihonova.domain.RobotVacuumCleaner;
import tihonova.domain.VacuumCleaner;
import tihonova.domain.WashingVacuumCleaner;

import java.util.ArrayList;

public class SAXReader extends DefaultHandler {
    private boolean firmName;
    private boolean power;
    private boolean color;
    private boolean image;
    private boolean waterContainerVolume;
    private boolean screen;

    private VacuumCleaner cleaner;
    private ArrayList<VacuumCleaner> vacuumCleaners;

    public ArrayList<VacuumCleaner> getVacuumCleaners() {
        return vacuumCleaners;
    }

    public SAXReader() {
    }

    @Override
    public void startDocument() throws SAXException {
        vacuumCleaners = new ArrayList<VacuumCleaner>();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("washing")) {
            cleaner = new WashingVacuumCleaner();
        } else if (qName.equals("robot")) {
            cleaner = new RobotVacuumCleaner();
        } else if (qName.equals("firm")) {
            firmName = true;
        } else if (qName.equals("power")) {
            power = true;
        } else if (qName.equals("color")) {
            color = true;
        } else if (qName.equals("image")) {
            image = true;
        }else if(qName.equals("container")){
            waterContainerVolume=true;
        }else if(qName.equals("screen")){
            screen=true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (cleaner == null) {
            throw new SAXException();
        } else if (qName.equals("washing")||qName.equals("robot")) {
            vacuumCleaners.add(cleaner);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (firmName) {
            cleaner.setFirmName(new String(ch, start, length));
            firmName = false;
        } else if (power) {
            cleaner.setPower(Integer.parseInt(new String(ch, start, length)));
            power = false;
        } else if (color) {
            cleaner.setColor(new String(ch, start, length));
            color = false;
        } else if (image) {
            cleaner.setColor(new String(ch, start, length));
            image = false;
        }else if(waterContainerVolume){
            ((WashingVacuumCleaner)cleaner).setWaterContainerVolume(Integer.parseInt(new String(ch, start, length)));
            waterContainerVolume=false;
        }else if(screen){
            ((RobotVacuumCleaner)cleaner).setScreen(Boolean.parseBoolean(new String(ch, start, length)));
            screen=false;
        }
    }
}
