package com.varvara.task9.window;

import com.varvara.task9.series.exponential.Exponential;
import com.varvara.task9.series.linear.Linear;
import com.varvara.task9.series.series.Series;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Application extends JFrame {
    private Application app;

    private JPanel panelNorth, panelCenter, panelRight;

    private JPanel seriesPanel;
    private JPanel seriesTextPanel, seriesSumPanel;
    private JLabel seriesString, seriesSum;

    private JPanel paramPanel;
    private JLabel firstLabel, deltaLabel, nLabel;
    private JTextField firstText, deltaText, nText;

    private JPanel seriesTypePanel, buttonsPanel;
    private JRadioButton linearButton, exponentialButton;
    private JButton updateButton, saveButton;

    private Series series;


    public Application() {
        app = this;
        setTitle(" Series");
        setBounds(new Rectangle(700, 220));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        series = new Linear(1, 1, 1);
        initComponents();
    }

    private void initComponents() {
        Container container = this.getContentPane();
        panelNorth = new JPanel(new BorderLayout());
        container.add(panelNorth, BorderLayout.CENTER);

        seriesPanel = new JPanel(new BorderLayout());
        panelNorth.add(seriesPanel, BorderLayout.NORTH);

        seriesTextPanel = new MyJPanel("Series");
        seriesString = new JLabel(series.toString());
        seriesTextPanel.add(seriesString);
        seriesPanel.add(seriesTextPanel, BorderLayout.CENTER);

        seriesSumPanel = new MyJPanel("Sum");
        seriesSumPanel.setPreferredSize(new Dimension(70, 0));
        seriesSum = new JLabel(String.valueOf(series.getSum()));
        seriesSumPanel.add(seriesSum);
        seriesPanel.add(seriesSumPanel, BorderLayout.WEST);


        panelCenter = new JPanel(new BorderLayout());
        container.add(panelCenter, BorderLayout.SOUTH);

        paramPanel = new MyJPanel("Parameters");
        paramPanel.setLayout(new GridLayout(3, 2));
        panelCenter.add(paramPanel, BorderLayout.CENTER);


        paramPanel.add(firstText = new JTextField(String.valueOf(series.getFirstElem())));
        paramPanel.add(deltaLabel = new JLabel("Delta: "));
        paramPanel.add(deltaText = new JTextField(String.valueOf(series.getDelta())));
        paramPanel.add(nLabel = new JLabel("n of elements: "));
        paramPanel.add(nText = new JTextField(String.valueOf(series.getN())));

        panelRight = new JPanel(new BorderLayout());
        container.add(panelRight, BorderLayout.EAST);

        seriesTypePanel = new MyJPanel("Series type");
        panelRight.add(seriesTypePanel, BorderLayout.NORTH);

        ButtonGroup bg = new ButtonGroup();
        bg.add(linearButton = new JRadioButton("Linear", true));

        linearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                series = new Linear(series);
                seriesObject();
            }
        });



        bg.add(exponentialButton = new JRadioButton("Exponential", false));


        exponentialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                series = new Exponential(series);
                seriesObject();
            }
        });

        seriesTypePanel.add(linearButton);
        seriesTypePanel.add(exponentialButton);

        buttonsPanel = new JPanel(new BorderLayout());
        buttonsPanel.add(saveButton = new JButton("Save as..."), BorderLayout.  CENTER);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooserSelectedFile = new JFileChooser();
                if (chooserSelectedFile.showSaveDialog(app) == JFileChooser.APPROVE_OPTION) {
                    File outputFile = chooserSelectedFile.getSelectedFile();
                    try {
                        series.toFile(outputFile);
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(app, "File cannot be saved to this location");
                    }
                }
            }
        });
        seriesTypePanel.add(updateButton = new JButton("Update"), BorderLayout.SOUTH);



        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int first = Integer.parseInt(firstText.getText());
                    double delta = Double.parseDouble(deltaText.getText());
                    int n = Integer.parseInt(nText.getText());

                    series.setFirstElem(first);
                    series.setDelta(delta);
                    series.setN(n);

                    seriesObject();
                } catch (NumberFormatException err) {
                    JOptionPane.showMessageDialog(app, "Incorrect format of parameters");
                }

            }
        });


        panelRight.add(buttonsPanel, BorderLayout.CENTER);

    }

    private void seriesObject() {
        seriesString.setText(series.toString());
        seriesSum.setText(String.valueOf(series.getSum()));

//        firstText.setText(String.valueOf(series.getFirstElem()));
//        deltaText.setText(String.valueOf(series.getDelta()));
//        nText.setText(String.valueOf(series.getN()));
        if (series instanceof Linear)
            linearButton.setSelected(true);
        else if (series instanceof Exponential)
            exponentialButton.setSelected(true);
    }



    public class MyJPanel extends JPanel {
        public MyJPanel(String title) {
            super();
            this.setBorder(BorderFactory.createTitledBorder(title));
        }
    }
}
