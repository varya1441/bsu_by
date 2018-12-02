package com.tihonova.lab11.ts;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Paint extends JFrame {

    private static Paint app;
    private Canvas canvas;
    private JPanel toolPanel,buttonPanel ;
    private JButton openButton, saveButton, chooseColor;
    private Color currentColor;
    private Image image;
    private JScrollPane scrollPane;

    public Paint() {
        super("Lab 11: Paint");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        init();
    }

        public void init() {
            try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    makePaint();
                }
            });
        } catch (Exception ex) {
            System.out.println("Can't create because of " + ex.getMessage());
        }
    }


    public void makePaint() {


        canvas = new Canvas();
        canvas.setSize(800,800);
        scrollPane = new JScrollPane(canvas);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        toolPanel = new JPanel();
        toolPanel = new JPanel();
        toolPanel.setBackground(new Color(0xC1B8B0));
       // toolPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));

        buttonPanel = new JPanel(new GridLayout(1, 3));

        chooseColor = new JButton("Color");
        chooseColor.setBackground(new Color(0xAF8463));

        currentColor = Color.black;
        chooseColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color chosenColor = JColorChooser.showDialog(app, "Choose color", currentColor);
                if (chosenColor != null)
                    currentColor = chosenColor;
            }
        });


        openButton = new JButton("Open");
        openButton.setBackground(new Color(0xF06A74));
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(app) == JFileChooser.APPROVE_OPTION) {
                    File inputFile = fileChooser.getSelectedFile();
                    try {
                        image = ImageIO.read(inputFile);
                        canvas.clear();
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(app, "File cannot be opened");
                    }
                }
            }
        });


        saveButton = new JButton("Save");
        saveButton.setBackground(new Color(0xA62A37));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File("image.png"));
                if (fileChooser.showSaveDialog(app) == JFileChooser.APPROVE_OPTION) {
                    File outputFile = fileChooser.getSelectedFile();
                    try {
                        BufferedImage im = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
                        canvas.paint(im.getGraphics());
                        ImageIO.write(im, "PNG", outputFile);
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(app, "File cannot be saved to this location");
                    }
                }
            }
        });
        toolPanel.add(buttonPanel);
        buttonPanel.add(chooseColor);
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        this.add(toolPanel,BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

    }


    public Color getCurrentColor() {
        return currentColor;
    }

    public Image getBackgroundImage() {
        return image;
    }
}