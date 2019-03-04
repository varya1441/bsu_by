package com.tihonova.diagram.application;


import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@SuppressWarnings("ALL")
public class Application extends ApplicationFrame {

    public Application(String title) throws IOException, ParseException {
        super(title);
        setContentPane(createDemoPanel());
    }

    private PieDataset createDataset() throws IOException, ParseException {
        DefaultPieDataset dataset = new DefaultPieDataset();
        JSONParser parser = new JSONParser();
        try {
            Object a = parser.parse(new FileReader("diagram.json"));

            JSONObject jsonObject = (JSONObject) a;

            JSONArray books = (JSONArray) jsonObject.get("book");
            Iterator<Map.Entry> itr1;

            Iterator itr2 = books.iterator();
            String name = "none";
            Double pages = new Double(0);
            try {
                while (itr2.hasNext()) {
                    itr1 = ((Map) itr2.next()).entrySet().iterator();
                    while (itr1.hasNext()) {
                        Map.Entry pair = itr1.next();
                        String key = (String) pair.getKey();
                        if (key.equals("pages")) {
                            pages = Double.parseDouble((String) pair.getValue());
                        } else {
                            name = (String) pair.getValue();
                            dataset.setValue(name, pages);
                        }
                    }
                }
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Exception");
            }
        }catch (IOException|NumberFormatException|ParseException e){
            JOptionPane.showMessageDialog(this,
                    "Wrong input.",
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);
        }

        return dataset;
    }

    private static JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Mobile Sales",   // chart title
                dataset,          // data
                true,             // include legend
                true,
                false);

        return chart;
    }

    public  JPanel createDemoPanel() throws IOException, ParseException {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }


}
