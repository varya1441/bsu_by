package com.tihonova.game.pack;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;



class DemoPanel extends JPanel {
    private BufferedImage back;
    private Aim aim;
    private Hunter hunter;
    private DuckObservable ducks;
    private boolean end = false;
    private Timer timer;
    private int width;
    private int height;

    DemoPanel(int newWidth, int newHeight) {
        super();
        Dimension windowSize = new Dimension(newWidth, newHeight);
        setSize(windowSize);
        setMinimumSize(windowSize);
        setMaximumSize(windowSize);

        width = newWidth;
        height = newHeight;

        try {
            back = ImageIO.read(new File("pic1.jpg"));

            aim = new Aim();
            Duck.leftDuck = ImageIO.read(new File("duck.png"));
            Duck.rightDuck = ImageIO.read(new File("duck _left1.png"));
            Duck.dieLeftDuck = ImageIO.read(new File("duck.png"));
            Duck.dieRightDuck = ImageIO.read(new File("duck.png"));
            hunter = new Hunter(width, height);
        } catch (IOException ex) {
            System.out.println("exception io");
        }

        ducks = new DuckObservable(width, height);
        for (int i=0;i<5;i++) {
            ducks.registerObserver(new Duck(1, width, height));
        }

        timer = new Timer(20, e -> {

                ducks.notifyObservers(ducks);

            repaint();
        });
        timer.start();
        String killFile;
        killFile = "song2.wav";
        try {
            InputStream in = new FileInputStream(killFile);
            AudioStream audioStream = new AudioStream(in );
            AudioPlayer.player.start(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        addMouseMotionListener(new PanelMouseMotionListener());
        addMouseListener(new PanelMouseListener());
        addKeyListener(new PanelKeyListener());
        setFocusable(true);

        setVisible(true);
    }

    private void updateDucks() {

        ducks.move();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(back, 0, 0, back.getWidth()*2, back.getHeight()*3/2, this);
        g.setFont(new Font("TimesRoman", Font.PLAIN, back.getHeight()/10));
        g.setColor(Color.RED);
        if (!end) {
            hunter.draw(g);
            ducks.draw(g);
            if (aim.is()) {
                aim.draw(g);
            }
            g.drawString("Level:" + Integer.toString(ducks.getLevel()), 0, 50);
        } else {
            g.drawString("Your Statistic:", width / 3, height * 3 / 10);
            g.drawString("Level " + Integer.toString(ducks.getLevel()), width / 3, height * 4 / 10);
            g.drawString("Count hits " + Double.toString(ducks.getHits()), width / 3, height * 5 / 10);
            g.drawString("Count shoots " + Double.toString(ducks.getShoots()), width / 3, height * 6 / 10);
            timer.stop();
        }
    }

    class PanelMouseMotionListener extends MouseMotionAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
            hunter.setX(e.getX() - 200);
        }
    }

    class PanelMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
           aim.resize(e.getX(), e.getY());
           if( ducks.kill(e.getX(), e.getY())==3){
               JOptionPane.showConfirmDialog(null,
                       "Congrats", "YOU WIN", JOptionPane.CLOSED_OPTION);
               end=true;
           }
        }
    }

    class PanelKeyListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            end = true;
        }
    }
}