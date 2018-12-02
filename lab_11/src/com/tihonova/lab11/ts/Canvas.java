package com.tihonova.lab11.ts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JPanel {
    private CanvasLine currentLine;
    private List<CanvasLine> lines;
    private final static int AREA_SIZE = 400;
    private int maxX;
    private int maxY;
    private BufferedImage image =
            new BufferedImage(AREA_SIZE, AREA_SIZE, BufferedImage.TYPE_INT_ARGB);

    public Canvas() {
        super(null);
        //this.setBackground();

        lines = new ArrayList<CanvasLine>();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentLine = new CanvasLine(getCurrentColor());
                lines.add(currentLine);
                currentLine.add(e.getX(), e.getY());
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentLine.add(e.getX(), e.getY());
                maxX = Math.max(maxX, e.getX());
                maxY = Math.max(maxY, e.getY());
                repaint();
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        image.getBackgroundImage();
        if (image != null) {
            g.drawImage(image, 0, 0, null);
        }
        if (!lines.isEmpty()) {
            for (CanvasLine line : lines) {
                     line.paint(g);
            }
        }
        this.setPreferredSize(new Dimension(this.maxX, this.maxY));
         this.revalidate();
    }

    @Override
    public Dimension getPreferredSize() {
        return isPreferredSizeSet() ?
                super.getPreferredSize() : new Dimension(AREA_SIZE, AREA_SIZE);
    }

    private Color getCurrentColor() {
        return ((Paint) SwingUtilities.getRoot(this)).getCurrentColor();
    }

//    private Image getBackgroundImage() {
//        return ((Paint) SwingUtilities.getRoot(this)).getBackgroundImage();
//    }

    public void clear() {
        lines.clear();
        repaint();
    }


}
