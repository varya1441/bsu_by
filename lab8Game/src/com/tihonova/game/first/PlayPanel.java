package com.tihonova.game.first;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class PlayPanel extends JPanel {
    private Duck duck = new Duck();
    private Duck duck2=new Duck();
    private Hunter hunter=new Hunter();
    private int move;
    private int leftRight;
    private final int START_X = 0;
    private final int START_Y = 0;
    PlayPanel() {
        setSize(900, 600);
        setBackground(Color.PINK);
        duck.setX(START_X);
        leftRight=START_X;
        move=getWidth();
        hunter.setX(getWidth()-getWidth()/5);
        hunter.setY(getWidth());
        duck.setY((int)Math.random()* this.getWidth()+100);
        duck2.setX(move);
        duck2.setY((int)Math.random()* this.getWidth()+100);



    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.drawImage(duck.getPicture().getImage(), duck.getX() + move, duck.getY(), duck.getPicture().getIconWidth()*2, duck.getPicture().getIconHeight()*2, null);
        g2.drawImage(duck2.getPicture().getImage(), duck2.getX() - move, duck2.getY()/20, duck2.getPicture().getIconWidth()*2, duck2.getPicture().getIconHeight()*2, null);
        g2.drawImage(hunter.getPicture().getImage(), hunter.getX() , hunter.getY(), hunter.getPicture().getIconWidth()*2, hunter.getPicture().getIconHeight()*2, null);;
        g.drawImage(image, duck.getX() , duck.getY(), null);


    }

    public void moveDuck() {
        move--;
        if(move<-1){
            move=getWidth();

        }
    }
    public void moveHunter(boolean left){
        if (left){
            leftRight++;
        }else {
            leftRight--;
        }
    }
}
