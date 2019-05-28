package com.tihonova.duck.game;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
@Setter
public class PlayPanel extends JPanel {
    private Duck duck=new Duck();
    private int move;
    private final int START_X = 0;

    PlayPanel() {

        duck.setX(START_X);

        duck.setY((int) (Math.random() % this.getHeight()));
        //setSize(500,500);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = new BufferedImage(getHeight() / 5, getHeight() / 5, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g.setColor(Color.PINK);
        g.drawImage(image,duck.getX(),duck.getY()+move,null);
        //int x = (int) (xOffset + radius + Math.sin(angle) * radius);
        //int y = (int) (yOffset + radius - Math.cos(angle) * radius);
       // g2.drawImage(new ImageIcon("cat2.png").getImage(), 0, 0, getHeight() / 5, getHeight() / 5, null);
        //g.drawImage(image, x - 50, y - 50, null);
        g2.drawImage( duck.getPicture().getImage(),duck.getX(),duck.getY()+move,duck.getPicture().getIconHeight(),duck.getPicture().getIconWidth(),null);
//        xSize = this.getWidth();
//        ySize = this.getHeight();
//        radius = Math.min(xSize / 2, ySize / 2) * 4 / 5;
//        int xOffset = (xSize - 2 * radius) / 2;
//        int yOffset = (ySize - 2 * radius) / 2;
//
//
//        g.setColor(Color.PINK);
//        g.drawOval(xOffset, yOffset, 2 * radius, 2 * radius);
//
//
//        int x = (int) (xOffset + radius + Math.sin(angle) * radius);
//        int y = (int) (yOffset + radius - Math.cos(angle) * radius);
//
//        BufferedImage image = new BufferedImage(getHeight() / 5, getHeight() / 5, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g2 = image.createGraphics();
//        g2.drawImage(new ImageIcon("cat2.png").getImage(), 0, 0, getHeight() / 5, getHeight() / 5, null);
//        g.drawImage(image, x - 50, y - 50, null);
    }
    public void moveDuck(){
        move++;
    }
}
