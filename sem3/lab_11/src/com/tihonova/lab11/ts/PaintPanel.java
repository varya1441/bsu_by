package com.tihonova.lab11.ts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class PaintPanel extends JPanel {
    private final static int AREA_SIZE = 1500;
    private int lastX;
    private int lastY;
    private Color color;
    private BufferedImage bufferedImage
            = new BufferedImage(AREA_SIZE, AREA_SIZE, BufferedImage.TYPE_INT_ARGB);

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    PaintPanel() {
        this.setSize(AREA_SIZE,AREA_SIZE);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastX = e.getX();
                lastY = e.getY();
                color = getCurrentColor();

            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                Graphics graphicsPanel = e.getComponent().getGraphics();

                Graphics graphicsBuffer = bufferedImage.getGraphics();

                graphicsBuffer.setColor(color);
                graphicsPanel.setColor(color);

                ((Graphics2D) graphicsBuffer).setStroke(new BasicStroke(3));
                ((Graphics2D) graphicsPanel).setStroke(new BasicStroke(3));

                graphicsBuffer.drawLine(lastX, lastY, e.getPoint().x, e.getPoint().y);
                graphicsPanel.drawLine(lastX, lastY, e.getPoint().x, e.getPoint().y);

                lastX = e.getPoint().x;
                lastY = e.getPoint().y;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bufferedImage, 0, 0, null);

    }

    private Color getCurrentColor() {
        return ((Paint) SwingUtilities.getRoot(this)).getCurrentColor();
    }

    public void clear() {
        repaint();
    }
}
