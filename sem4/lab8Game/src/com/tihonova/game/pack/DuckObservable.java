package com.tihonova.game.pack;

import com.tihonova.game.observer.DuckObservableIMPL;
import com.tihonova.game.observer.Observable;
import com.tihonova.game.observer.Observer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;


class DuckObservable implements Painter, Observable {
    private ArrayList<Duck> list = new ArrayList<Duck>();
    private int level = 1;
    private int next_level = 2;
    private double count_shoots = 0;
    private double count_hits = 0;
    private int width;
    private int height;

    DuckObservable(int newWidth, int newHeight) {
        width = newWidth;
        height = newHeight;
    }

    void move() {
        for (int i = list.size() - 1; i >= 0; --i) {
            list.get(i).move();
            if (list.get(i).getX() > width - list.get(i).getImage().getWidth() || list.get(i).getX() < 0) {
                list.remove(i);
            } else if (list.get(i).getY() > height - 300) {
                list.remove(i);
            }
        }

        while (list.size() < 5) {
            registerObserver(new Duck(level, width, height));
        }
    }

    int kill(int x, int y) {

        ++count_shoots;
        int count_kill = 0;
        for (int i = list.size() - 1; i >= 0; --i) {
            if (x > list.get(i).getX() && x < list.get(i).getX() + list.get(i).getImage().getWidth() && !list.get(i).is_die() &&
                    y > list.get(i).getY() && y < list.get(i).getY() + list.get(i).getImage().getHeight()) {
                list.get(i).kill();
                ++count_hits;
                ++count_kill;
                if (count_hits >= next_level) {
                    next_level *= 2;
                    ++level;
                }

            }
        }

        music();
        return level;
    }

    @Override
    public void draw(Graphics g) {
        for (Duck duck : list) {
            g.drawImage(duck.getImage(), duck.getX(), duck.getY(), null);
        }
    }


    double getHits() {
        return count_hits;
    }

    double getShoots() {
        return count_shoots;
    }

    int getLevel() {
        return level;
    }



    private void music() {
        String killFile;
        killFile = "kill.wav";
        try {
            InputStream in = new FileInputStream(killFile);
            AudioStream audioStream = new AudioStream(in);
            AudioPlayer.player.start(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void registerObserver(Observer o) {
        list.add((Duck) o);
    }

    @Override
    public void removeObserver(Observer o) {

    }

    @Override
    public void notifyObservers(Object object) {
        move();
    }
}