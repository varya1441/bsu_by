package com.tihonova.game.pack;

import com.tihonova.game.observer.Observer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Observable;
import java.util.Random;


class Duck implements Observer<Duck> {
    private int x;
    private int y;
    private int speedX;
    private int speedY;
    private boolean die = false;
    private boolean to_left;
    static BufferedImage leftDuck;
    static BufferedImage rightDuck;
    static BufferedImage dieLeftDuck;
    static BufferedImage dieRightDuck;

    Duck(int maxSpeed, int width, int height) {
        x = 0;
        Random duckRandom = new Random();
        y = duckRandom.nextInt(400);
        speedX = duckRandom.nextInt(maxSpeed) + 1;
        to_left = duckRandom.nextBoolean();
        if (!to_left) {
            x = width - leftDuck.getWidth();
        }
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void move() {
        if (to_left) {
            x += speedX;
        } else {
            x -= speedX;
        }
        y += speedY;
    }

    boolean left() {
        return to_left;
    }

    boolean is_die() {
        return die;
    }

    BufferedImage getImage() {
        if (to_left) {
            if (die) {
                return dieLeftDuck;
            } else {
                return leftDuck;
            }
        } else {
            if (die) {
                return dieRightDuck;
            } else {
                return rightDuck;
            }
        }
    }


    void kill() {
        die = true;
        speedX = 0;
        speedY = 5;
    }


    @Override
    public void update(Duck object) {
        move();
    }
}