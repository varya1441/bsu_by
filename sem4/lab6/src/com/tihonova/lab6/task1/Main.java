package com.tihonova.lab6.task1;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    private final static int NUM = 3;
    private final static String IMAGE = "Picasso_03.JPG";



    private JPanel tilePanel;
    private JPanel controlPanel;

    private JButton[][] tileMatrix;

    private int swapX = -1;
    private int swapY = -1;

    public Main() {
        super("DRAW");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        controlPanel = new JPanel();
        JLabel exampleImage = new JLabel();
        exampleImage.setIcon(new ImageIcon(new ImageIcon(IMAGE).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        controlPanel.add(exampleImage);

        GridLayout gridLayout = new GridLayout(NUM, NUM);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        tilePanel = new JPanel(gridLayout);
        tileMatrix = generateTiles(IMAGE);

        for (int i = 0; i < NUM * NUM; i++)
            swapTiles((int) (Math.random() * NUM), (int) (Math.random() * NUM), (int) (Math.random() * NUM), (int) (Math.random() * NUM), false);

        for (int i = 0; i < NUM; i++)
            for (int j = 0; j < NUM; j++)
                tilePanel.add(tileMatrix[i][j]);

        add(controlPanel, BorderLayout.EAST);
        add(tilePanel, BorderLayout.CENTER);
    }

    private JButton[][] generateTiles(String imagePath) {
        try {
            BufferedImage wholeImage = ImageIO.read(new File(imagePath));
            int tileWidth = wholeImage.getWidth() / NUM, tileHeight = wholeImage.getHeight() / NUM;
            JButton[][] tiles = new JButton[NUM][NUM];
            for (int i = 0; i < NUM; i++)
                for (int j = 0; j < NUM; j++) {
                    tiles[i][j] = new JButton();
                    tiles[i][j].setIcon(new ImageIcon(wholeImage.getSubimage(j * tileWidth, i * tileHeight, tileWidth, tileHeight)));
                    tiles[i][j].setName("" + (i * NUM + j));
                    tiles[i][j].putClientProperty(0, i);
                    tiles[i][j].putClientProperty(1, j);
                    tiles[i][j].addActionListener(e -> {
                        JButton b = ((JButton) e.getSource());
                        if (swapX == -1) {
                            b.setEnabled(false);
                            swapX = (int) b.getClientProperty(0);
                            swapY = (int) b.getClientProperty(1);
                        } else {
                            swapTiles((int) b.getClientProperty(0), (int) b.getClientProperty(1), swapX, swapY, true);
                            checkCorrectness();
                            swapX = -1;
                        }
                    });
                }
            return tiles;

        } catch (IOException e) {
            return null;
        }
    }

    private void swapTiles(int tile1X, int tile1Y, int tile2X, int tile2Y, boolean repaint) {

        JButton tile1 = tileMatrix[tile1X][tile1Y];
        JButton tile2 = tileMatrix[tile2X][tile2Y];
        tile1.setEnabled(true);
        tile1.putClientProperty(0, tile2X);
        tile1.putClientProperty(1, tile2Y);
        tile2.setEnabled(true);
        tile2.putClientProperty(0, tile1X);
        tile2.putClientProperty(1, tile1Y);

        JButton temp = tileMatrix[tile1X][tile1Y];
        tileMatrix[tile1X][tile1Y] = tileMatrix[tile2X][tile2Y];
        tileMatrix[tile2X][tile2Y] = temp;

        if (repaint) {
            tilePanel.removeAll();
            for (int i = 0; i < NUM; i++)
                for (int j = 0; j < NUM; j++)
                    tilePanel.add(tileMatrix[i][j]);
            tilePanel.revalidate();
            tilePanel.repaint();
        }
    }

    void checkCorrectness() {
        for (int i = 0; i < NUM; i++)
            for (int j = 0; j < NUM; j++)
                if (Integer.parseInt(tileMatrix[i][j].getName()) != i * NUM + j)
                    return;
        JOptionPane.showMessageDialog(null, "You've solved the puzzle!", "CONGRATULATIONS!", JOptionPane.INFORMATION_MESSAGE);
    }


}