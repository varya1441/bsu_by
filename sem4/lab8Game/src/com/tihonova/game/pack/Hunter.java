package com.tihonova.game.pack;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Hunter implements Painter {
    private BufferedImage image;
    private int x;
    private int y;

    Hunter(int width, int height) throws IOException {
        image = ImageIO.read(new File("hunter.png"));

        x = (width - image.getWidth()) / 20;
        y = (height - image.getHeight())/20;
    }

    BufferedImage getImage() {

        return image;
    }
    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y+450,image.getWidth()/3,image.getHeight()/3, null);

    }
    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void setX(int newX) {
        x = newX;
    }
}