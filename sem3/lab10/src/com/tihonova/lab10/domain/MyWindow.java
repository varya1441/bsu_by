package com.tihonova.lab10.domain;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;

public class MyWindow extends JFrame {
    private JButton jButton;
    private JPanel labelPanel, dragPanel;
    private JLabel jLabel;
    private MenuBar menuBar;
    private Menu setMark;
    private MenuItem mark, quit;
    private JFrame app;

    public MyWindow() {
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

        menuBar = new MenuBar();
        this.setMenuBar(menuBar);
        setMark = new Menu("Mark");
        setMark.add(mark = new MenuItem("Set Mark"));
        setMark.add(quit = new MenuItem("Quit"));
        menuBar.add(setMark);
        setMark.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals(mark.getActionCommand())) {
                    JOptionPane.showInputDialog("What is my mark?");
                } else if (e.getActionCommand().equals(quit.getActionCommand())) {
                    app.dispose();
                }

            }
        });


        labelPanel = new JPanel();
        labelPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
        labelPanel.setBackground(Color.PINK);
        jLabel = new JLabel("hgh");
        labelPanel.add(jLabel);
        this.add(labelPanel, BorderLayout.SOUTH);


        dragPanel = new JPanel(null);
        dragPanel.setBackground(Color.getHSBColor(0.5f, 0.33f, 1f));
        this.add(dragPanel, BorderLayout.CENTER);

        jButton = new JButton("Button");
        dragPanel.add(jButton);
        jButton.setBounds(0, 0, jButton.getPreferredSize().width, jButton.getPreferredSize().height);
        jButton.setBackground(Color.ORANGE);

        dragPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jButton.setLocation(e.getX() - jButton.getWidth() / 2, e.getY() - jButton.getHeight() / 2);
            }
        });
        dragPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                jLabel.setText("x = " + e.getX() + "; y = " + e.getY());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                jLabel.setText("x = " + e.getX() + "; y = " + e.getY());
            }
        });
        jButton.addMouseMotionListener(new MouseMotionAdapter() {
            private int x, y;

            @Override
            public void mouseMoved(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                jLabel.setText("x = " + (jButton.getX() + e.getX() )+ "; y = " + (jButton.getY() + e.getY()));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (e.isControlDown()) {
                    jButton.setLocation(jButton.getX() + e.getX() - x, jButton.getY() + e.getY() - y);

                }
                jLabel.setText("x= " + (jButton.getX() + e.getX()) + "; y= " + (jButton.getY() + e.getY()));
            }
        });
        jButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    if (jButton.getText().length() > 0) {
                        jButton.setText(jButton.getText().substring(0, jButton.getText().length() - 1));
                    }
                } else {
                    jButton.setText(jButton.getText() + e.getKeyChar());
                }
                jButton.setSize(jButton.getPreferredSize());
            }
        });

    }
}


