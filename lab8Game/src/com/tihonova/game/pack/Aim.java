package com.tihonova.game.pack;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


class Aim implements Painter{
    private BufferedImage aim;

    private int x;
    private int y;
    private int count = 10;

    Aim() throws IOException {
        aim = ImageIO.read(new File("aim.png"));
    }

    void resize(int newX, int newY) {
        x = newX;
        y = newY;
        count = 0;
    }

    BufferedImage getImage() {
        return aim;
    }

    int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    boolean is() {
        ++count;
        return count < 10;
    }

    public int getY() {
        return y;
    }
    @Override
    public void draw(Graphics g) {
        g.drawImage(aim,x-10,y,aim.getWidth()/20,aim.getHeight()/20, null);

    }
}