package com.tihonova.game.first;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class BasicObject {
    private int x;
    private int y;
    private ImageIcon picture;
    private int speed;
    private BufferedImage bufferedImage;
    public abstract void tick();

    public abstract void render(Graphics g);

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ImageIcon getPicture() {
        return picture;
    }

    public void setPicture(ImageIcon picture) {
        this.picture = picture;
    }
}
