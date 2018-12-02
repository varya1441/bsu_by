package com.tihonova.lab11.window;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Collections;

public class Application extends JFrame {
    private JButton blueButton, redButton, greenButton;
    private JPanel buttonPanel, dragPanel, toolPanel;
    private JLabel jLabel;
    private MenuBar menuBar;
    private Menu setMark;
    private MenuItem mark, quit;
    private JFrame app;

    public Application() {
        setTitle("Lab 10   :3");
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
                    makeGUI();
                }
            });
        } catch (Exception ex) {
            System.out.println("Can't create because of " + ex.getMessage());
        }
    }

    private void makeGUI() {
        app = this;

        dragPanel = new JPanel();
        // dragPanel.setBackground(Color.getHSBColor(0.5f, 0.33f, 1f));
        toolPanel = new JPanel();
        toolPanel.setBackground(new Color(0xC1B8B0));
        toolPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));

        blueButton = new JButton("Open");
        blueButton.setBackground(new Color(24, 186, 250));

        greenButton = new JButton("Save");
        greenButton.setBackground(new Color(28, 179, 101));

        redButton = new JButton("Color");
        redButton.setBackground(new Color(0xF06A74));

        buttonPanel = new JPanel(new GridLayout(1, 3));

        toolPanel.add(buttonPanel);

        buttonPanel.add(blueButton);
        buttonPanel.add(redButton);
        buttonPanel.add(greenButton);

        this.add(toolPanel,BorderLayout.NORTH);
        this.add(dragPanel, BorderLayout.CENTER);


    }
}

