package com.tihonova.countries.app;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Countries extends JPanel {
    private Map<String, Pair<String, ImageIcon>> countries;
    private JList<String> countriesList;
    private JLabel jLabel;
    private JPanel panel;

    public Countries(Map<String, Pair<String, ImageIcon>> countries) {
        super();
        setLayout(new BorderLayout());
        setSize(500, 500);

        this.countries = countries;
        countriesList = new JList<>();
        jLabel = new JLabel();
        panel = new JPanel();
        panel.setSize(500, 100);
        this.add(new JScrollPane(countriesList), BorderLayout.CENTER);
        this.add(panel, BorderLayout.NORTH);
        panel.add(jLabel);
        DefaultListModel model = new DefaultListModel();
        countriesList.setModel(model);
        for (Map.Entry m : countries.entrySet()) {
            model.addElement(m.getKey());
        }
        countriesList.setCellRenderer(new CellRenderer());
        countriesList.addListSelectionListener(Listener -> {
            String country = countriesList.getSelectedValue();
            jLabel.setIcon(countries.get(country).getValue());
            jLabel.setText(country + " " + countries.get(country).getKey());
        });
        setPreferredSize(new Dimension(600, 550));
        setMinimumSize(new Dimension(600, 400));
        setVisible(true);
    }

    class CellRenderer extends DefaultListCellRenderer {
        public CellRenderer() {
            setOpaque(true);
        }

        public Component getListCellRendererComponent(JList<?> jlistst, Object value, int index, boolean isSelected,
                                                      boolean cellHarFocus) {
            super.getListCellRendererComponent(jlistst, value, index, isSelected, cellHarFocus);
            setIcon(countries.get(value.toString()).getValue());
            return this;
        }
    }

}
